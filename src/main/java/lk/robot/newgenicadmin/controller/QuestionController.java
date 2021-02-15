package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.dto.request.AnswerRequestDTO;
import lk.robot.newgenicadmin.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);
    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/getQuestions")
    private ResponseEntity<?> getQuestions(Principal principal){
        LOGGER.info("request - admin | getQuestions | adminId: {}",principal.getName());
        ResponseEntity<?> question = questionService.getQuestion();
        LOGGER.info("response - admin | getQuestions | questions: {}",question.getBody());
        return question;
    }

    @GetMapping("/nonAnsweredQuestion")
    private ResponseEntity<?> nonAnsweredQuestion(Principal principal){
        LOGGER.info("request - admin | nonAnsweredQuestion | adminId: {}",principal.getName());
        ResponseEntity<?> nonAnsweredQuestion = questionService.nonAnsweredQuestion();
        LOGGER.info("response - admin | nonAnsweredQuestion | nonAnsweredQuestion: {}",nonAnsweredQuestion.getBody());
        return nonAnsweredQuestion;
    }

    @PostMapping("/answerQuestion")
    private ResponseEntity<?> answerQuestion(@RequestBody AnswerRequestDTO answerRequestDTO,Principal principal){
        LOGGER.info("request - admin | answerQuestion | answerRequest: {} | adminId: {}",answerRequestDTO,principal.getName());
        ResponseEntity<?> answerQuestion = questionService.answerQuestion(answerRequestDTO);
        LOGGER.info("response - admin | answerQuestion | answerQuestion: {}",answerQuestion.getBody());
        return answerQuestion;
    }
}
