/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

import java.util.Comparator;

/**
 * Description: Year class (concrete class) to compare 2 books by year
 * @author Jorge
 */
public class YearCompare extends BookCompare {

    @Override
    public int compare(Book b1, Book b2) {
        
        if (b1.getYear() < b2.getYear())
            return -1;
        if (b1.getYear() > b2.getYear())
            return 1;
        else return 0;   
    }
      
}