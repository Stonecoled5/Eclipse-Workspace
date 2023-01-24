// --== CS400 Project One File Header ==--
// Name: <Drew Cudmore>
// CSL Username: <cudmore>
// Email: <dcudmore@wisc.edu>
// Lecture #: <002 @1:00pm>
// Notes to Grader: <none>

/*
 * Generic class that allows for the creation of linked nodes that hold
 * a key and value pair of any type
 */
public class LinkedNode<KeyType, ValueType> {

        protected KeyType key; // holds key
        protected ValueType value; // holds value

        /*
         * Constructor that created linked nodes holding a key and value
         * 
         * @param key is a generic value
         * 
         * @param value is a generic value
         */
        public LinkedNode(KeyType key, ValueType value) {
                this.key = key;
                this.value = value;
        }

        /**
         * Gets key
         * 
         * @return the key
         */
        public KeyType getKey() {
                return key;
        }

        /**
         * Gets value
         * 
         * @return the value
        */
        public ValueType getValue() {
                return value;
        }

}