package kz.alisher.springcourse.dao;

import kz.alisher.springcourse.models.Dog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
public class DogDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public DogDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Dog> index() {
        Session session = sessionFactory.getCurrentSession();
        List<Dog> dogs = session.createQuery("select d from Dog d where not exists (select r from DogRequest r where d.id = r.dog.id)", Dog.class)
                .getResultList();
        return dogs;
    }

//    select dogs.* from dogs where not exists(
//    select * from dog_requests where dogs.id = dog_requests.dog_id
//    );

    @Transactional
    public Dog getDog(int id) {
        Session session =sessionFactory.getCurrentSession();
        Dog dog = session.createQuery("from Dog where id = :paramName", Dog.class)
                .setParameter("paramName", id).getSingleResult();
        return dog;
    }

    @Transactional
    public void save(Dog dog){
        Session session = sessionFactory.getCurrentSession();
        session.save(dog);
    }

    @Transactional
    public void update(int id, Dog dog) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println(dog.getId());
        Dog dogToBeUpdated = session.get(Dog.class, id);

        dogToBeUpdated.setName(dog.getName());
        dogToBeUpdated.setAge(dog.getAge());
        dogToBeUpdated.setDescription(dog.getDescription());
        dogToBeUpdated.setGender(dog.getGender());

    }

    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Dog.class, id));
    }

    @Transactional
    public void addNew(Dog dog){
        Session session = sessionFactory.getCurrentSession();
        session.save(dog);
    }
}
