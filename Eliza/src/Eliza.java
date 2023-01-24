
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Eliza
// Files:           Config.java
// Course:          CS200, Fall, 2018
//
// Author:          Cole Johnstone
// Email:           cjohnstone@wisc.edu 
// Lecturer's Name: Mark Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//
// Online Sources:  Piazza help from other students
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * The Eliza class holds the user input and response formation for a system that
 * collects user input and responds appropriately. Eliza is based off of a
 * computer program written at MIT in the 1960's by Joseph Weizenbaum. Eliza
 * uses keyword matching to respond to users in a way that displays interest in
 * the users and continues the conversation until instructed otherwise.
 */
public class Eliza {

	/*
	 * This method does input and output with the user. It calls supporting methods
	 * to read and write files and process each user input.
	 * 
	 * @param args (unused)
	 */
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		Random rand = new Random(Config.SEED);
		String arg = "";
		boolean safe = true;
		ArrayList<ArrayList<String>> load = new ArrayList<ArrayList<String>>();
		ArrayList<String> dialog = new ArrayList<String>();
		// when the name of the AI cannot be used it will keep asking for
		// one of the usable names
		while (safe) {
			// only one argument then that argument will be used
			if (args.length == 1) {
				arg = args[0];
				// will give options of arguments if more than one
			} else if (args.length > 1) {
				String names = "";
				for (int i = 0; i < args.length; i++) {
					if (i != args.length - 1) {
						names = names + args[i] + ", ";
					} else {
						names = names + "or " + args[i];
					}
				}
				System.out.println("Would you like to speak with " + names + "?");
				arg = scnr.nextLine();
				// if there are no arguments the default is Eliza
			} else {
				arg = "Eliza";
			}
			load = loadResponseTable(arg + Config.RESPONSE_FILE_EXTENSION);
			// tests if there is enough rows to run and didnt run into an exception
			if (load.size() > 6) {
				safe = false;
			} else {

			}
		}
		// dialog.add saves the conversation
		String prompt = "Hi I'm " + arg + ", what is your name?";
		dialog.add(prompt);
		System.out.println(prompt);
		// gets users name
		String name = scnr.nextLine();
		dialog.add(name);
		String greet = "Nice to meet you " + name + ". What is on your mind?";
		dialog.add(greet);
		System.out.println(greet);
		// starts the conversation
		String onMind = scnr.nextLine();
		dialog.add(onMind);
		boolean quit = false;
		// if there is no quit word in the userinput it will continue the
		// conversation
		while (quit == false) {
			ArrayList<String> testQuit = separatePhrases(onMind);
			String[] array = prepareInput(onMind);
			// if there is no quit word it will prepare a response and print it out
			// then read another input
			if (foundQuitWord(testQuit) == false) {
				String resp = prepareResponse(array, rand, load);
				dialog.add(resp);
				System.out.println(resp);
				onMind = scnr.nextLine();
				dialog.add(onMind);
			} else {
				quit = true;
			}

		}
		String goodbye = "Goodbye " + name + ".";
		System.out.println(goodbye);
		dialog.add(goodbye);
		boolean saved = false;
		boolean doublebreak = false;
		boolean ask = false;
		String filename = "";
		// asks if the user would like to save the dialog
		while (!ask) {
			if (doublebreak == true) {
				break;
			}
			System.out.println("Would you like to save a recording of this conversation?");
			String yes = scnr.next();
			// if the first nonwhitespace character is a y or Y it will save the
			// conversation
			for (int i = 0; i < yes.length(); i++) {
				if (Character.isWhitespace(yes.charAt(i))) {
					continue;
				} else if (yes.charAt(0) == 'y' || yes.charAt(0) == 'Y') {
					saved = true;
					System.out.println("What would you like to call the filename?");
					scnr.nextLine();
					filename = scnr.nextLine();
					try {
						saveDialog(dialog, filename + ".txt");
						// looks for exceptions when trying to save
					} catch (IOException e) {
						System.out.println("Unable to save conversation to " + filename);
						break;
					}
					doublebreak = true;
				} else {
					ask = true;
					break;
				}
			}
		}
		scnr.close();
		// if they chose to save the convo it tells them where it is saved else says
		// goodbye
		if (saved) {
			System.out.println("Thanks again for talking! Our conversation is saved in: " + filename);
		} else {
			System.out.println("Ok. Goodbye.");
		}

	}

	/**
	 * This method processes the user input, returning an ArrayList containing
	 * Strings, where each String is a phrase from the user's input. This is done by
	 * removing leading and trailing whitespace, making the user's input all lower
	 * case, then going through each character of the user's input. When going
	 * through each character this keeps all digits, alphabetic characters and '
	 * (single quote). The characters ? ! , . signal the end of a phrase, and
	 * possibly the beginning of the next phrase, but are not included in the
	 * result. All other characters such as ( ) - " ] etc. should be replaced with a
	 * space. This method makes sure that every phrase has some visible characters
	 * but no leading or trailing whitespace and only a single space between words
	 * of a phrase. If userInput is null then return null, if no characters then
	 * return a 0 length list, otherwise return a list of phrases. Empty phrases and
	 * phrases with just invalid/whitespace characters should NOT be added to the
	 * list.
	 * 
	 * Example userInput: "Hi, I am! a big-fun robot!!!" Example returned: "hi", "i
	 * am", "a big fun robot"
	 * 
	 * @param userInput text the user typed
	 * @return the phrases from the user's input
	 */
	public static ArrayList<String> separatePhrases(String userInput) {
		char next = '\u0000';
		String str = "";
		ArrayList<String> phrases = new ArrayList<String>();
		// runs through the string to separate the phrases
		for (int i = 0; i < userInput.length(); i++) {
			next = userInput.charAt(i);
			// adds the letter to the phrase if it isn't punctuation
			if (next == '\'' || Character.isLetter(next) || Character.isDigit(next)) {
				str = str + next;
				// stops adding the letters to the string
				// and creates a row for the phrase just created
			} else if (next == '?' || next == '!' || next == ',' || next == '.') {
				if (str.length() > 0) {
					if (!str.equals(" ")) {
						str = str.trim().toLowerCase();
						phrases.add(str);
						str = "";
					}
				}
				// makes sure there is only one space
			} else if (Character.isWhitespace(next)) {
				if (i > 0) {
					if (next == userInput.charAt(i - 1)) {
					} else {
						str = str + " ";
					}
				}
			} else {
				str = str + " ";
			}

		}
		// if the string is long enough it will trim it and make all letters lowercase
		if (str.trim().length() != 0) {
			str = str.trim().toLowerCase();
			phrases.add(str);
		}
		return phrases;
	}

	/**
	 * Checks whether any of the phrases in the parameter match a quit word from
	 * Config.QUIT_WORDS. Note: complete phrases are matched, not individual words
	 * within a phrase.
	 * 
	 * @param phrases List of user phrases
	 * @return true if any phrase matches a quit word, otherwise false
	 */
	public static boolean foundQuitWord(ArrayList<String> phrases) {
		// runs through the phrases array and compares it to quit
		// words and sees if they match and returns true if it does else returns false
		String[] quit = Config.QUIT_WORDS;
		for (String str : quit) {
			for (String str2 : phrases) {
				if (str2.equals(str)) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Iterates through the phrases of the user's input, finding the longest phrase
	 * to which to respond. If two phrases are the same length, returns whichever
	 * has the lower index in the list. If phrases parameter is null or size 0 then
	 * return null.
	 * 
	 * @param phrases List of user phrases
	 * @return the selected phrase
	 */
	public static String selectPhrase(ArrayList<String> phrases) {
		int num = 0;
		String str = "";
		// returns null if the phrases parameter is null or the size is less than 1
		if (phrases == null || phrases.size() < 1) {
			return null;
		}
		// checks for longest phrase
		for (String str2 : phrases) {
			if (str2.length() > num) {
				num = str2.length();
				str = str2;
				// if the phrases are the longest and the same length it
				// will return the one that came first
			} else if (str2.length() == str.length()) {
			}
		}

		return str;
	}

	/**
	 * Looks for a replacement word for the word parameter and if found, returns the
	 * replacement word. Otherwise if the word parameter is not found then the word
	 * parameter itself is returned. The wordMap parameter contains rows of match
	 * and replacement strings. On a row, the element at the 0 index is the word to
	 * match and if it matches return the string at index 1 in the same row. Some
	 * example word maps that will be passed in are Config.INPUT_WORD_MAP and
	 * Config.PRONOUN_MAP.
	 * 
	 * If word is null return null. If wordMap is null or wordMap length is 0 simply
	 * return word parameter. For this implementation it is reasonable to assume
	 * that if wordMap length is >= 1 then the number of elements in each row is at
	 * least 2.
	 * 
	 * @param word    The word to look for in the map
	 * @param wordMap The map of words to look in
	 * @return the replacement string if the word parameter is found in the wordMap
	 *         otherwise the word parameter itself.
	 */
	public static String replaceWord(String word, String[][] wordMap) {
		// if the word is null return null
		if (word == null) {
			return null;
		}
		// if the wordMap is null or its length is zero return the String word
		if (wordMap == null || wordMap.length == 0) {
			return word;
		}
		// changes the word if it is equal to one of the words in wordMap in the
		// first index of each row
		for (int i = 0; i < wordMap.length; i++) {
			if (wordMap[i][0].equals(word)) {
				word = wordMap[i][1];
			}
		}
		return word;
	}

	/**
	 * Concatenates the elements in words parameter into a string with a single
	 * space between each array element. Does not change any of the strings in the
	 * words array. There are no leading or trailing spaces in the returned string.
	 * 
	 * @param words a list of words
	 * @return a string containing all the words with a space between each.
	 */
	public static String assemblePhrase(String[] words) {
		String sentup = "";
		int i = 0;
		// forms a string of words from an array of words
		for (String sent : words) {
			// adds a space after each word if its not the first word
			if (words.length - 1 > i) {
				sentup = sentup + sent + " ";
			} else {
				sentup = sentup + sent;
			}
			i++;
		}
		return sentup;
	}

	/**
	 * Replaces words in phrase parameter if matching words are found in the mapWord
	 * parameter. A word at a time from phrase parameter is looked for in wordMap
	 * which may result in more than one word. For example: i'm => i am Uses the
	 * replaceWord and assemblePhrase methods. Example wordMaps are
	 * Config.PRONOUN_MAP and Config.INPUT_WORD_MAP. If wordMap is null then phrase
	 * parameter is returned. Note: there will Not be a case where a mapping will
	 * itself be a key to another entry. In other words, only one pass through
	 * swapWords will ever be necessary.
	 * 
	 * @param phrase  The given phrase which contains words to swap
	 * @param wordMap Pairs of corresponding match & replacement words
	 * @return The reassembled phrase
	 */
	public static String swapWords(String phrase, String[][] wordMap) {
		// returns the phrase if the phrase is null or wordMap is null
		if (phrase == null || wordMap == null) {
			return phrase;
		}
		// creates a new array with elements divided by " "
		String[] Words2 = phrase.split(" ");
		int g = 0;
		// checks every element of the array to see if it needs to be replaced
		for (String newest : Words2) {
			newest = replaceWord(newest, wordMap);
			Words2[g] = newest;
			g++;
		}
		// creates a new String with assemble phrase and the array just put together
		phrase = assemblePhrase(Words2);
		return phrase;
	}

	/**
	 * This prepares the user input. First, it separates input into phrases (using
	 * separatePhrases). If a phrase is a quit word (foundQuitWord) then return
	 * null. Otherwise, select a phrase (selectPhrase), swap input words (swapWords
	 * with Config.INPUT_WORD_MAP) and return an array with each word its own
	 * element in the array.
	 * 
	 * @param input The input from the user
	 * @return words from the selected phrase
	 */
	public static String[] prepareInput(String input) {
		// returns an array of the input if the length of the input is less than 1
		if (input.length() < 1) {
			String[] words = new String[1];
			words[0] = input;
			return words;
		}
		ArrayList<String> phrases = separatePhrases(input);
		boolean bool = foundQuitWord(phrases);
		// if there is a quit word return null;
		if (bool) {
			return null;
		}
		// runs through some tests to create the final array to return
		String newPhrase = selectPhrase(phrases);
		String swappedWords = swapWords(newPhrase, Config.INPUT_WORD_MAP);
		String[] words = swappedWords.split(" ");
		return words;
	}

	/**
	 * Reads a file that contains keywords and responses. A line contains either a
	 * list of keywords or response, any blank lines are ignored. All leading and
	 * trailing whitespace on a line is ignored. A keyword line begins with
	 * "keywords" with all the following tokens on the line, the keywords. Each line
	 * that follows a keyword line that is not blank is a possible response for the
	 * keywords. For example (the numbers are for our description purposes here and
	 * are not in the file):
	 * 
	 * 1 keywords computer 2 Do computers worry you? 3 Why do you mention computers?
	 * 4 5 keywords i dreamed 6 Really, <3>? 7 Have you ever fantasized <3> while
	 * you were awake? 8 9 Have you ever dreamed <3> before?
	 *
	 * In line 1 is a single keyword "computer" followed by two possible responses
	 * on lines 2 and 3. Line 4 and 8 are ignored since they are blank (contain only
	 * whitespace). Line 5 begins new keywords that are the words "i" and "dreamed".
	 * This keyword list is followed by three possible responses on lines 6, 7 and
	 * 9.
	 * 
	 * The keywords and associated responses are each stored in their own ArrayList.
	 * The response table is an ArrayList of the keyword and responses lists. For
	 * every keywords list there is an associated response list. They are added in
	 * pairs into the list that is returned. There will always be an even number of
	 * items in the returned list.
	 * 
	 * Note that in the event an IOException occurs when trying to read the file
	 * then an error message "Error reading <fileName>", where <fileName> is the
	 * parameter, is printed and a non-null reference is returned, which may or may
	 * not have any elements in it.
	 * 
	 * @param fileName The name of the file to read
	 * @return The response table
	 */
	public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {
		try {
			// creates a new file with the given file name
			File file = new File(fileName);
			Scanner scnr = new Scanner(file);
			ArrayList<ArrayList<String>> keywordRes = new ArrayList<ArrayList<String>>();
			// loops if there is another line to read
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine();
				String words = "";
				// if the line contains the word keywords it will continue with
				// the following actions
				if (line.contains("keywords")) {
					ArrayList<String> keywords = new ArrayList<String>();
					char ch = '\u0000';
					if (line.length() < 10) {
						keywords.add(" ");
						continue;
					}
					for (int i = 9; i < line.length(); i++) {
						ch = line.charAt(i);
						if (Character.isWhitespace(ch)) {
							keywords.add(words);
							words = "";
						} else if (i == line.length() - 1) {
							words = words + ch;
							keywords.add(words);
							words = "";
						} else {
							words = words + ch;
						}

					}
					keywordRes.add(keywords);
				}
				// instead of having keywords if the length of the line is less than two
				// loop again
				else if (line.length() < 2) {
					continue;
				}
				// neither of those options means its a response
				else {
					ArrayList<String> responses = new ArrayList<String>();
					// while there is no keywords in the line check the line
					while (line.indexOf("keywords") == -1) {
						// if the there isn't a next line and the length of the line read
						// before is greater than 1 add it to the arraylist
						if (!scnr.hasNextLine()) {
							if (line.length() > 1) {
								responses.add(line);
							}
							// if there is a next line but the line length is shorter than 1
							// break the loop
							break;
						}
						// if the line is too short skip it and continue the loop
						if (line.length() < 2) {
							line = scnr.nextLine();
							continue;
						}
						// else add the line to the responses array and read next line
						else {
							responses.add(line);
							line = scnr.nextLine();
						}
					}
					// if there is no next line add the responses array
					// list and break the loop
					if (!scnr.hasNextLine()) {
						keywordRes.add(responses);
						break;
					}

					keywordRes.add(responses);
					ArrayList<String> keywords = new ArrayList<String>();
					// adds the keywords without the term keywords
					for (int i = 9; i < line.length(); i++) {
						char ch = line.charAt(i);
						if (Character.isWhitespace(ch)) {
							keywords.add(words);
							words = "";
						} else if (i == line.length() - 1) {
							words = words + ch;
							keywords.add(words);
							words = "";
						} else {
							words = words + ch;
						}

					}
					keywordRes.add(keywords);
				}

			}
			scnr.close();
			return keywordRes;
			// catches an exception, prints out a statement and returns a 3 by 2 arraylist
		} catch (IOException e) {
			System.out.println("Error reading " + fileName);
			ArrayList<String> nonNull2 = new ArrayList<String>();
			ArrayList<String> nonNull3 = new ArrayList<String>();
			ArrayList<ArrayList<String>> nonNull = new ArrayList<ArrayList<String>>();
			nonNull.add(nonNull2);
			nonNull.add(nonNull3);
			nonNull2.add("Hi");
			nonNull2.add("hi");
			nonNull3.add("Hi");
			nonNull3.add("Hi");
			return nonNull;
		}

	}

	/**
	 * Checks to see if the keywords match the sentence. In other words, checks to
	 * see that all the words in the keyword list are in the sentence and in the
	 * same order. If all the keywords match then this method returns an array with
	 * the unmatched words before, between and after the keywords. If the keywords
	 * do not match then null is returned.
	 * 
	 * When the phrase contains elements before, between, and after the keywords,
	 * each set of the three is returned in its own element String[] keywords =
	 * {"i", "dreamed"}; String[] phrase = {"do", "you", "know", that", "i", "have",
	 * "dreamed", "of", "being", "an", "astronaut"};
	 * 
	 * toReturn[0] = "do you know that" toReturn[1] = "have" toReturn[2] = "of being
	 * an astronaut"
	 * 
	 * In an example where there is a single keyword, the resulting List's first
	 * element will be the the pre-sequence element and the second element will be
	 * everything after the keyword, in the phrase String[] keywords = {"always"};
	 * String[] phrase = {"I", "always", "knew"};
	 * 
	 * toReturn[0] = "I" toReturn[1] = "knew"
	 * 
	 * In an example where a keyword is not in the phrase in the correct order, null
	 * is returned. String[] keywords = {"computer"}; String[] phrase = {"My","dog",
	 * "is", "lost"};
	 * 
	 * return null
	 * 
	 * @param keywords The words to match, in order, in the sentence.
	 * @param phrase   Each word in the sentence.
	 * @return The unmatched words before, between and after the keywords or null if
	 *         the keywords are not all matched in order in the phrase.
	 */
	public static String[] findKeyWordsInPhrase(ArrayList<String> keywords, String[] phrase) {
		// returns an array with the assembled phrase if the keywords size is 0
		if (keywords.size() == 0) {
			return new String[] { assemblePhrase(phrase) };
		}

		int k = 0;
		String[] unmatched = new String[keywords.size() + 1];
		// initializes the elements in the unmatched array to ""
		for (int i = 0; i < unmatched.length; i++) {
			unmatched[i] = "";
		}
		// runs through the sentence to check for keywords, stops and adds the unmatched
		// words
		// to the unmatched array
		for (int i = 0; i < phrase.length; i++) {
			if (k < keywords.size() && keywords.get(k).equals(phrase[i])) {
				k += 1;
			} else {
				unmatched[k] += phrase[i] + " ";
			}
		}
		// if the size of the array is not equal to the correct size returns null
		if (k != keywords.size())
			return null;
		// gets rid of leading and trailing whitespace
		for (int i = 0; i < unmatched.length; i++) {
			unmatched[i] = unmatched[i].trim();
		}
		return unmatched;
	}

	/**
	 * Selects a randomly generated response within the list of possible responses
	 * using the provided random number generator where the number generated
	 * corresponds to the index of the selected response. Use Random nextInt(
	 * responseList.size()) to generate the random number. If responseList is null
	 * or 0 length then return null.
	 * 
	 * @param rand         A random number generator.
	 * @param responseList A list of responses to choose from.
	 * @return A randomly selected response
	 */
	public static String selectResponse(Random rand, ArrayList<String> responseList) {
		// if the responselist is null or the size of the response list is 0 returns
		// null
		if (responseList == null || responseList.size() == 0) {
			return null;
		}
		// chooses a random response from the given list
		int num = rand.nextInt(responseList.size());
		String str = responseList.get(num);
		return str;
	}

	/**
	 * This method takes processed user input and forms a response. This looks
	 * through the response table in order checking to see if each keyword pattern
	 * matches the userWords. The first matching keyword pattern found determines
	 * the list of responses to choose from. A keyword pattern matches the
	 * userWords, if all the keywords are found, in order, but not necessarily
	 * contiguous. This keyword matching is done by findKeyWordsInPhrase method. See
	 * the findKeyWordsInPhrase algorithm in the Eliza.pdf.
	 * 
	 * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned.
	 * Otherwise one of possible responses for the matched keywords is selected with
	 * selectResponse method. The response selected is checked for the replacement
	 * symbol <n> where n is 1 to the length of unmatchedWords array returned by
	 * findKeyWordsInPhrase. For each replacement symbol the corresponding unmatched
	 * words element (index 0 for <1>, 1 for <2> etc.) has its pronouns swapped with
	 * swapWords using Config.PRONOUN_MAP and then replaces the replacement symbol
	 * in the response.
	 * 
	 * @param userWords     using input after preparing.
	 * @param rand          A random number generator.
	 * @param responseTable A table containing a list of keywords and response
	 *                      pairs.
	 * @return The generated response
	 */
	public static String prepareResponse(String[] userWords, Random rand, ArrayList<ArrayList<String>> responseTable) {
		// goes through every element of the response list
		for (int i = 0; i < responseTable.size(); i += 2) {
			String[] unmatchedWords = findKeyWordsInPhrase(responseTable.get(i), userWords);
			// if unmatched word array is not null chooses a response
			if (unmatchedWords != null) {
				ArrayList<String> responses = responseTable.get(i + 1);
				String res = selectResponse(rand, responses);
				// goes through the unmatchedWords array and checks for <n>
				for (int j = 0; j < unmatchedWords.length; j++) {
					String num = "<" + (j + 1) + ">";
					// if it does contain it chagne that to an appropriate response
					// to what the user inputs
					if (res.contains(num)) {
						String swap = swapWords(unmatchedWords[j], Config.PRONOUN_MAP);
						res = res.replaceFirst(num, swap);
					}
				}
				return res;
			}
		}
		// if no keyword pattern is matched returns Config.NO_MATCH_RESPONSE
		return Config.NO_MATCH_RESPONSE;
	}

	/**
	 * Creates a file with the given name, and fills that file line-by-line with the
	 * tracked conversation. Every line ends with a newline. Throws an IOException
	 * if a writing error occurs.
	 * 
	 * @param dialog   the complete conversation
	 * @param fileName The file in which to write the conversation
	 * @throws IOException
	 */
	public static void saveDialog(ArrayList<String> dialog, String fileName) throws IOException {
		File file = new File(fileName);
		// creates a file and opens it if it can
		try {
			FileWriter out = new FileWriter(file);
			// writes the dialog to the file array element by array element
			for (String str : dialog) {
				out.write(str + "\n");
			}
			out.close();
			// catches a possible file exception
		} catch (IOException e) {
			System.out.println("exception occured " + e);
		}
	}
}