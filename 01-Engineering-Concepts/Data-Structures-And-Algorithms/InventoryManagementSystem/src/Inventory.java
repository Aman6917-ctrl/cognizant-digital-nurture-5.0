import java.util.HashMap;

public class Inventory {

    private HashMap<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
        System.out.println("Product Added Successfully.");
    }

    public void updateProduct(int id, int quantity, double price) {

        if (products.containsKey(id)) {

            Product product = products.get(id);

            product.setQuantity(quantity);
            product.setPrice(price);

            System.out.println("Product Updated Successfully.");

        } else {

            System.out.println("Product Not Found.");

        }
    }

    public void deleteProduct(int id) {

        if (products.containsKey(id)) {

            products.remove(id);

            System.out.println("Product Deleted Successfully.");

        } else {

            System.out.println("Product Not Found.");

        }
    }

    public void displayProducts() {

        for (Product product : products.values()) {

            product.display();

        }
    }
}