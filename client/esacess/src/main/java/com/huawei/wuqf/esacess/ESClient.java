package com.huawei.wuqf.esacess;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ESClient
{
    private Client client;
    
    public void init()
    {
        client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("115.28.232.175", 9300));
    }
    
    public void close()
    {
        client.close();
    }
    
    public void createIndex()
    {
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < 1000; i++)
        {
            User user=new User();
            user.setName("name for "+i);
            user.setAge(i%100);
            client.prepareIndex("users","user").setSource(user.getJsonString(mapper)).execute().actionGet();
        }
    }
    
}
