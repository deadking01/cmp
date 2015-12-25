package com.huawei.wuqf.loggergenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App
{
    protected static final Logger logger = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            new Thread(new Runnable()
            {
                public void run()
                {
                    for (int j = 0; j < 100; j++)
                    {
                        logger.debug("i is "+j+" . j is "+j);
                        try
                        {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
