package com.huawei.wuqf.loggergenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 */
public class Generator {
    protected static final Logger logger = LoggerFactory.getLogger(Generator.class);

    public static void main(String[] args) {
        int threadCount;
        final int logsPerThread;
        final int timeGap;
        if (args == null || args.length < 3) {
            logsPerThread = 1000;
            threadCount = 10;
            timeGap = 100;
        } else {
            threadCount = Integer.valueOf(args[0]);
            logsPerThread = Integer.valueOf(args[1]);
            timeGap = Integer.valueOf(args[2]);
        }
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < logsPerThread; j++) {
                        logger.debug("thread name is " + Thread.currentThread().getName() + " .j is " + j);
                        try {
                            Thread.sleep(timeGap);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, String.valueOf(i)).start();
        }
    }
}
