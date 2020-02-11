/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

import java.lang.reflect.Field;

/**
 * Description: class book
 * @author Jorge
 */
public class Book {
    
    private String author;
    private String title;
    private String genre;
    private int year;
    private int isbn;
    
    
    // method which use the class reflective to return the attributes
    // of this class
    // it is static because we don't need to instanciate an object
    public static String[] getAttributes() {
          
        Class<?> clazz = Book.class;
        
        String[] attributes = new String[clazz.getDeclaredFields().length];
        
        int position = 0;
        
        for(Field attribute : clazz.getDeclaredFields()) {
        
            attributes[position] = attribute.getName();
            position++;
        }
        return attributes;
           
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public Book(String title, String author, String genre, int year, int isbn) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" + "author=" + author + ", title=" + title + ", genre=" + genre + ", year=" + year + ", isbn=" + isbn + '}';
    }

    
    // print the attributes of a object and paint an attribute with a color
    public String toStringColor(String attribute) {
        
        final String ANSI_PURPLE = "\u001B[35m";;
        String book;
        
        if (attribute.equalsIgnoreCase("author")) {

            book = ANSI_PURPLE + "Author: " + author + System.lineSeparator();
            book = book + "Title: " + title + System.lineSeparator();
            book = book + "Genre: " + genre + System.lineSeparator();
            book = book + "Year: " + year + System.lineSeparator();
            book = book + "ISBN: " + isbn;
            
        } else if (attribute.equalsIgnoreCase("title")) {
            
            book = "Author: " + author + System.lineSeparator();
            book = book + ANSI_PURPLE + "Title: " + title + System.lineSeparator();
            book = book + "Genre: " + genre + System.lineSeparator();
            book = book + "Year: " + year + System.lineSeparator();
            book = book + "ISBN: " + isbn;
            
        } else if (attribute.equalsIgnoreCase("genre")) {

            book = "Author: " + author + System.lineSeparator();
            book = book + "Title: " + title + System.lineSeparator();
            book = book + ANSI_PURPLE + "Genre: " + genre + System.lineSeparator();
            book = book + "Year: " + year + System.lineSeparator();
            book = book + "ISBN: " + isbn;            
            
        } else if (attribute.equalsIgnoreCase("year")) {

            book = "Author: " + author + System.lineSeparator();
            book = book + "Title: " + title + System.lineSeparator();
            book = book + "Genre: " + genre + System.lineSeparator();
            book = book + ANSI_PURPLE + "Year: " + year + System.lineSeparator();
            book = book + "ISBN: " + isbn;
            
        } else if (attribute.equalsIgnoreCase("isbn")) {

            book = "Author: " + author + System.lineSeparator();
            book = book + "Title: " + title + System.lineSeparator();
            book = book + "Genre: " + genre + System.lineSeparator();
            book = book + "Year: " + year + System.lineSeparator();
            book = book + ANSI_PURPLE + "ISBN: " + isbn;            
            
        } else {
            
            book = "The field does not exist";
                
        }
        return book;
    }
    
}
