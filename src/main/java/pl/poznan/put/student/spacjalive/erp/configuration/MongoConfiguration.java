package pl.poznan.put.student.spacjalive.erp.configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("pl.poznan.put.student.spacjalive.erp.mongo")
public class MongoConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public MongoDatabase mongodbDataBaseBean() {
        MongoClient mongoClient = new MongoClient(env.getProperty("mongodb.host"), Integer.valueOf(env.getProperty("mongodb.port")));
        MongoDatabase database = mongoClient.getDatabase(env.getProperty("mongodb.database"));

        return database;
    }


}
