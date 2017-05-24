package com.dzsy.service;

import com.dzsy.dao.BrowseDAO;
import com.dzsy.dao.FollowDAO;
import com.dzsy.dao.NewsColumnDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by positif on 15/05/2017.
 */
public class BrowseService {
    @Autowired
    private BrowseDAO browseDao;

    public BrowseDAO getBrowseDao() {
        return browseDao;
    }

    public void setBrowseDao(BrowseDAO browseDao) {
        this.browseDao = browseDao;
    }

    public void BrowseNews(String username, Integer newsID) {
        browseDao.BrowseNews(username, newsID);
    }
}
