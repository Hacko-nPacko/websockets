package com.softuni.config;

import com.softuni.model.Stock;
import com.softuni.model.StockValue;
import com.softuni.repository.StockRepository;
import com.softuni.repository.StockValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by niakoi on 25/7/16.
 */
@EnableScheduling
@Configuration
public class Ticker {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockValueRepository stockValueRepository;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedRate = 2_000)
    public void tick() {
        stockRepository.findAll().forEach(this::generate);
    }

    private StockValue generate(Stock stock) {
        StockValue stockValue = stockValueRepository.save(new StockValue(stock, 100 * Math.random()));
        simpMessagingTemplate.convertAndSend("/stock/" + stock.getCode(), stockValue);
        return stockValue;
    }
}
