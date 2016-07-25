package com.softuni.repository;

import com.softuni.model.Stock;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

/**
 * Created by niakoi on 27/6/16.
 */
@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock, String> {

    Stream<Stock> findByOrderByCodeAsc();
}
