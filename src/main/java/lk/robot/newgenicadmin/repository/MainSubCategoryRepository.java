package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.MainCategoryEntity;
import lk.robot.newgenicadmin.entity.MainSubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainSubCategoryRepository extends JpaRepository<MainSubCategoryEntity,Long> {

    MainSubCategoryEntity findByMainSubCategoryNameAndMainCategoryEntity(String name, MainCategoryEntity mainCategory);

    List<MainSubCategoryEntity> findByMainCategoryEntity(MainCategoryEntity mainCategory);
}
