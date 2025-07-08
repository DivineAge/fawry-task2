import Interfaces.*;
import java.util.List;
import models.*;
import services.*;

public class QuantumBookstoreFullTest {

    public static void main(String[] args) throws Exception {
        System.out.println("Quantum book store: Running tests...\n");

        testBookCreation();
        testInventory();
        testPurchase();
        testOutdatedRemoval();

        System.out.println("\nQuantum book store: Tests done.");
    }

    public static void testBookCreation() throws Exception {
        System.out.println("Book Creation:");
        PaperBook book1 = new PaperBook("Book1", 2020, 40.0, "ISBN1", BookType.PAPERBOOK, 10, "Author1", 2);
        EBook book2 = new EBook("Book2", 2022, 30.0, "ISBN2", BookType.EBOOK, 100, "Author2", "PDF");
        DemoBook book3 = new DemoBook("Book3", 2024, 0.0, "ISBN3", BookType.DEMOBOOK, 1, "Author3");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);
        System.out.println();
    }

    public static void testInventory() throws Exception {
        System.out.println("Inventory:");
        Inventory.clearInventory();

        PaperBook book1 = new PaperBook("Book1", 2018, 50.0, "ISBN4", BookType.PAPERBOOK, 5, "AuthorA", 3);
        EBook book2 = new EBook("Book2", 2021, 20.0, "ISBN5", BookType.EBOOK, 50, "AuthorB", "EPUB");
        DemoBook book3 = new DemoBook("Book3", 2023, 0.0, "ISBN6", BookType.DEMOBOOK, 1, "AuthorC");

        Inventory.addBook(book1);
        Inventory.addBook(book2);
        Inventory.addBook(book3);

        Inventory.displayInventory();

        Inventory.removeBook("ISBN6");

        System.out.println();
    }

    public static void testPurchase() throws Exception {
        System.out.println("Purchase:");
        Inventory.checkout("ISBN4", 2, "Some Street", "a@email.com");
        Inventory.checkout("ISBN5", 1, "", "b@email.com");
        System.out.println();
    }

    public static void testOutdatedRemoval() throws Exception {
        System.out.println("Outdated Book Removal:");
        PaperBook book1 = new PaperBook("OldBook1", 1990, 15.0, "ISBN7", BookType.PAPERBOOK, 3, "OldAuthor1", 1);
        EBook book2 = new EBook("OldBook2", 1985, 10.0, "ISBN8", BookType.EBOOK, 10, "OldAuthor2", "PDF");
        PaperBook book3 = new PaperBook("NewBook", 2023, 45.0, "ISBN9", BookType.PAPERBOOK, 4, "NewAuthor", 2);

        Inventory.addBook(book1);
        Inventory.addBook(book2);
        Inventory.addBook(book3);

        List<IBook> removed = Inventory.removeOutdatedBooks();
        System.out.println("Removed " + removed.size() + " outdated books.");
        System.out.println();
    }
}
