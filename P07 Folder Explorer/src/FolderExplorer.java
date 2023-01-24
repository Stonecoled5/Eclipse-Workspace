
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
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class that peruses through the folder provided
 * 
 * @author Cole Johnstone
 *
 */
public class FolderExplorer {

	/**
	 * Returns a list of the names of all files and directories in the the given
	 * folder currentDirectory. Throws NotDirectoryException with a description
	 * error message if the provided currentDirectory does not exist or if it is not
	 * a directory
	 * 
	 * @param currentDirectory
	 * @return ArrayList of names of all files and directories
	 * @throws NotDirectoryException
	 */
	public static ArrayList<String> getContents(File currentDirectory) throws NotDirectoryException {
		// makes sure currentDirectory can be used
		if (!currentDirectory.exists()) {
			throw new NotDirectoryException("The current directory does not exist");
		}
		if (!currentDirectory.isDirectory()) {
			throw new NotDirectoryException("The current directory is not a directory");
		}
		// adds all content from list command
		ArrayList<String> contents = new ArrayList<String>();
		String[] pathArray = currentDirectory.list();
		for (int i = 0; i < pathArray.length; i++) {
			contents.add(pathArray[i]);
		}
		return contents;
	}

	/**
	 * Recursive method that lists the names of all the files (not directories) in
	 * the given directory and its sub-directories. Throws NotDirectoryException
	 * with a description error message if the provided currentDirectory does not
	 * exist or if it is not a directory
	 * 
	 * @param currentDirectory
	 * @return ArrayList of names of all files and directories
	 * @throws NotDirectoryException
	 */
	public static ArrayList<String> getDeepContents(File currentDirectory) throws NotDirectoryException {
		// makes sure currentDirectory can be used
		if (!currentDirectory.exists()) {
			throw new NotDirectoryException("The current directory does not exist");
		}
		if (!currentDirectory.isDirectory()) {
			throw new NotDirectoryException("The current directory is not a directory");
		}
		// gets files in currentDirectory goes through and adds files to arraylist
		File[] fileArray = currentDirectory.listFiles();
		ArrayList<String> contents = new ArrayList<String>();
		for (int i = 0; i < fileArray.length; i++) {
			if (fileArray[i].isDirectory()) {
				contents.addAll(getDeepContents(fileArray[i]));
			} else {
				contents.add(fileArray[i].getName());
			}
		}
		return contents;
	}

	/**
	 * Searches the given directory and all of its sub-directories for an exact
	 * match to the provided fileName. This method returns a path to the file, if it
	 * exists. Throws NoSuchElementException with a descriptive error message if the
	 * search operation returns with no results found (including the case if
	 * fileName is null or currentDirectory does not exist, or was not a directory)
	 * 
	 * @param currentDirectory
	 * @param fileName
	 * @return path for fileName
	 */
	public static String lookupByName(File currentDirectory, String fileName) throws NoSuchElementException {
		// makes sure currentDirectory and fileName can be used
		if (fileName.equals(null))
			throw new NoSuchElementException("The fileName is null");
		if (!currentDirectory.exists()) {
			throw new NoSuchElementException("The current directory does not exist");
		}
		if (!currentDirectory.isDirectory()) {
			throw new NoSuchElementException("The current directory is not a directory");
		}
		// goes through first list of files under currentDirectory
		// sends sub-directories to helper method
		String path = "";
		File[] fileArray = currentDirectory.listFiles();
		for (int i = 0; i < fileArray.length; i++) {
			if (fileArray[i].isDirectory()) {
				path = lookupByNameHelper(fileArray[i], fileName, path);
			}
			if (fileArray[i].getName().equals(fileName)) {
				path = fileArray[i].getPath();
				return path;
			}
			if (!path.equals(""))
				return path;// to only return one path

		}
		if (path.equals("")) {
			throw new NoSuchElementException("The file does not exist in this directory");
		}
		return path;
	}

	/**
	 * Helper method for lookupByNameHelper
	 * 
	 * @param currentDirectory
	 * @param fileName
	 * @param path
	 * @return path for fileName
	 */
	public static String lookupByNameHelper(File currentDirectory, String fileName, String path) {
		// goes through given sub-directory and adds the pathname
		// to path if the file matches the fileName
		File[] fileArray = currentDirectory.listFiles();
		if (fileArray != null) {
			for (int i = 0; i < fileArray.length; i++) {
				if (fileArray[i].isDirectory()) {
					path = lookupByNameHelper(fileArray[i], fileName, path);
				}
				if (fileArray[i].getName().equals(fileName)) {
					return fileArray[i].getPath();
				} 
			}
		}
		return path;
	}

	/**
	 * Recursive method that searches the given folder and its sub-directories for
	 * ALL files that contain the given key in part of their name. Returns An
	 * arraylist of all the names of files that match and an empty arraylist when
	 * the operation returns with no results found (including the case where
	 * currentDirectory is not a directory)
	 * 
	 * @param currentDirectory
	 * @param key
	 * @return arrayList with all files ending with key
	 */
	public static ArrayList<String> lookupByKey(File currentDirectory, String key) {
		// makes sure currentDirectory and key can be used
		if (key.equals(null))
			return new ArrayList<String>();
		if (!currentDirectory.exists()) {
			return new ArrayList<String>();
		}
		if (!currentDirectory.isDirectory()) {
			return new ArrayList<String>();
		}
		// goes through listed files and sends sub-directories recursively
		// and adds files with the same ending key to the arrayList
		File[] fileArray = currentDirectory.listFiles();
		ArrayList<String> contents = new ArrayList<String>();
		for (int i = 0; i < fileArray.length; i++) {
			if (fileArray[i].isDirectory()) {
				contents.addAll(lookupByKey(fileArray[i], key));
			}
			if (fileArray[i].getName().contains(key)) {
				contents.add(fileArray[i].getName());
			}
		}
		return contents;
	}

	/**
	 * Recursive method that searches the given folder and its sub-directories for
	 * ALL files whose size is within the given max and min values, inclusive.
	 * Returns an array list of the names of all files whose size are within the
	 * boundaries and an empty arraylist if the search operation returns with no
	 * results found (including the case where currentDirectory is not a directory)
	 * 
	 * @param currentDirectory
	 * @param sizeMin
	 * @param sizeMax
	 * @return ArrayList with files within designated size parameters
	 */
	public static ArrayList<String> lookupBySize(File currentDirectory, long sizeMin, long sizeMax) {
		// makes sure currentDirectory can be used
		if (!currentDirectory.exists()) {
			return new ArrayList<String>();
		}
		if (!currentDirectory.isDirectory()) {
			return new ArrayList<String>();
		}
		// goes through listed files and sends sub-directories recursively
		// and adds files with a size within the parameters given to the arrayList
		File[] fileArray = currentDirectory.listFiles();
		ArrayList<String> contents = new ArrayList<String>();
		for (int i = 0; i < fileArray.length; i++) {
			if (fileArray[i].isDirectory()) {
				contents.addAll(lookupBySize(fileArray[i], sizeMin, sizeMax));
			}
			if (fileArray[i].isFile()) {
				if (fileArray[i].length() > sizeMin && fileArray[i].length() < sizeMax) {
					contents.add(fileArray[i].getName());
				}
			}
		}
		return contents;
	}
}
