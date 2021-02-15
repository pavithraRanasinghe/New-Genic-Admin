package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.request.AnswerRequestDTO;
import lk.robot.newgenicadmin.dto.response.QuestionResponseDTO;
import lk.robot.newgenicadmin.entity.AnswerEntity;
import lk.robot.newgenicadmin.entity.QuestionEntity;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.AnswerRepository;
import lk.robot.newgenicadmin.repository.QuestionAreaRepository;
import lk.robot.newgenicadmin.repository.QuestionRepository;
import lk.robot.newgenicadmin.service.QuestionService;
import lk.robot.newgenicadmin.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionAreaRepository questionAreaRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    @Autowired
    public QuestionServiceImpl(QuestionAreaRepository questionAreaRepository,
                               QuestionRepository questionRepository,
                               AnswerRepository answerRepository) {
        this.questionAreaRepository = questionAreaRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public ResponseEntity<?> getQuestion() {
        try {
            List<QuestionEntity> allQuestions = questionRepository.findAll();
            if (!allQuestions.isEmpty()) {
                List<QuestionResponseDTO> questionList = createQuestionList(allQuestions);
                return new ResponseEntity<>(questionList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No questions found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> nonAnsweredQuestion() {
        try {
            List<QuestionEntity> questions = questionRepository.findByApproveFalse();
            if (!questions.isEmpty()){
                List<QuestionResponseDTO> questionList = createQuestionList(questions);
                return new ResponseEntity<>(questionList,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No questions",HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> answerQuestion(AnswerRequestDTO answerRequestDTO) {
        try {
            if (answerRequestDTO != null){
                Optional<QuestionEntity> question = questionRepository.findById(answerRequestDTO.getQuestionId());
                if (question.isPresent()){
                    AnswerEntity answerEntity = new AnswerEntity();
                    answerEntity.setAnswer(answerRequestDTO.getAnswer());
                    answerEntity.setAnswerDate(DateConverter.localDateToSql(LocalDate.now()));
                    answerEntity.setAnswerTime(DateConverter.localTimeToSql(LocalTime.now()));
                    answerEntity.setQuestionEntity(question.get());

                    AnswerEntity answerResponse = answerRepository.save(answerEntity);
                    if (answerResponse != null){
                        return new ResponseEntity<>("Answer successful",HttpStatus.OK);
                    }else {
                        return new ResponseEntity<>("Answer not success",HttpStatus.BAD_REQUEST);
                    }
                }else {
                    return new ResponseEntity<>("Question not found",HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>("Answer detail not found",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    private List<QuestionResponseDTO> createQuestionList(List<QuestionEntity> questionList) {
        List<QuestionResponseDTO> questionResponseList = new ArrayList<>();
        for (QuestionEntity questionEntity :
                questionList) {
            questionResponseList.add(new QuestionResponseDTO(
                    questionEntity.getQuestionId(),
                    questionEntity.getQuestion(),
                    questionEntity.getQuestionDate(),
                    questionEntity.getQuestionTime(),
                    questionEntity.getQuestionAreaEntity().getUserEntity().getUserUuid(),
                    questionEntity.getQuestionAreaEntity().getUserEntity().getFirstName(),
                    questionEntity.getQuestionAreaEntity().getProductEntity().getUuid()
            ));
        }
        return questionResponseList;
    }
}
