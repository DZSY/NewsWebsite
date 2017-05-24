package com.dzsy.dao;

import java.util.List;

import com.dzsy.entity.User;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public boolean isUserExist(String username) {
        return !getSession().createSQLQuery("select user_id from user where user_id = '" + username + "'").list().isEmpty();
    }

    public boolean isEmailExist(String email) {
        return !getSession().createSQLQuery("select email from user where email = '" + email + "'").list().isEmpty();
    }

    public boolean isPasswordMatching(String username, String password) {
        return !getSession().createSQLQuery(
                "SELECT user_id FROM user WHERE user_id = '" + username + "' AND password = '" + password + "'")
                .list()
                .isEmpty();
    }


    public void addUser(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }




}