//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Draw Right Triangle
// Files:           DrawRightTriangle.java
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
public class DrawRightTriangle {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter a character: ");
		String userInput = scan.next();
		System.out.print("Enter triangle height (1-10): ");
		int userInput2 = scan.nextInt();
		while (userInput2 <1 || userInput2>10) {
			System.out.println("Please enter height between 1 and 10.");
			userInput2 = scan.nextInt();}
		if(userInput2 >=1 || userInput2<=10) {
			System.out.print("\n");
			for (int i = 1; i<=userInput2; i++) {
				for (int j = 1; j<=i; j++) {
				
				System.out.print(userInput + " ");}
				System.out.print("\n");
			}
		}
	
	}

}
