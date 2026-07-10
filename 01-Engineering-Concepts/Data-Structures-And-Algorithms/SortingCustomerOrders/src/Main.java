public class Main {

    public static void main(String[] args) {

        Order[] orders = {

                new Order(101, "Aman", 4500),
                new Order(102, "Rahul", 2500),
                new Order(103, "Priya", 6500),
                new Order(104, "Neha", 3500)

        };

        System.out.println("Original Orders\n");

        for (Order order : orders) {
            order.display();
        }

        BubbleSort.sort(orders);

        System.out.println("\nAfter Bubble Sort\n");

        for (Order order : orders) {
            order.display();
        }

        Order[] orders2 = {

                new Order(101, "Aman", 4500),
                new Order(102, "Rahul", 2500),
                new Order(103, "Priya", 6500),
                new Order(104, "Neha", 3500)

        };

        QuickSort.sort(orders2, 0, orders2.length - 1);

        System.out.println("\nAfter Quick Sort\n");

        for (Order order : orders2) {
            order.display();
        }
    }
}