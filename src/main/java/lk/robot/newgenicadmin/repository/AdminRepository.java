package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    AdminEntity findByUsername(String userName);
}
