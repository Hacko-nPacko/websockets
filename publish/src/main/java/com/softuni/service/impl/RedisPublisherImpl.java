package com.softuni.service.impl;

import com.softuni.service.RedisPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.ModelMap;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by niakoi on 26/7/16.
 */
public class RedisPublisherImpl implements RedisPublisher {

    @Value("${stock.code}")
    private String code;

    @Autowired
    private RedisTemplate<String, Object> template;

    @Autowired
    private ChannelTopic topic;

    @Scheduled(fixedDelay = 1_000)
    public void publish() {
        ModelMap msg = new ModelMap();
        msg.addAttribute("code", this.code);
        msg.addAttribute("value", 100 * Math.random());
        template.convertAndSend(topic.getTopic(), msg);
    }
}
