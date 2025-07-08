package models;

public class DemoBook extends Book {
    
    public DemoBook(String title, int year, double price, String ISBN, 
                    BookType type, int quantity, String author) throws Exception {
        super(title, year, price, ISBN, type, quantity, author);
    }
    
    @Override
    public boolean isPurchasable() {
        return false; 
    }
    
    @Override
    public String toString() {
        return super.toString() + " [DEMO - NOT FOR SALE]";
    }
}