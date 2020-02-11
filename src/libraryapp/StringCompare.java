/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;


/**
 * Description: Abstract Class which extends BookCompare to implement
 * the method string compare
 * @author Jorge
 */
public abstract class StringCompare extends BookCompare {

   
    protected int stringCompare(String str1, String str2) 
    { 
        int l1 = str1.length(); 
        int l2 = str2.length(); 
        int lmin = Math.min(l1, l2); 
        int str1Ch;
        int str2Ch;
  
        for (int i = 0; i < lmin; i++) { 
            str1Ch = (int)str1.charAt(i); 
            str2Ch = (int)str2.charAt(i); 
  
            if (str1Ch != str2Ch) { 
                return str1Ch - str2Ch; 
            } 
        } 
  
        if (l1 != l2)
            return l1 - l2; 
        else  
            return 0; 
    }
   
}
