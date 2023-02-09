package kz.alisher.springcourse.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "cats")
public class Cat implements Pets {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private int gender;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_name")
    private String photo_name;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private CatRequest catRequest;

    public Cat() {}

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

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public CatRequest getCatRequest() {
        return catRequest;
    }

    public void setCatRequest(CatRequest catRequest) {
        this.catRequest = catRequest;
        catRequest.setCat(this);
    }

    public Cat(String name, int age, int gender, String description, String photo_name) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.photo_name = photo_name;
    }



    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", description='" + description + '\'' +
                ", photo_name='" + photo_name + '\'' +
                '}';
    }
}
