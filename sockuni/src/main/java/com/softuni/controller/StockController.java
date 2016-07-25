package com.softuni.controller;

import com.softuni.model.Stock;
import com.softuni.model.StockValue;
import com.softuni.repository.StockValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by niakoi on 29/6/16.
 */
@Controller
public class StockController {

    @Autowired
    private StockValueRepository stockValueRepository;

    @RequestMapping
    public void stock() {
    }

    @SubscribeMapping("/listen/{stock}")
    public StockValue listen(@DestinationVariable Stock stock) {
        return stockValueRepository.findFirstByStockOrderByCreatedDateDesc(stock);
    }
}
