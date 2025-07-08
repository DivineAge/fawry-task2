package models;
import Interfaces.IShippable;

public class PaperBook extends Book implements IShippable {
    private int stock;
    
    public PaperBook(String title, int year, double price, String ISBN, 
                     BookType type, int quantity, String author, int stock) throws Exception {
        super(title, year, price, ISBN, type, quantity, author);
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stock = stock;
    }

    public int getStock() { return stock; }

    public void reduceStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (quantity > stock) {
            throw new RuntimeException("Quantum book store: Insufficient stock for " + title + 
                                     ". Available: " + stock + ", Requested: " + quantity);
        }
        stock -= quantity;
    }
    
    @Override
    public boolean isPurchasable() {
        return stock > 0;
    }

    @Override
    public void shipTo(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        System.out.println("Quantum book store: Preparing shipment to " + address);
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Stock: " + stock + "]";
    }
}