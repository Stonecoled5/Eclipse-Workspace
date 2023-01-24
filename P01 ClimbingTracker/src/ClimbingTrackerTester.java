//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P01 ClimbingTracker
// Course:   CS 300 Fall 2020
//
// Author:   Cole Johnstone
// Email:    cjohnstone@wisc.edu
// Lecturer: Mouna Kacem
// 
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Larry Drifke
// Partner Email:   ldrifke@wisc.edu
// Partner Lecturer's Name: Hobbes
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _x__ Write-up states that pair programming is allowed for this assignment.
//   _x__ We have both read and understand the course Pair Programming Policy.
//   _x__ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Online Sources:  zybooks
//
///////////////////////////////////////////////////////////////////////////////
/*
	 tests the sendClimb method in ClimbingTracker class
	 
	 */

public class ClimbingTrackerTester {
	/*
	 * 
	 * main method runs runAllTests to make sure everything is working properly
	 * 
	 * 
	 */
	public static void main(String[] args) {
		if (runAllTests() == true) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}/*
		 * tests the sendClimb method in ClimbingTracker class
		 * 
		 */

	public static boolean testSendClimb() {
		String[] sendClimbArray1 = { "V1" };
		int sendClimbArraySize1 = 1;
		String grade1 = "V6"; // tests if sendclimb will reject a grade when there's no
							// room in the send array
		int value1 = ClimbingTracker.sendClimb(sendClimbArray1, sendClimbArraySize1, grade1);
		if (value1 != sendClimbArraySize1) {
			return false;
		}
		String[] sendClimbArray2 = { "V1", null };
		int sendClimbArraySize2 = 1;
		String grade2 = "66"; // tests if it rejects an invalid grade with no v
		int value2 = ClimbingTracker.sendClimb(sendClimbArray2, sendClimbArraySize2, grade2);
		if (value2 != sendClimbArraySize2) {
			return false;
		}
		String[] sendClimbArray3 = { "V1", null };
		int sendClimbArraySize3 = 1;
		String grade3 = "V8"; // tests if it rejects an invalid grade with no 0-7 grade
		int value3 = ClimbingTracker.sendClimb(sendClimbArray3, sendClimbArraySize3, grade3);
		if (value3 != sendClimbArraySize3) {
			return false;
		}
		String[] sendClimbArray4 = { "V1", null };
		int sendClimbArraySize4 = 1;
		String grade4 = "V7 "; // tests if it will ignore extra spaces
		int value4 = ClimbingTracker.sendClimb(sendClimbArray4, sendClimbArraySize4, grade4);
		System.out.println(value4);
		if (value4 != 2) {
			return false;
		}
		String[] sendClimbArray5 = new String[100];
		int sendClimbArraySize5 = 0;
		String grade5 = "V7"; // tests if it adds a new grade to an empty oversized array
		int value5 = ClimbingTracker.sendClimb(sendClimbArray5, sendClimbArraySize5, grade5);
		if (value5 != 1) {
			return false;
		}
		return true;

	}

