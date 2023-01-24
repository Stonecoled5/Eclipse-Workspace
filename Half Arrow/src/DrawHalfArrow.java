//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Draw Half Arrow
// Files:           DrawHalfArrow.java
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

public class DrawHalfArrow {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      int arrowBaseHeight = 0;
      int arrowBaseWidth  = 0;
      int arrowHeadWidth = 0;

      int i = 0;
      int j = 0;
     

      System.out.print("Enter arrow base height: ");
      arrowBaseHeight = scnr.nextInt();

      System.out.print("Enter arrow base width: ");
      arrowBaseWidth = scnr.nextInt();

      System.out.print("Enter arrow head width: ");
      arrowHeadWidth = scnr.nextInt();

      while (arrowHeadWidth<=arrowBaseWidth)
      { System.out.print("Enter arrow head width: ");
    	  arrowHeadWidth = scnr.nextInt();}
      System.out.print("\n");
      
      for (i = 1; i<=arrowBaseHeight; i++) {
    		  for (j = 1; j<=arrowBaseWidth; j++) {
    			  System.out.print("*");
    		  }
    				  System.out.print("\n");
    	  }
      for (i = arrowHeadWidth; i>0; i--) {
    	  for (j = 1; j<=i; j++)
    			  {System.out.print("*");}
    	  System.out.print("\n");
      }
    




      return;
   }
}
