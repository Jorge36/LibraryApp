/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Description: Main class which has the mainmethod and perform all the operations
 * using the other classes
 * @author Jorge
 */
public class LibraryApp {

        // Global Variables declaration
        private Menu menu;
        private Logic logic;
        private Output output;
        private SetUp setup;
        
        // message to print in menu
        private final String msgWelcome = "**** Welcome to Cinema CCT *****";
        private final String msgFirstQuestion = "What would you like to do?";
        private final String[] msgOptions = {"[1] Sort all the books", "[2] Sort all the books of an Author", "[3] Search by Author or Title" ,"[4] Quit the program"};
        private final String msgChooseAuthor = "What author would you like to choose? ";
        private final String msgChoosebyFieldSort = "What field would you like to sort by";
        private final String msgChoosebyFieldSearch = "What field would you like to search by";
        private final String msgEnterTitle = "Could you enter the title? ";
        private final String msgEnterAuthor = "Could you enter the author? ";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // instance of an object
        new LibraryApp();
        
        
    }
    
    public LibraryApp() {
        
        // definition of the global variables
        menu = new Menu();
        logic = new Logic();
        output = new Output();
        setup = new SetUp();
        
        // bring the books generated from setup
        Book[] books = setup.buildThem();
        // create another structure with books with this one is sorted by author
        // to amortize the efficiency of this program
        // in other words we sort this array only once and we reuse to search by author
        Book[] booksSortedByAuthor = getBooksSortedByAuthor(books);
        // contain the attributes to sort title, author, year and genre
        String[] attributesToSort = getAttributes("sort");
        // contain the attributes to search author and title
        String[] attributesToSearch = getAttributes("search");
        // option selected from the main menu by the user 
        int choice;
        // choiceField field chosen to sort or search
        String choiceField;
        // books result for author
        Book[] booksByAuthor;
        // books results genral
        Book[] booksFound = null;
        
        while(true) {
            // call from menu to get the answer from the user
            choice = menu.mainMenu(msgWelcome, msgFirstQuestion, msgOptions);
            
            switch(choice) {
                        // return the name of the field chosen and the book sorted
                case 1: choiceField = optionFieldAndSort(attributesToSort, books);
                        if (choiceField == null)
                            continue;
                        // print the books
                        output.printBooks(books, choiceField); 
                        break;
                        // return the books of an author
                case 2: booksByAuthor = optionAuthor(booksSortedByAuthor);
                        if (booksByAuthor == null) {
                            output.printNoBookFound();
                            continue;
                        }    
                         // return the name of the field chosen and the book sorted
                        choiceField = optionFieldAndSort(attributesToSort, booksByAuthor);
                        if (choiceField == null)
                            continue;
                         // print the books
                        output.printBooks(booksByAuthor, choiceField); 
                        break;
                        // return the name of the field chosen to search
                case 3: choiceField = optionSearch(attributesToSearch);
                        if (choiceField == null) 
                            continue;    
                        // return the book/s found by title or author
                        //if the user chose title, only one book return
                        // if the user chose author, the method could return more than once
                        booksFound = SearchBy(choiceField, books, booksSortedByAuthor);
                        if (booksFound == null) {
                            output.printNoBookFound();
                            continue;
                        }
                        // print the books
                        output.printBooks(booksFound, choiceField); 
                        break;
                        // program finished
                case 4: output.printProgramFinished();
                        // close the scanner
                        menu.CloseScanner(); // close scanner, there is a attribute called in of type scanner
                                             // which is used by view to interact with the user
                        return;
                
            }
                    
        }
      
    }
    
    
    // get the attriutes from Book class to sort and search
    private String[] getAttributes(String function) {
        
        String[] attributes = Book.getAttributes();
        ArrayList<String> attributesAux = new ArrayList<>();
        
        switch(function) {
           
           case "sort": 
                        for(String attribute: attributes)
                             if (!attribute.equalsIgnoreCase("author"))
                                attributesAux.add(attribute);
                        break;
           case "search": 
                        for(String attribute: attributes)
                            if (!attribute.equalsIgnoreCase("genre") && !attribute.equalsIgnoreCase("year") && !attribute.equalsIgnoreCase("isbn"))
                                attributesAux.add(attribute);
           
        }
        
        return attributesAux.toArray(new String[attributesAux.size()]);        
    }
    
    // // return the name of the field chosen and the book sorted (title, genre, year, etc)
    private String optionFieldAndSort(String[] attributesToSort, Book[] books) {
               
       String choice = menu.chooseOptionNumber(attributesToSort, msgChoosebyFieldSort);
       if (choice == null)
           return null;
       
       switch(choice) {
           
           case "title": 
                        logic.sortbyTitle(books);
                        break;
           case "genre": 
                        logic.sortbyGenre(books);
                        break;
           case "year": 
                        logic.sortbyYear(books);
                        break;
           case "isbn": 
                        logic.sortbyIsbn(books);
                        break;
           default:     
                        output.printNotFunctionality(choice);
                        return null;
                     
       }
       return choice;
    }
    
    // return the books of an author using the binary search, O(log 2) 
    private Book[] optionAuthor(Book[] booksSortByAuthor) {
        
        String choice = menu.chooseOptionNumber(setup.getAuthors(), msgChooseAuthor);
        if (choice == null)
           return null;
        
        ArrayList<Book> author = new ArrayList<>();
        Book keyBook = new Book("", choice, "", 0, 0);
        
        
        int index = logic.binarySearch(booksSortByAuthor, new AuthorCompare(), keyBook);
        
        if (index >= 0) { // how the binary search always find the first position of repeated elemented 
                          // we loop the array from that position to check if there are more books from the same author
            for (int i = index; i <= booksSortByAuthor.length - 1; i++) {
                // the author of next book is different from the current one
                if (!keyBook.getAuthor().equalsIgnoreCase(booksSortByAuthor[i].getAuthor()))
                    break;
                
                author.add(booksSortByAuthor[i]);
               
            }
            
            
            return author.toArray(new Book[author.size()]);
        }
        
        return null;
        
    }
    
    // return an array sorted by author
    private Book[] getBooksSortedByAuthor(Book[] books) {
        
        Book[] booksSortByAuthor = books.clone();
        
        Arrays.sort(booksSortByAuthor, new AuthorCompare());
        return booksSortByAuthor;
        
    }
    
    // get the option (title or author)
    private String optionSearch(String[] attributesToSerch) {
        
        String choice = menu.chooseOptionNumber(attributesToSerch, msgChoosebyFieldSearch);
        return choice;
        
    }
    // perform search by author or title
    private Book[] SearchBy(String attribute, Book[] books, Book[] booksSortedByAuthor) {

        Book keyBook;
        Book book;
        String choice;
        Book[] booksFound;
        
        switch(attribute) {
           
           case "title":
                        // return the title
                        choice = menu.typeString(msgEnterTitle);
                        if (choice == null)
                            return null;
                        // create a new book
                        keyBook = new Book(choice, "", "", 0, 0);
                        // sort the array
                        Arrays.sort(books, new TitleCompare());
                        // search by title
                        book = logic.searchByTitle(books, keyBook);
                        if (book == null)
                            return null;
                        // return the book 
                        booksFound = new Book[1];
                        booksFound[0] = book;
                        return booksFound;
           case "author": 
                        // return the author
                        choice = menu.typeString(msgEnterAuthor);
                        if (choice == null)
                            return null;
                        // create the key book
                        keyBook = new Book("", choice, "", 0, 0);
                        // search by author
                        return logic.searchByAuthor(booksSortedByAuthor, keyBook);
           default: output.printNotFunctionality(attribute);
                    return null;
                     
       }

              
    }
        
}
