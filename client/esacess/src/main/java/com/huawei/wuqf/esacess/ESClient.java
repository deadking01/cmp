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
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;


public class ESClient implements IESclient {

    private TransportClient client;

    public static void main(String[] args) throws IOException {
        ESClient esClient = new ESClient();
        esClient.init("localhost", 9300);
        esClient.createIndex("users", "user");
        esClient.close();
    }

    public void init(String ip, int port) {
        client = TransportClient.builder()
                .addTransportAddress(new InetSocketTransportAddress(ip, port));

    }

    public void close() {
        client.close();
    }

    public boolean createIndex(String indexName, String typeName) throws IOException {
        for (int i = 0; i < 1000; i++) {
            User user;
            user = new User();
            user.setName("name for " + i);
            user.setAge(i % 100);
            client.prepareIndex(indexName, typeName).setSource(generateJson(user)).execute().actionGet();
        }
        return true;
    }

    public ActionFuture<CountResponse> count(CountRequest request) {
        return null;
    }

    public ActionFuture<GetResponse> get(GetRequest request) {
        return null;
    }

    public ActionFuture<SearchResponse> search(SearchRequest request) {
        return null;
    }

    public ActionFuture<UpdateResponse> update(UpdateRequest request) {
        return null;
    }

    public ActionFuture<DeleteResponse> delete(DeleteRequest request) {
        return null;
    }

    private String generateJson(User user) throws IOException {
        String json = "";
        XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject();
        contentBuilder.field("id", user.getId());
        contentBuilder.field("age", user.getAge());
        contentBuilder.field("name", user.getName());
        return json;
    }

}
