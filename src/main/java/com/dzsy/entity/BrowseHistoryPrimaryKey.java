package com.dzsy.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by positif on 24/05/2017.
 */

@Embeddable
public class BrowseHistoryPrimaryKey implements Serializable {


    private static final long serialVersionUID = -1473851651834821654L;

    private String username;
    private Integer newsID;
    private Timestamp time = null;

    public BrowseHistoryPrimaryKey() {
    }

    public BrowseHistoryPrimaryKey(String username, Integer newsID, Timestamp time) {
        this.username = username;
        this.newsID = newsID;
        this.time = time;
    }

    public BrowseHistoryPrimaryKey(String username, Integer newsID) {
        this.username = username;
        this.newsID = newsID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrowseHistoryPrimaryKey that = (BrowseHistoryPrimaryKey) o;

        if (!username.equals(that.username)) return false;
        if (!newsID.equals(that.newsID)) return false;
        return time != null ? time.equals(that.time) : that.time == null;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + newsID.hashCode();
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}