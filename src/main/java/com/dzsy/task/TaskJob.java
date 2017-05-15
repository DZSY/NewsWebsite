package com.dzsy.task;

/**
 * Created by positif on 03/05/2017.
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

@Component("taskJob")
public class TaskJob {
    public static CrawlerDB crawlerDB = new CrawlerDB();

    //每5分钟爬1分钟
    @Scheduled(fixedRate = 300000)
    public void job(){
        Crawler crawler = new XinhuanetCrawler(crawlerDB);
        crawler.start();
        try { sleep(60000);}
        catch (InterruptedException e) {}
        crawler.stop();
        System.out.println("Stop Crawling XinhuaNet");
    }

    //每5分钟爬1分钟
    @Scheduled(fixedRate = 300000)
    public void job2(){
        Crawler crawler = new SohuCrawler(crawlerDB);
        crawler.start();
        try { sleep(60000);}
        catch (InterruptedException e) {}
        crawler.stop();
        System.out.println("Stop Crawling Sohu");
    }

}
