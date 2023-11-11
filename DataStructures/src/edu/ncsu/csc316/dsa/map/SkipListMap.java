package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /** Coin tosses are used when inserting entries into the data structure to ensure 50/50 probability that an entry will be added to the current level of the skip list structure. */
    private Random coinToss;

    /**
     * Start references the topmost, leftmost corner of the skip list. In other
     * words, start references the sentinel front node at the top level of the skip
     * list
     */
    private SkipListNode<K, V> start;

    /** The number of entries stored in the map. */
    private int size;

    /** The number of levels of the skip list data structure. */
    private int height;

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}.
     */
    public SkipListMap() {
        this(null);
    }

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on a
     * provided {@link Comparator}.
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SkipListMap(Comparator<K> compare) {
        super(compare);
        coinToss = new Random();
        // Create a dummy head node for the left "-INFINITY" sentinel tower
        start = new SkipListNode<K, V>(null);
        // Create a dummy tail node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListNode<K, V>(null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
    }

    /**
     * Returns a collection of the entries stored in the map.
     * Starts from the bottom-leftmost non-sentinel node and iterates
     * through the bottom level of the skip list.
     *
     * @return the collection of entries within the map
     */
    @Override
	public Iterable<Entry<K, V>> entrySet() {
    	// New collection to store entries
	    EntryCollection set = new EntryCollection();
	    // Start at top left of skip list
	    SkipListNode<K, V> current = start;
	    // Traverse to reach bottom left
	    while (current.below != null) {
	        current = current.below;
	    }
	    // Move to first non-sentinel node on bottom level
	    current = current.next;
	    
	    //Iterate through bottom level until a sentinel node is found
	    while (!isSentinel(current)) {
	    	// Add the entry to the collection
	        set.add(current.getEntry());
	        // Move to the next node on the bottom level
	        current = current.next;
	    }
	    return set;
	}

	/**
	 * Gets the value tied to the given key. Utilizes lookUp() to search for
	 * the node tied to they key and then gets its value.
	 *
	 * @param key the key tied to the value to return
	 * @return the value that the key is mapped to, returns null
	 * if it can't be found
	 */
	@Override
	public V get(K key) {
		//Find the node that contains the key
	    SkipListNode<K, V> temp = lookUp(key);
	    
	    // If the key is found, return the value tied to the key
	    if (temp.getEntry() != null && temp.getEntry().getKey().equals(key)) {
	        return temp.getEntry().getValue();
	    }
	    // If the key is not found, return null
	    return null;
	}

	/**
	 * Inserts a SkipListNode into the skip list after the given node and above another given node.
	 *
	 * @param prev the previous node on the current level
	 * @param down the down node on the level below the current level
	 * @param entry the entry to insert into the new node
	 * @return the skip list node that was created
	 */
	private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
	    // Create a new node with the Entry given
		SkipListNode<K, V> newNode = new SkipListNode<>(entry);
	    newNode.setBelow(down);
	    newNode.setPrevious(prev);
	
	    // Link new node horizontally
	    if (prev != null) {
	        newNode.setNext(prev.getNext());
	        prev.setNext(newNode);
	    }
	
	    // Update the previous reference of next node
	    if (newNode.getNext() != null) {
	        newNode.getNext().setPrevious(newNode);
	    }
	
	    // Link new node vertically
	    if (down != null) {
	        down.setAbove(newNode);
	    }
	
	    // Return the newly created node
	    return newNode;
	}

	// Helper method to determine if an entry is one of the sentinel
    /**
	 * Checks if is sentinel.
	 *
	 * @param node the node
	 * @return true, if is sentinel
	 */
	// -INFINITY or +INFINITY nodes (containing a null key)
    private boolean isSentinel(SkipListNode<K, V> node) {
        return node.getEntry() == null;
    }

    /**
     * Searches for the appropriate position in the skip list where the given key exists or should be placed.
     * It begins from the top-leftmost node, navigating right and downwards 
     * until it reaches the bottom level or identifies the intended location.
     *
     * @param key the key to search for within the skip list
     * @return the node in the skip list that represents or precedes the spot 
     *         where the provided key resides or would be positioned
     */
    private SkipListNode<K, V> lookUp(K key) {
        SkipListNode<K, V> current = start;
        // Go through the skip list to search for the node
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
    }

    /**
     * Inserts or replaces a key-value pair into the skip list.
     * If the key already exists, its associated value is updated.
     * If the key does not exist, a new entry is added. Coin tosses determine 
     * the height of the new entry's tower.
	 *
	 * @param key   the key to be put into the skip list
	 * @param value the value to be associated with the given key
	 * @return the previous value associated with the key, or null if there was no mapping
	 */
    @Override
    public V put(K key, V value) {
    	// Find the node with the given key
        SkipListNode<K, V> temp = lookUp(key);
        
        // If the key is found, update the value tied to it 
        if (temp.getEntry() != null && temp.getEntry().getKey().equals(key)) {
            V original = temp.getEntry().getValue();
            while (temp != null) {
            	temp.setEntry(new MapEntry<>(key, value));
                temp = temp.getAbove();	
            }
            return original;
        }

        // Insert a new k-v pair into the skip list
        SkipListNode<K, V> q = null;
        int current = -1;
        do {
            current++;
            
            // If there is a need to add a new level
            if (current >= height) {
                height++;
                SkipListNode<K, V> tail = start.getNext();
                start = insertAfterAbove(null, start, null);
                insertAfterAbove(start, tail, null);
            }
            // Current level
            q = insertAfterAbove(temp, q, new MapEntry<>(key, value));
            // Move horizontally left
            while (temp.getAbove() == null && temp != start) {
                temp = temp.getPrevious();
            }
            // Move up a level
            temp = temp.getAbove();
            
        } while (coinToss.nextBoolean());

        size++;
        return null;
    }

    /**
     * Deletes the entry associated with the specified key in the skip list.
     * The method navigates through the skip list, both vertically and horizontally, 
     * to eradicate every instance of the key's tower.
     *
     * @param key the key to be deleted
     * @return the value that was previously tied to the key, or null is it doesn't exist
     */
    @Override
    public V remove(K key) {
    	// Find the node with the key
        SkipListNode<K, V> temp = lookUp(key);

        // If the key is not found, return null
        if (temp == null || temp.getEntry() == null || !temp.getEntry().getKey().equals(key)) {
            return null;
        }
        // Store value being removed
        V remove = temp.getEntry().getValue();
        // Remove any instance of the key
        while (temp != null) {
            SkipListNode<K, V> prevNode = temp.getPrevious();
            prevNode.setNext(temp.getNext());
            if (temp.getNext() != null) {
                temp.getNext().setPrevious(prevNode);
            }
            temp = (temp.getAbove() != null) ? temp.getAbove() : null;
        }
        // Decrease size of skip list
        size--;
        return remove;
    }

    /**
     * Returns the size.
     *
     * @return the size
     */
    @Override
    public int size() {
        return size;
    }

	/**
     * To full string.
     *
     * @return the string
     */
	public String toFullString() {
	    StringBuilder sb = new StringBuilder("SkipListMap[\n");
	    SkipListNode<K, V> cursor = start;
	    SkipListNode<K, V> firstInList = start;
	    while (cursor != null) {
	        firstInList = cursor;
	        sb.append("-INF -> ");
	        cursor = cursor.next;
	        while (cursor != null && !isSentinel(cursor)) {
	            sb.append(cursor.getEntry().getKey() + " -> ");
	            cursor = cursor.next;
	        }
	        sb.append("+INF\n");
	        cursor = firstInList.below;
	    }
	    sb.append("]");
	    return sb.toString();
	}

	/**
	 * Returns the string representation of a SkipListMap.
	 *
	 * @return the string representation of SkipListMap
	 */
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipListMap[");
        SkipListNode<K, V> cursor = start;
        while (cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
            sb.append(cursor.getEntry().getKey());
            if (!isSentinel(cursor.next)) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * The Class SkipListNode.
     *
     * @param <K> the key type
     * @param <V> the value type
     */
    private static class SkipListNode<K, V> {

        /** The entry. */
        private Entry<K, V> entry;
        
        /** The above. */
        private SkipListNode<K, V> above;
        
        /** The below. */
        private SkipListNode<K, V> below;
        
        /** The prev. */
        private SkipListNode<K, V> prev;
        
        /** The next. */
        private SkipListNode<K, V> next;

        /**
         * Instantiates a new skip list node.
         *
         * @param entry the entry
         */
        public SkipListNode(Entry<K, V> entry) {
            setEntry(entry);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }

        /**
         * Gets above.
         *
         * @return the above
         */
        public SkipListNode<K, V> getAbove() {
            return above;
        }

        /**
         * Gets the entry.
         *
         * @return the entry
         */
        public Entry<K, V> getEntry() {
            return entry;
        }

        /**
         * Gets next.
         *
         * @return the next
         */
        public SkipListNode<K, V> getNext() {
            return next;
        }

        /**
         * Gets previous.
         *
         * @return the previous
         */
        public SkipListNode<K, V> getPrevious() {
            return prev;
        }

        /**
         * Sets above.
         *
         * @param up the up
         */
        public void setAbove(SkipListNode<K, V> up) {
            this.above = up;
        }

        /**
         * Sets below.
         *
         * @param down the down
         */
        public void setBelow(SkipListNode<K, V> down) {
            this.below = down;
        }

        /**
         * Sets the entry.
         *
         * @param entry the entry
         */
        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }

        /**
         * Sets next.
         *
         * @param next the next
         */
        public void setNext(SkipListNode<K, V> next) {
            this.next = next;
        }

        /**
         * Sets previous.
         *
         * @param prev the prev
         */
        public void setPrevious(SkipListNode<K, V> prev) {
            this.prev = prev;
        }
    }
}