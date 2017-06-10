package com.dzsy.task;

import com.dzsy.task.Crawler;
import com.dzsy.task.CrawlerDB;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by positif on 14/05/2017.
 */

public class NetEaseCrawler implements Crawler {


    private boolean stop = false;
    public CrawlerDB crawlerDb;

    public NetEaseCrawler(CrawlerDB crawlerDB) {
        this.crawlerDb = crawlerDB;
    }

    public void stop () {stop = true;}

    public void start() {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (crawlerDb) {
                    try {
                        crawlerDb.runSQL("TRUNCATE crawled;");
                    } catch (SQLException e) {
                        return;
                    }
                    System.out.println("Start Crawling NetEase News...");
                    crawl("http://news.163.com");
                    //crawl("http://tech.163.com/17/0606/06/CM7QN11M00097U7T.html");
                    System.out.println("Stop Crawling NetEase News");
                }
            }
        })).start();
    }


    public void crawl(String url) {
        List<String> list = new LinkedList<>();
        list.add(url);
        for(int i = 0; i < list.size() && !stop; i++) {
            String URL = list.get(i);
            try {
                //check if the given URL is already in database
                String sql = "SELECT * FROM `crawled` WHERE `url` = '" + URL + "'";
                //store the URL to database to avoid parsing again
                if(!crawlerDb.select(sql).next()){
                    sql = "INSERT INTO  `crawled` " + "(`URL`) VALUES " + "(?);";
                    PreparedStatement stmt = crawlerDb.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    stmt.setString(1, URL);
                    stmt.execute();

                    Elements questions;
                    String time = "", source = "网易新闻", news_column, title = "", body = "";

                    //get useful information
                    Document doc = Jsoup.connect(URL).timeout(1000).get();
                    if (!doc.select(".post_time_source").isEmpty() || !doc.select(".ep-time-soure").isEmpty()){
                        if (URL.contains("domestic")) {
                            news_column = "国内";
                        }
                        else if (URL.contains("world")) {
                            news_column = "国际";
                        }
                        else if (URL.contains("discover")) {
                            news_column = "探索";
                        }
                        else if (URL.contains("war")) {
                            news_column = "军事";
                        }
                        else if (URL.contains("localnew")) {
                            news_column = "本地";
                        }
                        else if (URL.contains("sports")) {
                            news_column = "体育";
                        }
                        else if (URL.contains("ent")) {
                            news_column = "娱乐";
                        }
                        else if (URL.contains("money")) {
                            news_column = "财经";
                        }
                        else if (URL.contains("auto")) {
                            news_column = "汽车";
                        }
                        else if (URL.contains("tech")) {
                            news_column = "科技";
                        }
                        else if (URL.contains("lady")) {
                            news_column = "女人";
                        }
                        else if (URL.contains("fashion")) {
                            news_column = "时尚";
                        }
                        else if (URL.contains("shoucang")) {
                            news_column = "收藏";
                        }
                        else if (URL.contains("mobile")) {
                            news_column = "手机";
                        }
                        else if (URL.contains("house")) {
                            news_column = "房产";
                        }
                        else if (URL.contains("travel")) {
                            news_column = "旅游";
                        }
                        else if (URL.contains("edu")) {
                            news_column = "教育";
                        }

                        else if (URL.contains("news")) {
                            news_column = "热点";
                        }
                        else continue;

                        sql = "select * from `news` where `url` = '" + URL + "'";
                        if (crawlerDb.select(sql).next())
                            continue;

                        title = doc.select("h1").first().ownText();
                        Elements paragraphs = new Elements();
                        if (!doc.select(".post_time_source").isEmpty()) {
                            time = doc.select(".post_time_source").first().ownText();
                            paragraphs = doc.select("#endText").first().select("p");
                        }
                        else if (!doc.select(".ep-time-soure").isEmpty()) {
                            time = doc.select(".ep-time-soure").first().ownText();
                            paragraphs = doc.select("#endText").first().select("p");
                        }

                        time = time.trim();
                        time = time.substring(0, 19);
                        body = paragraphs.outerHtml();

                        sql = "INSERT INTO  `news` " + "(`time`,`url`,`source`,`news_column`,`title`,`body`) VALUES " + "(?,?,?,?,?,?);";
                        stmt = crawlerDb.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        stmt.setString(1, time);
                        stmt.setString(2, URL);
                        stmt.setString(3, source);
                        stmt.setString(4, news_column);
                        stmt.setString(5, title);
                        stmt.setString(6, body);
                        stmt.execute();
                    }
                    questions = doc.select("a[href]");
                    for(Element link: questions) {
                        if (stop)
                            break;
                        String linkAddress = link.attr("href");
                        if (linkAddress.contains("163.com")) {
                            list.add(link.attr("abs:href"));
                        }
                    }
                }
            } catch (Exception e) { continue; }
        }
    }
}

