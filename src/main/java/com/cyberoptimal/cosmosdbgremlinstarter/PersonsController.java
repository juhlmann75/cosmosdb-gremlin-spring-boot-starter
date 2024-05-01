package com.cyberoptimal.cosmosdbgremlinstarter;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonsController {

    private final Client client;

    public PersonsController(Client client) {
        this.client = client;
    }

    @GetMapping("/{name}")
    public String getPerson(@PathVariable("name") String name) {
        ResultSet results = client.submit("g.V('%s')".formatted(name));
        Result result = results.one();
        return result.getString();
    }
}
