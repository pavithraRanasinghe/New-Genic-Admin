package lk.robot.newgenicadmin.service;

import lk.robot.newgenicadmin.dto.request.AnswerRequestDTO;
import org.springframework.http.ResponseEntity;

public interface QuestionService {

    ResponseEntity<?> getQuestion();

    ResponseEntity<?> nonAnsweredQuestion();

    ResponseEntity<?> answerQuestion(AnswerRequestDTO answerRequestDTO);
}
