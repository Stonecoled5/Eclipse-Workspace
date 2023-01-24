// --== CS400 Project One File Header ==--
// Name: Cole Johnstone
// CSL Username: johnstone
// Email: cjohnstone@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: N/A

/**
 * Helper class for HashtableMap
 * 
 * @author Cole Johnstone
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class item<KeyType, ValueType> {
	private KeyType key;
	private ValueType value;
	private item<KeyType, ValueType> next;

	/**
	 * Setter method for key
	 * 
	 * @param key
	 */
	public void setKey(KeyType key) {
		this.key = key;
	}

	/**
	 * Setter method for value
	 * 
	 * @param value
	 */
	public void setValue(ValueType value) {
		this.value = value;
	}

	/**
	 * Getter method for key
	 * 
	 * @return key
	 */
	public KeyType getKey() {
		return key;
	}

	/**
	 * Getter method for value
	 * 
	 * @return value
	 */
	public ValueType getValue() {
		return value;
	}

	/**
	 * Sets the next node in the linked list
	 * 
	 * @param next
	 */
	public void setNext(item<KeyType, ValueType> next) {
		this.next = next;
	}

	/**
	 * gets the next node in the linked list
	 * 
	 * @return next item
	 */
	public item<KeyType, ValueType> getNext() {
		return next;
	}

}
