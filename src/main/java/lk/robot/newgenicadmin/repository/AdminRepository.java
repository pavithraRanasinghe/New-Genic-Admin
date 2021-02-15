package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    AdminEntity findByUsername(String userName);

    Optional<AdminEntity> findByUuid(String uuid);
}
