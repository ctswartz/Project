package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    /**
     * MapEntry implements the Entry abstract data type.
     * Creates a MapEntry with the provided key and value.
     * 
     * @author Dr. King
     * @author Courtney T Swartz (ctswartz)
     *
     * @param <K> the type of key stored in the entry
     * @param <V> the type of value stored in the entry
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {

        /** The key. */
        private K key;
        
        /** The value. */
        private V value;

        /**
         * Constructs a MapEntry with a provided key and a provided value.
         *
         * @param key   the key to store in the entry
         * @param value the value to store in the entry
         */
        public MapEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }

        /**
         * Gets the key.
         *
         * @return the key
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Set the key of the entry to the provided key.
         *
         * @param key the key to store in the entry
         */
        private void setKey(K key) {
            this.key = key;
        }

        /**
         * Set the value of the entry to the provided value.
         *
         * @param value the value to store in the entry
         */
        public void setValue(V value) {
            this.value = value;
        }
        
        /**
         * Compare the current Entry's key with another
         *
         * @param o the other Entry
         * @return a negative number, zero, or a positive number
         * if the key is less than, equal to, or greater than the other key
         */
        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable<K>)this.key).compareTo(o.getKey());
        }       
    }     

    /**
     * KeyIterator implements the {@link Iterator} interface to allow traversing
     * through the keys stored in the map.
     *
     * @author Dr. King
     * @author Courtney T Swartz (ctswartz)
     */
    protected class KeyIterator implements Iterator<K> {

        /** The Entry. */
        private Iterator<Entry<K, V>> it;
        
        /**
         * Instantiates a new key iterator.
         */
        public KeyIterator() {
            it = entrySet().iterator();
        }
        
        /**
         * Checks for the next element.
         *
         * @return true, if the next element if found, false otherwise
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
         * Returns the next key.
         *
         * @return the key
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public K next() {
            return it.next().getKey();
        }
        
        /**
         * Remove is not supported for the iterator.
         * 
         * @throws UnsupportedOperationException since the remove operation is not supported
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    }
    
    /**
     * ValueIterator implements the {@link Iterator} interface to allow traversing
     * through the values stored in the map.
     *
     * @author Dr. King
     * @author Courtney T Swartz (ctswartz)
     */
    private class ValueIterable implements Iterable<V> {
        
        /**
         * Iterator.
         *
         * @return the iterator
         */
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Iterator.
     *
     * @return the iterator
     */
    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    /**
     * Values.
     *
     * @return the iterable
     */
    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }
    
    /**
     * ValueIterator implements the {@link Iterator} interface to allow traversing
     * through the values stored in the map.
     *
     * @author Courtney T Swartz (ctswartz)
     */
    protected class ValueIterator implements Iterator<V> {

        /** The Entry. */
        private Iterator<Entry<K, V>> it;
        
        /**
         * Instantiates a new value iterator.
         */
        public ValueIterator() {
            it = entrySet().iterator();
        }
        
        /**
         * Checks for next element
         *
         * @return true if there's a next element, false otherwise
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
         * Returns the next value.
         *
         * @return the next value
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public V next() {  
            return it.next().getValue();
        }
        
        /**
         * Remove is not supported for the iterator.
         * 
         * @throws UnsupportedOperationException since the remove operation is not supported
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    }
    
    /**
     * EntryCollection implements the {@link Iterable} interface to allow traversing
     * through the entries stored in the map. EntryCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     * @author Courtney T Swartz (ctswartz)
     *
     */
    protected class EntryCollection implements Iterable<Entry<K, V>> {

        /** The list. */
        private List<Entry<K, V>> list;

        /**
         * Instantiates a new entry collection.
         */
        public EntryCollection() {
            list = new SinglyLinkedList<Entry<K, V>>();
        }

        /**
         * Adds the provided Entry to the list.
         *
         * @param entry the entry to add
         */
        public void add(Entry<K, V> entry) {
            list.addLast(entry);
        }

        /**
         * Provides an iterator over the entires.
         *
         * @return the iterator
         */
        public Iterator<Entry<K, V>> iterator() {
            return new EntryCollectionIterator();
        }

        /**
         * The Class EntryCollectionIterator.
         */
        private class EntryCollectionIterator implements Iterator<Entry<K, V>> {

            /** The it. */
            private Iterator<Entry<K, V>> it;

            /**
             * Instantiates a new entry collection iterator.
             */
            public EntryCollectionIterator() {
                it = list.iterator();
            }

            /**
             * Checks for next element
             *
             * @return true if there is a next element
             */
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            /**
             * Returns the next entry.
             *
             * @return the next entry
             * @throws NoSuchElementException if there are no more elements
             */
            @Override
            public Entry<K, V> next() {
                return it.next();
            }

            /**
             * Remove is not supported for the iterator.
             * 
             * @throws UnsupportedOperationException since the remove operation is not supported
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }

}