package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

    /** The list of Entries. */
    private PositionalList<Entry<K, V>> list;
    
    /**
     * Instantiates a new unordered linked map.
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    /**
     * Finds the position of the specified key within the map
     *
     * @param key the key to search for
     * @return the position of the entry with the specified key
     */
    private Position<Entry<K, V>> lookUp(K key) {
        for(Position<Entry<K, V>> p : list.positions()) {
            if(p.getElement().getKey().equals(key)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets the value of the key.
     *
     * @param key the key tied to the value
     * @return the value tied to the key
     */
    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if(p != null) {
            V result = p.getElement().getValue();
            moveToFront(p);
            return result;
        }
        return null;
    }
    
    /**
     * Moves the entry at the specified position to the front.
     *
     * @param position the position of the entry to move to the front
     */
    private void moveToFront(Position<Entry<K, V>> position) {
        Entry<K, V> e = list.remove(position);
        list.addFirst(e);
    }

    /**
     * Inserts the entry with the specified key and value
     * If there is already an entry, it is replaced.
     *
     * @param key the key to tie to the value
     * @param value the value to tie with the key
     * @return the value that was previously tied to the key
     */
    @Override
    public V put(K key, V value) {
    	// Find the position of the entry tied to the given key
        Position<Entry<K, V>> p = lookUp(key);
        // If not found, add a new entry
        if(p == null) {
            list.addFirst(new MapEntry<K, V>(key, value));
            return null;
        }
        // If found, store the old value
        V old = p.getElement().getValue();
        // Remove the old entry
        list.remove(p);
        // Add the entry to the front
        list.addFirst(new MapEntry<K, V>(key, value));
        return old;
    }
    
    /**
     * Removes the entry for the specified key.
     *
     * @param key the key whose entry is to be removed
     * @return the previous value tied to the key, or null if there isn't one
     */
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if(p != null) {
           return list.remove(p).getValue();
       }
       return null;
    }
    
    /**
     * Returns a collection of all the key-value entries
     *
     * @return all of the key-value entries
     */
    @Override
    public int size() {
        return list.size();
    }
    
    /**
     * Returns all of the key-value entries in the map.
     *
     * @return a collection of all key-value entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    /**
     * Returns a string representation of the map.
     *
     * @return a string representation of the map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
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