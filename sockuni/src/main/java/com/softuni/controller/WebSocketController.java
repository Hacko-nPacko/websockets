package com.softuni.controller;

import com.softuni.service.SomeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by niakoi on 29/6/16.
 */
@Controller
public class WebSocketController {

    @Autowired
    private SomeService someService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Future<Greeting> greeting(HelloMessage message) throws Exception {
        return someService.getMessage(message.getName())
                .thenApply(msg -> new Greeting(msg + " greeting"));
    }

    @Data
    private static class Greeting {

        private String content;

        Greeting(String content) {
            this.content = content;
        }

    }

    @Data
    private static class HelloMessage {
        private String name;

    }

}
