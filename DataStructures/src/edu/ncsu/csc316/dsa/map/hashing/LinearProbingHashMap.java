package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

    /** The table. */
    private TableEntry<K, V>[] table;
    
    /** The size. */
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    /**
     * Returns the set of all entries in the hash map.
     * 
     * @return the iterable collection of all entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for (TableEntry<K, V> entry : table) {
            if (entry != null && !entry.isDeleted()) {
                collection.add(entry);
            }
        }
        return collection;
    }

    /**
     * Initializes the hash table with a given capacity.
     *
     * @param capacity the initial size for the table
     */
    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    /**
     * Checks if a bucket at the given index is available for use.
     *
     * @param index the index of the bucket
     * @return true if the bucket at the given index is available, otherwise false
     */
    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    /**
     * Retrieves the value associated with the given key at the specified hash.
     * Returns null if the key is not found.
     *
     * @param hash the hash value of the key
     * @param key the key to retrieve the value for
     * @return the value associated with the given key, or null if not found
     */
    @Override
    public V bucketGet(int hash, K key) {
        int j = findBucket(hash, key);
        
        // Not found
        if (j < 0) {
        	return null;
        }
        return table[j].getValue();
    }

    /**
     * Inserts or updates the key-value pair at the specified hash. 
     * If the key already exists, its associated value is updated.
     *
     * @param hash the hash value of the key
     * @param key the key to be inserted/updated
     * @param value the value to be associated with the key
     * @return the old value associated with the key if updated, otherwise null
     */
    @Override
    public V bucketPut(int hash, K key, V value) {
        int j = findBucket(hash, key);
        if (j < 0) { // If not present, insert
            table[-(j + 1)] = new TableEntry<>(key, value);
            size++;
            return null;
        }
        V oldValue = table[j].getValue();
        table[j].setValue(value);
        return oldValue;

    }

    /**
     * Finds the bucket for the given key starting from the specified index.
     * If the key is not present, it returns a negative value indicating
     * the first available bucket index where the key can be inserted.
     *
     * @param index the starting index
     * @param key the key to be searched
     * @return the index of the bucket containing the key, or a negative value if not found
     */
    private int findBucket(int index, K key) {
        int avail = -1;
        int j = index;
        do {
            if (isAvailable(j)) {
                if (avail == -1) avail = j;
                if (table[j] == null) return -(avail + 1);
            } else if (table[j].getKey().equals(key)) {
                return j;
            }
            j = (j + 1) % capacity();
        } while (j != index);
        
        int value = -(avail + 1);
        return value;
    }

    /**
     * Removes the entry associated with the given key at the specified hash.
     * If the key is not found, it returns null.
     *
     * @param hash the hash value of the key
     * @param key the key to be removed
     * @return the old value associated with the removed key, or null if not found
     */
    @Override
    public V bucketRemove(int hash, K key) {
        // Remember to set the table bucket as DELETED using setDeleted(true)
        int j = findBucket(hash, key);
        // Not found
        if (j < 0) {
        	return null; 
        }
        V old = table[j].getValue();
        table[j].setDeleted(true);
        size--;
        return old;
    }

    /**
     * Returns the current number of key-value pairs stored in the hash map.
     *
     * @return the size of the hash map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the current capacity of the hash table.
     *
     * @return the length of the internal table array
     */
    @Override
    protected int capacity() {
        return table.length;
    }

    /**
     * The Class TableEntry.
     *
     * @param <K> the key type
     * @param <V> the value type
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {

        /** Flag for if it is deleted. */
        private boolean isDeleted;

        /**
         * Instantiates a new table entry.
         *
         * @param key the key
         * @param value the value
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        /**
         * Checks if is deleted.
         *
         * @return true, if is deleted
         */
        public boolean isDeleted() {
            return isDeleted;
        }

        /**
         * Sets the deleted.
         *
         * @param deleted the new deleted
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}