package services;
import models.EBook;

class EmailService {
    public static void sendEBook(EBook book, String email) {
        System.out.println("Quantum book store: Sending EBook '" + book.getTitle() + 
                         "' (" + book.getFileType() + ") to email: " + email);
    }
}