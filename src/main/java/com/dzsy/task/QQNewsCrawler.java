package com.dzsy.task;

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

public class QQNewsCrawler implements Crawler{


    private boolean stop = false;
    public CrawlerDB crawlerDb;

    public QQNewsCrawler(CrawlerDB crawlerDB) {
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
                    System.out.println("Start Crawling QQ News...");
                    crawl("http://news.qq.com");
                    //crawl("http://news.qq.com/a/20170606/037265.htm");
                    System.out.println("Stop Crawling QQ News");
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
                    String time = "", source = "腾讯新闻", news_column, title = "", body = "";

                    //get useful information
                    Document doc = Jsoup.connect(URL).timeout(1000).get();
                    if (!doc.select("#Cnt-Main-Article-QQ").isEmpty()){
                        if (URL.contains("sports")) {
                            news_column = "体育";
                        }
                        else if (URL.contains("ent")) {
                            news_column = "娱乐";
                        }
                        else if (URL.contains("finance")) {
                            news_column = "财经";
                        }
                        else if (URL.contains("stock")) {
                            news_column = "证券";
                        }
                        else if (URL.contains("auto")) {
                            news_column = "汽车";
                        }
                        else if (URL.contains("house")) {
                            news_column = "房产";
                        }
                        else if (URL.contains("tech")) {
                            news_column = "科技";
                        }
                        else if (URL.contains("digi")) {
                            news_column = "数码";
                        }
                        else if (URL.contains("edu")) {
                            news_column = "教育";
                        }
                        else if (URL.contains("fashion")) {
                            news_column = "时尚";
                        }
                        else if (URL.contains("cul")) {
                            news_column = "文化";
                        }
                        else if (URL.contains("society")) {
                            news_column = "社会";
                        }
                        else if (URL.contains("mil")) {
                            news_column = "军事";
                        }
                        else if (URL.contains("history")) {
                            news_column = "历史";
                        }
                        else if (URL.contains("gongyi")) {
                            news_column = "公益";
                        }
                        else if (URL.contains("ly")) {
                            news_column = "旅游";
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
                        if (!doc.select("#Cnt-Main-Article-QQ").isEmpty()) {
                            if (!doc.select(".article-time").isEmpty()) {
                                time = doc.select(".article-time").first().ownText();
                            }
                            else if (!doc.select(".a_time").isEmpty())
                                time = doc.select(".a_time").first().ownText();
                            else {
                                int ll = 0;
                            }
                            paragraphs = doc.select("#Cnt-Main-Article-QQ").first().select("p");
                        }

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
                        if (linkAddress.contains("qq.com")) {
                            list.add(link.attr("abs:href"));
                        }
                    }
                }
            } catch (Exception e) { continue; }
        }
    }
}

