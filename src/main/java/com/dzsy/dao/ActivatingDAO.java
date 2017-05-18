package com.dzsy.dao;

import java.util.List;

import com.dzsy.entity.Activation;
import com.dzsy.entity.User;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivatingDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public String getActivationCode(String username) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List list = session.createSQLQuery("SELECT activation_code FROM activating WHERE user_id = '" + username + "'").list();
        if (list.isEmpty())
            return null;
        else return list.get(0).toString();
    }

    public void addActivating(Activation activation) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = session.beginTransaction();
        session.save(activation);
        transaction.commit();
    }

    public void deleteActivation(String username) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        try {
            Transaction transaction = session.beginTransaction();
            Activation activation = (Activation) session.get(Activation.class, username);
            session.delete(activation);
            transaction.commit();
        } catch (Exception e) {
            return;
        }
    }


}