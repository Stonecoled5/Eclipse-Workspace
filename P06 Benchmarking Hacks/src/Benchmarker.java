//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P06 Benchmark Hacks
// Course:   CS 300 Fall 2021
//
// Author:   Cole Johnstone
// Email:    cjohnstone@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    -
// Partner Email:   -
// Partner Lecturer's Name: -
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         -
// Online Sources:  zybooks
//
///////////////////////////////////////////////////////////////////////////////
/**
 * Times how long it takes the methods in passwordHacker to 
 * complete
 * @author Cole Johnstone
 *
 */
public class Benchmarker {
	/**
	 * Returns the time it takes to open the lockbox using the 
	 * bruteforce method
	 * @param ph
	 * @return
	 */
	public static long timeBruteForce(PasswordHacker ph) {
		long time1 = System.currentTimeMillis();
		ph.bruteForce();
		long time2 = System.currentTimeMillis();
		return time2-time1;
	}
	/**
	 * Returns the time it takes to open the lockbox using the 
	 * hack method
	 * @param ph
	 * @return
	 */
	public static long timeHack(PasswordHacker ph) {
		long time1 = System.currentTimeMillis();
		ph.hack();
		long time2 = System.currentTimeMillis();
		return time2-time1;
	}
	/**
	 * takes the average of the two runtimes through numRuns and
	 * returns them as a string
	 * @return
	 */
	public static String race(int passwordLength, int numRuns) {
		//adds times taken for numRuns to an array for each type way of opening
		//the box
		long [] array1 = new long[numRuns];
		for(int i = 0; i < numRuns; i++) {
			array1[i] = timeBruteForce(new PasswordHacker(passwordLength));
		}
		
		long [] array2 = new long[numRuns];
		for(int i = 0; i < numRuns; i++) {
			array2[i] = timeHack(new PasswordHacker(passwordLength));
		}
		//makes average time for each hacking method over numRuns
		long value1 = 0;
		for(int i = 0; i < numRuns; i++) {
			value1 += array1[i]; 
		}
		long avg1 = value1/numRuns;
		
		long value2 = 0;
		for(int i = 0; i < numRuns; i++) {
			value2 += array2[i]; 
		}
		long avg2 = value2/numRuns;
		
		return "Brute force " + passwordLength + ": " + avg1 + "\nHack " + passwordLength +": " + avg2;
	}
	/**
	 * Runs the program
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(race(5,6));		
	}

}
