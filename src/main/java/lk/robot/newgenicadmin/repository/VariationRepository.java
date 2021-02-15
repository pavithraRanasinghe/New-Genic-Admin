package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.ProductEntity;
import lk.robot.newgenicadmin.entity.VariationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariationRepository extends JpaRepository<VariationEntity, Long> {

    List<VariationEntity> findByProductEntity(ProductEntity productEntity);

    VariationEntity findByProductEntityAndAndVariationName(ProductEntity productEntity,String name);
}
