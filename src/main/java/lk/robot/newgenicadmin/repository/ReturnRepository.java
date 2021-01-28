package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.ReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnRepository extends JpaRepository<ReturnEntity, Long> {

    List<ReturnEntity> findByAction(String action);
}
