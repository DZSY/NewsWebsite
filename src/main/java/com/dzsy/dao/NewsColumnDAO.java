package com.dzsy.dao;

import com.dzsy.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import com.dzsy.entity.User;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by positif on 15/05/2017.
 */
public class NewsColumnDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public int getTotalCount() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session.createSQLQuery("SELECT DISTINCT news_column from news").list().size();
    }

    public List getPage(int begin, int count) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session.createSQLQuery("SELECT DISTINCT news_column from news LIMIT " + begin + "," + count).list();
    }

    public boolean isFollowd(String username, String newsColumn) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return !session.createSQLQuery(
                "SELECT * FROM user WHERE user_id = '" + username + "' AND news_column = '" + newsColumn + "'")
                .list()
                .isEmpty();
    }


}