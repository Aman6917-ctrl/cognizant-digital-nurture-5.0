import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        Book[] books = {

                new Book(101, "Java", "James Gosling"),
                new Book(102, "Python", "Guido van Rossum"),
                new Book(103, "C", "Dennis Ritchie"),
                new Book(104, "Data Structures", "Mark Allen Weiss")

        };

        System.out.println("Linear Search Result:");

        Book result1 = Search.linearSearch(books, "Python");

        if (result1 != null)
            result1.display();

        Arrays.sort(books, Comparator.comparing(book -> book.title));

        System.out.println("\nBinary Search Result:");

        Book result2 = Search.binarySearch(books, "Java");

        if (result2 != null)
            result2.display();
    }
}