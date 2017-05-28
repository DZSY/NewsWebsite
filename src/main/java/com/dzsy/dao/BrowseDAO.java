package com.dzsy.dao;

import com.dzsy.entity.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apdplat.word.analysis.CosineTextSimilarity;
import org.apdplat.word.analysis.SimpleTextSimilarity;
import org.apdplat.word.analysis.TextSimilarity;
import org.apdplat.word.segmentation.Word;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apdplat.word.*;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private TextSimilarity textSimilarity = new SimpleTextSimilarity();
    private final int recommandNumber = 20;


    public String formatColumns(List itemList) {
        StringBuffer items = new StringBuffer("");
        for (Object object : itemList) {
            items.append("OR news_column = '" + object.toString() + "' ");
        }
        return items.delete(0,3).toString();
    }

    public List Recommend(String username) {
        Session session = getSession();
        List<Object[]> tempList = session.createSQLQuery("select news.news_id,title from news,browse_history where user_id='"+username+"' and news.news_id = browse_history.news_id ORDER BY browse_history.time DESC limit 0,20;").list();
        StringBuffer stringBuffer = new StringBuffer("*");
        Map<Integer, Boolean> browseHistoryMap = new HashMap<>();
        for (Object[] object : tempList) {
            browseHistoryMap.put((Integer)object[0], true);
            List<Word> words = WordSegmenter.seg((String)object[1]);
            for (Word word : words) {
                stringBuffer.append(word.getText() + "*");
            }
        }
        String formatedColumn = formatColumns(session.createSQLQuery("select distinct news_column from news,browse_history where user_id='"+username+"' and news.news_id = browse_history.news_id;").list());
        List<Object[]> list = session.createSQLQuery(
                "SELECT news_id,title,time from news " +
                        "WHERE (" +
                        formatedColumn +
                        ") " +
                        "AND MATCH(`title`) AGAINST('" +
                        stringBuffer.toString() +
                        "' IN BOOLEAN MODE)" +
                        "ORDER BY time DESC;").list();
        Queue<NewsSkeleton> newsSkeletonPriorityQueue = new PriorityQueue<>(similarityComparator);
        String[] items = stringBuffer.toString().split("\\*");
        for (Object[] object : list) {
            if (browseHistoryMap.get(object[0]) != null)
                continue;
            NewsSkeleton newsSkeleton = new NewsSkeleton((Integer)object[0], (String)object[1], simpleDateFormat.format((Timestamp)object[2]));
            int similarity = 0;
            for (int i = 0; i < items.length; i++) {
                if (newsSkeleton.getNewsTitle().contains(items[i])) similarity++;
            }
            newsSkeleton.setSimilarity(similarity);
            newsSkeletonPriorityQueue.add(newsSkeleton);
        }
        List<NewsSkeleton> result = new LinkedList<>();
        for (int i = 0; i < recommandNumber && !newsSkeletonPriorityQueue.isEmpty(); i++) {
            result.add(newsSkeletonPriorityQueue.poll());
        }
        return result;
    }

    public static Comparator<NewsSkeleton> similarityComparator = new Comparator<NewsSkeleton>(){

        @Override
        public int compare(NewsSkeleton newsSkeleton1, NewsSkeleton newsSkeleton2) {
            return newsSkeleton2.getSimilarity() - newsSkeleton1.getSimilarity();
        }
    };
    

}