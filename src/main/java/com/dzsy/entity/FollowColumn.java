package com.dzsy.entity;

import javax.persistence.*;

/**
 * Created by positif on 24/05/2017.
 */
@Entity
@IdClass(value=FollowColumnPrimaryKey.class)
@Table(name = "follow_column")
public class FollowColumn {

    @Id
    @Column(name="user_id",length=16)
    private String username;

    @Id
    @Column(name="news_column",length=4)
    private String newsColumn;

    public FollowColumn() {
    }

    public FollowColumn(String username, String newsColumn) {
        this.username = username;
        this.newsColumn = newsColumn;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewsColumn() {
        return newsColumn;
    }

    public void setNewsColumn(String newsColumn) {
        this.newsColumn = newsColumn;
    }

}
