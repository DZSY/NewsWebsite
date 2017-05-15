package com.dzsy.task;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by positif on 04/05/2017.
 */
public class SohuCrawler implements Crawler{


    private boolean stop = false;
    public CrawlerDB crawlerDb;

    SohuCrawler(CrawlerDB crawlerDB) {
        this.crawlerDb = crawlerDB;
    }

    public void stop () {stop = true;}

    public void start() {
        try {
            synchronized (crawlerDb) {
                crawlerDb.runSQL("TRUNCATE crawled;");
                crawl("http://news.sohu.com");
            }
        } catch (SQLException e) {}
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
                    String time, source, news_column, title, body;
                    //get useful information
                    Document doc = Jsoup.connect(URL).get();
                    Elements elements = doc.select("div#mypos");
                    if (!elements.isEmpty()) {
                        sql = "select * from `news` where `url` = '" + URL + "'";
                        if (crawlerDb.select(sql).next())
                            continue;
                        title = doc.select("h1").first().ownText();
                        news_column = elements.select("span > a").first().ownText();
                        time = doc.select("div.time[content]").first().attr("content");
                        time = time.replace('T', ' ').substring(0,time.indexOf('+'));
                        source = "搜狐新闻";
                        body = doc.select("div#contentText > div").first().html();
                        if (body.contains("<script")) {
                            body = body.substring(0, body.lastIndexOf("<script"));
                        }
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
                    //get all links and recursively call the crawlSohu method
                    questions = doc.select("a[href]");
                    for(Element link: questions) {
                        if (stop)
                            break;
                        if (link.attr("href").contains("news.sohu.com")) {
                            list.add(link.attr("abs:href"));
                        }
                    }
                }
            } catch (Exception e) {}
        }

    }
}
