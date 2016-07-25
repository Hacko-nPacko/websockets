package com.softuni;

import com.softuni.model.Stock;
import com.softuni.model.StockValue;
import com.softuni.repository.StockRepository;
import com.softuni.repository.StockValueRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

/**
 * Created by niakoi on 28/6/16.
 */
@Component
public class Demo implements CommandLineRunner {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockValueRepository stockValueRepository;

    @Override
    public void run(String... strings) throws Exception {
        Stock aapl = stockRepository.save(new Stock("AAPL"));
        Stock yhoo = stockRepository.save(new Stock("YHOO"));
        Stock orcl = stockRepository.save(new Stock("ORCL"));

        IntStream.rangeClosed(0, 3 * 60).forEach(i -> {
            save(aapl, DateTime.now().minusHours(3 * 60 - i));
            save(yhoo, DateTime.now().minusHours(3 * 60 - i));
            save(orcl, DateTime.now().minusHours(3 * 60 - i));
        });



        StockValue lastAAPL = stockValueRepository.findFirstByStockOrderByCreatedDateDesc(aapl);
        StockValue lastYHOO = stockValueRepository.findFirstByStockOrderByCreatedDateDesc(yhoo);
        StockValue lastORCL = stockValueRepository.findFirstByStockOrderByCreatedDateDesc(orcl);
    }

    private StockValue save(Stock stock, DateTime dateTime) {
        StockValue sv = stockValueRepository.save(new StockValue(stock, 100 * Math.random()));
        sv.setCreatedDate(dateTime);
        return stockValueRepository.save(sv);
    }
}
