package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * A HeapAdaptablePriorityQueue is an array-based min-heap implementation of the
 * {@link AdaptablePriorityQueue} abstract data type. HeapAdaptablePriorityQueue
 * ensures a O(logn) worst-case runtime for {@link PriorityQueue#insert},
 * {@link PriorityQueue#deleteMin}, {@link AdaptablePriorityQueue#remove}, and
 * {@link AdaptablePriorityQueue#replaceKey}. HeapAdaptablePriorityQueue ensures
 * a O(1) worst-case runtime for {@link PriorityQueue#min},
 * {@link PriorityQueue#size}, {@link PriorityQueue#isEmpty}, and
 * {@link AdaptablePriorityQueue#replaceValue}.
 * 
 * The HeapAdaptablePriorityQueue class is based on an implementation developed
 * for use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <K> the type of keys (priorities) stored in the adaptable priority
 *            queue
 * @param <V> the type of values that are associated with keys in the adaptable
 *            priority queue
 */
public class HeapAdaptablePriorityQueue<K extends Comparable<K>, V> extends HeapPriorityQueue<K, V>
        implements AdaptablePriorityQueue<K, V> {

    /**
     * An AdaptablePQEntry extends {@link PQEntry} to maintain a reference of the
     * entry's current index within the array-based heap data structure.
     * 
     * Adaptable PQ Entries must be location-aware so that the worst-case runtime of
     * replaceKey, replaceValue, and remove are O(log n)
     * 
     * @author Dr. King
     * @author Courtney T Swartz (ctswartz)
     *
     * @param <K> the type of key (priority) stored in the adaptable prioriy queue
     *            entry
     * @param <V> the type of value stored in the adaptable priority queue entry
     */
    public static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {

        /** The index. */
        private int index;

        /**
         * Constructs a new AdaptablePQEntry with the given key, value, and index.
         *
         * @param key   the key (priority) of the adaptable priority queue entry
         * @param value the value of the adaptable priority queue entry
         * @param index the index within the array where the entry is currently located
         */
        public AdaptablePQEntry(K key, V value, int index) {
            super(key, value);
            setIndex(index);
        }

        /**
         * Returns the index of the entry within the array-based heap structure.
         *
         * @return the index of the entry within the array-based heap structure
         */
        public int getIndex() {
            return index;
        }

        /**
         * Sets the index of the entry within the array-based heap structure.
         *
         * @param index the index of the entry within the array-based heap structure
         */
        public void setIndex(int index) {
            this.index = index;
        }
    }

    /**
     * Constructs a new HeapAdaptablePriorityQueue using a custom comparator.
     *
     * @param comparator the custom Comparator to use when comparing keys (priorities)
     */
    public HeapAdaptablePriorityQueue(Comparator<K> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new HeapAdaptablePriorityQueue using the natural ordering of
     * keys.
     */
    public HeapAdaptablePriorityQueue() {
        this(null);
    }

    /**
     * {@inheritDoc}
     * 
     * Specifically, creates a new AdaptablePQEntry with an initial index set to the
     * end of the array-based heap structure
     */
    @Override
    protected AdaptablePQEntry<K, V> createEntry(K key, V value) {
        AdaptablePQEntry<K, V> temp = new AdaptablePQEntry<K, V>(key, value, size());
        return temp;
    }

    /**
     * Validate the entry.
     *
     * @param entry the entry
     * @return the adaptable PQ entry
     * @throws IllegalArgumentException if entry is not valid
     * @throws IllegalArgumentException if entry is not adaptable
     */
    private AdaptablePQEntry<K, V> validate(Entry<K, V> entry) {
        if (!(entry instanceof AdaptablePQEntry)) {
            throw new IllegalArgumentException("Entry is not a valid adaptable priority queue entry.");
        }
        AdaptablePQEntry<K, V> temp = (AdaptablePQEntry<K, V>) entry;
        if (temp.getIndex() >= list.size() || list.get(temp.getIndex()) != temp) {
            throw new IllegalArgumentException("Invalid Adaptable PQ Entry.");
        }
        return temp;
    }

    /**
     * Swaps entries.
     *
     * @param index1 the index 1
     * @param index2 the index 2
     */
    @Override
    public void swap(int index1, int index2) {
        // Delegate to the super class swap method
        super.swap(index1, index2);
        // But then update the index of each entry so that they remain location-aware
        ((AdaptablePQEntry<K, V>) list.get(index1)).setIndex(index1);
        ((AdaptablePQEntry<K, V>) list.get(index2)).setIndex(index2);
    }

    /**
     * Removes the entry.
     *
     * @param entry the entry
     */
    @Override
    public void remove(Entry<K, V> entry) {
        AdaptablePQEntry<K, V> locate = validate(entry);
        int idx = locate.getIndex();
        if (idx == list.size() - 1) { // last entry
            list.remove(list.size() - 1);
        } else {
            swap(idx, list.size() - 1); // swap with last entry
            list.remove(list.size() - 1); // remove last entry
            bubble(idx);
        }
    }

    /**
     * Restores the heap property for an entry at a given index after a change in its key or value.
     * If the entry needs to rise to maintain the heap property, an up-heap operation is performed;
     * otherwise, if it needs to sink down, a down-heap operation is executed.
     *
     * @param index the index of the modified entry within the heap
     */
    private void bubble(int index) {
        if (index > 0 && compare(list.get(index).getKey(), list.get(parent(index)).getKey()) < 0) {
            upHeap(index);
        } else {
            downHeap(index);
        }
    }

    /**
     * Replaces the key of the given entry with a new key and repositions the entry
     * within the heap to maintain the heap property.
     * 
     * @param entry the entry whose key is to be replaced
     * @param key   the new key to replace the old key
     */
    @Override
    public void replaceKey(Entry<K, V> entry, K key) {
        AdaptablePQEntry<K, V> locate = validate(entry);
        locate.setKey(key);
        bubble(locate.getIndex());
    }

    /**
     * Replace value.
     *
     * @param entry the entry
     * @param value the value
     */
    @Override
    public void replaceValue(Entry<K, V> entry, V value) {
        AdaptablePQEntry<K, V> locate = validate(entry);
        locate.setValue(value);
    }
}
