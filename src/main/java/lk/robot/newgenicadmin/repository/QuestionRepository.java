package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {

    List<QuestionEntity> findByApproveFalse();
}
