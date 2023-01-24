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
public class ClimbingTracker {
/*
 * returns an int that is either the numSend or the new length of send[]
 * 
 */
	public static int sendClimb(String[] send, int numSend, String grade) {
		grade = grade.replace(" ", "");
		if (grade.length() != 2) {	//makes sure grade is only two characters
			return numSend;
		}
		if (grade.charAt(0) != 'V') {//makes sure grade has a v at the first character

			return numSend;
		}
		char[] chars = grade.toCharArray();
		int number = -1;
		for (int j = 0; j < send.length; j++) {//checks if there is a null or"" space in the array
			if (send[j] == null || send[j].equals("")) {
				number = j;
				break;
			}
		}
		if (number == -1) {		//if the array is full it sends back the original number
			return numSend;
		}
		if (Character.isDigit(chars[1])) {//checks if the second character in the grade is
			for (int i = 0; i <= 7; i++) {//a number 0-7
				int a = Character.getNumericValue(chars[1]);
				if (a == i) {
					send[number] = grade;	//puts new grade into array and sends new num back
					return number + 1;
				}

			}

		}
		return numSend;
	}
/*
 * 
 * is the same as sendClimb but instead for the fail arrays
 * so it sends the fail arrays through the sendClimb method
 */
	public static int failClimb(String[] fail, int numFail, String grade) {
		int fails = sendClimb(fail, numFail, grade);//sends values to the sendClimb method
		if (fails == numFail) {
			return numFail;
		}
		return fails;
	}
/*
 * returns average grades of historyLength number of recent climbs for send and fails
 * 
 */
	public static String getStats(String[] send, int numSend, String[] fail, int numFail, int historyLength) {
		String avgSend = "";
		String avgFail = "";
		if (historyLength < 1) {	//makes sure historyLength is not <1
			String says = "send: --\nfail: --";
			return says;
		}
		boolean maybe1 = false;
		boolean maybe2 = false;//tests if the averages can be calculated, if not, resulting --
		if (numSend < 1 || send.length < 1 || send[0] == null || send == null) {
			maybe1 = true;
			avgSend = "--";
		}
		if (numFail < 1 || fail.length < 1 || fail[0] == null || send == null) {
			maybe2 = true;
			avgFail = "--";
		}
		if (historyLength > numSend && maybe1 == false) {
			double sum = 0;						//gets the sum of all the send array values
			for (int i = 0; i < numSend; i++) { 
				double a = Character.getNumericValue(send[i].charAt(1));
				sum += a;
			}
			double avg = sum / numSend;//gets average for send grades  
			avgSend = String.valueOf(avg);
		}
		if (historyLength > numFail && maybe2 == false) {
			double sum = 0;						//gets the sum of all the fail array values
			for (int i = 0; i < numFail; i++) {
				double a = Character.getNumericValue(fail[i].charAt(1));
				sum += a;
			}
			double avg = sum / numFail;
			avgFail = String.valueOf(avg);
		}
		if (historyLength <= numSend) {			//gets the sum of the historyLength recent
			double sum = 0;						 //number of grades in send
			for (int i = numSend - historyLength; i < numSend; i++) {
				double a = Character.getNumericValue(send[i].charAt(1));
				sum += a;
			}
			double avg = sum / historyLength;
			avgSend = String.valueOf(avg);
		}
		if (historyLength <= numFail) {		//gets the sum of the historyLength recent	
			double sum = 0;					//number of grades in fail
			for (int i = numFail - historyLength; i < numFail; i++) {
				double a = Character.getNumericValue(fail[i].charAt(1));
				sum += a;
			}
			double avg = sum / historyLength;
			avgFail = String.valueOf(avg);
		}
		String says = "send: " + avgSend + "\nfail: " + avgFail;//puts the string together
		return says;

	}
/*
 * 
 * returns a histogram of successful and failed grades based on the numSend and numFail
 */
	public static String getHistogram(String[] send, int numSend, String[] fail, int numFail) {
		if (send.length < 1 && fail.length < 1 || numSend < 1 && numFail < 1 || send == null || fail == null) {
			String says = "Error: no data to display";//checks if it can output data based on the arrays given
			return says;
		}
		String[] array = { "", "", "", "", "", "", "", "" };//creates array for + and -
		if (numFail != 0) {									
			for (int i = 0; i < numFail; i++) {//if there are fail grades requested it
				for (int j = 0; j < 8; j++) {//puts the - in the right position in the array
					if (Character.getNumericValue(fail[i].charAt(1)) == j) {
						array[j] += " -";
					}
				}
			}
		}
		if (numSend != 0) {
			for (int i = 0; i < numSend; i++) {//if there are fail grades requested it
				for (int j = 0; j < 8; j++) {//puts the + in the right position in the array
					if (Character.getNumericValue(send[i].charAt(1)) == j) {
						array[j] += " +";
					}
				}
			}
		}
		String says = "";
		for (int i = 0; i < array.length; i++) {
			if (array[i] != "") {	//puts all the grades and + and - together in a string
				says += "V" + String.valueOf(i) + ":" + String.valueOf(array[i]) + "\n";
			}
		}
		says = says.substring(0, says.length() - 1);//gets rid of last \n
		return says;
	}
}
