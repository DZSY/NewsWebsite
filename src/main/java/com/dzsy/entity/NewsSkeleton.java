package com.dzsy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by positif on 17/05/2017.
 */
public class NewsSkeleton {
    private Integer newsID;
    private String newsTitle;
    private String newsTime;
    private int similarity = 0;



    public NewsSkeleton(Integer newsID, String newsTitle, String newsTime) {
        this.newsID = newsID;
        this.newsTitle = newsTitle;
        this.newsTime = newsTime;
    }

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public int getSimilarity() {
        return similarity;
    }

    public void setSimilarity(int similarity) {
        this.similarity = similarity;
    }
}
