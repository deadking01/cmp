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
        client.init("115.28.232.175", 9300);
        client.createIndex("users","user");
        client.close();
    }
}
