package com.dzsy.entity;

import javax.persistence.Entity;

/**
 * Created by positif on 16/05/2017.
 */
public class NewsColumn {
    public String columnName;
    public boolean isfollowed;
    public String columnLabel;

    public NewsColumn(String columnName, boolean isfollowed, String columnLabel) {
        this.columnName = columnName;
        this.isfollowed = isfollowed;
        this.columnLabel = columnLabel;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean getIsFollowed() {
        return isfollowed;
    }

    public void setIsfollowed(boolean isfollowed) {
        this.isfollowed = isfollowed;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }
}