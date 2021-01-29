package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.DeliveryCostEntity;
import lk.robot.newgenicadmin.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryCostRepository extends JpaRepository<DeliveryCostEntity, Long> {

    DeliveryCostEntity findByDistrictAndDeliveryEntity(String district, DeliveryEntity deliveryEntity);

    List<DeliveryCostEntity> findByDeliveryEntity(DeliveryEntity deliveryEntity);
}
