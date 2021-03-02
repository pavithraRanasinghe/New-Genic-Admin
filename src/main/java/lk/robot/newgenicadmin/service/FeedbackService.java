package lk.robot.newgenicadmin.service;

import org.springframework.http.ResponseEntity;

public interface FeedbackService {

    ResponseEntity<?> getNonApprovedFeedbacks();

    ResponseEntity<?> approveFeedbacks(long feedbackId);


}
