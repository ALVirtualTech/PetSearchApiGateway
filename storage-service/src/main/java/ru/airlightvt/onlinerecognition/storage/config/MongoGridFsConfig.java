package ru.airlightvt.onlinerecognition.storage.config;

import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
@EnableAutoConfiguration
@ComponentScan({
        "ru.airlightvt.onlinerecognition.storage.repository",
        "ru.airlightvt.onlinerecognition.storage.service"
})
public class MongoGridFsConfig  extends AbstractMongoConfiguration {
    @Bean
    public GridFsTemplate gridFsTemplate(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter) throws Exception {
        return new GridFsTemplate(mongoDbFactory, mappingMongoConverter);
    }

    @Override
    public MongoClient mongoClient() {
        return null;
    }

    @Override
    protected String getDatabaseName() {
        return null;
    }
}
