package com.dzsy.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by positif on 24/05/2017.
 */
@Entity
@IdClass(value=BrowseHistoryPrimaryKey.class)
@DynamicInsert()
@DynamicUpdate()
@Table(name = "browse_history")
public class BrowseHistory {


    @Id
    @Column(name="user_id",length=16)
    private String username;

    @Id
    @Column(name="news_id")
    private Integer newsID;

    @Id
    @Column(name="time")
    private String time;

    public BrowseHistory() {
    }

    public BrowseHistory(String username, Integer newsID) {
        this.username = username;
        this.newsID = newsID;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
