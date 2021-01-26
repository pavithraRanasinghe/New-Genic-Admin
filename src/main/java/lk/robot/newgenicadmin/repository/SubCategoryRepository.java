package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.MainSubCategoryEntity;
import lk.robot.newgenicadmin.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity,Long> {

    List<SubCategoryEntity> findByMainSubCategoryEntity(MainSubCategoryEntity main);

    List<SubCategoryEntity> findAllByMainSubCategoryEntity(MainSubCategoryEntity mainSubCategoryEntity);

    SubCategoryEntity findBySubCategoryNameAndMainSubCategoryEntity(String name,MainSubCategoryEntity mainSubCategory);
}
