package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.CombinationEntity;
import lk.robot.newgenicadmin.entity.VariationCombinationDetailEntity;
import lk.robot.newgenicadmin.entity.VariationDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariationCombinationDetailRepository extends JpaRepository<VariationCombinationDetailEntity,Long> {

    List<VariationCombinationDetailEntity> findByCombinationEntity(CombinationEntity combinationEntity);

    List<VariationCombinationDetailEntity> findByVariationDetailEntity(VariationDetailEntity variationDetailEntity);
}
