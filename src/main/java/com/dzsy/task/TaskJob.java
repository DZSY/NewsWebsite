package com.dzsy.task;

/**
 * Created by positif on 03/05/2017.
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskJob")
public class TaskJob {
    //每分钟的10秒执行
    @Scheduled(fixedRate = 10000 * 1)
    public void job(){
        System.out.println("Hello");
    }

}
