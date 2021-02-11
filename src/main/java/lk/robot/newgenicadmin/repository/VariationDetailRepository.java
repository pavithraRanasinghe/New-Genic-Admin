package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.VariationEntity;
import lk.robot.newgenicadmin.entity.VariationProductDetailEntity;
import lk.robot.newgenicadmin.entity.VariationDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariationDetailRepository extends JpaRepository<VariationDetailEntity,Long> {

    VariationDetailEntity findByValueAndVariationEntity(String value, VariationEntity variationEntity);
}
