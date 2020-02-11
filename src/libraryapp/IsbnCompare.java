/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

import java.util.Comparator;

/**
 * Description: Isbn class (concrete class) to compare 2 books by ibsn
 * @author Jorge
 */
public class IsbnCompare extends BookCompare {

    @Override
    public int compare(Book b1, Book b2) {
        
        if (b1.getIsbn()< b2.getIsbn())
            return -1;
        if (b1.getIsbn()> b2.getIsbn())
            return 1;
        else return 0;   
    }
      
}
