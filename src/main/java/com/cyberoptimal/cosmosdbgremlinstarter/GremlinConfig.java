package com.cyberoptimal.cosmosdbgremlinstarter;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.util.ser.Serializers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GremlinConfig {

    @Value("${cosmosdb.host}")
    private String gremlinHost;
    @Value("${cosmosdb.key}")
    private String gremlinKey;
    @Value("${cosmosdb.port}")
    private int port;
    @Value("${cosmosdb.database}")
    private String database;
    @Value("${cosmosdb.collection}")
    private String collection;

    @Bean
    public Client gremlinClient() {
        String username = "/dbs/%s/colls/%s".formatted(database, collection);
        Cluster cluster = Cluster.build()
                .addContactPoint(gremlinHost)
                .port(port)
                .credentials(username, gremlinKey)
                .enableSsl(true)
                .serializer(Serializers.GRAPHSON_V2)
                .create();
        return cluster.connect();
    }
}
