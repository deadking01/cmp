package com.huawei.wuqf.esacess;

/**
 * Hello world!
 *
 */
public class App 
{
    private static ESClient client =new ESClient();
    public static void main( String[] args )
    {
        client.init();
        client.createIndex();
        client.close();
    }
}