	/*
	 * tests the failClimb method in ClimbingTracker class
	 * 
	 */
	public static boolean testFailClimb() {
		String[] sendClimbArray1 = { "V1" };
		int sendClimbArraySize1 = 1;
		String grade1 = "V6"; // tests if it rejects a grade if array is full
		int value1 = ClimbingTracker.failClimb(sendClimbArray1, sendClimbArraySize1, grade1);
		if (value1 != sendClimbArraySize1) {
			return false;
		}
		String[] sendClimbArray2 = { "V1", null };
		int sendClimbArraySize2 = 1;
		String grade2 = "66"; // tests if it rejects a grade with no v in first position
		int value2 = ClimbingTracker.failClimb(sendClimbArray2, sendClimbArraySize2, grade2);
		if (value2 != sendClimbArraySize2) {
			return false;
		}
		String[] sendClimbArray3 = { "V1", null };
		int sendClimbArraySize3 = 1;
		String grade3 = "V8"; // tests if it rejects a grade above 8
		int value3 = ClimbingTracker.failClimb(sendClimbArray3, sendClimbArraySize3, grade3);
		if (value3 != sendClimbArraySize3) {
			return false;
		}
		String[] sendClimbArray4 = { "V1", null };
		int sendClimbArraySize4 = 1;
		String grade4 = "V7 "; // tests if it takes out spaces and adds to the array
		int value4 = ClimbingTracker.failClimb(sendClimbArray4, sendClimbArraySize4, grade4);
		if (value4 != 2) {
			return false;
		}
		String[] sendClimbArray5 = new String[0];
		int sendClimbArraySize5 = 0;
		String grade5 = "V7"; // tests if adds to an empty array with no room
		int value5 = ClimbingTracker.failClimb(sendClimbArray5, sendClimbArraySize5, grade5);
		if (value5 != sendClimbArraySize5) {
			return false;
		}
		String[] sendClimbArray6 = { "V1", null };
		int sendClimbArraySize6 = 0;
		String grade6 = "V7"; // tests if it still adds to the open space in an
								// array even if numSend is wrong
		int value6 = ClimbingTracker.failClimb(sendClimbArray6, sendClimbArraySize6, grade6);
		if (value6 != 2) {
			return false;
		}
		return true;
	}

	/*
	 * tests the getStats method in ClimbingTracker class
	 * 
	 */
	public static boolean testGetStats() {
		String[] send1 = { "V1", "V2" };
		String[] fail1 = { "V2", "V2", "V2" };
		int numsend1 = 2;
		int numfail1 = 3;
		int hist1 = 0; // tests if it rejects a historyLength of 0
		String correct1 = "send: --\nfail: --";
		String value1 = ClimbingTracker.getStats(send1, numsend1, fail1, numfail1, hist1);
		if (!value1.equals(correct1)) {
			return false;
		}
		String[] send2 = { "V1", "V2" };
		String[] fail2 = { "V2", "V2", "V2" };
		int numsend2 = 2;
		int numfail2 = 3; // tests if it correctly gives averages when
		int hist2 = 1; // historyLength is less than numSend and numFail
		String correct2 = "send: 2.0\nfail: 2.0";
		String value2 = ClimbingTracker.getStats(send2, numsend2, fail2, numfail2, hist2);
		if (!value2.equals(correct2)) {
			return false;
		}
		String[] send3 = { null, null };
		String[] fail3 = { "V2", "V2", "V2" };
		int numsend3 = 0;
		int numfail3 = 3; // tests if it correctly gives averages with an empty send array
		int hist3 = 1;
		String correct3 = "send: --\nfail: 2.0";
		String value3 = ClimbingTracker.getStats(send3, numsend3, fail3, numfail3, hist3);
		if (!value3.equals(correct3)) {
			return false;
		}
		String[] send4 = { "V2", "V1" };
		String[] fail4 = new String[0];
		int numsend4 = 2;
		int numfail4 = 0;
		int hist4 = 1; // tests if it correctly gives averages with an empty fail array
		String correct4 = "send: 1.0\nfail: --";
		String value4 = ClimbingTracker.getStats(send4, numsend4, fail4, numfail4, hist4);
		if (!value4.equals(correct4)) {
			return false;
		}
		String[] send5 = { "V2", "V1" };
		String[] fail5 = { "V2", "V2", "V2" };
		int numsend5 = 2;
		int numfail5 = 3; // tests if it correctly gives averages when
		int hist5 = 5; // historyLength is more than numSend and numFail
		String correct5 = "send: 1.5\nfail: 2.0";
		String value5 = ClimbingTracker.getStats(send5, numsend5, fail5, numfail5, hist5);
		if (!value5.equals(correct5)) {
			return false;
		}
		String[] send6 = { "V2", "V1" };
		String[] fail6 = { "V2", "V2", "V2" };
		int numsend6 = 2;
		int numfail6 = 3; // tests if it correctly gives averages when
		int hist6 = -1; // historyLength is negative
		String correct6 = "send: --\nfail: --";
		String value6 = ClimbingTracker.getStats(send6, numsend6, fail6, numfail6, hist6);
		if (!value6.equals(correct6)) {
			return false;
		}
		String[] send7 = { "V2", "V1" };
		String[] fail7 = { "V0", "V2" };
		int numsend7 = 2;
		int numfail7 = 2;
		int hist7 = 2; // tests a normal input if it gives correct avgs
		String correct7 = "send: 1.5\nfail: 1.0";
		String value7 = ClimbingTracker.getStats(send7, numsend7, fail7, numfail7, hist7);
		if (!value7.equals(correct7)) {
			return false;
		}
		String[] send8 = new String[4];
		String[] fail8 = new String[4];
		int numsend8 = 0;
		int numfail8 = 0;
		int hist8 = 2; // tests correct averages of two empty arrays
		String correct8 = "send: --\nfail: --";
		String value8 = ClimbingTracker.getStats(send8, numsend8, fail8, numfail8, hist8);
		if (!value8.equals(correct8)) {
			return false;
		}
		return true;
	}

