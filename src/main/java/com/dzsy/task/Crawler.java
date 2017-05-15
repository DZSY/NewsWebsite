package com.dzsy.task;

/**
 * Created by positif on 14/05/2017.
 */
public interface Crawler {
    void start();
    void stop();
    void crawl(String URL);

}
