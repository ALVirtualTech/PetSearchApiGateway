package ru.airlightvt.onlinerecognition.api_gateway.rest;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqTestController {
    private AmqpTemplate template;

    public MqTestController(AmqpTemplate template) {
        this.template = template;
    }

    @RequestMapping("/emit")
    @ResponseBody
    String queue1() {
        //logger.info("Emit to queue1");
        template.convertAndSend("queue1","Message to queue");
        return "Emit to queue";
    }
}
