package ru.airlightvt.onlinerecognition.advert;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ComponentScan(value = {
        "ru.airlightvt.onlinerecognition.common.queue",
        "ru.airlightvt.onlinerecognition.common.transport"
})
public class AdvertServiceApplication {
    @Value("${test}")
    private String val;

    public static void main(String[] args) {
        SpringApplication.run(AdvertServiceApplication.class);
    }

    @EventListener
    public void onContextStart(ContextStartedEvent event) {
        System.out.println(val);
    }
}
