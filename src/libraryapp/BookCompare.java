/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

import java.util.Comparator;

/**
 * Description: Class father which start the inheritance to do generic comparitions 
 * @author Jorge
 */
public abstract class BookCompare implements Comparator<Book> {

    @Override
    public abstract int compare(Book b1, Book b2);
    
}
