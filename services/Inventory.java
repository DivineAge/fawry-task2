package services;

import Interfaces.IBook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.EBook;
import models.PaperBook;

public class Inventory {
    private static final Map<String, IBook> inventory = new HashMap<>();
    private static final int YEAR_THRESHOLD = 2000; // More reasonable threshold
    
    public static void addBook(IBook book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        inventory.put(book.getISBN(), book);
        System.out.println("Quantum book store: Added " + book.getType() + " - " + book.getTitle());
    }

    public static IBook findBookByISBN(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        return inventory.get(isbn);
    }
    
    public static boolean exists(String isbn) {
        return inventory.containsKey(isbn);
    }

    public static List<IBook> removeOutdatedBooks() {
        List<IBook> removedBooks = new ArrayList<>();
        inventory.entrySet().removeIf(entry -> {
            if (entry.getValue().getYear() < YEAR_THRESHOLD) {
                removedBooks.add(entry.getValue());
                System.out.println("Quantum book store: Removed outdated book - " + entry.getValue().getTitle());
                return true;
            }
            return false;
        });
        return removedBooks;
    }
    
    public static void removeBook(String isbn) throws Exception {
        if (!inventory.containsKey(isbn)) {
            throw new Exception("Quantum book store: Book not found with ISBN: " + isbn);
        }
        IBook removed = inventory.remove(isbn);
        System.out.println("Quantum book store: Removed book - " + removed.getTitle());
    }

    public static double checkout(String isbn, int quantity, String address, String email) throws Exception {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        IBook book = findBookByISBN(isbn);
        if (book == null) {
            throw new Exception("Quantum book store: Book not found with ISBN: " + isbn);
        }
        
        if (!book.isPurchasable()) {
            throw new Exception("Quantum book store: Book '" + book.getTitle() + "' is not purchasable");
        }
        
        if (book instanceof PaperBook paper) {
            if (paper.getStock() < quantity) {
                throw new Exception("Quantum book store: Insufficient stock for " + paper.getTitle() + 
                                  ". Available: " + paper.getStock() + ", Requested: " + quantity);
            }
            paper.reduceStock(quantity);
            ShippingService.ship(paper, quantity, address);
        } else if (book instanceof EBook ebook) {
            EmailService.sendEBook(ebook, email);
        }

        double totalCost = book.getPrice() * quantity;
        System.out.println("Quantum book store: Successfully processed purchase of " + quantity + 
                         " copies of '" + book.getTitle() + "' for $" + String.format("%.2f", totalCost));
        return totalCost;
    }
    
    public static void displayInventory() {
        System.out.println("Quantum book store: Current Inventory:");
        System.out.println("=====================================");
        if (inventory.isEmpty()) {
            System.out.println("No books in inventory");
        } else {
            for (IBook book : inventory.values()) {
                System.out.println(book);
            }
        }
        System.out.println("=====================================");
    }
    
    public static int getInventorySize() {
        return inventory.size();
    }
    
    public static void clearInventory() {
        inventory.clear();
        System.out.println("Quantum book store: Inventory cleared");
    }
}