package com.softuni.repository;

import com.softuni.model.Stock;
import com.softuni.model.StockValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by niakoi on 25/7/16.
 */
@Repository
public interface StockValueRepository extends PagingAndSortingRepository<StockValue, Long> {

    StockValue findFirstByStockOrderByCreatedDateDesc(Stock stock);
    Page<StockValue> findFirstByOrderByCreatedDateDesc(Pageable p);
}
