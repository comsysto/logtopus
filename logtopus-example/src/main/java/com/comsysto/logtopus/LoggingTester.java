package com.comsysto.logtopus;

import java.util.ConcurrentModificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author steinerb
 */
public class LoggingTester {

    private static Logger log = LoggerFactory.getLogger("logEvent");

    public static void main(String[] args){
        log.warn("this is my last warning...");
        log.error("this is an exception...", new ConcurrentModificationException("blub"));

        logMultipleInfoMessages(10);
    }

    private static void logMultipleInfoMessages(int numberOfMessages) {
        for (int i = 0; i < numberOfMessages; i++) {
            log.info("info message");
        }
    }
}

