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
    //private static final int ES_PORT = 9300;

    //The TransportClient connects remotely to an Elasticsearch cluster using the transport module
    TransportClient client;

    public ClientElastic() {
    }

    public ClientElastic(TransportClient client) {
        this.client = client;
    }

    /**
     * Set specific settings to elasticsearch client
     * Note that you have to set the cluster name if you use one different than "elasticsearch"
     *
     * @return
     */
    public Settings getSettings() {
        return Settings.builder()
                .put("cluster.name", KeyValue.getDataProperty("URL_ELASTICSEARCH")).build();
    }

    /**
     * Gets one or more initial transport addresses and communicates with them in round robin fashion on each action
     *
     * @param settings -> Specific settings
     * @return TransportClient
     * @throws UnknownHostException
     */
    public TransportClient getTransportClient(Settings settings) throws UnknownHostException {
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(KeyValue.getDataProperty("URL_ELASTICSEARCH")), Integer.valueOf(KeyValue.getDataProperty("PORT_ELASTICSEARCH"))));
        return client;
    }

    /**
     * Gets one or more initial transport addresses and communicates with them in round robin fashion on each action
     * Get instance TransportClient with empty settings
     *
     * @return TransportClient
     * @throws UnknownHostException
     */
    public TransportClient getTransportClient() throws UnknownHostException {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(KeyValue.getDataProperty("URL_ELASTICSEARCH")), Integer.valueOf(KeyValue.getDataProperty("PORT_ELASTICSEARCH"))));
        return client;
    }

    /**
     * Closes the client.
     */
    public void closeClient() {
        client.close();
    }

}
