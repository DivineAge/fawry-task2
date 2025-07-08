package models;
import Interfaces.IBook;

abstract class Book implements IBook {
    protected String title;
    protected int publishedYear; 
    protected double price;
    protected String ISBN;
    protected final BookType type;
    protected int quantity;
    protected String author;
    
    public Book(String title, int publishedYear, double price, String ISBN, 
                BookType type, int quantity, String author) throws Exception {
        if (title == null || title.isBlank()) 
            throw new IllegalArgumentException("Title cannot be null or empty");
        if (ISBN == null || ISBN.isBlank()) 
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        if (author == null || author.isBlank()) 
            throw new IllegalArgumentException("Author cannot be null or empty");
        if (quantity < 0) 
            throw new IllegalArgumentException("Quantity cannot be negative");
        if (price < 0) 
            throw new IllegalArgumentException("Price cannot be negative");
        if (publishedYear < 1000 || publishedYear > 2030) 
            throw new IllegalArgumentException("Invalid publication year");

        this.quantity = quantity;
        this.title = title;
        this.publishedYear = publishedYear;
        this.price = price;
        this.ISBN = ISBN;
        this.type = type;
        this.author = author;
    }
    
    @Override
    public String getISBN() { return ISBN; }
    @Override
    public String getTitle() { return title; }
    @Override
    public int getYear() { return publishedYear; }
    @Override
    public double getPrice() { return price; }
    @Override
    public String getAuthor() { return author; }
    @Override   
    public int getQuantity() { return quantity; }
    @Override
    public BookType getType() { return type; }
    @Override
    public abstract boolean isPurchasable();
    
    @Override
    public String toString() {
        return String.format("%s - %s by %s (%d) - $%.2f [%s]", 
                           ISBN, title, author, publishedYear, price, type);
    }
}