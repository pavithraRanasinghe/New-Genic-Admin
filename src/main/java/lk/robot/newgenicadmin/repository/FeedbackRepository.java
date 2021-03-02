package lk.robot.newgenicadmin.repository;

import lk.robot.newgenicadmin.entity.ProductFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<ProductFeedbackEntity,Long> {

    List<ProductFeedbackEntity> findByApprovedFalse();
}
