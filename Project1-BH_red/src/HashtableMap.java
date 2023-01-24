// --== CS400 Project One File Header ==--
// Name: <Drew Cudmore>
// CSL Username: <cudmore>
// Email: <dcudmore@wisc.edu>
// Lecture #: <002 @1:00pm>
// Notes to Grader: <none>

import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * HashtableMap class allows for the implementation of a hashtable holding
 * generic key and value pairs and methods to add, remove, and get those pairs
 * from the datastructure Hashtable will dyamically grow when load factor is
 * greater than or equal to 75%
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

        @SuppressWarnings("rawtypes")
        protected LinkedList<LinkedNode>[] array;
        // protected single-dimensional LinkedList array instance field

        /*
         * Constructor initializes a new HashtableMap with a specified capacity
         * 
         * @param capacity is the intended capacity for the new HashtableMap
         */
        @SuppressWarnings("unchecked")
        public HashtableMap(int capacity) {
                array = new LinkedList[capacity];
                initializeArray(); // initializes LinkedLists in the array
        }

        /*
         * Constructor initializes a new HashtableMap with a default capacity of 20
         */
        @SuppressWarnings("unchecked")
        public HashtableMap() // with default capacity = 20
        {
                array = new LinkedList[20];
                initializeArray(); // initializes LinkedLists in the array
        }

        /*
         * Helper method to initialize LinkedLists in HashtableMap
         */
        @SuppressWarnings({ "rawtypes" })
        private void initializeArray() {
                for (int i = 0; i < array.length; i++) {
                        // creates new LinkedList at each element in array
                        array[i] = new LinkedList<LinkedNode>(); 
                }
        }

        /*
         * Helper method to calculate the load factor of the HashtableMap
         * 
         * @return double representing load factor
         */
        private double getLoadFactor() {
                return ((double) (this.size())) / array.length;
        }

        /*
         * Resizes HashtableMap to double its capacity
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        private void resizeArray() {
                // creates new hashtable map with double previous capacity
                HashtableMap result = new HashtableMap(this.array.length * 2);

                // adds all LinkedNodes from current HashtableMap to new HashtableMap
                for (LinkedList<LinkedNode> list : array) {
                        for (LinkedNode node : list) {
                                result.put((node).getKey(), node.getValue());
                        }
                }

                // points current Hashtablemap array reference at resized array reference
                this.array = result.array;
        }
        /*
         * Adds a key value pair to the HashtableMap if it is unique and not null
         * 
         * @param key is the generic key to add
         * 
         * @param value is the generic value to add
         * 
         * @return boolean value indicating whether or not the key value pair was added
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public boolean put(KeyType key, ValueType value) {
                // returns false if key is null or already in array
                if ((key == null) || (this.containsKey(key)))
                        return false;

                int indexToStore = Math.abs(key.hashCode()) % array.length;
                LinkedNode node = new LinkedNode(key, value);
                array[indexToStore].add(node);
                // resizes array if the node addition exceeds load factor of 75%
                if (getLoadFactor() >= 0.75)
                        resizeArray();

                return true;
        }

        /*
         * Gets the value of a specified key in the HashtableMap
         * 
         * @param key is the key to retrieve the value of
         * 
         * @return ValueType value of the key
         */
        @SuppressWarnings({ "rawtypes", "unchecked" })
        @Override
        public ValueType get(KeyType key) throws NoSuchElementException{
                // Throws NoSuchElementException if key is null or not in array
                if (key == null)
                        throw new NoSuchElementException("Key cannot be null");
                if (!this.containsKey(key))
                        throw new NoSuchElementException("Key not in Hashtable");
                ValueType result = null;
                int indexToCheck = Math.abs(key.hashCode()) % array.length;
                // checks if matching key is present in the HashtableMap
                if (array[indexToCheck] != null) {
                        for (LinkedNode node : array[indexToCheck]) {
                                // if matching key is present, sets result to equal the value of that key
                                if (node.getKey().equals(key))
                                        result = (ValueType) node.getValue();
                        }
                }

                return result;
        }

        /*
         * Gets the size of the HashtableMap
         * 
         * @return int size
         */
        @SuppressWarnings("rawtypes")
        @Override
        public int size() {
                int size = 0;
                // appends size of each LinkedList to size variable
                for (LinkedList list : array) {
                        size += list.size();
                }

                return size;
        }

        /*
         * Checks if HashtableMap contains a specified key
         * 
         * @param key is the key to check for
         * 
         * @returns true if key is present in HashtableMap and false otherwise
         */
        @SuppressWarnings("rawtypes")
        @Override
        public boolean containsKey(KeyType key) {
        	int indexToCheck = Math.abs(key.hashCode()) % array.length;
            // Checks if key is present in correct position
            if (array[indexToCheck] != null) {
                    for (LinkedNode node : array[indexToCheck]) {
                            if (node.getKey().equals(key))
                                    return true;
                    }
            }
            // return false if key was not found
            return false;
    }

    /*
     * Removes a specified key from the HashtableMap and gives its value
     * 
     * @param key is the key to remove
     * 
     * @return ValueType value of removed key
     */
    @SuppressWarnings("rawtypes")
    @Override
    public ValueType remove(KeyType key) {
            if (key == null)
                    return null;
            int indexToRemove = Math.abs(key.hashCode()) % array.length;
            // finds key in correct position
            for (LinkedNode node : array[indexToRemove]) {
                    if (node.getKey().equals(key)) {
                            @SuppressWarnings({ "unchecked" }) // result variable holds value of key
                            ValueType result = (ValueType) (node).getValue();
                            // removes key from LinkedList
                            array[indexToRemove].remove(node);
                            // returns value of key
                            return result;
                    }
            }

            // If key is not found, return null
            return null;
    }
    /*
     * Clears HashtableMap
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void clear() {
            // iterates through each LinkedList in HashtableMap removing all their elements
            for (LinkedList list : array) {
                    list.clear();
            }
    }
}