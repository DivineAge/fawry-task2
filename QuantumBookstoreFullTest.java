import Interfaces.*;
import java.util.List;
import models.*;
import services.*;

public class QuantumBookstoreFullTest {
    
    public static void main(String[] args) {
        System.out.println("Quantum book store: Starting comprehensive test suite...\n");
        
        try {
            testBookCreation();
            testInventoryOperations();
            testPurchaseOperations();
;
            testOutdatedBookRemoval();
            
            System.out.println("Quantum book store: All tests completed successfully!");
            
        } catch (Exception e) {
            System.err.println("Quantum book store: Test failed with error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void testBookCreation() throws Exception {
        System.out.println("=== Testing Book Creation ===");
        
        
        PaperBook paperBook = new PaperBook("Effective Java", 2017, 45.99, "978-0134685991", 
                                          BookType.PAPERBOOK, 100, "Joshua Bloch", 50);
        System.out.println("Created PaperBook: " + paperBook);
        
        
        EBook eBook = new EBook("Clean Code", 2008, 29.99, "978-0135166307", 
                               BookType.EBOOK, 1000, "Robert Martin", "PDF");
        System.out.println("Created EBook: " + eBook);
        
        
        DemoBook demoBook = new DemoBook("Java Basics Demo", 2023, 0.0, "978-0000000001", 
                                       BookType.DEMOBOOK, 1, "Demo Author");
        System.out.println("Created DemoBook: " + demoBook);
        
        
        assert paperBook.isPurchasable() == true : "PaperBook should be purchasable";
        assert eBook.isPurchasable() == true : "EBook should be purchasable";
        assert demoBook.isPurchasable() == false : "DemoBook should not be purchasable";
        
        System.out.println("All book creation tests passed!\n");
    }
    
    public static void testInventoryOperations() throws Exception {
        System.out.println("=== Testing Inventory Operations ===");
        
        
        Inventory.clearInventory();
        
        
        PaperBook book1 = new PaperBook("Design Patterns", 2004, 39.99, "978-0596007126", 
                                      BookType.PAPERBOOK, 50, "Gang of Four", 25);
        EBook book2 = new EBook("Spring in Action", 2020, 34.99, "978-1617294945", 
                               BookType.EBOOK, 200, "Craig Walls", "EPUB");
        DemoBook book3 = new DemoBook("Programming Preview", 2024, 0.0, "978-0000000002", 
                                    BookType.DEMOBOOK, 1, "Preview Author");
        
        Inventory.addBook(book1);
        Inventory.addBook(book2);
        Inventory.addBook(book3);
        
        
        assert Inventory.getInventorySize() == 3 : "Inventory should have 3 books";
        
        
        IBook found = Inventory.findBookByISBN("978-0596007126");
        assert found != null : "Should find book by ISBN";
        assert found.getTitle().equals("Design Patterns") : "Found book should have correct title";
        
        
        assert Inventory.exists("978-1617294945") == true : "Book should exist";
        assert Inventory.exists("978-9999999999") == false : "Non-existent book should not exist";
        
        
        Inventory.displayInventory();
        
        
        Inventory.removeBook("978-0000000002");
        assert Inventory.getInventorySize() == 2 : "Inventory should have 2 books after removal";
        
        System.out.println("✓ All inventory operations tests passed!\n");
    }
    
    public static void testPurchaseOperations() throws Exception {
        System.out.println("=== Testing Purchase Operations ===");
        
        
        double cost1 = Inventory.checkout("978-0596007126", 2, "123 Main St, City, State", "test@email.com");
        assert cost1 == 79.98 : "Cost should be 39.99 * 2 = 79.98";
        
        
        PaperBook paperBook = (PaperBook) Inventory.findBookByISBN("978-0596007126");
        assert paperBook.getStock() == 23 : "Stock should be reduced to 23";
        
        
        double cost2 = Inventory.checkout("978-1617294945", 1, "", "customer@email.com");
        assert cost2 == 34.99 : "EBook cost should be 34.99";
        
        
        double cost3 = Inventory.checkout("978-1617294945", 5, "", "bulk@email.com");
        assert cost3 == 174.95 : "Multiple EBook cost should be 34.99 * 5 = 174.95";
        
        System.out.println("✓ All purchase operations tests passed!\n");
    }
    
     
    
    public static void testOutdatedBookRemoval() throws Exception {
        System.out.println("=== Testing Outdated Book Removal ===");
        
        
        PaperBook oldBook1 = new PaperBook("Old Java", 1995, 19.99, "978-0000000008", 
                                         BookType.PAPERBOOK, 10, "Old Author", 5);
        EBook oldBook2 = new EBook("Ancient Programming", 1990, 9.99, "978-0000000009", 
                                  BookType.EBOOK, 50, "Ancient Author", "PDF");
        PaperBook newBook = new PaperBook("Modern Java", 2023, 49.99, "978-0000000010", 
                                        BookType.PAPERBOOK, 20, "Modern Author", 15);
        
        Inventory.addBook(oldBook1);
        Inventory.addBook(oldBook2);
        Inventory.addBook(newBook);
        
        int sizeBefore = Inventory.getInventorySize();
        System.out.println("Inventory size before removal: " + sizeBefore);
        
        
        List<IBook> removedBooks = Inventory.removeOutdatedBooks();
        
        int sizeAfter = Inventory.getInventorySize();
        System.out.println("Inventory size after removal: " + sizeAfter);
        System.out.println("Number of books removed: " + removedBooks.size());
        
        
        assert !Inventory.exists("978-0000000008") : "Old book should be removed";
        assert !Inventory.exists("978-0000000009") : "Old book should be removed";
        assert Inventory.exists("978-0000000010") : "New book should remain";
        
        System.out.println("Outdated book removal tests passed!\n");
    }
}
