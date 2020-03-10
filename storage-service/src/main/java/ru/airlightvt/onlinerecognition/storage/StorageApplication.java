package ru.airlightvt.onlinerecognition.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {
        "ru.airlightvt.onlinerecognition.storage",
        "ru.airlightvt.onlinerecognition.common.queue",
        "ru.airlightvt.onlinerecognition.common.transport"
})
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }
}
