/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

/**
 * Description: Author class (concrete class) to compare 2 books by author
 * @author Jorge
 */
public class AuthorCompare extends StringCompare {
    
    @Override
    public int compare(Book b1, Book b2) {
    
        if (super.stringCompare(b1.getAuthor(), b2.getAuthor()) > 0)
            return 1;
	if (super.stringCompare(b1.getAuthor(), b2.getAuthor()) < 0)
            return -1;
	else return 0; 
			
    }
    
}
