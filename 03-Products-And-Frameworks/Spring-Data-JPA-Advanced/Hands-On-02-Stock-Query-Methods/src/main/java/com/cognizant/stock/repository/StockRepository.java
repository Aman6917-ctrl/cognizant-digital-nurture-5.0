package com.cognizant.stock.repository;

import com.cognizant.stock.model.Stock;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findBySymbolAndTradeDateBetween(String symbol, LocalDate start, LocalDate end);

    List<Stock> findBySymbolAndClosePriceGreaterThan(String symbol, BigDecimal closePrice);

    List<Stock> findTop3ByOrderByVolumeDesc();

    List<Stock> findTop3BySymbolOrderByClosePriceAsc(String symbol);
}
