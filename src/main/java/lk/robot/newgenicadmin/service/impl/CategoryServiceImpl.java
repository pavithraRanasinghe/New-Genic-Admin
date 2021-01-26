package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.SubCategoryDTO;
import lk.robot.newgenicadmin.dto.request.CategoryRequestDTO;
import lk.robot.newgenicadmin.dto.response.CategoryResponseDTO;
import lk.robot.newgenicadmin.dto.response.MainSubCategoryResponseDTO;
import lk.robot.newgenicadmin.dto.response.SubCategoryResponseDTO;
import lk.robot.newgenicadmin.entity.AdminEntity;
import lk.robot.newgenicadmin.entity.MainCategoryEntity;
import lk.robot.newgenicadmin.entity.MainSubCategoryEntity;
import lk.robot.newgenicadmin.entity.SubCategoryEntity;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.AdminRepository;
import lk.robot.newgenicadmin.repository.MainCategoryRepository;
import lk.robot.newgenicadmin.repository.MainSubCategoryRepository;
import lk.robot.newgenicadmin.repository.SubCategoryRepository;
import lk.robot.newgenicadmin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private MainCategoryRepository mainCategoryRepository;
    private AdminRepository adminRepository;
    private MainSubCategoryRepository mainSubCategoryRepository;
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    public CategoryServiceImpl(MainCategoryRepository mainCategoryRepository,
                               AdminRepository adminRepository,
                               MainSubCategoryRepository mainSubCategoryRepository,
                               SubCategoryRepository subCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
        this.adminRepository = adminRepository;
        this.mainSubCategoryRepository = mainSubCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public ResponseEntity<?> addCategory(CategoryRequestDTO categoryRequestDTO, long adminId) {
        try {
            if (categoryRequestDTO != null && !categoryRequestDTO.getSubCategoryList().isEmpty()) {
                Optional<AdminEntity> admin = adminRepository.findById(adminId);
                Optional<MainCategoryEntity> mainCategory = mainCategoryRepository.findById(categoryRequestDTO.getMainCategoryId());
                if (mainCategory.isPresent()) {
                    MainSubCategoryEntity existingMainSub = mainSubCategoryRepository.findByMainSubCategoryNameAndMainCategoryEntity(
                            categoryRequestDTO.getMainSubCategoryName(), mainCategory.get());
                    if (!existingMainSub.equals(null)) {
                        MainSubCategoryEntity mainSubCategoryEntity = new MainSubCategoryEntity();
                        mainSubCategoryEntity.setMainSubCategoryName(categoryRequestDTO.getMainSubCategoryName());
                        mainSubCategoryEntity.setMainSubCategoryDescription(categoryRequestDTO.getMainSubCategoryDescription());
                        mainSubCategoryEntity.setMainCategoryEntity(mainCategory.get());

                        MainSubCategoryEntity mainSubCategory = mainSubCategoryRepository.save(mainSubCategoryEntity);
                        if (mainSubCategory != null) {
                            List<SubCategoryDTO> existingSubCategories = saveSubCategories(categoryRequestDTO, mainSubCategory);
                            if (existingSubCategories.isEmpty()) {
                                return new ResponseEntity<>("All categories added", HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>(existingSubCategories, HttpStatus.OK);
                            }

                        } else {
                            return new ResponseEntity<>("Something wrong on main sub category", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        List<SubCategoryDTO> existingSubCategories = saveSubCategories(categoryRequestDTO, existingMainSub);
                        if (existingSubCategories.isEmpty()) {
                            return new ResponseEntity<>("All categories added", HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(existingSubCategories, HttpStatus.OK);
                        }
                    }
                } else {
                    return new ResponseEntity<>("Main category not found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Category details not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getCategory() {

        List<CategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();
        try {
            List<MainCategoryEntity> all = mainCategoryRepository.findAll();
            if (all.size() != 0) {
                for (MainCategoryEntity mainCategoryEntity : all) {

                    CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
                    categoryResponseDTO.setMain_category_id(mainCategoryEntity.getMainCategoryId());
                    categoryResponseDTO.setMain_category_name(mainCategoryEntity.getMainCategoryName());
                    categoryResponseDTO.setMain_category_description(mainCategoryEntity.getMainCategoryDescription());

                    List<MainSubCategoryEntity> mainSubCategoryList = mainSubCategoryRepository.findByMainCategoryEntity(mainCategoryEntity);
                    List<MainSubCategoryResponseDTO> mainSubCategoryResponseDTOList = new ArrayList<>();
                    if (mainSubCategoryList.size() != 0) {
                        for (MainSubCategoryEntity mainSubCategoryEntity : mainSubCategoryList) {

                            MainSubCategoryResponseDTO mainSubCategoryResponseDTO = new MainSubCategoryResponseDTO();
                            mainSubCategoryResponseDTO.setMain_sub_category_id(mainSubCategoryEntity.getMainSubCategoryId());
                            mainSubCategoryResponseDTO.setMain_sub_category_name(mainSubCategoryEntity.getMainSubCategoryName());
                            mainSubCategoryResponseDTO.setMain_sub_category_description(mainSubCategoryEntity.getMainSubCategoryDescription());

                            List<SubCategoryEntity> subCategoryList = subCategoryRepository.findByMainSubCategoryEntity
                                    (mainSubCategoryEntity);
                            if (subCategoryList.size() != 0) {
                                List<SubCategoryResponseDTO> subCategoryDTOList = new ArrayList<>();
                                for (SubCategoryEntity subCategoryEntity : subCategoryList) {
                                    SubCategoryResponseDTO subCategoryDTO = new SubCategoryResponseDTO(
                                            subCategoryEntity.getSubCategoryId(),
                                            subCategoryEntity.getSubCategoryName(),
                                            subCategoryEntity.getSubCategoryDescription()
                                    );
                                    subCategoryDTOList.add(subCategoryDTO);
                                    mainSubCategoryResponseDTO.setSubCategoryResponseDTO(subCategoryDTOList);
                                }
                            } else {
                                return new ResponseEntity<>("Not found Sub category list", HttpStatus.NOT_FOUND);
                            }

                            mainSubCategoryResponseDTOList.add(mainSubCategoryResponseDTO);
                        }
                    } else {
                        return new ResponseEntity<>("Not found Main sub category list", HttpStatus.NOT_FOUND);
                    }
                    categoryResponseDTO.setMainSubCategoryResponseDTO(mainSubCategoryResponseDTOList);
                    categoryResponseDTOList.add(categoryResponseDTO);
                }
                return new ResponseEntity<>(categoryResponseDTOList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found Main category list", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Category search failed");
        }
    }

    private SubCategoryEntity subCategoryDtoToEntity(SubCategoryDTO subCategoryDTO, MainSubCategoryEntity mainSubCategoryEntity) {
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        subCategoryEntity.setSubCategoryName(subCategoryDTO.getSubCategoryName());
        subCategoryEntity.setSubCategoryDescription(subCategoryDTO.getSubCategoryDescription());
        subCategoryEntity.setMainSubCategory(mainSubCategoryEntity);

        return subCategoryEntity;
    }

    private List<SubCategoryDTO> saveSubCategories(CategoryRequestDTO categoryRequestDTO, MainSubCategoryEntity mainSubCategoryEntity) {
        List<SubCategoryDTO> existingSubCategories = null;
        for (SubCategoryDTO subCategoryDTO :
                categoryRequestDTO.getSubCategoryList()) {
            SubCategoryEntity existingSubCategory = subCategoryRepository.
                    findBySubCategoryNameAndMainSubCategoryEntity(subCategoryDTO.getSubCategoryName(), mainSubCategoryEntity);
            if (existingSubCategory.equals(null)) {
                SubCategoryEntity subCategoryEntity = subCategoryDtoToEntity(subCategoryDTO, mainSubCategoryEntity);
                subCategoryRepository.save(subCategoryEntity);
            } else {
                existingSubCategories.add(subCategoryDTO);
            }
        }
        return existingSubCategories;
    }
}
