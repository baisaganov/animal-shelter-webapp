package kz.alisher.springcourse.dao;

import kz.alisher.springcourse.models.Dog;
import kz.alisher.springcourse.models.DogRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RequestsDAO {

    private final SessionFactory sessionFactory;

    public RequestsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<DogRequest> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d FROM DogRequest d", DogRequest.class).getResultList();
    }

    @Transactional
    public void bookNewDog(DogRequest dogRequest, int petId){
        Session session = sessionFactory.getCurrentSession();
        Dog dog = session.get(Dog.class, petId);
        dogRequest.setDog(dog);
        System.out.println(dogRequest.getId());

        session.save(dog);
        session.save(dogRequest);
    }

    @Transactional
    public void requestDeleteDog(int id){
        Session session = sessionFactory.getCurrentSession();

        session.remove(session.get(DogRequest.class, id));
    }

    @Transactional
    public DogRequest index(int id) {
        Session session = sessionFactory.getCurrentSession();
        DogRequest dogRequest = session.createQuery("select d FROM DogRequest d where id = :paramID", DogRequest.class)
                .setParameter("paramID", id)
                .getSingleResult();
        return dogRequest;
    }
}
