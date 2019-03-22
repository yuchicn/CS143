/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package famousoperas;

/**
 * File         Validation.java
 * Description  A validation class use in famous operas application to make s
 * ure all the user inputs are valid.
 * Platform     Windows 10, Netbean 8.2, jdk 1.8.0 101
 * Course       CS 143, SCC
 * Hours        1 hours
 * Date         5/8/2017
 * @author      yu-chi.chen
 */
public class Validation {
    
    /**
     * Method isInteger
     * Description Decide if a string input is an integer or not. Return true if it 
     * is, false if it is not.
     * @param integer string
     * @return boolean
     */
    public static boolean isInteger(String integer)
    {
        try{
            int i = Integer.parseInt(integer);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        } 
    }
    
    /**
     * Method isValidYear
     * Description Decide if a string input is an valid year or not. Return true 
     * if it is, false if it is not.
     * @param integer string
     * @return boolean
     * pre-condition: the string contains only an integer
     */
    public static boolean isValidYear(String integer)
    {
        int i = Integer.parseInt(integer);
        return i >= 1000 && i<= 2020;
    }
    
    /**
     * Method isEmpty
     * Description Decide if a string is empty or not.
     * @param s string
     * @return boolean
     */
    public static boolean isEmpty(String s)
    {
        return s.length() <= 0;
    }
    
    /**
     * Method isValidName
     * Description Decide if a string a valid name for a person or not. Which 
     * means it shouldn't contain any character that is not a letter. Return 
     * true if it is valid, false if it is not.
     * @param name
     * @return boolean
     */
    public static boolean isValidName(String name)
    {
        for(int i = 0; i < name.length(); i++)
        {
            if(!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' ')
                return false;
        }
        return true;
    }  
    
    /**
     * Method isValidLink
     * Description Decide if a string a valid link for a Youtube videl or not 
     * by checking if it contains the "https://" and "youtube" keywords.Return
     * true if it is valid, false if it is not.
     * @param link
     * @return boolean
     */
    public static boolean isValidLink(String link)
    {
        return link.contains("https://") && link.contains("youtube");
    }
}
