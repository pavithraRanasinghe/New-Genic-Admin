package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);
    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PatchMapping("/approveFeedback/{feedbackId}")
    public ResponseEntity<?> approveFeedback(@PathVariable long feedbackId, Principal principal){
        LOGGER.info("request - admin | approveFeedback | feedback ID: {} | adminId: {}",feedbackId,principal.getName());
        ResponseEntity<?> approveFeedbacks = feedbackService.approveFeedbacks(feedbackId);
        LOGGER.info("response - admin | approveFeedback | response: {}",approveFeedbacks.getBody());
        return approveFeedbacks;
    }

    @GetMapping("/nonApprovedFeedbacks")
    public ResponseEntity<?> nonApprovedFeedbacks(Principal principal){
        LOGGER.info("request - admin | nonApprovedFeedbacks | adminId: {}",principal.getName());
        ResponseEntity<?> nonApprovedFeedbacks = feedbackService.getNonApprovedFeedbacks();
        LOGGER.info("response - admin | nonApprovedFeedbacks | nonApprovedFeedbacks: {}",nonApprovedFeedbacks.getStatusCode());
        return nonApprovedFeedbacks;
    }
}
