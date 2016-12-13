package com.gy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 入口类
 */
public class StartMain {

    private final static Logger logger = LoggerFactory.getLogger(StartMain.class);

    public static void main(String[] args){
        logger.info("应用开始启动");
        AbstractXmlApplicationContext ac = new ClassPathXmlApplicationContext("/spring/stand-alone.xml");
        ac.start();
        logger.info("应用启动完成!");
    }

}
