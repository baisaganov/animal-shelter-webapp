package kz.alisher.springcourse.dao;

import kz.alisher.springcourse.models.Cat;
import kz.alisher.springcourse.models.CatRequest;
import kz.alisher.springcourse.models.Others;
import kz.alisher.springcourse.models.OthersRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OthersRequestsDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public OthersRequestsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<OthersRequest> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d FROM OthersRequest d", OthersRequest.class).getResultList();
    }

    @Transactional
    public OthersRequest index(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d FROM OthersRequest d where id = :paramID", OthersRequest.class)
                .setParameter("paramID", id)
                .getSingleResult();
    }

    @Transactional
    public void bookNewOthers(OthersRequest othersRequest, int petId){
        Session session = sessionFactory.getCurrentSession();
        Others others = session.get(Others.class, petId);
        othersRequest.setOthers(others);
        System.out.println(othersRequest.getId());

        session.save(others);
        session.save(othersRequest);
    }

    @Transactional
    public void othersRequestDelete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(OthersRequest.class, id));
    }

    @Transactional
    public OthersRequest getOthersByRequestId(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(OthersRequest.class, id);
    }

    @Transactional
    public int getOthersId(int reqId){
        Session session =sessionFactory.getCurrentSession();
        System.out.println("Cat getting started with reqID = " + reqId);

        Others others = session.createQuery("from OthersRequest r where r.id = :paramId", Others.class)
                .setParameter("paramId", reqId)
                .getSingleResult();
        System.out.println("Cat getted " + others.getName());
        return others.getId();
    }
}
