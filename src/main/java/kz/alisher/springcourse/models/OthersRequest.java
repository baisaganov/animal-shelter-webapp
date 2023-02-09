package kz.alisher.springcourse.models;

import jakarta.persistence.*;

@Entity
@Table(name = "others_requests")
public class OthersRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "tel")
    private String tel;

    @OneToOne()
    @JoinColumn(name = "others_id", referencedColumnName = "id")
    private Others others;

    public OthersRequest() {
    }

    public OthersRequest(int id, String name, String tel, Others others) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.others = others;
    }

    public OthersRequest(String name, String tel, Others others) {
        this.name = name;
        this.tel = tel;
        this.others = others;
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

    public Others getOthers() {
        return others;
    }

    public void setOthers(Others others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "OthersRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", others=" + others +
                '}';
    }
}
