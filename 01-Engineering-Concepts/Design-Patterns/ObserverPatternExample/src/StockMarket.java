import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {

    private List<Observer> observers = new ArrayList<>();

    private String stockName;
    private double price;

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observers) {
            observer.update(stockName, price);
        }

    }

    public void setStock(String stockName, double price) {

        this.stockName = stockName;
        this.price = price;

        notifyObservers();

    }

}