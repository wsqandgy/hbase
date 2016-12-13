package com.gy.thread;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 监听心跳的小程序
 */
public class StartThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(StartThread.class);

    @Override
    public void run() {
        while(true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("监听心跳......");

        }
    }

}
