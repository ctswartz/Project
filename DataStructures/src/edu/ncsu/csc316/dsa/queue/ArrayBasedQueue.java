package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * The Array-based Queue is implemented as a circular array-based data structure
 * to support efficient, O(1) worst-case Queue abstract data type behaviors. The
 * internal array dynamically resizes using the doubling strategy to ensure O(1)
 * amortized cost for adding to the queue.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the queue
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

    /** Internal array to store the data within the queue. */
    private E[] data;

    /** A reference to the index of the first element in the queue. */
    private int front;

    /** A reference to the index immediately after the last element in the queue. */
    private int rear;

    /** The number of elements stored in the queue. */
    private int size;

    /** The initial default capacity of the internal array that stores the data. */
    private static final int DEFAULT_CAPACITY = 0;

    /**
     * Constructs a new array-based queue with the given initialCapcity for the
     * array.
     *
     * @param initialCapacity the initial capacity to use when creating the initial
     *                        array
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedQueue(int initialCapacity) {
        data = (E[]) (new Object[initialCapacity]);
        size = 0;
    }

    /**
     * Constructs a new array-based queue with the default initial capacity for the
     * array.
     */
    public ArrayBasedQueue() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * To ensure amortized O(1) cost for adding to the array-based queue, use the
     * doubling strategy on each resize. Here, we add +1 after doubling to handle
     * the special case where the initial capacity is 0 (otherwise, 0*2 would still
     * produce a capacity of 0).
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
            @SuppressWarnings("unchecked")
            E[] newData = (E[]) (new Object[newCapacity]);

            // Remember that we cannot copy an array the same way we do
            // when resizing an array-based list since we do not want to
            // "break" the elements in the queue since there may be wrap-around
            // at the end of the array
            
            // front of old array
            int j = front;
            // place elements at index 0 in new array
            int i = 0;
            
            // Copy elements from the old array to the new array
            while (i < size) {
            	// Copy current element, add to new array
                newData[i] = data[j];
                // Wrap-around to next position in old array
                j = (j + 1) % data.length;
                // Move to next position in new array
                i++;
            }

            front = 0; // Reset front
            rear = size; // Reset rear
            
            // Replace old array with new, resized array
            data = newData;
        }
    }

	/**
	 * Enqueue adds the given element to the end of the queue.
	 * If the array if full, this method ensures capacity before
	 * the element is added.
	 *
	 * @param value the element to add to the end of the queue
	 */
	@Override
	public void enqueue(E value) {
	    // Check if array is empty and if so, ensure a capacity of 1
	    if (data.length == 0) {
	        ensureCapacity(1);
	    // If array is full, double it
	    } else if (size == data.length) {
	        ensureCapacity(size * 2);
	    }
        // Add new value to rear of array
        data[rear] = value;
        // Wrap around to 0 if array rear pointer exceeds array length
        rear = (rear + 1) % data.length;
        // Increase size of queue
        size++;
	}

	/**
	 * Dequeue removes and returns the element at the front of the queue.
	 * 
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // Get the front element
        E removedValue = data[front];
        // Set the front position to null
        data[front] = null;
        // Wrap around to 0 if array front pointer exceeds array length
        front = (front + 1) % data.length;
        // Decrease the size of the queue
        size--;
        // Return the removed element
        return removedValue;
	}

	/**
	 * Gets the element at the front of the queue
	 * but does not remove it.
	 *
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E front() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[front];
	}

	/**
	 * Returns the size of the queue.
	 *
	 * @return the number of elements in the queue.
	 */
	@Override
	public int size() {
		return size;
	}
}   