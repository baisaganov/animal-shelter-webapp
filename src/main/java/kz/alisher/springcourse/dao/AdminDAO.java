package kz.alisher.springcourse.dao;


import kz.alisher.springcourse.models.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AdminDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public AdminDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public boolean auth(String username, String password){
        Session session = sessionFactory.getCurrentSession();
        List<Admin> list = session.createQuery("from Admin where name = :paramName", Admin.class)
                .setParameter("paramName", username)
                .getResultList();
        if(list.isEmpty())
            return false;

        return list.get(0).getPassword().equals(password);
    }
}
