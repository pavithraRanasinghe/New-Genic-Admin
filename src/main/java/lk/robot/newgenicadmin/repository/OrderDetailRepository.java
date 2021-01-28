package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.OrderDetailEntity;
import lk.robot.newgenicadmin.entity.OrderEntity;
import lk.robot.newgenicadmin.entity.ReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {

    List<OrderDetailEntity> findByOrderEntity(OrderEntity orderEntity);

}
