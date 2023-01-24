
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Eliza Tests
// Files:           Config.java, Eliza.java
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the Eliza class as
 * they are developed. These methods are private since they are only intended
 * for use within this class.
 * 
 * @author Jim Williams
 * @author TODO add your name here when you add tests and comment the tests
 *
 */
public class ElizaTests {

	/**
	 * This is the main method that runs the various tests. Uncomment the tests when
	 * you are ready for them to run.
	 * 
	 * @param args (unused)
	 */
	public static void main(String[] args) {

		// Milestone 1: Process User Input
		// M1: main nothing to do
		testSeparatePhrases();
		testFoundQuitWord();
		testSelectPhrase();
		testReplaceWord();
		testAssemblePhrase();

		// Milestone 2:
		// M2: implement parts of main as described in main method comments
		testSwapWords();
		testPrepareInput();
		testLoadResponseTable();

		// Milestone 3:
		// main: implement the rest of main as described in the main method comments
		testFindKeyWordsInPhrase();
		testSelectResponse();
		testInputAndResponse();
		testSaveDialog();
	}

	/**
	 * This runs some tests on the separatePhrases method.
	 * 
	 */
	private static void testSeparatePhrases() {
		boolean error = false;

		// 1. This test tests if separate phrases separates by punctuation
		// and trims the whitespace
		ArrayList<String> phrases = Eliza.separatePhrases("No.  I'm talking to my dog! Bye.");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("no");
		expected.add("i'm talking to my dog");
		expected.add("bye");
//tests if separate phrases made an array list with the correct size
		if (phrases.size() != expected.size()) {
			error = true;
			System.out.println(
					"testSeparatePhrases: expected " + expected.size() + " but found " + phrases.size() + " phrases.");
		}

		// 2. tests if the contents are correct
		for (int i = 0; i < expected.size(); i++) {
			if (!expected.get(i).equals(phrases.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected.get(i));
				System.out.println("  " + phrases.get(i));
			}
		}
		// 3 tests the most complicated phrase to separate. gets rid of
		// surrounding whitespace and separates by punctuation
		ArrayList<String> phrases2 = Eliza.separatePhrases("  ,  Hello   World !!! How're you doing ? ... ");
		ArrayList<String> expected2 = new ArrayList<>();
		expected2.add("hello world");
		expected2.add("how're you doing");

//tests size again
		if (phrases2.size() != expected2.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " + expected2.size() + " but found " + phrases2.size()
					+ " phrases.");
			System.out.println(phrases2);
		}

