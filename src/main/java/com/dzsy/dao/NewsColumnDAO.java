package com.dzsy.dao;

import com.dzsy.entity.News;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

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

    public int getColumnsTotalCount() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session.createSQLQuery("SELECT DISTINCT news_column from news").list().size();
    }

    public List getColumnsPage(int begin, int count) {
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

    public int getNewsTotalCount(String newsColumn) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        if ("".equals(newsColumn))
            return session.createSQLQuery("SELECT news_id from news").list().size();
        return session.createSQLQuery("SELECT news_id from news WHERE news_column = '" + newsColumn + "'" ).list().size();
    }

    public List getNewsPage(String newsColumn, int begin, int count) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        if ("".equals(newsColumn))
            return session.createSQLQuery("SELECT news_id,title,time from news ORDER BY time DESC LIMIT " + begin + "," + count).list();
        return session.createSQLQuery("SELECT news_id,title,time from news WHERE news_column = '" + newsColumn + "' ORDER BY time DESC LIMIT " + begin + "," + count).list();
    }

    public News getNewsInfo(Integer ID) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        return (News) session.load(News.class, ID);
    }
}