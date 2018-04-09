package com.adidas.elasticsearch;

import com.adidas.elasticsearch.util.KeyValue;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class ClientElastic {

    //private static final String ES_URI = "localhost";
    private static final int ES_PORT = 9300;

    TransportClient client;

    public ClientElastic() {

    }

    public ClientElastic(TransportClient client) {
        this.client = client;
    }

    /**
     * @return
     */
    public Settings getSettings() {
        return Settings.builder()
                .put("cluster.name", KeyValue.getDataProperty("URL_ELASTICSEARCH")).build();
    }

    /**
     * @param settings
     * @return
     * @throws UnknownHostException
     */
    public TransportClient getTransportClient(Settings settings) throws UnknownHostException {
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(KeyValue.getDataProperty("URL_ELASTICSEARCH")), ES_PORT));
        return client;
    }

    public TransportClient getTransportClient() throws UnknownHostException {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(KeyValue.getDataProperty("URL_ELASTICSEARCH")), ES_PORT));
        return client;
    }

    public void closeClient() {
        client.close();
    }

//    public static void main(String[] args) throws UnknownHostException {
//
//
//        //Index document
//        //The following example indexes a JSON document into an index called twitter, under a type called tweet
//        CreateService create = new CreateService(client);
//        IndexResponse response = create.create("test_diegmer", "tweet", mapJson);
//
//        SearchService search = new SearchService(client);
//        SearchRequestBuilder r = search.searchMatchQuery("tweet", "diegmer");
//
//        //Report
//        // Index name
//        String _index = response.getIndex();
//        // Type name
//        String _type = response.getType();
//        //Document ID (generated or not)
//        String _id = response.getId();
//        //Version (if it's the first time you index this document, you will get: 1)
//        long _version = response.getVersion();
//        //status has stored current instance statement.
//        RestStatus status = response.status();
//
//        //The get API allows to get a typed JSON document from the index based on its id
//        //The following example gets a JSON document from an index called twitter, under a type called tweet, with id valued 1:
//        GetResponse response2 = client.prepareGet("twitter", "tweet", "1").get();
//
//        DeleteService deleteService = new DeleteService(client);
//        deleteService.deleteAllIndices();
//

    //@before
//        client.close();
//
//    }

}
