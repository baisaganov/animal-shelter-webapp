package kz.alisher.springcourse.dao;

import kz.alisher.springcourse.models.Cat;
import kz.alisher.springcourse.models.Others;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OthersDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public OthersDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Others> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Others d where not exists (from OthersRequest r where d.id = r.others.id)", Others.class)
                .getResultList();
    }

//    select dogs.* from dogs where not exists(
//    select * from dog_requests where dogs.id = dog_requests.dog_id
//    );

    @Transactional
    public Others getOthers(int id) {
        Session session =sessionFactory.getCurrentSession();
        return session.createQuery("from Others where id = :paramName", Others.class)
                .setParameter("paramName", id).getSingleResult();
    }

    @Transactional
    public void save(Others Others){
        Session session = sessionFactory.getCurrentSession();
        session.save(Others);
    }

    @Transactional
    public void update(int id, Others others) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println(others.getId());
        Others catToBeUpdated = session.get(Others.class, id);

        catToBeUpdated.setName(others.getName());
        catToBeUpdated.setAge(others.getAge());
        catToBeUpdated.setDescription(others.getDescription());
        catToBeUpdated.setGender(others.getGender());

    }

    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        Others others = session.get(Others.class, id);
        session.remove(others);
    }

    @Transactional
    public void addNew(Others others){
        Session session = sessionFactory.getCurrentSession();
        session.save(others);
    }


}
