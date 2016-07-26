package com.softuni.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softuni.model.Stock;
import com.softuni.model.StockValue;
import com.softuni.repository.StockRepository;
import com.softuni.repository.StockValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;

/**
 * Created by niakoi on 26/7/16.
 */
public class RedisMessageListener implements MessageListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockValueRepository stockValueRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void onMessage(final Message message, final byte[] pattern) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message.getBody());
            String code = jsonNode.findValue("code").asText();
            Double value = jsonNode.findValue("value").asDouble();

            generate(code, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("message = " + message);
    }

    private StockValue generate(String code, Double value) {
        Stock stock = stockRepository.findOne(code);
        StockValue stockValue = stockValueRepository.save(new StockValue(stock, value));
        simpMessagingTemplate.convertAndSend("/stock/" + stock.getCode(), stockValue);
        return stockValue;
    }
}
