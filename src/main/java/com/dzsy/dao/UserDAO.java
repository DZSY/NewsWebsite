package com.dzsy.dao;

import java.util.List;

import com.dzsy.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public boolean isUserExist(String username) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return !session.createSQLQuery("select user_id from user where user_id = '" + username + "'").list().isEmpty();
    }

    public boolean isEmailExist(String email) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return !session.createSQLQuery("select email from user where email = '" + email + "'").list().isEmpty();
    }

    public boolean isPasswordMatching(String username, String password) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return !session.createSQLQuery(
                "SELECT user_id FROM user WHERE user_id = '" + username + "' AND password = '" + password + "'")
                .list()
                .isEmpty();
    }

    public String getActivationCode(String username) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List list = session.createSQLQuery("SELECT user_id FROM activating WHERE user_id = '" + username + "'").list();
        if (list.isEmpty())
            return null;
        else return list.get(0).toString();
    }

    public void addUser(User user) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createSQLQuery("INSERT INTO user (user_id, password, email) VALUES (?,?,?))");
        query.setParameter(1, user.getUsername());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getEmail());
        query.executeUpdate();
    }


}