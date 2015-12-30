package com.huawei.wuqf.loggergenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class Generator
{
    protected static final Logger logger = LoggerFactory.getLogger(Generator.class);
    
    public static void main(String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            new Thread(new Runnable()
            {
                public void run()
                {
                    for (int j = 0; j < 100000000; j++)
                    {
                        logger.debug("j is "+j);
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
