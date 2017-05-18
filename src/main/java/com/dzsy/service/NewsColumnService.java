package com.dzsy.service;

import com.dzsy.dao.NewsColumnDAO;
import com.dzsy.entity.News;
import com.dzsy.entity.NewsSkeleton;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by positif on 15/05/2017.
 */
public class NewsColumnService {
    @Autowired
    private NewsColumnDAO newsColumnDao;

    public int getColumnsTotalCount() {
        return newsColumnDao.getColumnsTotalCount();
    }

    public List getColumnsPage(int begin, int count) {
        return newsColumnDao.getColumnsPage(begin, count);
    }

    public boolean isFollowd(String username, String newsColumn) {
        return newsColumnDao.isFollowd(username, newsColumn);
    }

    public int getNewsTotalCount(String newsColumn) { return newsColumnDao.getNewsTotalCount(newsColumn); }

    public List getNewsPage(String newsColumn, int begin, int count) {
        return newsColumnDao.getNewsPage(newsColumn, begin, count);
    }

    public News getNewsInfo(Integer ID) {
        return newsColumnDao.getNewsInfo(ID);
    }

    public NewsColumnDAO getNewsColumnDao() {
        return newsColumnDao;
    }

    public void setNewsColumnDao(NewsColumnDAO newsColumnDao) {
        this.newsColumnDao = newsColumnDao;
    }


}
