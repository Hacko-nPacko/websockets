package com.softuni.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.softuni.model.Stock;
import com.softuni.model.StockValue;
import com.softuni.model.view.StockValueView;
import com.softuni.repository.StockRepository;
import com.softuni.repository.StockValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by niakoi on 29/6/16.
 */
@Controller
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockValueRepository stockValueRepository;

    @RequestMapping("/stock")
    public void stock(Model model) {
        model.addAttribute(stockRepository.findAll());
    }

    @JsonView(StockValueView.Summary.class)
    @MessageMapping("/listen")
    public List<StockValue> listen() {
        return stockRepository.findByOrderByCodeAsc().map(stockValueRepository::findFirstByStockOrderByCreatedDateDesc).collect(Collectors.toList());
    }
}
