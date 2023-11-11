package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /** The initial capacity of the list if the client does not provide a capacity when constructing an instance of the array-based list. */
    private final static int DEFAULT_CAPACITY = 0;

    /**  The array in which elements will be stored *. */
    private E[] data;

    /**  The number of elements stored in the array-based list data structure *. */
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array.
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity.
     *
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }
    
    /**
     * Inserts an element at the specified index.
     * Resizes the internal array if needed.
     *
     * @param index The index at which to add the element.
     * @param value The element to add.
     */
    @Override
    public void add(int index, E value) {
        checkIndexForAdd(index);
        
        // Resize the array if the size plus new element exceeds capacity
        if (size + 1 > data.length) {
            ensureCapacity(size + 1);
        }
        
        // Shift elements to the right from the index
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }
    
    /**
		 * To ensure amortized O(1) cost for adding to the end of the array-based list,
		 * use the doubling strategy on each resize. Here, we add +1 after doubling to
		 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
		 * still produce a capacity of 0).
		 * 
		 * @param minCapacity the minimium capacity that must be supported by the
		 *                    internal array
		 */
	    private void ensureCapacity(int minCapacity) {
	        int oldCapacity = data.length;
	        if (minCapacity > oldCapacity) {
	            int newCapacity = (oldCapacity * 2) + 1;
	            if (newCapacity < minCapacity) {
	                newCapacity = minCapacity;
	            }
	            data = Arrays.copyOf(data, newCapacity);
	        }
	}

	/**
	 * Returns the element at the specified index in the list.
	 * 
	 * @param index The index of the element being returned.
	 * @return E the element at the specified index.
	 */
	@Override
	public E get(int index) {
	    checkIndex(index);
	    return data[index];
	}
	
	/**
	 * Returns an iterator over the elements in this list.
	 *
	 * @return the iterator over the list's elements
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * Deletes the element at the given index in the list and returns it.
	 * Subsequent elements are shifted left, reducing their indices by one.
	 *
	 * @param index Position of the element to remove.
	 * @return E The element that was removed.
	 */
	@Override
    public E remove(int index) {
        checkIndex(index);
        
        // Save the value that is being removed
        E old = data[index];
        
        // Shift elements to the left to remove blank space caused by removing
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        
        // Clear the last value
        data[size - 1] = null;
        size--;
        
        // Return value that was removed
        return old;
    }
    
	/**
	 * Updates the list by setting the given element at the specified index, 
	 * and returns the element that was formerly at that position.
	 *
	 * @param index Position at which the element should be updated.
	 * @param value Element to be set at the indicated index.
	 * @return The element that was originally at the given index.
	 */
    @Override
    public E set(int index, E value) {
        checkIndex(index);
        // Store the value at the given index
        E previous = data[index];
        //Replace the value with the new value
        data[index] = value;
        
        // Return the original value
        return previous;
    }

    /**
     * Returns the size or number of elements in the list.
     *
     * @return size The number of elements in the list.
     */
    @Override
    public int size() {
        return size;
    }
    
    
    /**
     * Iterator over the array-based list's elements.
     */
    private class ElementIterator implements Iterator<E> {
        
        /** The position. */
        private int position;
        
        /** Boolean flag for removing */
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
        	position = 0;
        	removeOK = false;
        }

        /**
         * Checks if there are more elements to iterate over
         *
         * @return true, if successful
         */
        @Override
        public boolean hasNext() {
        	return position < size();
        }

        /**
         * Returns the next element in the list.
         *
         * @return the e The element
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list to iterate over.");
            }
            removeOK = true;
            
            //Return the element and increment the position
            return data[position++];
        }
            
        /**
         * Removes the current element.
         */
        @Override
        public void remove() {
        	
            if (!removeOK) {
                throw new IllegalStateException("Cannot call remove() before next() or more than once in a row.");
            }
            
            ArrayBasedList.this.remove(position - 1);
            position--;
            // Reset boolean so next() is called before remove()
            removeOK = false; 
        }
    }
}