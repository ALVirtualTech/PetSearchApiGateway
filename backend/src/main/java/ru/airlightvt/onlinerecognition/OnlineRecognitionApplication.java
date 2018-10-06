package ru.airlightvt.onlinerecognition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.airlightvt.onlinerecognition.queue.MessagePublisher;
import ru.airlightvt.onlinerecognition.queue.model.QueueMessage;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@EnableAsync
public class OnlineRecognitionApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(OnlineRecognitionApplication.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(OnlineRecognitionApplication.class, args);

        MessagePublisher messagePublisher = (MessagePublisher) ctx.getBean("redisPublisher");
        CountDownLatch latch = ctx.getBean(CountDownLatch.class);

        LOGGER.info("Sending message...");
        messagePublisher.publish(new QueueMessage(1000L, new byte[1], 101));

        //latch.await();

        //System.exit(0);
    }
}
