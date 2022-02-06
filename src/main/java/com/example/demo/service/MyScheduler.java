package com.example.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MyScheduler {

    @Scheduled(cron = "0 0/1 * 1/1 * ?") // generated code from cronemaker website without last * mark
    public void firstScheduler()
    {
        // this will print date in every 1 minute automatically
        System.out.println(new Date());
    }
}
