package com.huawei.wuqf.esacess;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.count.CountRequest;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ESClient implements IESclient
{
    private static Client client=null;
    
    private static ESClient esClient = new ESClient();
    
    private ESClient()
    {
    };
    
    public static ESClient getInstance(String ip, int port)
    {
        if (client == null)
        {
            client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(ip, port));
        }
        return esClient;
    }
    
    public void close()
    {
        client.close();
    }
    
    public boolean createIndex(String indexName, String typeName)
    {
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < 1000; i++)
        {
            User user = new User();
            user.setName("name for " + i);
            user.setAge(i % 100);
            client.prepareIndex(indexName, typeName).setSource(user.getJsonString(mapper)).execute().actionGet();
        }
        return true;
    }
    
    public ActionFuture<CountResponse> count(CountRequest request)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ActionFuture<GetResponse> get(GetRequest request)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ActionFuture<SearchResponse> search(SearchRequest request)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ActionFuture<UpdateResponse> update(UpdateRequest request)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ActionFuture<DeleteResponse> delete(DeleteRequest request)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
}
