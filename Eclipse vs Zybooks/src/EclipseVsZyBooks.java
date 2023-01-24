//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Text Message Expander
// Files:           TextMsgExpander.java
// Course:          Fall 2018
//
// Author:          Cole Johnstone
// Email:           cjohnstone@wisc.edu
// Lecturer's Name: Mark
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// No outside help
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;

/**
 * The behavior of this code is different in Eclipse as compared to zyBooks.
 * Refactor the code so that it works in both zyBooks and Eclipse.
 * 
 * @author mrenault
 *
 */

public class EclipseVsZyBooks {

    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        int a = scan.nextInt();
        System.out.println("Enter a second integer: ");
        int b = scan.nextInt();
        if(a == b)
            System.out.println(a + " equals " + b);
        else if (a < b)
            System.out.println(a + " is less than " + b);
        else
            System.out.println(a + " is more than " + b);
    }

    

}