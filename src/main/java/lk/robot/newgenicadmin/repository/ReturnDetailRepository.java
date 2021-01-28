package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.ReturnDetailEntity;
import lk.robot.newgenicadmin.entity.ReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnDetailRepository extends JpaRepository<ReturnDetailEntity,Long> {

    List<ReturnDetailEntity> findByReturnEntity(ReturnEntity returnEntity);
}
