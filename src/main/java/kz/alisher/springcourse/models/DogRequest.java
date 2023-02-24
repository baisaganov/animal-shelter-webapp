package kz.alisher.springcourse.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "dog_requests")
public class DogRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "tel")
    private String tel;

    @OneToOne()
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    private Dog dog;


    public DogRequest(){}

    public DogRequest(int id, String name, String tel, Dog dog) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.dog = dog;
    }

    public DogRequest(String name, String tel, Dog dog) {
        this.name = name;
        this.tel = tel;
        this.dog = dog;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

}
