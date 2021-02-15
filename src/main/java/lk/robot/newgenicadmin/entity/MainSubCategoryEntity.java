package lk.robot.newgenicadmin.entity;

import javax.persistence.*;

@Entity
@Table(name = "main_sub_category")
public class MainSubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_sub_category_id")
    private long mainSubCategoryId;
    @Column(nullable = false)
    private String mainSubCategoryName;
    @Column
    private String mainSubCategoryDescription;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_main_category_id")
    private MainCategoryEntity mainCategoryEntity;

    public MainSubCategoryEntity() {
    }

    public MainSubCategoryEntity(
            long mainSubCategoryId,
            String mainSubCategoryName,
            String mainSubCategoryDescription,
            MainCategoryEntity mainCategoryEntity) {
        this.mainSubCategoryId = mainSubCategoryId;
        this.mainSubCategoryName = mainSubCategoryName;
        this.mainSubCategoryDescription = mainSubCategoryDescription;
        this.mainCategoryEntity = mainCategoryEntity;
    }

    public long getMainSubCategoryId() {
        return mainSubCategoryId;
    }

    public void setMainSubCategoryId(long mainSubCategoryId) {
        this.mainSubCategoryId = mainSubCategoryId;
    }

    public String getMainSubCategoryName() {
        return mainSubCategoryName;
    }

    public void setMainSubCategoryName(String mainSubCategoryName) {
        this.mainSubCategoryName = mainSubCategoryName;
    }

    public String getMainSubCategoryDescription() {
        return mainSubCategoryDescription;
    }

    public void setMainSubCategoryDescription(String mainSubCategoryDescription) {
        this.mainSubCategoryDescription = mainSubCategoryDescription;
    }

    public MainCategoryEntity getMainCategoryEntity() {
        return mainCategoryEntity;
    }

    public void setMainCategoryEntity(MainCategoryEntity mainCategoryEntity) {
        this.mainCategoryEntity = mainCategoryEntity;
    }


    @Override
    public String toString() {
        return "MainSubCategory{" +
                "mainSubCategoryId=" + mainSubCategoryId +
                ", mainSubCategoryName='" + mainSubCategoryName + '\'' +
                ", mainSubCategoryDescription='" + mainSubCategoryDescription + '\'' +
                ", mainCategoryEntity=" + mainCategoryEntity +
                '}';
    }
}
