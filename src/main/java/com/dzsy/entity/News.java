package com.dzsy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by positif on 17/05/2017.
 */

@Entity
@Table(name = "news")
public class News {
    @Id
    @Column(name="news_id")
    private Integer newsID;

    @Column(name="time")
    private Timestamp time;

    @Column(name="url",length=100)
    private String url;

    @Column(name="source",length=10)
    private String source;

    @Column(name="news_column",length=10)
    private String newsColumn;

    @Column(name="title",length=50)
    private String title;

    @Column(name="body")
    private String body;




    public News() {}

    public News(Integer newsID, Timestamp time, String url, String source, String newsColumn, String title, String body) {
        this.newsID = newsID;
        this.time = time;
        this.url = url;
        this.source = source;
        this.newsColumn = newsColumn;
        this.title = title;
        this.body = body;
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
    public String getUrl() {
        return url;
    }

    public void setUrl(String  url) {
        this.url = url;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getNewsColumn() {
        return newsColumn;
    }

    public void setNewsColumn(String newsColumn) {
        this.newsColumn = newsColumn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}


