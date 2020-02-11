/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Description: this class contains the sort algorithms, binary search algorithm
 * and method which are used by LibraryApp class
 * @author Jorge
 */
public class Logic {
   

    // Bubblesort algorithm: it is in the O(n^2) because the first loop
    // is going to loop n times and the second one, n - quantity of elements which
    // already were processed - 1 because the last elements doesn't have next
    // The worst case is O(n squared) and this happend when the array is reverse sorted
    // The upper bound of this is n^2 because cover the worst case
    // The idea of this algorithm is push the biggest element to the end of the array
    private void bubbleSort(Book[] books, BookCompare bookCompare) { 
        int n = books.length; 
        Book temp;
        for (int i = 0; i < n-1; i++)  // iteration n times
            for (int j = 0; j < n-i-1; j++) { // this loop decrement the quantity of iterations because depeneds on the
                // first loop or the value of i 
                if (bookCompare.compare(books[j], books[j+1]) > 0) { 
                    temp = books[j]; 
                    books[j] = books[j+1]; 
                    books[j+1] = temp; 
                }
            }
    } 
    
    // Selection is = O(n^2) in the worst case
    // the idea of this algorithm is take the minimum elements in a subarray and take
    // it to the begginig of that subarray, if the element is less than the others
    private void selection(Book[] books, BookCompare bookCompare) { 
        int length = books.length; 
        Book temp;
  
        // Iterate n times
        for (int i = 0; i < length-1; i++) { 
            // iterate from the i + 1 to lenght - 1
            // the minimum is inserted at the begining of the subarray  
            // [i..(length - 1)] 
            int min_pos = i; 
            for (int j = i+1; j < length; j++) {
                if (bookCompare.compare(books[j], books[min_pos]) < 0) 
                    min_pos = j;                 
            } 

  
            // Swap minimum element with the first element 
            temp = books[min_pos]; 
            books[min_pos] = books[i]; 
            books[i] = temp; 
        }
    }
     
    // Same situation than previous one, the worst case is = O(n^2)
    // the first loop is going to iterate lenght times and
    // the second one depends of the first loop
    // the idea of this algorithm: move elements of the array [0..i-1] which
    // are greater than the key to one position ahead of their current position
    // Therefore the upper bound of this algoritm is n^2
    private void insertion(Book[] books, BookCompare bookCompare) { 
    
        int length = books.length; 
        Book key;// iterate n times
        for (int i = 1; i < length; ++i) { 
            key = books[i]; 
            int j = i - 1; 
            // depends of the first iteration
            while (j >= 0 && bookCompare.compare(books[j], key) > 0) { 
                books[j + 1] = books[j]; 
                j = j - 1; 
            } 
            books[j + 1] = key; 
        } 
    }
    
    // this method is used by the main class to sort the books by title
    // this one call to the algorithm insertion
    public void sortbyTitle(Book[] books) {
        
        TitleCompare titleCompare = new TitleCompare();
        insertion(books, titleCompare);
    }

    // same like previous one but with genre attribute
    public void sortbyGenre(Book[] books) {
        
        GenreCompare genreCompare = new GenreCompare();
        selection(books, genreCompare);
    }
    
    // same but with year attribute
    public void sortbyYear(Book[] books) {
        
        YearCompare yearCompare = new YearCompare();
        insertion(books, yearCompare); //mergeSort(books, yearCompare);
    }
    
    // sort by isbn
    public void sortbyIsbn(Book[] books) {
        
        IsbnCompare isbnCompare = new IsbnCompare();
        bubbleSort(books, isbnCompare);
    }
    
    // Binary search algorithm. search an element in an array. This alorithm always find the first one
    // if there are repeated elements
    // this one is the not recusive version, it uses a explicit iteration
    // the wors case of this algorithm is equal to O(log n) base 2
    // because goes to the right side or left side if the value in the middle
    // is greater or less than key. So we discard one side in every step.
    // therefore the upper bound is log n (base 2)
    public int binarySearch(Book[] books, BookCompare bookCompare, Book keyBook) {
        
        int start = 0;
        int end = books.length - 1;
        int mid;
        
        // check if they are at the same position of 
        while(start < end) {
            
            mid = start + (end - start) / 2;
            
            if (bookCompare.compare(books[mid], keyBook) >= 0) 
                end = mid;
            else
                start = mid + 1;
        }
        if (bookCompare.compare(books[start], keyBook) == 0)
            return start;
        else return -1;
                  
    }
    
    // method which seach a title of a book using binary search
    // this method has an order of log n time complexity because 
    // this methods depends of binary search
    public Book searchByTitle(Book[] books, Book keyBook) {
        
        TitleCompare titleCompare = new TitleCompare();
        
        int index = binarySearch(books, titleCompare, keyBook);
        
        if (index > 0)
            return books[index];
        else
            return null;
        
    }

    // method which seach a title of a book using binary search
    // this method has an order of n time complexity because in the worst 
    // case (every book belongs to same author) has to loop the whole array 
    public Book[] searchByAuthor(Book[] books, Book keyBook) {
        
            AuthorCompare authorCompare = new AuthorCompare();
            
            ArrayList<Book> keyBooks = new ArrayList<>();

            int index = binarySearch(books, authorCompare, keyBook);
            
            if (index >= 0) {
                
                for (int i=index; i <= books.length - 1; i++) {
                    
                    if (!books[i].getAuthor().equalsIgnoreCase(keyBook.getAuthor()))
                        break;
                    
                    keyBooks.add(books[i]);
          
                }
                
                return keyBooks.toArray(new Book[keyBooks.size()]);
            }
        
           return null;
        
        
    }
}
