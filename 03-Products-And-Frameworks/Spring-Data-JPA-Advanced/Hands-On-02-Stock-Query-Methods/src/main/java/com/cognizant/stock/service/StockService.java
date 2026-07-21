package com.cognizant.stock.service;

import com.cognizant.stock.model.Stock;
import com.cognizant.stock.repository.StockRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private static final Logger log = LoggerFactory.getLogger(StockService.class);

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional(readOnly = true)
    public List<Stock> findFacebookStocksSeptember2019() {
        LocalDate start = LocalDate.of(2019, 9, 1);
        LocalDate end = LocalDate.of(2019, 9, 30);
        log.info("Query: findBySymbolAndTradeDateBetween('FB', {}, {})", start, end);
        return stockRepository.findBySymbolAndTradeDateBetween("FB", start, end);
    }

    @Transactional(readOnly = true)
    public List<Stock> findGoogleCloseAbove1250() {
        BigDecimal threshold = new BigDecimal("1250");
        log.info("Query: findBySymbolAndClosePriceGreaterThan('GOOG', {})", threshold);
        return stockRepository.findBySymbolAndClosePriceGreaterThan("GOOG", threshold);
    }

    @Transactional(readOnly = true)
    public List<Stock> findTop3HighestVolume() {
        log.info("Query: findTop3ByOrderByVolumeDesc()");
        return stockRepository.findTop3ByOrderByVolumeDesc();
    }

    @Transactional(readOnly = true)
    public List<Stock> findLowest3NetflixByClosePrice() {
        log.info("Query: findTop3BySymbolOrderByClosePriceAsc('NFLX')");
        return stockRepository.findTop3BySymbolOrderByClosePriceAsc("NFLX");
    }
}
