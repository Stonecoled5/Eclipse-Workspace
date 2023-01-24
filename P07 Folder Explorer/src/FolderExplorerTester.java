
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Folder Explorer
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
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Tester class for FolderExplorer class
 * 
 * @author Cole Johnstone
 *
 */
public class FolderExplorerTester {
	/**
	 * tests the getContents method in FolderExplorer
	 * 
	 * @param folder
	 * @return boolean
	 */
	public static boolean testGetContents(File folder) {
		try {
			// Scenario 1
			// list the basic contents of the cs300 folder
			ArrayList<String> listContent = FolderExplorer.getContents(folder);
			// expected output must contain "exams preparation", "grades",
			// "lecture notes", "programs", "reading notes", "syllabus.txt",
			// and "todo.txt" only.
			String[] contents = new String[] { "exams preparation", "grades", "lecture notes", "programs",
					"reading notes", "syllabus.txt", "todo.txt" };
			List<String> expectedList = Arrays.asList(contents);
			// check the size and the contents of the output
			if (listContent.size() != 7) {
				System.out.println("Problem detected: cs300 folder must contain 7 elements.");
				return false;
			}
			for (int i = 0; i < expectedList.size(); i++) {
				if (!listContent.contains(expectedList.get(i))) {
					System.out.println("Problem detected: " + expectedList.get(i)
							+ " is missing from the output of the list contents of cs300 folder.");
					return false;
				}
			}
			// Scenario 2 - list the contents of the grades folder
			File f = new File(folder.getPath() + File.separator + "grades");
			listContent = FolderExplorer.getContents(f);
			if (listContent.size() != 0) {
				System.out.println("Problem detected: grades folder must be empty.");
				return false;
			}
			// Scenario 3 - list the contents of the p02 folder
			f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
			listContent = FolderExplorer.getContents(f);
			if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
				System.out.println("Problem detected: p02 folder must contain only " + "one file named FishTank.java.");
				return false;
			}
			// Scenario 4 - List the contents of a file
			f = new File(folder.getPath() + File.separator + "todo.txt");
			try {
				listContent = FolderExplorer.getContents(f);
				System.out.println("Problem detected: Your FolderExplorer.getContents() must "
						+ "throw a NotDirectoryException if it is provided an input which is not" + "a directory.");
				return false;
			} catch (NotDirectoryException e) { // catch only the expected exception
				// no problem detected
			}
			// Scenario 5 - List the contents of not found directory/file
			f = new File(folder.getPath() + File.separator + "music.txt");
			try {
				listContent = FolderExplorer.getContents(f);
				System.out.println("Problem detected: Your FolderExplorer.getContents() must "
						+ "throw a NotDirectoryException if the provided File does not exist.");
				return false;
			} catch (NotDirectoryException e) {
				// behavior expected
			}
		} catch (Exception e) {
			System.out.println(
					"Problem detected: Your FolderExplorer.getContents() has thrown" + " a non expected exception.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * tests base case of getDeepContents in FolderExplorer
	 * 
	 * @param folder
	 * @return boolean
	 */
	public static boolean testDeepGetContentsBaseCase(File folder) {
		try {
			// Scenario 1
			// list the basic contents of a folder with only files in it
			ArrayList<String> listContent = FolderExplorer.getDeepContents(folder);
			String[] contents = new String[] { "zyBooksCh1.txt", "zyBooksCh2.txt", "zyBooksCh3.txt", "zyBooksCh4.txt" };
			List<String> expectedList = Arrays.asList(contents);
			// check the size and the contents of the output
			if (listContent.size() != 4) {
				System.out.println("Problem detected: cs300 folder must contain 4 elements.");
				return false;
			}
			for (int i = 0; i < expectedList.size(); i++) {
				if (!listContent.contains(expectedList.get(i))) {
					System.out.println("Problem detected: " + expectedList.get(i)
							+ " is missing from the output of the list contents of cs300 folder.");
					return false;
				}
			}
			return true;
		} catch (NotDirectoryException e) {
			return false;
		}
	}

	/**
	 * tests recursive requests from getDeepContents in FolderExplorer
	 * 
	 * @param folder
	 * @return boolean
	 */
	public static boolean testDeepListRecursiveCase(File folder) {
		try {
			// Scenario 1
			// list the basic contents of the cs300 folder
			ArrayList<String> listContent = FolderExplorer.getDeepContents(folder);
			String[] contents = new String[] { "codeSamples.java", "outline.txt", "ExceptionHandling.txt",
					"proceduralProgramming.txt", "UsingObjects.txt", "CreatingClasses.txt", "Generics.txt",
					"Inheritance.txt", "AlgorithmAnalysis.txt", "Recursion.txt", "Sorting.txt", "ClimbingTracker.java",
					"ClimbingTrackerTester.java", "FishTank.java", "ExceptionalClimbing.java",
					"ExceptionalClimbingTester.java", "Program01.pdf", "Program02.pdf", "Program03.pdf",
					"zyBooksCh1.txt", "zyBooksCh2.txt", "zyBooksCh3.txt", "zyBooksCh4.txt", "syllabus.txt",
					"todo.txt" };
			List<String> expectedList = Arrays.asList(contents);
			// check the size and the contents of the output
			if (listContent.size() != 25) {
				System.out.println(listContent);
				System.out.println("Problem detected: cs300 folder must contain 25 elements.");
				return false;
			}
			for (int i = 0; i < expectedList.size(); i++) {
				if (!listContent.contains(expectedList.get(i))) {
					System.out.println("Problem detected: " + expectedList.get(i)
							+ " is missing from the output of the list contents of cs300 folder.");
					return false;
				}
			} // Scenario 2 - list the contents of the grades folder
			File f = new File(folder.getPath() + File.separator + "grades");
			listContent = FolderExplorer.getDeepContents(f);
			if (listContent.size() != 0) {
				System.out.println("Problem detected: grades folder must be empty.");
				return false;
			}
			// Scenario 3 - list the contents of the p02 folder
			f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
			listContent = FolderExplorer.getDeepContents(f);
			if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
				System.out.println("Problem detected: p02 folder must contain only " + "one file named FishTank.java.");
				return false;
			}
		} catch (NotDirectoryException e) {
			return false;
		}
		// Scenario 4 - List the contents of a file
		File f = new File(folder.getPath() + File.separator + "todo.txt");
		try {
			ArrayList<String> listContent = FolderExplorer.getDeepContents(f);
			System.out.println("Problem detected: Your FolderExplorer.getContents() must "
					+ "throw a NotDirectoryException if it is provided an input which is not" + "a directory.");
			return false;
		} catch (NotDirectoryException e) { // catch only the expected exception
			// no problem detected
		}
		// Scenario 5 - List the contents of not found directory/file
		f = new File(folder.getPath() + File.separator + "music.txt");
		try {
			ArrayList<String> listContent = FolderExplorer.getDeepContents(f);
			System.out.println("Problem detected: Your FolderExplorer.getContents() must "
					+ "throw a NotDirectoryException if the provided File does not exist.");
			return false;
		} catch (NotDirectoryException e) {
			// behavior expected
		} catch (Exception e) {
			System.out.println(
					"Problem detected: Your FolderExplorer.getContents() has thrown" + " a non expected exception.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * tests lookupByName method in FolderExplorer
	 * 
	 * @param folder
	 * @return boolean
	 */
	public static boolean testLookupByFileName(File folder) {
		// Scenario 1
		// base case
		try {
			String path = FolderExplorer.lookupByName(folder, "zyBooksCh1.txt");
			String expected = "cs300" + File.separator + "reading notes" + File.separator + "zyBooksCh1.txt";
			if (!path.equals(expected)) {
				System.out.println(path);
				return false;
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
			return false;
		}
		// Scenario 2
		// tests to make sure it returns an exception if given a file that
		// is not in the given directory
		try {
			FolderExplorer.lookupByName(folder, "zyBooksCh.txt");
			return false;
		} catch (NoSuchElementException e) {
		}
		// Scenario 3
		// tests if it correctly gives pathname when file is in sub-folders
		try {
			String path = FolderExplorer.lookupByName(folder, "ClimbingTracker.java");
			String expected = "cs300" + File.separator + "programs" + File.separator + "p01" + File.separator
					+ "ClimbingTracker.java";
			if (!path.equals(expected)) {
				System.out.print(path);
				return false;
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	/**
	 * Tests the base case for lookupByKey method in FolderExplorer
	 * 
	 * @param folder
	 * @return boolean
	 */
	public static boolean testLookupByKeyBaseCase(File folder) {
		// makes sure the arrayList returns has only the following files
		// from the base case
		try {
			File f = new File(folder.getPath() + File.separator + "reading notes");
			ArrayList<String> list = FolderExplorer.lookupByKey(f, ".txt");
			ArrayList<String> expected = new ArrayList<String>();
			expected.add("zyBooksCh1.txt");
			expected.add("zyBooksCh2.txt");
			expected.add("zyBooksCh3.txt");
			expected.add("zyBooksCh4.txt");
			if (list.size() != 4) {
				System.out.println("Problem detected: cs300 folder must contain 4 elements.");
				return false;
			}
			for (int i = 0; i < expected.size(); i++) {
				if (!list.contains(expected.get(i))) {
					System.out.println("Problem detected: " + expected.get(i)
							+ " is missing from the output of the list contents of cs300 folder.");
					return false;
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	/**
	 * Main method for FolderExplorerTester gets things started
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("testGetContents: " + testGetContents(new File("cs300")));
		System.out.println("testGetDeepContents Base Case: "
				+ testDeepGetContentsBaseCase(new File("cs300" + File.separator + "reading notes")));
		System.out.println("testGetDeepContents Recursive Case: " + testDeepListRecursiveCase(new File("cs300")));
		System.out.println("testLookupByFileName: " + testLookupByFileName(new File("cs300")));
		System.out.println("testLookupByKey: " + testLookupByKeyBaseCase(new File("cs300")));
	}

}
