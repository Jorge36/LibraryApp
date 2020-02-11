/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Description: interaction with the user
 * @author Jorge
 */
public class Menu {

    // Attribute global in to read from keyboard
    private Scanner in = new Scanner(System.in);
    private final String msgInvalidValue = "You have entered an invalid input. Try again";

    // Close scanner
    public void CloseScanner() {
        
        in.close();
        
    }
    
    // Method which allow user to choose a field or author
    public String chooseOptionNumber(String[] options, String msgQuestion) {
        
        int choice;
        int howMany = 1;

        System.out.println(msgQuestion);

        do {
           
            try {
                 // code to display all possible user choices
                 printOptions(options);
                 // Scanner for user input
                 choice = in.nextInt();  
                 // if the user type a valid value of option
                 if(choice>=1 && choice<=options.length) 
                    return options[choice-1]; // return that option
                 else {
                    // if the user fail to type the value 4 times, the metohod return with value null
                    if (howMany > 3) 
                        return null;
                        
                    System.out.println(msgInvalidValue);
                    howMany++;
                     
                 }
                     
            } catch(InputMismatchException e) {
                
               // to clear an invalid input from the Scanner
               in.next();
                // if the user fail to typed the value 4 times, the metohod return with value null
               if (howMany > 3) 
                    return null;
                        
               System.out.println(msgInvalidValue);
               howMany++;
            }
        
        }while(true);
       
    }
    // main menu
    public int mainMenu(String msgWelcome, String msgfirstQuestion, String[] msgOptions){
       
        // Variable choice to save the option which is typed by the user
        int choice;
        // print meesage welcome and questions (4)
        System.out.println(msgWelcome);
        System.out.println(msgfirstQuestion);
        
        do{
            // code to display all possible user choices
            for (String msgOption: msgOptions) {
                System.out.println(msgOption);            
            }
            
            try {
                // Scanner for user input
                choice = in.nextInt();  
                if(choice>=1 && choice<=4) {
                    return choice;
                }    
                else 
                    System.out.println(msgInvalidValue); 
                
                   
            } catch (InputMismatchException e) {
                in.next();
                System.out.println(msgInvalidValue);
            }
            
        }while(true); 

    }

    // print options to the user
    private void printOptions(String[] options) {

        int position = 1;
        
        for (String option: options) {
            
            System.out.println("[" + position + "] " + option + System.lineSeparator());
            position++;
            
        }                  
    }
    
    // return the tile or author typed by the user
    public String typeString(String msgQuestion) {
        
        System.out.println(msgQuestion);
        in.skip("\n");
        return in.nextLine(); 
        
    }
          
}
