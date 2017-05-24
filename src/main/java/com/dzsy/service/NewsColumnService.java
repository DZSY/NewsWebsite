package com.dzsy.service;

import com.dzsy.dao.NewsColumnDAO;
import com.dzsy.entity.News;
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

    public int getNewsTotalCount(String newsColumn) { return newsColumnDao.getNewsTotalCount(newsColumn); }

    public List getNewsPage(String newsColumn, int begin, int count) {
        return newsColumnDao.getNewsPage(newsColumn, begin, count);
    }

    public News getNewsInfo(Integer ID) {
        return newsColumnDao.getNewsInfo(ID);
    }

    public int getSearchTitleTotalCount(String item) {
        return newsColumnDao.getSearchTitleTotalCount(item);
    }

    public List getSearchTitleNewsPage(String item, int begin, int count) {
        return newsColumnDao.getSearchTitleNewsPage(item, begin, count);
    }

    public int getSearchBodyTotalCount(String item) {
        return newsColumnDao.getSearchBodyTotalCount(item);
    }

    public List getSearchBodyNewsPage(String item, int begin, int count) {
        return newsColumnDao.getSearchBodyNewsPage(item, begin, count);
    }

    public NewsColumnDAO getNewsColumnDao() {
        return newsColumnDao;
    }

    public void setNewsColumnDao(NewsColumnDAO newsColumnDao) {
        this.newsColumnDao = newsColumnDao;
    }


}
