package com.huawei.wuqf.esacess;

/**
 * Hello world!
 *
 */
public class ESClientTest 
{
    private static  ESClient client =ESClient.getInstance("115.28.232.175", 9300);
    public static void main( String[] args )
    {
        client.createIndex("users","user");
        client.close();
    }
}
