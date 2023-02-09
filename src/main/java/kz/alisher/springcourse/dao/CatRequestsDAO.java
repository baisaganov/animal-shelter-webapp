package kz.alisher.springcourse.dao;

import kz.alisher.springcourse.models.Cat;
import kz.alisher.springcourse.models.CatRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CatRequestsDAO {

    private final SessionFactory sessionFactory;

    public CatRequestsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<CatRequest> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d FROM CatRequest d", CatRequest.class).getResultList();
    }

    @Transactional
    public CatRequest index(int id) {
        Session session = sessionFactory.getCurrentSession();
        CatRequest catRequest = session.createQuery("select d FROM CatRequest d where id = :paramID", CatRequest.class)
                .setParameter("paramID", id)
                .getSingleResult();
        return catRequest;
    }

    @Transactional
    public void bookNewCat(CatRequest catRequest, int petId){
        Session session = sessionFactory.getCurrentSession();
        Cat cat = session.get(Cat.class, petId);
        catRequest.setCat(cat);
        System.out.println(catRequest.getId());

        session.save(cat);
        session.save(catRequest);
    }

    @Transactional
    public void catRequestDelete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(CatRequest.class, id));
    }

    @Transactional
    public CatRequest getCatByRequestId(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(CatRequest.class, id);
    }

    @Transactional
    public int getCatId(int reqId){
        Session session =sessionFactory.getCurrentSession();
        System.out.println("Cat getting started with reqID = " + reqId);

        Cat cat = session.createQuery("from CatRequest r where r.id = :paramId", Cat.class)
                .setParameter("paramId", reqId)
                .getSingleResult();
        System.out.println("Cat getted " + cat.getName());
        return cat.getId();
    }
}
