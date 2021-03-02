package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.response.ProductFeedbackResponseDTO;
import lk.robot.newgenicadmin.entity.ProductFeedbackEntity;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.FeedbackRepository;
import lk.robot.newgenicadmin.repository.ProductRepository;
import lk.robot.newgenicadmin.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                               ProductRepository productRepository,
                               ModelMapper modelMapper) {
        this.feedbackRepository = feedbackRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<?> getNonApprovedFeedbacks() {
        try {
            List<ProductFeedbackEntity> feedbackList = feedbackRepository.findByApprovedFalse();
            if (!feedbackList.isEmpty()) {
                List<ProductFeedbackResponseDTO> productFeedbackList = new ArrayList<>();
                for (ProductFeedbackEntity productFeedbackEntity :
                        feedbackList) {
                    ProductFeedbackResponseDTO responseDTO = modelMapper.map(productFeedbackEntity, ProductFeedbackResponseDTO.class);
                    responseDTO.setUserFirstName(productFeedbackEntity.getUserEntity().getFirstName());
                    productFeedbackList.add(responseDTO);
                }
                return new ResponseEntity<>(productFeedbackList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No feedbacks found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> approveFeedbacks(long feedbackId) {
        try {
            if (feedbackId != 0) {
                Optional<ProductFeedbackEntity> feedbackEntity = feedbackRepository.findById(feedbackId);
                if (feedbackEntity.isPresent()) {
                    feedbackEntity.get().setApproved(true);

                    ProductFeedbackEntity save = feedbackRepository.save(feedbackEntity.get());
                    return new ResponseEntity<>("Feedback " + feedbackId + " approved", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Feedback not found", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Product id not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
