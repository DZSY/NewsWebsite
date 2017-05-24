package com.dzsy.dao;

import com.dzsy.entity.Activation;
import com.dzsy.entity.FollowColumn;
import com.dzsy.entity.FollowColumnPrimaryKey;
import com.dzsy.entity.News;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apdplat.word.segmentation.Word;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apdplat.word.*;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by positif on 15/05/2017.
 */
public class FollowDAO {

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

    public boolean isFollowd(String username, String newsColumn) {
        return !getSession().createSQLQuery(
                "SELECT * FROM follow_column WHERE user_id = '" + username + "' AND news_column = '" + newsColumn + "'")
                .list()
                .isEmpty();
    }

    public int getFollowedColumnsTotalCount(String username) {
        Object object = getSession().createSQLQuery("SELECT COUNT(user_id) from follow_column WHERE user_id = '" + username + "'").list().get(0);
        return ((BigInteger)object).intValue();
    }

    public List getFollowedColumnsPage(String username, int begin, int count) {
        return getSession().createSQLQuery("SELECT news_column from follow_column WHERE user_id = '" + username + "' LIMIT " + begin + "," + count).list();
    }

    public void Follow(String username, String newsColumn) {
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(new FollowColumn(username, newsColumn));
            transaction.commit();
        } catch (Exception e) {
            Follow(username, newsColumn);
        }
    }

    public void Unfollow(String username, String newsColumn) {
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
            FollowColumn followColumn = (FollowColumn) session.get(FollowColumn.class, new FollowColumnPrimaryKey(username, newsColumn));
            session.delete(followColumn);
            transaction.commit();
        } catch (Exception e) {
            Unfollow(username, newsColumn);
        }
    }


}