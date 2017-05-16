package com.dzsy.service;

import com.dzsy.dao.ActivatingDAO;
import com.dzsy.dao.NewsColumnDAO;
import com.dzsy.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by positif on 15/05/2017.
 */
public class NewsColumnService {
    @Autowired
    private NewsColumnDAO newsColumnDao;

    public int gettotalCount() {
        return newsColumnDao.getTotalCount();
    }

    public List getPage(int begin, int count) {
        return newsColumnDao.getPage(begin, count);
    }

    public boolean isFollowd(String username, String newsColumn) {
        return newsColumnDao.isFollowd(username, newsColumn);
    }


    public NewsColumnDAO getNewsColumnDao() {
        return newsColumnDao;
    }

    public void setNewsColumnDao(NewsColumnDAO newsColumnDao) {
        this.newsColumnDao = newsColumnDao;
    }


}
