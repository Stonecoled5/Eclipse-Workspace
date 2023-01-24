import java.util.ArrayList;
import java.util.List;

// --== CS400 Project One File Header ==--
// Name: <Drew Cudmore>
// CSL Username: <cudmore>
// Email: <dcudmore@wisc.edu>
// Lecture #: <002 @1:00pm>
// Notes to Grader: <none>

/**
 * @author drewcudmore
 *      This class extends the implementation of the HashTableMap class with the additional 
 *      functionality of adding multiple values to one key in a sorted order based on Comparable
 */
public class HashTableSortedSets<KeyType, ValueType extends Comparable<ValueType>> 
extends HashtableMap<KeyType, List<ValueType>> implements IHashTableSortedSets<KeyType, ValueType> {

                
        /*
         * Appends a single value to the end of the list associated with a given key.
         * If a key does not yet have a list of values associated with it, then a new 
     * one will be created when this method is called.
     * 
     * @param key used to later lookup the list containing this value
     * @param value associated with the previous key
         */
        @Override
        public void add(KeyType key, ValueType value) {
                // stops method if key is null
                if (key == null) return;
                
//if there is no value associated with key yet, a new list of values will be made
                if (!this.containsKey(key))
                {
                        //creates new list of with value
                        ArrayList<ValueType> list = new ArrayList<ValueType>();
                        list.add(value);
                        //assigns list of values to 
                        this.put(key, list);
                } else //if key is already present in array
                {
                        //adds value to the ValueType list associated with key
                        this.get(key).add(value);
                        //sorts array of values
                        sortLastValue(key, value);
                }
                
        }
        
        
        private void sortLastValue(KeyType key, ValueType value)
        {
                //stores list of values attributed to key in "list" var
                List<ValueType> list = this.get(key);
                //iterates through list, comparing the new value to add
                for (int i = 0; i < list.size(); i++)
                {
                        //if new value is less than one in the list, add it there
                        if (value.compareTo(list.get(i)) < 0)
                        {
                                list.add(i, value);
                                //remove the previous position of the value at the end of the arraylist
                                list.remove(list.size() - 1);
                                //value has been sorted so exit loop
                                break;
                        }
                }
                
        }
        
}