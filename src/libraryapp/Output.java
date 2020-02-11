/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Description: This class is responsible of print the results on the screen
 * we use println function along with ANSI colour codes
 * @author Jorge
 */
public class Output {
    
    private final String msgProgramFinished = "The program has finished";
    private final String msgNotFunctionality = "The program can't sort by ";
    private final String msgNoResults = "No results were found";
    private final String msgNoBookFound = "No books were found";

    // this method print the result and receive the name of the field whitch 
    // musb be painted with a color
    // this method has order of n (linear algorithm) where n is the quantity of elements
    //in book
    public void printBooks(Book[] books, String attribute) {
        
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN  = "\u001B[32m";
        final String ANSI_BLUE   = "\u001B[34m";

        if (books == null) {
            System.out.println(msgNoResults);
            return;
        }
        
        Iterator<Book> iterator = Arrays.stream(books).iterator();
            
        int position = 1;
        
        while (iterator.hasNext()) {
         
            if ((position % 2) == 0)
                System.out.println(ANSI_RED + "[" + position + "]");
            else
                System.out.println(ANSI_BLUE + "[" + position + "]");                
            System.out.println(iterator.next().toStringColor(attribute));
            if (iterator.hasNext())
                System.out.println(ANSI_GREEN + "==============================");
            
            position++;

        }        
        
    }
    
    public void printProgramFinished() {
        
        System.out.println(msgProgramFinished);
        
    }
    
    public void printNotFunctionality(String name) {
        
        System.out.println(msgProgramFinished + name);        
       
    }
    
    public void printNoBookFound() {
        
         System.out.println(msgNoBookFound);       
        
    }
    
}
