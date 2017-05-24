package com.dzsy.dao;

import com.dzsy.entity.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apdplat.word.segmentation.Word;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apdplat.word.*;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by positif on 15/05/2017.
 */
public class BrowseDAO {

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

    public void BrowseNews(String username, Integer newsID) {
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(new BrowseHistory(username, newsID));
            transaction.commit();
        } catch (Exception e) {
            BrowseNews(username, newsID);
        }
    }

    public void DeleteBrowseNews(String username, Integer newsID, Timestamp time) {
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
            BrowseHistory browseHistory = (BrowseHistory) session.get(BrowseHistory.class, new BrowseHistoryPrimaryKey(username, newsID, time));
            session.delete(browseHistory);
            transaction.commit();
        } catch (Exception e) {
            DeleteBrowseNews(username, newsID, time);
        }
    }

}