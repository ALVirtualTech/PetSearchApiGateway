package ru.airlightvt.onlinerecognition.api_gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@ComponentScan(value = {
        "ru.airlightvt.onlinerecognition.api_gateway",
        "ru.airlightvt.onlinerecognition.common.transport"
})
@EnableAsync
public class ApiGatewayApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiGatewayApplication.class);

    public static void main(String[] args) {
        ApplicationContext ctx = new SpringApplicationBuilder(ApiGatewayApplication.class)
                .web(WebApplicationType.SERVLET).run(args);
    }
}
