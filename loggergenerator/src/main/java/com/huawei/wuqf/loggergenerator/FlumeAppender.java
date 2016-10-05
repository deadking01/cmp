package com.huawei.wuqf.loggergenerator;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class FlumeAppender extends AppenderSkeleton {

    public void close() {
        // TODO Auto-generated method stub
    }

    public boolean requiresLayout() {
        return false;
    }

    @Override
    protected void append(LoggingEvent arg0) {
    }

}
