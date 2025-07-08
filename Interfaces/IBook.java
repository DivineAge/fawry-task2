package Interfaces;

import models.BookType;

public interface IBook {
    String getISBN();
    String getTitle();
    int getYear();
    double getPrice();
    String getAuthor();
    int getQuantity();
    BookType getType();
    boolean isPurchasable();
}
