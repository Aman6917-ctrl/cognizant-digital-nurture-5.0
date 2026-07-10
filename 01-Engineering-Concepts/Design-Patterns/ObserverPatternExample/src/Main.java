public class Main {

    public static void main(String[] args) {

        StockMarket stockMarket = new StockMarket();

        Observer mobile = new MobileApp();
        Observer web = new WebApp();

        stockMarket.register(mobile);
        stockMarket.register(web);

        stockMarket.setStock("TCS", 4250.50);

    }

}