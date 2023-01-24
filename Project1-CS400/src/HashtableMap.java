// --== CS400 Project One File Header ==--
// Name: Cole Johnstone
// CSL Username: johnstone
// Email: cjohnstone@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: N/A

import java.util.NoSuchElementException;
import java.util.LinkedList;
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

	protected LinkedList<item<KeyType, ValueType>>[] hashArray;
	private int size;

	/**
	 * Constructor to create hashtable with a given capacity
	 * 
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap(int capacity) {
		hashArray = (LinkedList<item<KeyType, ValueType>>[]) new LinkedList[capacity];
		size = 0;
	}

	/**
	 * constructor to create hashtable with capacity of 20
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap() {
		hashArray = (LinkedList<item<KeyType, ValueType>>[]) new LinkedList[20];
		size = 0;
	}

	/**
	 * Adds a new item to the hashtable map
	 * 
	 * @param key
	 * @param value
	 * @return boolean
	 */
	public boolean put(KeyType key, ValueType value) {
		if (key == null || containsKey(key))
			return false;
		item<KeyType, ValueType> thing = new item<KeyType, ValueType>();
		thing.setKey(key);
		thing.setValue(value);
		int index = Math.abs(key.hashCode()) % hashArray.length;
		if (hashArray[index] == null)
			hashArray[index] = new LinkedList<item<KeyType, ValueType>>();
		hashArray[index].add(thing);
		size++;
		if (size >= hashArray.length * 3 / 4)
			increaseSize();
		return true;
	}

	/**
	 * Checks to see if the key given is already in the hashtable
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean containsKey(KeyType key) {
		if (size() == 0)
			return false;
		int index = Math.abs(key.hashCode()) % hashArray.length;
		if (hashArray[index] != null) {
			for (int i = 0; i < hashArray[index].size(); i++) {
				if (hashArray[index].get(i).getKey().equals(key))
					return true;
			}
		}
		return false;
	}

	/**
	 * dynamically increases the size of the hashtable map
	 */
	@SuppressWarnings("unchecked")
	public void increaseSize() {
		LinkedList<item<KeyType, ValueType>>[] oldHashArray = hashArray;
		hashArray = (LinkedList<item<KeyType, ValueType>>[]) new LinkedList[hashArray.length * 2];
		for (int i = 0; i < oldHashArray.length; i++) {
			if (oldHashArray[i] != null) {
				for(int j = 0; j < oldHashArray[i].size(); j++) {
					put(oldHashArray[i].get(j).getKey(),oldHashArray[i].get(j).getValue());
					size--;
				}
			}
		}
	}

	/**
	 * returns a reference to the value associated with the key that is being
	 * removed
	 * 
	 * @param key
	 * @return value associated with key
	 */
	public ValueType remove(KeyType key) {
		if (!containsKey(key)) 
			return null;
		ValueType toReturn = null;
		int index = Math.abs(key.hashCode()) % hashArray.length;
		for(int i = 0; i < hashArray[index].size(); i++) {
			if(hashArray[index].get(i).getKey().equals(key)) {
				toReturn = hashArray[index].get(i).getValue();
				hashArray[index].remove(i);
				size--;
				break;
			}
		}
		return toReturn;
	}

	/**
	 * Retrieves the value type for the provided key
	 * 
	 * @param key
	 * @return ValueType
	 * @throws NoSuchElementException
	 */
	public ValueType get(KeyType key) throws NoSuchElementException {
		if (!containsKey(key))
			throw new NoSuchElementException("The provided key is not in the HashTableMap");
		ValueType toReturn = null;
		int index = Math.abs(key.hashCode()) % hashArray.length;
		for(int i = 0; i < hashArray[index].size(); i++) {
			if(hashArray[index].get(i).getKey().equals(key)) {
				toReturn = hashArray[index].get(i).getValue();
				break;
			}
		}
		return toReturn;
	}

	/**
	 * clears the current hashtable map
	 */
	public void clear() {
		for (int i = 0; i < hashArray.length; i++) {
			hashArray[i] = null;
		}
		size = 0;
	}

	/**
	 * returns the number of key-value pairs stored in this collection
	 * 
	 * @return size
	 */
	public int size() {
		return this.size;
	}

}
