package com.comsysto.logtopus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.ConcurrentModificationException;

/**
 * @author steinerb
 */
public class LoggingTester {

    private static Logger log = LoggerFactory.getLogger(LoggingTester.class);

    public static void main(String[] args) {

        while(true){
            double rand1 = Math.random();
            double rand2 = Math.random();
            int level = (int) Math.round(rand1 * 4.0d);
            switch (level) {
                case 0:
                    debug();
                    break;
                case 1:
                    info();
                    break;
                case 2:
                    warn();
                    break;
                case 3:
                    error();
                    break;
            }

            try {
                Thread.sleep((long) (10000.0d * rand2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void warn() {
        if(Math.random()>0.2){
            log.warn("this is my last warning...");
            System.out.println("warn");
        }
    }

    private static void debug() {
        if(Math.random()>0.9d){
            log.debug("nothing important!");
            System.out.println("debug");
        }
    }

    private static void error() {
        double random = Math.random();
        if(random >0.33 && random < 0.9){
            log.error("oh no, you deleted the internet!", new ConcurrentModificationException("file www.google.com not found!"));
            System.out.println("error");
        }
        if(random < 0.1){
            log.error("Internet Backup Procedure terminated with errors", new UnknownHostException());
            System.out.println("error");
        }
        if(random >= 0.9){
            try{
                getSomeSeriousExceptionStack();
            } catch (Exception e){
                log.error("Internet Backup Procedure terminated with errors", e);
            }
            System.out.println("stack error");
        }
    }

    private static void info() {
        if(Math.random()>0.1){
            log.info("just to inform you...");
            System.out.println("info");
        }
    }

    public static void getSomeSeriousExceptionStack() {
        try {
            getNullPointerException();
        } catch (Exception e){
            throw new IllegalStateException("oops",e);
        }

    }

    public static void getNullPointerException() {
        throw new NullPointerException("ohoh");
    }

}

