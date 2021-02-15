package lk.robot.newgenicadmin.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lk.robot.newgenicadmin.dto.VariationDetailDTO;
import lk.robot.newgenicadmin.dto.request.ProductPriceRequestDTO;
import lk.robot.newgenicadmin.dto.request.VariationRequestDTO;
import lk.robot.newgenicadmin.dto.request.BasicProductRequestDTO;
import lk.robot.newgenicadmin.dto.request.ProductImageRequestDTO;
import lk.robot.newgenicadmin.dto.response.CombinationResponseDTO;
import lk.robot.newgenicadmin.entity.*;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.*;
import lk.robot.newgenicadmin.service.ProductService;
import lk.robot.newgenicadmin.util.DateConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private SubCategoryRepository subCategoryRepository;
    private VariationRepository variationRepository;
    private VariationDetailRepository variationDetailRepository;
    private ProductImageRepository productImageRepository;
    private CombinationRepository combinationRepository;
    private VariationCombinationDetailRepository variationCombinationDetailRepository;
    private ModelMapper modelMapper;
    @Value("${application.bucket.name}")
    private String bucketName;
    @Value("${aws.folder.product}")
    private String folder;

    @Autowired
    private AmazonS3 s3Client;
    private List<List<VariationDetailDTO>> combinationList = new ArrayList<>();

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              SubCategoryRepository subCategoryRepository,
                              VariationRepository variationRepository,
                              VariationDetailRepository variationDetailRepository,
                              ProductImageRepository productImageRepository,
                              CombinationRepository combinationRepository,
                              VariationCombinationDetailRepository variationCombinationDetailRepository,
                              ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.variationRepository = variationRepository;
        this.variationDetailRepository = variationDetailRepository;
        this.productImageRepository = productImageRepository;
        this.combinationRepository = combinationRepository;
        this.variationCombinationDetailRepository = variationCombinationDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<?> addProductBasic(BasicProductRequestDTO requestDTO) {
        try {
            if (requestDTO != null) {
                Optional<SubCategoryEntity> subCategory = subCategoryRepository.findById(requestDTO.getSubCategoryId());
                if (subCategory.isPresent()) {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setProductCode(requestDTO.getProductCode());
                    productEntity.setUuid(UUID.randomUUID().toString());
                    productEntity.setName(requestDTO.getName());
                    productEntity.setDescription(requestDTO.getDescription());
                    productEntity.setBrand(requestDTO.getBrand());
                    productEntity.setActive(requestDTO.isActive());
                    productEntity.setFreeShipping(requestDTO.isFreeShipping());
                    productEntity.setAddedDate(DateConverter.localDateToSql(LocalDate.now()));
                    productEntity.setAddedTime(DateConverter.localTimeToSql(LocalTime.now()));
                    productEntity.setSubCategoryEntity(subCategory.get());

                    ProductEntity save = productRepository.save(productEntity);
                    if (save != null) {
                        return new ResponseEntity<>(save.getProductId(), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Product not saved", HttpStatus.BAD_GATEWAY);
                    }
                } else {
                    return new ResponseEntity<>("Sub category not found", HttpStatus.BAD_GATEWAY);
                }
            } else {
                return new ResponseEntity<>("Product details not found", HttpStatus.BAD_GATEWAY);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> addVariations(List<VariationRequestDTO> variationRequestDTOList, String productId) {
        try {
            if (!variationRequestDTOList.isEmpty()) {
                Optional<ProductEntity> productEntity = productRepository.findByUuid(productId);
                if (productEntity.isPresent()) {
                    List<List<VariationDetailDTO>> places = new ArrayList<>();
                    for (VariationRequestDTO variationRequestDTO :
                            variationRequestDTOList) {
                        VariationEntity existingVariation = variationRepository.findByProductEntityAndAndVariationName(productEntity.get(), variationRequestDTO.getVariationName());
                        List<VariationDetailDTO> variationCombinationType = new ArrayList<>();
                        if (existingVariation == null) {
                            VariationEntity newVariation = new VariationEntity();
                            newVariation.setVariationName(variationRequestDTO.getVariationName());
                            newVariation.setVariationDescription(variationRequestDTO.getVariationDescription());
                            newVariation.setProductEntity(productEntity.get());

                            VariationEntity variationEntity = variationRepository.save(newVariation);

                            for (String value :
                                    variationRequestDTO.getValueList()) {
                                VariationDetailEntity newVariationDetail = new VariationDetailEntity();
                                newVariationDetail.setValue(value);
                                newVariationDetail.setVariationEntity(variationEntity);

                                VariationDetailEntity detailEntity = variationDetailRepository.save(newVariationDetail);
                                variationCombinationType.add(modelMapper.map(detailEntity, VariationDetailDTO.class));
                            }

                            places.add(variationCombinationType);
                        } else {
                            for (String value :
                                    variationRequestDTO.getValueList()) {
                                VariationDetailEntity existingVariationDetail = variationDetailRepository.findByValueAndVariationEntity(value, existingVariation);
                                if (existingVariationDetail == null){
                                    VariationDetailEntity newVariationDetail = new VariationDetailEntity();
                                    newVariationDetail.setValue(value);
                                    newVariationDetail.setVariationEntity(existingVariation);

                                    VariationDetailEntity detailEntity = variationDetailRepository.save(newVariationDetail);
                                    variationCombinationType.add(modelMapper.map(detailEntity, VariationDetailDTO.class));
                                }else{
                                    variationCombinationType.add(modelMapper.map(existingVariationDetail,VariationDetailDTO.class));
                                }
                            }
                            places.add(variationCombinationType);
                        }
                    }
                    LinkedList<VariationDetailDTO> tokens = new LinkedList<>();
                    List<List<VariationDetailDTO>> combinations = combine(places, 0, tokens);

                    List<CombinationResponseDTO> combinationList = new ArrayList<>();
                    for (List<VariationDetailDTO> variationDetailList :
                            combinations) {
                        List<VariationDetailDTO> variationList = new ArrayList<>();
                        CombinationEntity combinationEntity = new CombinationEntity();
                        CombinationEntity newCombination = combinationRepository.save(combinationEntity);
                        CombinationResponseDTO combinationResponseDTO = new CombinationResponseDTO();
                        combinationResponseDTO.setCombinationId(newCombination.getCombinationId());

                        for (VariationDetailDTO variationDetailDTO :
                                variationDetailList) {
                            VariationCombinationDetailEntity variationCombinationDetailEntity = new VariationCombinationDetailEntity();
                            Optional<VariationDetailEntity> variationDetailEntity = variationDetailRepository.findById(variationDetailDTO.getVariationDetailId());
                            variationCombinationDetailEntity.setCombinationEntity(newCombination);
                            variationCombinationDetailEntity.setVariationDetailEntity(variationDetailEntity.get());
                            variationList.add(modelMapper.map(variationDetailEntity.get(), VariationDetailDTO.class));
                            variationCombinationDetailRepository.save(variationCombinationDetailEntity);
                        }
                        combinationResponseDTO.setVariationList(variationList);
                        combinationList.add(combinationResponseDTO);
                    }
                    if (!combinationList.isEmpty()) {
                        return new ResponseEntity<>(combinationList, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Combinations not created", HttpStatus.EXPECTATION_FAILED);
                    }
                } else {
                    return new ResponseEntity<>("Product not found", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("variations not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> addProductImage(long variationDetailId, List<MultipartFile> multipartFiles) {
        try {
            if (variationDetailId != 0) {
                    Optional<VariationDetailEntity> variationDetailEntity = variationDetailRepository.findById(variationDetailId);
                    if (variationDetailEntity != null) {
                        for (MultipartFile multipartFile :
                                multipartFiles) {
                            uploadFile(multipartFile);
                            ProductImageEntity productImageEntity = new ProductImageEntity();
                            productImageEntity.setUrl(getFileUrl(multipartFile.getOriginalFilename()));
                            productImageEntity.setVariationDetailEntity(variationDetailEntity.get());
                            productImageRepository.save(productImageEntity);
                        }
                        return new ResponseEntity<>("Images uploaded successful", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Value not found", HttpStatus.BAD_REQUEST);
                    }

            } else {
                return new ResponseEntity<>("Image data not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> addProductDetail(List<ProductPriceRequestDTO> productPriceRequestDTOList) {
        try {
            if (!productPriceRequestDTOList.isEmpty()) {
                List<Long> nonCombinationIdList = new ArrayList<>();
                for (ProductPriceRequestDTO priceRequest :
                        productPriceRequestDTOList) {
                    Optional<CombinationEntity> combinationEntity = combinationRepository.findById(priceRequest.getCombinationId());
                    if (combinationEntity.isPresent()) {
                        combinationEntity.get().setStock(priceRequest.getStock());
                        combinationEntity.get().setWeight(priceRequest.getWeight());
                        combinationEntity.get().setBuyingPrice(priceRequest.getBuyingPrice());
                        combinationEntity.get().setSalePrice(priceRequest.getSalePrice());
                        combinationEntity.get().setRetailPrice(priceRequest.getRetailPrice());
                        combinationEntity.get().setActive(priceRequest.isActive());

                        combinationRepository.save(combinationEntity.get());
                    } else {
                        nonCombinationIdList.add(priceRequest.getCombinationId());
                    }
                }
                if (nonCombinationIdList.isEmpty()) {
                    return new ResponseEntity<>("All combination updated", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(nonCombinationIdList, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Product detail not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> removeBasicProductDetail(String uuid) {
        try {
            if (uuid != null) {
                Optional<ProductEntity> productEntity = productRepository.findByUuid(uuid);
                if (productEntity.isPresent()) {
                    productRepository.delete(productEntity.get());
                    return new ResponseEntity<>("Product deleted", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Product not found", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Product id not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<List<VariationDetailDTO>> combine(List<List<VariationDetailDTO>> places, int index, Deque<VariationDetailDTO> tokens) {

        if (index == places.size()) {
            List<VariationDetailDTO> ret = new ArrayList<>(tokens);
            combinationList.add(ret);
        } else {
            List<VariationDetailDTO> p = places.get(index);
            for (VariationDetailDTO token : p) {
                tokens.addLast(token);
                combine(places, index + 1, tokens);
                tokens.removeLast();
            }
        }
        return combinationList;
    }

    private void uploadFile(MultipartFile multipartFile) {
        try {
            File file = new File(multipartFile.getOriginalFilename());

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folder + multipartFile.getOriginalFilename(), file);
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(putObjectRequest);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileUrl(String fileName) {
        return s3Client.getUrl(bucketName, folder + fileName).toString();
    }
}
