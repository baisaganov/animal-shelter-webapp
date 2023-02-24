package kz.alisher.springcourse.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "cat_requests")
public class CatRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "tel")
    private String tel;

    @OneToOne()
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    private Cat cat;


    public CatRequest(){}

    public CatRequest(int id, String name, String tel, Cat cat) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.cat = cat;
    }

    public CatRequest(String name, String tel, Cat cat){
        this.name = name;
        this.tel = tel;
        this.cat = cat;
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

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "eCatRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", cat=" + cat.getName() + cat.getId() +
                '}';
    }
}
