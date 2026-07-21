package com.cognizant.stock.demo;

import com.cognizant.stock.model.Stock;
import com.cognizant.stock.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class StockQueryDemoRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StockQueryDemoRunner.class);

    private final StockService stockService;

    public StockQueryDemoRunner(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 2: Stock Query Methods ===");

        print("Facebook stocks September 2019", stockService.findFacebookStocksSeptember2019());
        print("Google close price > 1250", stockService.findGoogleCloseAbove1250());
        print("Top 3 highest volume", stockService.findTop3HighestVolume());
        print("Lowest 3 Netflix by close price", stockService.findLowest3NetflixByClosePrice());

        log.info("=== Hands-On 2 complete ===");
    }

    private void print(String label, Iterable<Stock> stocks) {
        System.out.println("-- " + label + " --");
        stocks.forEach(System.out::println);
    }
}
