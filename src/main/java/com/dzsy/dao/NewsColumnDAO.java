package com.dzsy.dao;

import com.dzsy.entity.News;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apdplat.word.segmentation.Word;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apdplat.word.*;

import java.math.BigInteger;
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

    private Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public int getColumnsTotalCount() {
        Object object = getSession().createSQLQuery("SELECT COUNT(DISTINCT news_column) from news").list().get(0);
        return ((BigInteger)object).intValue();
    }

    public List getColumnsPage(int begin, int count) {
        return getSession().createSQLQuery("SELECT DISTINCT news_column from news LIMIT " + begin + "," + count).list();
    }

    public boolean isFollowd(String username, String newsColumn) {
        return !getSession().createSQLQuery(
                "SELECT * FROM user WHERE user_id = '" + username + "' AND news_column = '" + newsColumn + "'")
                .list()
                .isEmpty();
    }

    public int getNewsTotalCount(String newsColumn) {
        if ("".equals(newsColumn)) {
            Object object = getSession().createSQLQuery("SELECT COUNT(news_id) from news").list().get(0);
            return ((BigInteger)object).intValue();
        }
        Object object = getSession().createSQLQuery("SELECT COUNT(news_id) from news WHERE news_column = '" + newsColumn + "'").list().get(0);
        return ((BigInteger)object).intValue();
    }

    public List getNewsPage(String newsColumn, int begin, int count) {
        if ("".equals(newsColumn))
            return getSession().createSQLQuery("SELECT news_id,title,time from news ORDER BY time DESC LIMIT " + begin + "," + count).list();
        return getSession().createSQLQuery("SELECT news_id,title,time from news WHERE news_column = '" + newsColumn + "' ORDER BY time DESC LIMIT " + begin + "," + count).list();
    }

    public News getNewsInfo(Integer ID) {
        return (News) getSession().load(News.class, ID);
    }

    public String getItems(String item) {
        //移除停用词
        List<Word> words = WordSegmenter.seg(item);
        StringBuffer items = new StringBuffer("*");
        for (Word word : words) {
            items.append(word.getText() + "*");
        }
        return items.toString();
    }



    public int getSearchTitleTotalCount(String item) {
        Object object = getSession().createSQLQuery("" +
                "SELECT COUNT(news_id) from news " +
                "WHERE MATCH(`title`) AGAINST('" +
                getItems(item) +
                "' IN BOOLEAN MODE);" ).list().get(0);
        return ((BigInteger)object).intValue();

    }

    public List getSearchTitleNewsPage(String item, int begin, int count) {
        return getSession().createSQLQuery(
                "SELECT news_id,title,time from news " +
                        "WHERE MATCH(`title`) AGAINST('" +
                        getItems(item) +
                        "' IN BOOLEAN MODE)" +
                        "ORDER BY time DESC LIMIT " + begin + "," + count + ";").list();
    }

    public int getSearchBodyTotalCount(String item) {
        Object object = getSession().createSQLQuery("" +
                "SELECT COUNT(news_id) from news " +
                "WHERE MATCH(`body`) AGAINST('" +
                getItems(item) +
                "' IN BOOLEAN MODE);" ).list().get(0);
        return ((BigInteger)object).intValue();

    }

    public List getSearchBodyNewsPage(String item, int begin, int count) {
        return getSession().createSQLQuery(
                "SELECT news_id,title,time from news " +
                        "WHERE MATCH(`body`) AGAINST('" +
                        getItems(item) +
                        "' IN BOOLEAN MODE)" +
                        "ORDER BY time DESC LIMIT " + begin + "," + count + ";").list();
    }

}