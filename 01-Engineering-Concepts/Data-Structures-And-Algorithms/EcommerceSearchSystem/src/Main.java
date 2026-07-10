public class Main {

    public static void main(String[] args) {

        Product[] products = {

            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Mouse", "Accessories"),
            new Product(103, "Keyboard", "Accessories"),
            new Product(104, "Monitor", "Electronics")

        };

        System.out.println("Linear Search Result:");

        Product p1 = Search.linearSearch(products, 103);

        if (p1 != null)
            p1.display();
        else
            System.out.println("Product Not Found");

        System.out.println();

        System.out.println("Binary Search Result:");

        Product p2 = Search.binarySearch(products, 104);

        if (p2 != null)
            p2.display();
        else
            System.out.println("Product Not Found");

    }
}