package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.request.ProductRequestDTO;
import lk.robot.newgenicadmin.entity.ProductEntity;
import lk.robot.newgenicadmin.entity.SubCategoryEntity;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.ProductRepository;
import lk.robot.newgenicadmin.repository.SubCategoryRepository;
import lk.robot.newgenicadmin.service.ProductService;
import lk.robot.newgenicadmin.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              SubCategoryRepository subCategoryRepository) {
        this.productRepository = productRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public ResponseEntity<?> addProduct(List<ProductRequestDTO> productRequestList) {
        try{
            if (!productRequestList.isEmpty()){
                Optional<SubCategoryEntity> subCategoryEntity = subCategoryRepository.findById(productRequestList.get(1).getSubCategoryId());
                if (subCategoryEntity.isPresent()){
                    for (ProductRequestDTO productRequestDTO :
                            productRequestList) {
                        ProductEntity productEntity = requestDTOtoEntity(productRequestDTO, subCategoryEntity.get());
                        ProductEntity save = productRepository.save(productEntity);

                    }
                    return new ResponseEntity<>("Product save successful",HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Sub category not found",HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("Product details not found", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    private ProductEntity requestDTOtoEntity(ProductRequestDTO productRequestDTO,SubCategoryEntity subCategoryEntity){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCode(productRequestDTO.getProductCode());
        productEntity.setName(productRequestDTO.getName());
        productEntity.setDescription(productRequestDTO.getDescription());
        productEntity.setBrand(productRequestDTO.getBrand());
        productEntity.setStock(productRequestDTO.getStock());
        productEntity.setColor(productRequestDTO.getColor());
        productEntity.setSize(productRequestDTO.getSize());
        productEntity.setGender(productRequestDTO.getGender());
        productEntity.setWeight(productRequestDTO.getWeight());
        productEntity.setBuyingPrice(productRequestDTO.getBuyingPrice());
        productEntity.setSalePrice(productRequestDTO.getSalePrice());
        productEntity.setRetailPrice(productRequestDTO.getRetailPrice());
        productEntity.setAddedDate(DateConverter.localDateToSql(LocalDate.now()));
        productEntity.setAddedTime(DateConverter.localTimeToSql(LocalTime.now()));
        productEntity.setActive(productRequestDTO.isActive());
        productEntity.setFreeShipping(productRequestDTO.isFreeShipping());
        productEntity.setSubCategoryEntity(subCategoryEntity);

        return productEntity;
    }
}
