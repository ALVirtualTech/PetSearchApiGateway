package ru.airlightvt.onlinerecognition.storage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@ComponentScan(value = {
        "ru.airlightvt.onlinerecognition.storage",
        "ru.airlightvt.onlinerecognition.common.queue",
        "ru.airlightvt.onlinerecognition.common.transport"
})
@EnableScheduling
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }

    @Scheduled(fixedRate = 60000)
    private void backgroundDaemon() {

    }
}
