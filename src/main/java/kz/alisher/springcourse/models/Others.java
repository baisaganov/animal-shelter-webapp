package kz.alisher.springcourse.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "others")
public class Others implements Pets{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private int gender;

    @Column(name = "description")
    private String description;

    @Column(name = "pet_type")
    private String petType;

    @Column(name = "photo_name")
    private String photo_name;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private OthersRequest othersRequest;

    public Others() {
    }

    public Others(String name, int age, int gender, String description, String petType, String photo_name) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.petType = petType;
        this.photo_name = photo_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public OthersRequest getOthersRequest() {
        return othersRequest;
    }

    public void setOthersRequest(OthersRequest othersRequest) {
        this.othersRequest = othersRequest;
    }
}