	/*
	 * tests the getHistogram method in ClimbingTracker class
	 * 
	 */
	public static boolean testGetHistogram() {
		String[] send1 = { "V1", "V2" };
		String[] fail1 = { "V2", "V2", "V2" };
		int numsend1 = 2;
		int numfail1 = 3; // tests if it correctly gives histogram with normal input
		String correct1 = "V1: +\nV2: - - - +";
		String value1 = ClimbingTracker.getHistogram(send1, numsend1, fail1, numfail1);
		if (!value1.equals(correct1)) {
			return false;
		}
		String[] send2 = new String[0];
		String[] fail2 = new String[0];
		int numsend2 = 0;
		int numfail2 = 0; // tests if it gives correct histogram with two empty arrays
		String correct2 = "Error: no data to display";
		String value2 = ClimbingTracker.getHistogram(send2, numsend2, fail2, numfail2);
		if (!value2.equals(correct2)) {
			return false;
		}
		String[] send3 = new String[0];
		String[] fail3 = { "V6", "V3", "V1" };
		int numsend3 = 0;
		int numfail3 = 3; // tests if it gives correct histogram with one empty array
		String correct3 = "V1: -\nV3: -\nV6: -";
		String value3 = ClimbingTracker.getHistogram(send3, numsend3, fail3, numfail3);
		if (!value3.equals(correct3)) {
			return false;
		}
		String[] send4 = { "V1", "V2", null };
		String[] fail4 = { "V2", "V1", null };
		int numsend4 = 2; // tests if it gives correct histogram with different
		int numfail4 = 0; // numbers than items in arrays with room
		String correct4 = "V1: +\nV2: +";
		String value4 = ClimbingTracker.getHistogram(send4, numsend4, fail4, numfail4);
		System.out.println(value4);
		if (!value4.equals(correct4)) {
			return false;
		}
		String[] send5 = { "V6", "V3", "V1" };
		String[] fail5 = { "V1", "V2" };
		int numsend5 = 2; // tests if it gives correct histogram with different
		int numfail5 = 1; // numbers than items in arrays with no room
		String correct5 = "V1: -\nV3: +\nV6: +";
		String value5 = ClimbingTracker.getHistogram(send5, numsend5, fail5, numfail5);
		System.out.println(value5);
		if (!value5.equals(correct5)) {
			return false;
		}
		String[] send6 = new String[0];
		String[] fail6 = new String[0];
		int numsend6 = 2; // tests if it gives correct histogram with numbers
		int numfail6 = 1; // but empty arrays
		String correct6 = "Error: no data to display";
		String value6 = ClimbingTracker.getHistogram(send6, numsend6, fail6, numfail6);
		System.out.println(value6);
		if (!value6.equals(correct6)) {
			return false;
		}
		return true;
	}

	/*
	 * makes sure all test above returns true
	 * 
	 */
	public static boolean runAllTests() {
		if (testSendClimb() == true) {
			if (testFailClimb() == true) {
				if (testGetStats() == true) {
					if (testGetHistogram() == true) {
						return true;
					}
					return false;
				}
				return false;
			}
			return false;
		}
		return false;
	}
}



