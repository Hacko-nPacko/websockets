package com.softuni.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by niakoi on 27/6/16.
 */
@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    public String home() {
        return "redirect:/stock";
    }
}