		// 4.tests if contents match correct answer
		for (int i = 0; i < expected2.size(); i++) {
			if (!expected2.get(i).equals(phrases2.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected2.get(i));
				System.out.println("  " + phrases2.get(i));
			}
		}
		// 5 tests the separation of the sentence with multiple punctuation
		ArrayList<String> phrases3 = Eliza.separatePhrases("Thank you, but no, I have to go. Goodbye!!!");
		ArrayList<String> expected3 = new ArrayList<>();
		expected3.add("thank you");
		expected3.add("but no");
		expected3.add("i have to go");
		expected3.add("goodbye");

//tests size
		if (phrases3.size() != expected3.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " + expected3.size() + " but found " + phrases3.size()
					+ " phrases.");
			System.out.println(phrases3);
		}

		// 6. tests contents of arraylist
		for (int i = 0; i < expected3.size(); i++) {
			if (!expected3.get(i).equals(phrases3.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected3.get(i));
				System.out.println("  " + phrases3.get(i));
			}
		}

		if (error) {
			System.out.println("testSeparatePhrases failed");
		} else {
			System.out.println("testSeparatePhrases passed");
		}
	}

	/**
	 * This runs some tests on the foundQuitWord method.
	 * 
	 */
	private static void testFoundQuitWord() {
		boolean error = false;

		// 1. finds a quit word if its not in the first row
		ArrayList<String> phrases = new ArrayList<>();
		phrases.add("thank you for talking");
		phrases.add("seey");
		phrases.add("seeya");
		phrases.add("seey");

		boolean quit = Eliza.foundQuitWord(phrases);
		if (!quit) {
			error = true;
			System.out.println("testFoundQuitWord 1: failed");
		}
		// 2 only takes a quit word if its in its own row not in a phrase
		ArrayList<String> phrases2 = new ArrayList<>();
		phrases2.add("seeya bud");
		phrases2.add("I don't like you anymore");

		boolean quit2 = Eliza.foundQuitWord(phrases2);
		if (quit2) {
			error = true;
			System.out.println("testFoundQuitWord 2: failed");
		}
		// 3 if no quit word returns false
		ArrayList<String> phrases3 = new ArrayList<>();
		phrases3.add("Hi bud");
		phrases3.add("I don't like you anymore");

		boolean quit3 = Eliza.foundQuitWord(phrases2);
		if (quit3) {
			error = true;
			System.out.println("testFoundQuitWord 3: failed");
		}

		if (error) {
			System.err.println("testFoundQuitWord failed");
		} else {
			System.out.println("testFoundQuitWord passed");
		}
	}

	/**
	 * This runs some tests on the selectPhrase method.
	 * 
	 */
	private static void testSelectPhrase() {
		boolean error = false;

		// 1 chooses longest phrase
		ArrayList<String> phrases = new ArrayList<>();
		phrases.add("no");
		phrases.add("sometimes I remember being on a boat");
		phrases.add("not often");
		String choice = Eliza.selectPhrase(phrases);
		if (!choice.equals("sometimes I remember being on a boat")) {
			error = true;
			System.out.println("testSelectPhrase 1: failed");
		}
		// 2 chooses longest phrases with lower index
		ArrayList<String> phrases2 = new ArrayList<>();
		phrases2.add("sometimes I remember being on a boat");
		phrases2.add("sometimes I remember being on a boaf");
		phrases2.add("not often");
		String choice2 = Eliza.selectPhrase(phrases2);
		if (!choice2.equals("sometimes I remember being on a boat")) {
			error = true;
			System.out.println("testSelectPhrase 2: failed");
		}

		if (error) {
			System.err.println("testSelectPhrase failed");
		} else {
			System.out.println("testSelectPhrase passed");
		}
	}

	/**
	 * This runs some tests on the assemblePhrase method.
	 *
	 */
	private static void testAssemblePhrase() {
		boolean error = false;
//expands the words array into a String sentence with a phrase with a space sentence
		String[] words = { "This", "is a", "sentence" };
		String sentence = Eliza.assemblePhrase(words);
		String expectedSentence = "This is a sentence";

		if (!sentence.equals(expectedSentence)) {
			error = true;
			System.out.println("testAssembleSentence 1 failed '" + sentence + "'");
		}
		// expands the words array into a String sentence with single words
		String[] words2 = { "This", "is", "a", "sentence" };
		String sentence2 = Eliza.assemblePhrase(words2);
		String expectedSentence2 = "This is a sentence";

		if (!sentence2.equals(expectedSentence2)) {
			error = true;
			System.out.println("testAssembleSentence 2 failed '" + sentence2 + "'");
		}

		if (error) {
			System.err.println("testAssemblePhrase failed");
		} else {
			System.out.println("testAssemblePhrase passed");
		}
	}

	/**
	 * This runs some tests on the findKeyWordsInPhrase method.
	 * 
	 */
	private static void testFindKeyWordsInPhrase() {
		boolean error = false;

		{// block so each test has its own variable scope.
			// 1. tests if find key words returns an array with the unmatched words divided
			// by the keywords
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "are", "you", "a", "computer" };

			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2 || !matches[0].equals("are you a") || !matches[1].equals("")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 1 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 2. if the first word of the array is a keyword replaces it with ""
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "computer", "are", "you" };

			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2 || !matches[0].equals("") || !matches[1].equals("are you")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 2 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 3.tests if find key words returns an array with the unmatched words divided
			// by the keywords with
			// a longer sentence
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "does", "that", "computer", "on", "your", "desk", "work" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2 || !matches[0].equals("does that")
					|| !matches[1].equals("on your desk work")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 3 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 4.tests if find key words returns an array with the unmatched words divided
			// by the keywords
			// and if the last word is a keyword replaces it with""
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("you");
			keywords.add("me");
			String[] phrase = { "why", "don't", "you", "like", "me" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 3 || !matches[0].equals("why don't") || !matches[1].equals("like")
					|| !matches[2].equals("")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 4 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 5.tests if find key words returns an array with the unmatched words divided
			// by the keywords
			// and if the last word is a keyword replaces it with""
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("you");
			keywords.add("me");
			String[] sentence = { "do", "you", "appreciate", "me" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
			if (matches == null || matches.length != 3 || !matches[0].equals("do") || !matches[1].equals("appreciate")
					|| !matches[2].equals("")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 5 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}
		// 6. if the keywords appear in the sentence but in the wrong order should
		// return null
		{
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("you");
			keywords.add("me");
			String[] sentence = { "me", "don't", "like", "you" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
			if (matches != null) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 6 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		if (error) {
			System.err.println("testFindKeyWordsInPhrase failed");
		} else {
			System.out.println("testFindKeyWordsInPhrase passed");
		}
	}

	/**
	 * This runs some tests on the saveDialog method.
	 * 
	 */
	private static void testSaveDialog() {
		boolean error = false;
		final String TEST_FILENAME = "testing.txt";
		{ // 1. tests if a single line is saved to a file
			ArrayList<String> list = new ArrayList<>();
			list.add("this is a single line.");
			try {
				Eliza.saveDialog(list, TEST_FILENAME);
				String readFromFile = readFile(TEST_FILENAME);
				// opens and reads to test if it ran correctly
				if (!readFromFile.equals(list.get(0) + "\n")) {
					error = true;
					System.out.println("testSaveDialog 1 failed.");
					System.out.println("content read: " + readFromFile);
				}
				removeFile(TEST_FILENAME);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		{ // 1. tests if two lines are saved to a document correctly
			ArrayList<String> list = new ArrayList<>();
			list.add("this is a single line.");
			list.add("This is a second line.");
			try {
				Eliza.saveDialog(list, TEST_FILENAME);
				String readFromFile = readFile(TEST_FILENAME);
				// opens and reads to test if it ran correctly
				if (!readFromFile.equals(list.get(0) + "\n" + list.get(1) + "\n")) {
					error = true;
					System.out.println("testSaveDialog 2 failed.");
					System.out.println("content read: " + readFromFile);
				}
				removeFile(TEST_FILENAME);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		if (error) {
			System.err.println("testSaveDialog failed");
		} else {
			System.out.println("testSaveDialog passed");
		}
	}

	/**
	 * Supporting method for testSaveDialog
	 * 
	 * @param fileName name of the file to read
	 * @return the contents of the file
	 */
	private static String readFile(String fileName) {
		StringBuffer buf = new StringBuffer();
		// tries to reads a file
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
			String line;
			// if a file is not null it'll add the lines to the buf StringBuffer
			while ((line = reader.readLine()) != null) {
				buf.append(line);
				buf.append("\n");
			}
			return buf.toString();
			// catches exceptions that may occur
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Supporting method for testSaveDialog.
	 * 
	 * @param filename file to be removed.
	 */
	private static void removeFile(String filename) {
		// deletes a file
		File file = new File(filename);
		try {
			if (file.exists())
				file.delete();
			// catches a possible exception
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This runs some tests on the replaceWord method.
	 * 
	 */
	private static void testReplaceWord() {
		boolean error = false;

		{ // 1. tests if the replaceWord correctly replaces the word using input word map
			String word = "machine";
			String expected = "computer";
			String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 1 failed. result:'" + result + "' expected:'" + expected + "'");
			}
		}

		{ // 2. tests if the replaceWord correctly replaces the word using pronoun map
			String word = "yourself";
			String expected = "myself";
			String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 2 failed. result:'" + result + "' expected:'" + expected + "'");
			}
		}
		{ // 3. makes sure it replaces contractions with the correct two words
			String word = "i'm";
			String expected = "you are";
			String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 3 failed. result:'" + result + "' expected:'" + expected + "'");
			}
		}
		{ // 4. if the map input in replace word is null it should return the word
			// inputted
			String word = "i'm";
			String expected = "i'm";
			String result = Eliza.replaceWord(word, null);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 2 failed. result:'" + result + "' expected:'" + expected + "'");
			}
		}

		if (error) {
			System.err.println("testReplaceWord failed");
		} else {
			System.out.println("testReplaceWord passed");
		}
	}

	/**
	 * This runs some tests on the swapWords method.
	 * 
	 */
	private static void testSwapWords() {
		boolean error = false;

		{ // 1. tests if the swap words replaces the words correctly using input word map
			// and returns the correct string
			String someWords = "i'm cant recollect";
			String expected = "i am cannot remember";
			String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 1 failed. result:'" + result + "' expected:'" + expected + "'");
			}
		}

		{ // 2. tests if the swap words replaces the words correctly using pronoun map
			// and returns the correct string
			String someWords = "i'm happy";
			String expected = "you are happy";
			String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 2 failed. result:'" + result + "' expected:'" + expected + "'");
			}
		}

		{ // 3. tests if the swap words replaces the words correctly using pronoun map
			// and returns the correct string
			String someWords = "about my dog";
			String expected = "about your dog";
			String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 3 failed. result:'" + result + "' expected:'" + expected + "'");
			}
		}
		{ // 4. tests if the swap words replaces the words correctly using input word map
			// and returns the correct string
			String someWords = "everybody hates dogs";
			String expected = "everyone hates dogs";
			String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 3 failed. result:'" + result + "' expected:'" + expected + "'");
			}
		}

		if (error) {
			System.err.println("testSwapWords failed");
		} else {
			System.out.println("testSwapWords passed");
		}
	}

	/**
	 * This runs some tests on the selectResponse method. 1. TODO describe each test
	 * in your own words. 2.
	 */
	private static void testSelectResponse() {
		boolean error = false;

		{ // 1.
			// tests if one of the 3 strings is chosen
			Random randGen = new Random(434);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("The");
			strList.add("happy");
			strList.add("cat");
			String choice = Eliza.selectResponse(randGen, strList);

			if (!(choice.equals("The") || choice.equals("happy") || choice.equals("cat"))) {
				error = true;
				System.out.println("testSelectResponse 1 failed.");
			}
		}

		{ // 2.
			// if called 1000 times, are the choices approximately random?
			Random randGen = new Random(765);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("this");
			strList.add("is");
			strList.add("a");
			strList.add("list");
			strList.add("to");
			strList.add("choose");
			strList.add("from");
			int[] actualCount = new int[strList.size()];
			int[] expectedCount = new int[] { 156, 146, 142, 138, 160, 130, 128 };
			// creates the actualCount array to compare to the expectedCount
			for (int iterationIndex = 0; iterationIndex < 1000; iterationIndex++) {
				String choice = Eliza.selectResponse(randGen, strList);
				for (int wordIndex = 0; wordIndex < strList.size(); wordIndex++) {
					if (choice.equals(strList.get(wordIndex))) {
						actualCount[wordIndex]++;
					}
				}
			}
			// since we set a seed on the random number generator we should know the
			// expected
			// outcome
			for (int countIndex = 0; countIndex < actualCount.length; countIndex++) {
				if (actualCount[countIndex] != expectedCount[countIndex]) {
					error = true;
					System.out.println("testSelectResponse 2 failed.");
					System.out.println("  expectedCount: " + Arrays.toString(expectedCount));
					System.out.println("  actualCount: " + Arrays.toString(actualCount));
				}
			}

		}
		{ // 3. If there is only one line there should be only one answer
			Random randGen = new Random(434);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("The");
			String choice = Eliza.selectResponse(randGen, strList);

			if (!choice.equals("The")) {
				error = true;
				System.out.println("testSelectResponse 3 failed.");
			}
		}
		{ // 4. If the inserted Arraylist is null select response should return null
			Random randGen = new Random(434);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("The");
			strList.add("happy");
			strList.add("cat");
			String choice = Eliza.selectResponse(randGen, null);

			if (choice != null) {
				error = true;
				System.out.println("testSelectResponse 4 failed.");
			}
		}

		if (error) {
			System.err.println("testSelectResponse failed");
		} else {
			System.out.println("testSelectResponse passed");
		}
	}

	/**
	 * This runs some tests on the prepareInput method.
	 * 
	 */
	private static void testPrepareInput() {
		boolean error = false;

		{ // 1.tests if there is only a quit word in the sentence prepare
			// input uses foundquitword to return null
			String input = "bye";
			String[] result = null;
			result = Eliza.prepareInput("bye");
			if (result != null) {
				error = true;
			}
			if (error) {
				System.out.println("testPrepareInput 1: failed");
				System.out.println("   input: " + input);
				System.out.println("  result: " + Arrays.toString(result));
			}
		}

		{ // 2. tests if prepare input correctly replaces and separates the words
			String input = "I can't do that";
			String[] expected = { "i", "cannot", "do", "that" };
			String[] result = Eliza.prepareInput(input);
			if (result.length != expected.length) {
				error = true;
			}
			for (int i = 0; i < result.length; i++) {
				if (!expected[i].equals(result[i])) {
					error = true;
				}
			}
			if (error) {
				System.out.println("testPrepareInput 2: failed");
				System.out.println("expected: " + Arrays.toString(expected));
				System.out.println("  result: " + Arrays.toString(result));
			}
		}
		{ // 3.makes sure it separates the input so it is ready to be tested for
			// found quit word and other methods
			String input = "I said goodbye";
			String[] expected = { "i", "said", "goodbye" };
			String[] result = Eliza.prepareInput(input);
			if (result.length != expected.length) {
				error = true;
			}
			for (int i = 0; i < result.length; i++) {
				if (!expected[i].equals(result[i])) {
					error = true;
				}
			}
			if (error) {
				System.out.println("testPrepareInput 2: failed");
				System.out.println("expected: " + Arrays.toString(expected));
				System.out.println("  result: " + Arrays.toString(result));
			}
		}

		if (error) {
			System.err.println("testPrepareInput failed");
		} else {
			System.out.println("testPrepareInput passed");
		}
	}

	/**
	 * This runs some tests on the loadResponseTable method.
	 * 
	 */
	private static void testLoadResponseTable() {
		boolean error = false;
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
		// 1.tests to see if the first row of the table created by loadResponse table
		// matches the expected file
		ArrayList<String> expected1stRow = new ArrayList<String>();
		expected1stRow.add("computer");
		table = Eliza.loadResponseTable("Eliza.rsp");
		if (!table.get(0).equals(expected1stRow)) {
			error = true;
			System.out.println("testLoadResponseTable 1: failed");
			System.out.println("expected1stRow: " + expected1stRow);
			System.out.println("   table1stRow: " + table.get(0));
		}

		// 2.tests if there is an even number of rows
		if (table.size() % 2 != 0) {
			error = true;
			System.out.println("testLoadResponseTable 2: failed");
			System.out.println("  expected an even number of elements in table but found: " + table.size());
		}
		// 3. tests to make sure the first two keywords are correctly in the right order
		// and position
		// when loaded from loadResponseTable
		ArrayList<String> expected1stRow2 = new ArrayList<String>();
		ArrayList<String> expected2stRow2 = new ArrayList<String>();

		expected1stRow2.add("computer");
		expected2stRow2.add("name");
		table = Eliza.loadResponseTable("Eliza.rsp");
		if (!table.get(0).equals(expected1stRow2) || !table.get(2).equals(expected2stRow2)) {
			error = true;
			System.out.println("testLoadResponseTable 1: failed");
			System.out.println("expected1stRow: " + expected1stRow2);
			System.out.println("   table1stRow: " + table.get(0));
		}

		// 3.tests if there is an even number of rows
		if (table.size() % 2 != 0) {
			error = true;
			System.out.println("testLoadResponseTable 2: failed");
			System.out.println("  expected an even number of elements in table but found: " + table.size());
		}

		if (error) {
			System.err.println("testLoadResponseTable failed");
			System.out.println("responses: " + table);
		} else {
			System.out.println("testLoadResponseTable passed");

		}
	}

	/*
	 * Supporting method for testInputAndResponse. Returns 1 if the test passed and
	 * 0 if the test failed.
	 */
	private static int checkResponse(String input, String expectedResponse, Random rand,
			ArrayList<ArrayList<String>> table) {

		String[] words = Eliza.prepareInput(input);
		// tests if the prepareInput returns a correct null array based on expected
		// response
		if (words == null) {
			if (expectedResponse == null) {
				return 1;
			} else {
				System.out.println("testInputLines  checkResponse error");
				System.out.println("  input='" + input + "'");
				System.out.println("  response=null");
				System.out.println("  expected='" + expectedResponse + "'");
				return 0;
			}
		}
		// if words is not null it will test prepare response to make sure that it
		// correctly creates
		// the expected response
		String response = Eliza.prepareResponse(words, rand, table);
		if (!response.equals(expectedResponse)) {
			System.out.println("testPrepareResponse checkResponse error");
			System.out.println("  input='" + input + "'");
			System.out.println("  response='" + response + "'");
			System.out.println("  expected='" + expectedResponse + "'");
			return 0;
		} else {
			return 1;
		}
	}

	/*
	 * Runs tests on a bunch of example sentences using a Random number generator
	 * with a seed.
	 */
	private static void testInputAndResponse() {
		int numPassed = 0;
		// since we are using a seed in the random number generator, the responses
		// should be fixed for the provided response table.
		Random randGen = new Random(923);
		ArrayList<ArrayList<String>> responseTable = Eliza.loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);
//if the checkResponse passes it will increase numPassed and will eventually say how many failed
		numPassed += checkResponse("I like computers.", "What do you think machines have to do with your problem?",
				randGen, responseTable);
		numPassed += checkResponse("What is your name?", "I don't care about names -- please continue.", randGen,
				responseTable);
		numPassed += checkResponse("You remind me of a grumpy uncle.", "What other connections do you see?", randGen,
				responseTable);
		numPassed += checkResponse("You don't seem to say much.", "What are your feelings now?", randGen,
				responseTable);
		numPassed += checkResponse("You reflect me.", "What makes you think I reflect you?", randGen, responseTable);
		numPassed += checkResponse("I wish to believe you.", "Why do you think you wish to believe me?", randGen,
				responseTable);
		numPassed += checkResponse("I dreamed of going into space.",
				"Have you ever dreamed of going into space before?", randGen, responseTable);
		numPassed += checkResponse("I apologize for boring you.", "Why do you think you apologize for boring me?",
				randGen, responseTable);
		numPassed += checkResponse("Because it seems the polite thing to do.",
				"Does that reason seem to explain anything else?", randGen, responseTable);
		numPassed += checkResponse("Yes.", "You are sure.", randGen, responseTable);
		numPassed += checkResponse("I remember being in 1st grade.",
				"What is the connection between me and being in 1st grade?", randGen, responseTable);
		numPassed += checkResponse("No, not often. Sometimes I remember being on a boat.",
				"Why do you recollect being on a boat just now?", randGen, responseTable);
		numPassed += checkResponse("My family liked to play cards.", "Tell me more about your family.", randGen,
				responseTable);
		numPassed += checkResponse("Do you remember growing up?", "What about growing up?", randGen, responseTable);
		numPassed += checkResponse("Are you a Packers fan?", "Perhaps I am a packers fan in your fantasies.", randGen,
				responseTable);
		numPassed += checkResponse("I am sad to hear that.", "Can you explain what made you sad?", randGen,
				responseTable);
		numPassed += checkResponse("I cannot explain this.", "Do you really want to be able to explain this?", randGen,
				responseTable);
		numPassed += checkResponse("You seem to have a different perspective than many.",
				"You're not really talking about me -- are you?", randGen, responseTable);
		numPassed += checkResponse("I'm talking to my dog.", "How long have you been talking to your dog?", randGen,
				responseTable);
		numPassed += checkResponse("goodbye", null, randGen, responseTable);
		numPassed += checkResponse("", "I'm not sure I understand you fully.", randGen, responseTable);
		// tells us how many failed or if all of them passed
		int expected = 21;
		if (numPassed == expected) {
			System.out.println("testInputAndResponse passed ");
		} else {
			System.err.println("testInputAndResponse failed " + (expected - numPassed));
		}
	}

}