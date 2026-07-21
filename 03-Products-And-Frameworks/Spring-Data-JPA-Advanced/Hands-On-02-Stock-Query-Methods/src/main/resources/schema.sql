CREATE TABLE IF NOT EXISTS stock (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    symbol VARCHAR(10) NOT NULL,
    trade_date DATE NOT NULL,
    open_price DECIMAL(12, 4) NOT NULL,
    close_price DECIMAL(12, 4) NOT NULL,
    high_price DECIMAL(12, 4) NOT NULL,
    low_price DECIMAL(12, 4) NOT NULL,
    volume BIGINT NOT NULL,
    CONSTRAINT uk_stock_symbol_date UNIQUE (symbol, trade_date)
);
