package lk.robot.newgenicadmin.dto.request;

public class AnswerRequestDTO {

    private long questionId;
    private String answer;

    public AnswerRequestDTO() {
    }

    public AnswerRequestDTO(long questionId,
                            String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerRequestDTO{" +
                "questionId=" + questionId +
                ", answer='" + answer + '\'' +
                '}';
    }
}
