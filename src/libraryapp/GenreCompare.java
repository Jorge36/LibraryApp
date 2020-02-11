package libraryapp;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
* Description: Genre class (concrete class) to compare 2 books by genre
 * @author Jorge
 */
public class GenreCompare extends StringCompare {
    
    @Override
    public int compare(Book b1, Book b2) {
    
        if (super.stringCompare(b1.getGenre(), b2.getGenre()) > 0)
            return 1;
	if (super.stringCompare(b1.getGenre(), b2.getGenre()) < 0)
            return -1;
	else return 0; 
			
    }
    
}
