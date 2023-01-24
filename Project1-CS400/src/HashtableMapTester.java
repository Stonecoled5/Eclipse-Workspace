
// --== CS400 Project One File Header ==--
// Name: Cole Johnstone
// CSL Username: johnstone
// Email: cjohnstone@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: N/A
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Tester Class for HashtableMap class and my item helper class
 * 
 * @author Cole Johnstone
 *
 */
public class HashtableMapTester<KeyType, ValueType> {

	/**
	 * Tester class for the clear method
	 * 
	 * @return boolean
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean clearTests() {
		HashtableMap hashArray = new HashtableMap(10);
		hashArray.put("bear", "a");
		hashArray.put("poop", "bark");
		hashArray.put("plow", "bb");
		hashArray.clear();
		// tests if the hashtable contains none of the previously inputted keys
		if (hashArray.containsKey("bear"))
			return false;
		if (hashArray.containsKey("poop"))
			return false;
		if (hashArray.containsKey("plow"))
			return false;
		// makes sure the capacity stays the same and changes the size to 0
		if (hashArray.hashArray.length != 10)
			return false;
		if (hashArray.size() != 0)
			return false;
		// makes sure all spots in the array are null
		for (int i = 0; i < hashArray.hashArray.length; i++) {
			if (hashArray.hashArray[i] != null)
				return false;
		}
		return true;
	}

	/**
	 * Tester class for the get method
	 * 
	 * @return boolean
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean getTests() {
		HashtableMap hashArray = new HashtableMap(10);
		try {
			hashArray.get("blah");// throws exception when key isn't in array
		} catch (NoSuchElementException e1) {
			hashArray.put("bear", "a");
			hashArray.put("poop", "bark");
			hashArray.put("plow", "bb");
			// checks that the method get properly returns the correct valuetype
			if (!hashArray.get("poop").equals("bark"))
				return false;
			if (!hashArray.get("plow").equals("bb"))
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Tester class for the remove method
	 * 
	 * @return boolean
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean removeTests() {
		HashtableMap hashArray = new HashtableMap(10);
		hashArray.put("bear", "a");
		hashArray.put("poop", "bark");
		hashArray.put("plow", "bb");
		if (hashArray.size() != 3)
			return false;
		if (!((item<KeyType, ValueType>) hashArray.hashArray[0].get(0)).getKey().equals("bear"))
			return false;
		// checks if the remove method properly returns the correct valuetype
		if (!hashArray.remove("bear").equals("a"))
			return false;
		// checks if size is adjusted correctly
		if (hashArray.size() != 2)
			return false;
		// checks if a the linked list is properly taken care of when an element is
		// removed
		if (!((item<KeyType, ValueType>) hashArray.hashArray[0].get(0)).getKey().equals("plow"))
			return false;
		// checks to make sure that when a given key isn't in the array remove returns
		// null
		if (hashArray.remove("bark") != null)
			return false;
		if (!hashArray.remove("poop").equals("bark"))
			return false;
		HashtableMap hashArray2 = new HashtableMap(100);
		if (hashArray2.remove('9') != null)
			return false;
		return true;
	}

	/**
	 * Tester class for the increaseSize method
	 * 
	 * @return boolean
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean increaseSizeTests() {
		HashtableMap hashArray = new HashtableMap(4);
		if (hashArray.size() != 0)
			return false;
		hashArray.put("bear", "a");
		hashArray.put("poop", "bark");
		hashArray.put("blah", "tada");
		// tests if the current size of the array is properly increased
		if (hashArray.size() != 3)
			return false;
		// checks if the length of the map is properly doubled
		if (hashArray.hashArray.length != 8)
			return false;
		// checks if the elements are put back in the array in the correct location.
		if (!((item<KeyType, ValueType>) hashArray.hashArray[4].get(0)).getKey().equals("bear"))
			return false;
		return true;
	}

	/**
	 * Tester class for the put method
	 * 
	 * @return boolean
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean putTests() {
		HashtableMap hashArray = new HashtableMap(10);
		if (hashArray.hashArray.length != 10)
			return false;// testing proper capacity of map is created

		if (!hashArray.put("bear", "a"))
			return false;
		// tests if put puts the item in the correct location
		if (!((item<KeyType, ValueType>) hashArray.hashArray[0].get(0)).getKey().equals("bear"))
			return false;
		if (!((item<KeyType, ValueType>) hashArray.hashArray[0].get(0)).getValue().equals("a"))
			return false;
		// checks that put didn't put this item anywhere else
		for (int i = 1; i < hashArray.hashArray.length; i++) {
			if (hashArray.hashArray[i] != null)
				return false;
		}
		// checks to see if put links an item with the same index to the one already in
		// that index
		if (!hashArray.put("plow", "bb"))
			return false;
		for (int i = 1; i < hashArray.hashArray.length; i++) {
			if (hashArray.hashArray[i] != null)
				return false;
		}
		if (hashArray.hashArray[0].size()!=2) return false;
		if (!((item<KeyType, ValueType>) hashArray.hashArray[0].get(1)).getValue().equals("bb"))
			return false;
		if (!((item<KeyType, ValueType>) hashArray.hashArray[0].get(1)).getKey().equals("plow"))
			return false;
		// checks if put adds a non 0 ending hash code in the proper spot
		if (!hashArray.put("poop", "bark"))
			return false;
		if (!((item<KeyType, ValueType>) hashArray.hashArray[6].get(0)).getValue().equals("bark"))
			return false;
		if (!((item<KeyType, ValueType>) hashArray.hashArray[6].get(0)).getKey().equals("poop"))
			return false;
		// checks if put returns false if key is null
		if (hashArray.put(null, null))
			return false;
		return true;
	}

	/**
	 * Tester class for the containsKey method
	 * 
	 * @return boolean
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean containsKeyTests() {
		HashtableMap hashArray = new HashtableMap(7);
		if (hashArray.containsKey("bark"))
			return false;
		hashArray.put("bear", "a");
		hashArray.put("poop", "bark");
		// checks if containsKey finds the key in different locations
		if (!hashArray.containsKey("bear"))
			return false;
		if (!hashArray.containsKey("poop"))
			return false;
		// checks if containsKey returns false if the key is not in the hashtablemap and
		// doesn't check value
		if (hashArray.containsKey("bark"))
			return false;
		return true;

	}

	/**
	 * Runs all of the tests
	 * 
	 * @return
	 */
	public static boolean runAllTests() {
		HashtableMapTester test = new HashtableMapTester();
		if (test.putTests()) {
			System.out.println("putTests passed");
			if (test.containsKeyTests()) {
				System.out.println("containsKeyTests passed");
				if (test.increaseSizeTests()) {
					System.out.println("increaseSizeTests passed");
					if (test.removeTests()) {
						System.out.println("removeTests passed");
						if (test.getTests()) {
							System.out.println("getTests passed");
							if (test.clearTests()) {
								System.out.println("clearTests passed");
								return true;
							}
							else {System.out.println("clearTests failed");}
						}
						else {System.out.println("getTests failed");}
					}
					else {System.out.println("removeTests failed");}
				}
				else {System.out.println("increaseSizeTests failed");}
			}
			else {System.out.println("containsKeyTests failed");}	
		}
		else {System.out.println("putTests failed");}
		return false;
	}

	/**
	 * Main method to test all methods
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(runAllTests());
	}

}
