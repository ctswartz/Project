package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A Search Table map is an ordered (meaning entries are stored in a sorted
 * order based on the keys of the entries) contiguous-memory representation of
 * the Map abstract data type. This array-based map delegates to an existing
 * array-based list. To improve efficiency of lookUps, the search table map
 * implements binary search to locate entries in O(logn) worst-case runtime.
 * Insertions and deletions have O(n) worst-case runtime.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /** The list. */
    private ArrayBasedList<Entry<K, V>> list;

    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}.
     */
    public SearchTableMap() {
        this(null);
    }
    
    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on a
     * provided {@link Comparator}.
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */ 
    public SearchTableMap(Comparator<K> compare) {
        super(compare);
        list = new ArrayBasedList<Entry<K, V>>();
    }

    /**
     * Finds the position of the specified key within the list using binary search
     * using helper method
     * 
     * @param key the key to find
     * @return the index of the key if it is found, otherwise returns a negative number
     * indicating the position at which to insert the key
     */
    private int lookUp(K key) {
        // Use helper method with boundaries of 0 to list.size-1
        return binarySearchHelper(0, list.size() - 1, key);
    }

    /**
     * Helper method to utilize binary search as explained in the algorithm.
     * 
     * Performs a binary search on the sorted list to determine the index 
     * at which the given key should be located or inserted.
     * If the entry exists, the index of the entry is returned. 
     * Otherwise, a negative indicator is returned to suggest the correct position. 
     * To compute the insertion index, evaluate the outcome from the binarySearchHelper, add 1, then negate. 
     * If the helper returns a negative value x, the correct insertion index is -1*(x+1). 
     * This index is then used in conjunction with the List ADT’s add(index, value) method.
     * 
     * @param min the starting index
     * @param max the ending index
     * @param key the key value to locate
     * @return the index of the key. 
     * If it isn't found, returns a negative value that indicates where the key should be inserted.
     */
    private int binarySearchHelper(int min, int max, K key) {
    	
    	// If the key does not exist
        if (min > max) {
        	// Calculate the index for insertion
            return -1 * (min + 1);
        }
        // If the key is in the middle
        int mid = (max + min) / 2;
        if (compare(key, list.get(mid).getKey()) == 0) {
            return mid;
        // If the key is less than the middle key, search the left half
        } else if (compare(key, list.get(mid).getKey()) < 0) {
            return binarySearchHelper(min, mid - 1, key);
        // If the key is greater than the middle key, search the right half
        } else {
            return binarySearchHelper(mid + 1, max, key);
        }
    }

    /**
     * Returns the size of the map.
     *
     * @return the number of entries in the map.
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Gets the value tied to the key.
     *
     * @param key the key with the value
     * @return the value tied to the key if it exists, null otherwise
     */
    @Override
    public V get(K key) {
        int index = lookUp(key);
        if (index >= 0) {
            return list.get(index).getValue();
        }
        return null;
    }

    /**
     * Returns a set of all entries in the map.
     *
     * @return the collection of all entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        for (Entry<K, V> entry : list) {
            set.add(entry);
        }
        return set;
    }

    /**
     * Puts the key-value pair into the map.
     * 
     * If the key exists, its value is updated and the old value is returned.
     * If not, the key-value pair is inserted at the correct position.
     * 
     * @param key the key to insert
     * @param value the value to tied to the key
     * @return the previous value associated with the key, or null if there is not one
     */
    @Override
    public V put(K key, V value) {
        int index = lookUp(key);
        if (index >= 0) {
            // Key exists, replace the value but return old value
            V old = list.get(index).getValue();
            list.set(index, new MapEntry<K, V>(key, value));
            return old;
        }
        //Otherwise, insert at the right position
        list.add(-index - 1, new MapEntry<K, V>(key, value));
        return null;
    }

    /**
     * Removes the entry ties to the key in the map.
     *
     * @param key the key to remove
     * @return the value tied to the key
     */
    @Override
    public V remove(K key) {
        int index = lookUp(key);
        if (index >= 0) {
            return list.remove(index).getValue();
        }
        return null;
    }
    
    /**
     * To string representation.
     *
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SearchTableMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}