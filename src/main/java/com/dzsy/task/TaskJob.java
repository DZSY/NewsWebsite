package com.dzsy.task;

/**
 * Created by positif on 03/05/2017.
 */

import org.apdplat.word.WordSegmenter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

@Component("taskJob")
public class TaskJob {

    public CrawlerDB crawlerDB;
    private List<Crawler> crawlers;

    TaskJob() {
        super();
        crawlerDB = new CrawlerDB();
        crawlers = new LinkedList<>();
        crawlers.add(new XinhuaCrawler(crawlerDB));
        //WordSegmenter.seg("初始化");
    }
    //每10分钟爬一次，每个新闻门户网站爬5分钟
    @Scheduled(fixedRate = 600000)
    public void job(){
        for (Crawler crawler : crawlers) {
            crawler.start();
            try { sleep(300000);}
            catch (InterruptedException e) {continue;}
            crawler.stop();
        }
    }

    @Scheduled(fixedRate = 6000000)
    public void job2(){
        try {
            crawlerDB.finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            crawlerDB = new CrawlerDB();
            crawlers = new LinkedList<>();
            crawlers.add(new XinhuaCrawler(crawlerDB));
        }
    }
}
