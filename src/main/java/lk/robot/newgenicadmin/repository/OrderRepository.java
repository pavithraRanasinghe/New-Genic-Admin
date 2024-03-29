package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.OrderEntity;
import lk.robot.newgenicadmin.entity.ReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    List<OrderEntity> findByStatus(String status);

    OrderEntity findByOrderUuid(String uuid);

    OrderEntity findByReturnEntity(ReturnEntity returnEntity);
}
