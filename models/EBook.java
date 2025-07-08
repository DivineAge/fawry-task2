package models;

public class EBook extends Book {
    private String fileType;
    
    public EBook(String title, int year, double price, String ISBN, 
                 BookType type, int quantity, String author, String fileType) throws Exception {
        super(title, year, price, ISBN, type, quantity, author);
        if (fileType == null || fileType.isBlank()) {
            throw new IllegalArgumentException("File type cannot be null or empty");
        }
        this.fileType = fileType;
    }
    
    @Override
    public boolean isPurchasable() {
        return true; 
    }
    
    public String getFileType() {
        return fileType;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [FileType: " + fileType + "]";
    }
}