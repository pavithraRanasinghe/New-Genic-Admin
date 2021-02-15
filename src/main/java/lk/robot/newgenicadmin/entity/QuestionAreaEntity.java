package lk.robot.newgenicadmin.entity;

import javax.persistence.*;

@Entity
@Table(name = "question_area")
public class QuestionAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_area_id")
    private long questionAreaId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id")
    private UserEntity userEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_id")
    private ProductEntity productEntity;

    public QuestionAreaEntity() {
    }

    public QuestionAreaEntity(long questionAreaId,
                              UserEntity userEntity,
                              ProductEntity productEntity) {
        this.questionAreaId = questionAreaId;
        this.userEntity = userEntity;
        this.productEntity = productEntity;
    }

    public long getQuestionAreaId() {
        return questionAreaId;
    }

    public void setQuestionAreaId(long questionAreaId) {
        this.questionAreaId = questionAreaId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    @Override
    public String toString() {
        return "QuestionAreaEntity{" +
                "questionAreaId=" + questionAreaId +
                ", userEntity=" + userEntity +
                ", productEntity=" + productEntity +
                '}';
    }
}
