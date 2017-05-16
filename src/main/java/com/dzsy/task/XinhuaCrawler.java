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

public class XinhuaCrawler implements Crawler{


    private boolean stop = false;
    public CrawlerDB crawlerDb;

    XinhuaCrawler(CrawlerDB crawlerDB) {
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
                    System.out.println("Start Crawling XinhuaNet...");
                    crawl("http://news.xinhuanet.com");
                    System.out.println("Stop Crawling XinhuaNet");
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
                    String time = "", source = "新华网", news_column, title = "", body = "";

                    //get useful information
                    Document doc = Jsoup.connect(URL).timeout(1000).get();
                    if (!doc.select(".h-title").isEmpty() || !doc.select("#title").isEmpty()) {
                        if (URL.contains("politics")) {
                            news_column = "时政";
                        } else if (URL.contains("world")) {
                            news_column = "国际";
                        } else if (URL.contains("info")) {
                            news_column = "信息化";
                        } else if (URL.contains("fortune")) {
                            news_column = "财经";
                        } else if (URL.contains("house")) {
                            news_column = "房产";
                        } else if (URL.contains("mil")) {
                            news_column = "军事";
                        } else if (URL.contains("gangao")) {
                            news_column = "港澳";
                        } else if (URL.contains("tw")) {
                            news_column = "台湾";
                        } else if (URL.contains("ent")) {
                            news_column = "娱乐";
                        } else if (URL.contains("fashion")) {
                            news_column = "时尚";
                        } else if (URL.contains("sports")) {
                            news_column = "体育";
                        } else if (URL.contains("auto")) {
                            news_column = "汽车";
                        } else if (URL.contains("tech")) {
                            news_column = "科技";
                        } else if (URL.contains("food")) {
                            news_column = "食品";
                        } else if (URL.contains("money")) {
                            news_column = "金融";
                        } else if (URL.contains("legal")) {
                            news_column = "法治";
                        } else if (URL.contains("overseas")) {
                            news_column = "海外";
                        } else if (URL.contains("education")) {
                            news_column = "教育";
                        } else if (URL.contains("energy")) {
                            news_column = "能源";
                        } else if (URL.contains("renshi")) {
                            news_column = "人事";
                        } else if (URL.contains("sike")) {
                            news_column = "思客";
                        } else if (URL.contains("health")) {
                            news_column = "健康";
                        } else if (URL.contains("yuqing")) {
                            news_column = "舆情";
                        } else if (URL.contains("yuqing")) {
                            news_column = "舆情";
                        } else if (URL.contains("gongyi")) {
                            news_column = "公益";
                        } else if (URL.contains("chanye")) {
                            news_column = "产业园";
                        } else if (URL.contains("finance")) {
                            news_column = "证券";
                        } else if (URL.contains("newmedia")) {
                            news_column = "传媒";
                        }
                        else continue;


                        sql = "select * from `news` where `url` = '" + URL + "'";
                        if (crawlerDb.select(sql).next())
                            continue;
                        Elements paragraphs = new Elements();
                        if (!doc.select(".h-title").isEmpty()) {
                            title = doc.select(".h-title").first().ownText().trim();
                            time = doc.select(".h-time").first().ownText().trim();
                            paragraphs = doc.select("#p-detail").first().select("p");
                        } else if (!doc.select("#title").isEmpty()) {
                            title = doc.select("#title").first().ownText().trim();
                            if (!doc.select("#pubtime").isEmpty()) {
                                time = doc.select("#pubtime").first().ownText().trim();
                            }
                            else if (!doc.select(".time").isEmpty()) {
                                time = doc.select(".time").first().ownText().trim();
                            }
                            time = time.replace("年", "-");
                            time = time.replace("月", "-");
                            time = time.replace("日", "");

                            if (!doc.select("#content").isEmpty()) {
                                paragraphs = doc.select("#content").first().select("p");
                            }
                            else if (!doc.select(".article").isEmpty()) {
                                paragraphs = doc.select(".article").first().select("p");
                            }



                        }
                        Elements imgs = doc.select("img[src]");
                        for (Element element : imgs) {
                            if (!element.attr("src").trim().startsWith("http://")) {
                                element.attr("src", URL.substring(0, URL.lastIndexOf('/')) + '/' + element.attr("src").trim());
                            }
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
                    //get all links and recursively call the crawlSohu method
                    questions = doc.select("a[href]");
                    for(Element link: questions) {
                        if (stop)
                            break;
                        String linkAddress = link.attr("href");
                        if (!linkAddress.contains("forum") &&
                                !linkAddress.contains("russian") &&
                                !linkAddress.contains("arabic") &&
                                !linkAddress.contains("uyghur") &&
                                !linkAddress.contains("xizang") &&
                                (linkAddress.contains("news.xinhuanet.com") || linkAddress.contains("news.cn"))) {
                            list.add(link.attr("abs:href"));
                        }
                    }
                }
            } catch (Exception e) { continue; }
        }
    }
}

