/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;
import java.time.Year;
import java.util.Random;

/**
 * Class SetUp: Creation of the array Book with 500 elements. 
 *              This class also has the arrays of author, titles and genres
 * @author Jorge
 */
public class SetUp {
    
   // Array of authors
    private final String[] authors = {"Nicholas Sparks", "William Shakespeare", "Paulo Coelho", "Tenesee Williams", "Thomas Childers", "Samuel Becket", "Antoine Saint", "Rowling", "Homero", "Stephen King", "Bob McCabe", "William Shakespeare", "Miguel de Cervantes", "Alexandre Dumas", "Leo Tolstoy", "Oscar Wilde", "JRR Tolkien", "Vladimir NaboKov", "Osho", "Eckhartolle", "Conni Mendez", "Jules H Gilder", "Stephen King", "Stephen King"};
    
    //Array of author without repetition, because the other arrays works together. For example, author[1] is the author of the book with title[1] and its genres is genres[1]
    private final String[] authorsWithoutRepetition = {"Nicholas Sparks", "William Shakespeare", "Paulo Coelho", "Tenesee Williams", "Thomas Childers", "Samuel Becket", "Antoine Saint", "Rowling", "Homero", "Stephen King", "Bob McCabe", "Miguel de Cervantes", "Alexandre Dumas", "Leo Tolstoy", "Oscar Wilde", "JRR Tolkien", "Vladimir NaboKov", "Osho", "Eckhartolle", "Conni Mendez", "Jules H Gilder"};
    
    // Array of titles
    private final String[] titles = {"The Notebook and The Wedding", "Romeo and Juliet", "O Alquimista", "The Crystal Zoo", "The Third Reich: A History of Nazi Germany", "Happy Days", "Little Prince", "Harry Potter and the Philosopher's Stone", "La Ilíada. La Odisea", "Misery", "The Excorcist: Out of the Shadows", "Hamlet", "Adventures of Don Quixote", "The count of Montecristo", "Anna Karenina", "The picture of Dorian Gray", "The lords of the Ring", "Lolita", "Freedom", "The power of now", "4 in 1", "Basic computer programs in science and engineering", "The Institute", "Different Seassons"};

    //Array of genres
    private final String[] genres = {"Romance", "Shakespearean tragedy", "Fantasy", "Fantasy", "History", "Fantasy", "Fairytale", "Fantasy", "Tragedy", "Horror", "Horror", "Theatre", "Comedy", "Drama", "Drama", "Drama", "Fantasy", "Drama", "SelfHelp", "SelfHelp", "Metaphysic", "Computer Science", "Horror", "Horror"} ;
    
    
    Random rGen = new Random();
    
    int rNum;
    
    // method to build the arrays of book
    public Book[] buildThem () {
        
        Book[] books = new Book[500];
        
        Book book;
        
        // This loop is going to iterate n times so 
        // this method is a linear algorithm, in
        // other words the rutime grows in proportion to n
        for(int i = 1 ;i <= books.length;i++) {  
            
            // we generate a number randomly to create a book using the arrays defined at the attributes section
            rNum = rGen.nextInt(titles.length);

            book = new Book(titles[rNum], authors[rNum], 
                            genres[rNum], calculateYear(), calculateISBN());
            
            books[i-1] = book;

        }

        return books;
        
    }
   
    // we calculate a year randomly using another function of the random class
    private int calculateYear() {
        final int firstYear = 1900;
        final int lastYear = 2019;
                   
        return rGen.nextInt((lastYear - firstYear) + 1) + firstYear;

    }
    
    // we calculate a ISBN randomly using another function of the random class
    private int calculateISBN() {
        
        final int firstISBN = 10000;
        final int lastISBN = 99999;
                   
        return rGen.nextInt((lastISBN - firstISBN) + 1) + firstISBN;
                
    }

    // return the array without repition
    public String[] getAuthors() {
        return authorsWithoutRepetition;
    }
    
    
    
}