package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class Mytask {

    @Scheduled(cron = "0/5 * * * * ?")// 每5秒执行一次
    public void executeTask() {

        log.info("定时任务执行中...");
        // 执行具体的任务逻辑
    }
}
