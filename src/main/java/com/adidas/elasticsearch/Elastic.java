package com.adidas.elasticsearch;

import com.adidas.elasticsearch.service.CreateService;
import com.adidas.elasticsearch.service.SearchService;
import com.adidas.elasticsearch.util.KeyValue;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Elastic {

    //private static final String ES_URI = "localhost";
    private static final int ES_PORT = 9300;

    public static void main(String[] args) throws UnknownHostException {

        //Note that you have to set the cluster name if you use one different than "elasticsearch":
        Settings settings = Settings.builder()
                .put("cluster.name", "myClusterName").build();
        //TransportClient client = new PreBuiltTransportClient(settings);


        //You can start locally a Coordinating Only Node and then simply create a TransportClient in your application which connects to this Coordinating Only Node
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(KeyValue.getUrlProperty("URL_ELASTICSEARCH")), ES_PORT));


        //Manually (aka do it yourself) using native byte[] or as a String
        String json = "{" +
                "\"user\":\"diegmer\"," +
                "\"postDate\":\"2018-04-01\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";


        //Using Map
        Map<String, Object> mapJson = new HashMap<String, Object>();
        mapJson.put("user", "diegmer");
        //mapJson.put("postDate", new Date());
        mapJson.put("postDate", new Date().getTime());
        mapJson.put("message", "trying out Elasticsearch");

        //Index document
        //The following example indexes a JSON document into an index called twitter, under a type called tweet
        CreateService create = new CreateService(client);
        IndexResponse response = create.create("test_diegmer", "tweet", mapJson);

        SearchService search = new SearchService(client);
        SearchRequestBuilder r = search.searchMatchQuery("tweet", "diegmer");

        //Report
        // Index name
        String _index = response.getIndex();
        // Type name
        String _type = response.getType();
        //Document ID (generated or not)
        String _id = response.getId();
        //Version (if it's the first time you index this document, you will get: 1)
        long _version = response.getVersion();
        //status has stored current instance statement.
        RestStatus status = response.status();

        //The get API allows to get a typed JSON document from the index based on its id
        //The following example gets a JSON document from an index called twitter, under a type called tweet, with id valued 1:
        GetResponse response2 = client.prepareGet("twitter", "tweet", "1").get();

        client.close();

    }

}
