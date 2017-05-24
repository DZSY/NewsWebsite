package com.dzsy.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by positif on 24/05/2017.
 */

@Embeddable
public class FollowColumnPrimaryKey implements Serializable {


    private static final long serialVersionUID = 1960856188501678352L;

    private String username;
    private String newsColumn;

    public FollowColumnPrimaryKey() {
    }

    public FollowColumnPrimaryKey(String username, String newsColumn) {
        this.username = username;
        this.newsColumn = newsColumn;
    }

    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    String getNewsColumn() {
        return newsColumn;
    }

    void setNewsColumn(String newsColumn) {
        this.newsColumn = newsColumn;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowColumnPrimaryKey that = (FollowColumnPrimaryKey) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return newsColumn != null ? newsColumn.equals(that.newsColumn) : that.newsColumn == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (newsColumn != null ? newsColumn.hashCode() : 0);
        return result;
    }
}