package kz.alisher.springcourse.dao;

import kz.alisher.springcourse.models.Cat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CatDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CatDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Cat> index() {
        Session session = sessionFactory.getCurrentSession();
        List<Cat> cats = session.createQuery("select d from Cat d where not exists (select r from CatRequest r where d.id = r.cat.id)", Cat.class)
                .getResultList();
        return cats;
    }

//    select dogs.* from dogs where not exists(
//    select * from dog_requests where dogs.id = dog_requests.dog_id
//    );

    @Transactional
    public Cat getCat(int id) {
        Session session =sessionFactory.getCurrentSession();
        Cat cat = session.createQuery("from Cat where id = :paramName", Cat.class)
                .setParameter("paramName", id).getSingleResult();
        return cat;
    }

    @Transactional
    public void save(Cat cat){
        Session session = sessionFactory.getCurrentSession();
        session.save(cat);
    }

    @Transactional
    public void update(int id, Cat cat) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println(cat.getId());
        Cat catToBeUpdated = session.get(Cat.class, id);

        catToBeUpdated.setName(cat.getName());
        catToBeUpdated.setAge(cat.getAge());
        catToBeUpdated.setDescription(cat.getDescription());
        catToBeUpdated.setGender(cat.getGender());

    }

    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        Cat cat = session.get(Cat.class, id);
        session.remove(cat);
    }

    @Transactional
    public void addNew(Cat cat){
        Session session = sessionFactory.getCurrentSession();
        session.save(cat);
    }


}
