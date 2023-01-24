//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P03 Exceptional Climbing
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
import java.util.zip.DataFormatException;

/**
 * Tests ExceptionalClimbing class
 * 
 * @author Cole
 */
public class ExceptionalClimbingTester {

	/**
	 * 
	 * main method runs runAllTests to make sure everything is working properly
	 * 
	 * @param args 
	 * @throws DataFormatException
	 */
	public static void main(String[] args) throws DataFormatException {
		if (runAllTests() == true) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}

	/**
	 * tests the sendClimb method in ExceptionalClimbing class
	 * 
	 * @return boolean
	 * @throws DataFormatException
	 */

	public static boolean testSendClimb() throws DataFormatException {
		try {
			String[] sendClimbArray1 = { "V1" };
			int sendClimbArraySize1 = 1;
			String grade1 = "V6"; // tests if sendClimb will reject a grade when there's no
									// room in the send array
			ExceptionalClimbing.sendClimb(sendClimbArray1, sendClimbArraySize1, grade1);
		} catch (Exception e1) {
			if (!(e1 instanceof IllegalArgumentException)) {
				return false;
			}
			if (!e1.getMessage().equals("cannot add new value to full length 1 array")) {
				return false;
			}
			try {
				String[] sendClimbArray2 = { "V1", null };
				int sendClimbArraySize2 = 1;
				String grade2 = "66"; // tests if it rejects an invalid grade with no v
				ExceptionalClimbing.sendClimb(sendClimbArray2, sendClimbArraySize2, grade2);
			} catch (Exception e2) {
				if (!(e2 instanceof IllegalArgumentException)) {
					return false;
				}
				if (!e2.getMessage().equals("66 is not a valid grade")) {
					return false;
				}
				try {
					String[] sendClimbArray3 = { "V1", null };
					int sendClimbArraySize3 = 1;
					String grade3 = "V8"; // tests if it rejects an invalid grade with no 0-7 grade
					ExceptionalClimbing.sendClimb(sendClimbArray3, sendClimbArraySize3, grade3);
				} catch (Exception e3) {
					if (!(e3 instanceof IllegalArgumentException)) {
						return false;
					}
					if (!e3.getMessage().equals("V8 is not a valid grade")) {
						return false;
					}
					try {
						String[] sendClimbArray5 = { "V1", null, "V2", null };
						int sendClimbArraySize5 = 3;
						String grade5 = "V7"; // tests if it handles an array with a space in between grades correctly
						ExceptionalClimbing.sendClimb(sendClimbArray5, sendClimbArraySize5, grade5);
					} catch (Exception e5) {
						if (!(e5 instanceof DataFormatException)) {
							return false;
						}
						if (!e5.getMessage().equals("invalid oversize array")) {
							return false;
						}
						try {
							String[] sendClimbArray6 = new String[100];
							int sendClimbArraySize6 = -1;
							String grade6 = "V7"; // tests if it handles negative value for numSend correctly
							ExceptionalClimbing.sendClimb(sendClimbArray6, sendClimbArraySize6, grade6);
						} catch (Exception e6) {
							if (!(e6 instanceof DataFormatException)) {
								return false;
							}
							if (!e6.getMessage().equals("invalid oversize array")) {
								return false;
							}
							try {
								String[] sendClimbArray6 = { "V1", null };
								int sendClimbArraySize6 = 1;
								String grade6 = "V7"; // tests if it correctly adds new grade with no exceptions
								ExceptionalClimbing.failClimb(sendClimbArray6, sendClimbArraySize6, grade6);
							} catch (Exception e7) {
								return false;
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * tests the failClimb method in ExceptionalClimbing class
	 * 
	 * @return boolean
	 * @throws DataFormatException
	 */
	public static boolean testFailClimb() throws DataFormatException {
		try {
			String[] failClimbArray1 = { "V1" };
			int failClimbArraySize1 = 1;
			String grade1 = "V6"; // tests if sendclimb will reject a grade when there's no
									// room in the send array
			ExceptionalClimbing.failClimb(failClimbArray1, failClimbArraySize1, grade1);
		} catch (Exception e1) {
			if (!(e1 instanceof IllegalArgumentException)) {
				return false;
			}
			if (!e1.getMessage().equals("cannot add new value to full length 1 array")) {
				System.out.println(e1.getMessage());
				System.out.println("boobs");
				return false;
			}
			try {
				String[] failClimbArray2 = { "V1", null };
				int failClimbArraySize2 = 1;
				String grade2 = "66"; // tests if it rejects an invalid grade with no v
				ExceptionalClimbing.failClimb(failClimbArray2, failClimbArraySize2, grade2);
			} catch (Exception e2) {
				if (!(e2 instanceof IllegalArgumentException)) {
					return false;
				}
				if (!e2.getMessage().equals("66 is not a valid grade")) {
					return false;
				}
				try {
					String[] failClimbArray3 = { "V1", null };
					int failClimbArraySize3 = 1;
					String grade3 = "V8"; // tests if it rejects an invalid grade with no 0-7 grade
					ExceptionalClimbing.failClimb(failClimbArray3, failClimbArraySize3, grade3);
				} catch (Exception e3) {
					if (!(e3 instanceof IllegalArgumentException)) {
						return false;
					}
					if (!e3.getMessage().equals("V8 is not a valid grade")) {
						return false;
					}
					try {
						String[] failClimbArray5 = { "V1", null, "V2", null };
						int failClimbArraySize5 = 3;
						String grade5 = "V7"; // tests if it handles an array with a space in between grades correctly
						ExceptionalClimbing.failClimb(failClimbArray5, failClimbArraySize5, grade5);
					} catch (Exception e5) {
						if (!(e5 instanceof DataFormatException)) {
							return false;
						}
						if (!e5.getMessage().equals("invalid oversize array")) {
							return false;
						}
						try {
							String[] failClimbArray6 = new String[100];
							int failClimbArraySize6 = -1;
							String grade6 = "V7"; // tests if it handles negative value for numSend correctly
							ExceptionalClimbing.failClimb(failClimbArray6, failClimbArraySize6, grade6);
						} catch (Exception e6) {
							if (!(e6 instanceof DataFormatException)) {
								return false;
							}
							if (!e6.getMessage().equals("invalid oversize array")) {
								return false;
							}
							try {
								String[] failClimbArray6 = { "V1", null };
								int failClimbArraySize6 = 1;
								String grade6 = "V7"; // tests if it correctly adds new grade with no exceptions
								ExceptionalClimbing.failClimb(failClimbArray6, failClimbArraySize6, grade6);
							} catch (Exception e7) {
								return false;
							}
							return true;
						}
					}
				}
			}

		}
		return false;
	}

	/**
	 * tests the getStats method in ExceptionalClimbing class
	 * 
	 * @return boolean
	 * @throws DataFormatException
	 */
	public static boolean testGetStats() {
		try {
			String[] send = { "V1", "V2" };
			String[] fail = { "V2", "V2", "V2" };
			int numsend = 2;
			int numfail = 3;
			int hist = 0; // tests if it throws correct exception for hist of 0
			ExceptionalClimbing.getStats(send, numsend, fail, numfail, hist);
		} catch (Exception e1) {
			if (!(e1 instanceof IllegalArgumentException)) {
				return false;
			}
			if (!e1.getMessage().equals("0 is not a valid history length")) {
				return false;
			}
			try {
				String[] send = { null };
				String[] fail = { null };
				int numsend = 0;
				int numfail = 0;
				int hist = 2; // tests if it throws correct exception for no data but a hist
				ExceptionalClimbing.getStats(send, numsend, fail, numfail, hist);
			} catch (Exception e2) {
				if (!(e2 instanceof RuntimeException)) {
					return false;
				}
				if (!e2.getMessage().equals("no climbs provided")) {
					return false;
				}
				try {
					String[] send = { null };
					String[] fail = { null };
					int numsend = 0;
					int numfail = 0;
					int hist = 0; // tests if it throws correct exception for no data and hist of 0
					ExceptionalClimbing.getStats(send, numsend, fail, numfail, hist);
				} catch (Exception e3) {
					if (!(e3 instanceof RuntimeException)) {
						return false;
					}
					if (!e3.getMessage().equals("no climbs provided")) {
						return false;
					}
					try {
						String[] send7 = { "V2", "V1" };
						String[] fail7 = { "V0", "V2" };
						int numsend7 = 2;
						int numfail7 = 2;
						int hist7 = 2; // tests a normal input so no exceptions
						ExceptionalClimbing.getStats(send7, numsend7, fail7, numfail7, hist7);
					} catch (Exception e4) {
						return false;
					}
					return true;

				}
			}
		}
		return false;
	}

	/**
	 * tests the getHistogram method in ExceptionalClimbing class
	 * 
	 * @return boolean
	 * 
	 */
	public static boolean testGetHistogram() {
		try {
			String[] send1 = new String[0];
			String[] fail1 = new String[0];
			int numsend1 = 0;
			int numfail1 = 0; // tests if it throws right exception with two empty arrays
			ExceptionalClimbing.getHistogram(send1, numsend1, fail1, numfail1);
		} catch (Exception e1) {
			if (!(e1 instanceof RuntimeException)) {
				return false;
			}
			if (!e1.getMessage().equals("no climbs provided")) {
				return false;
			}
			try {
				String[] send1 = { "V1", "V2" };
				String[] fail1 = new String[0];
				int numsend1 = 2;
				int numfail1 = 0; // tests if it gives correct histogram with one empty array with no exceptions
				ExceptionalClimbing.getHistogram(send1, numsend1, fail1, numfail1);
			} catch (Exception e2) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * makes sure all tests above return true
	 * 
	 * @return boolean
	 * @throws DataFormatException
	 */
	public static boolean runAllTests() throws DataFormatException {
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
