package com.dzsy.service;

import com.dzsy.dao.FollowDAO;
import com.dzsy.dao.NewsColumnDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by positif on 15/05/2017.
 */
public class FollowService {
    @Autowired
    private FollowDAO followDao;

    public boolean isFollowd(String username, String newsColumn) {
        return followDao.isFollowd(username, newsColumn);
    }

    public int getFollowedColumnsTotalCount(String username) {
        return followDao.getFollowedColumnsTotalCount(username);
    }

    public List getFollowedColumnsPage(String username, int begin, int count) {
        return followDao.getFollowedColumnsPage(username, begin, count);
    }

    public void Follow(String username, String newsColumn) {
        followDao.Follow(username, newsColumn);
    }

    public void Unfollow(String username, String newsColumn) {
        followDao.Unfollow(username, newsColumn);
    }

    public FollowDAO getFollowDao() {
        return followDao;
    }

    public void setFollowDao(FollowDAO followDao) {
        this.followDao = followDao;
    }


}
