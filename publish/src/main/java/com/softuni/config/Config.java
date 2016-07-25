package com.softuni.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by niakoi on 29/6/16.
 */
@Configuration
@EnableSpringDataWebSupport
@EnableJpaAuditing
public class Config {

    @Bean
    public Executor executor() {
        return new ThreadPoolTaskExecutor();
    }
}
