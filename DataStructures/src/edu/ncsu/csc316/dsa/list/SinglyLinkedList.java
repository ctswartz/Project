package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /**  A reference to the dummy/sentinel node at the front of the list *. */
    private LinkedListNode<E> front;
    
    /**  A reference to the last/final node in the list *. */
    private LinkedListNode<E> tail;
    
    /**  The number of elements stored in the list *. */
    private int size;
        
    /**
     * Constructs an empty singly-linked list.
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }

    /**
     * Adds an element at a specific index in the list.
     *
     * @param index the position where the element will be added
     * @param element the element to add
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
	@Override
	public void add(int index, E element) {
	    if(index < 0 || index > size) {
	        throw new IndexOutOfBoundsException("Invalid index");
	    }

	    // Start with the dummy front node.
	    LinkedListNode<E> current = front;
	    
	    // Traverse, if index is 0, the element is inserting at the start
	    for(int i = 0; i < index; i++) {
	        current = current.getNext();
	    }
	    
	    // Create the new node with the given element.
	    LinkedListNode<E> newNode = new LinkedListNode<>(element);
	    // Connect new next to the node after current
	    newNode.setNext(current.getNext());
	    // Update current next to point at the new node
	    current.setNext(newNode);
	    
	    // if added to the end of the list, update tail reference
	    // to point to the new node
	    if(index == size) {
	        tail = newNode;
	    }
	    
	    size++;
	}
	
	/**
	 * Adds an element to the end of the list.
	 *
	 * @param element the element to add
	 */
	@Override
	public void addLast(E element) {
		
	    LinkedListNode<E> newNode = new LinkedListNode<>(element);
	    if(isEmpty()) {
	        front.setNext(newNode);
	    } else {
	        tail.setNext(newNode);
	    }
	    tail = newNode;
	    size++;
	}

	/**
	 * Retrieves the element at a specific index in the list.
	 *
	 * @param index the position of the element to retrieve
	 * @return the element at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public E get(int index) {
	    if(index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException("Invalid index");
	    }
	    
	    // Skip over dummy node
	    LinkedListNode<E> current = front.getNext();  
	    for(int i = 0; i < index; i++) {
	        current = current.getNext();
	    }

	    return current.getData();
	}

	/**
	 * Returns an iterator over the list's elements.
	 *
	 * @return an iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * Retrieves the last element in the list.
	 *
	 * @return the last element
	 * @throws IndexOutOfBoundsException if the list is empty
	 */
	@Override
	public E last() {
	    if(isEmpty()) {
	        throw new IndexOutOfBoundsException("The list is empty");
	    }
	    return tail.getData();
	}
	
	/**
	 * Removes the element at a specific index in the list.
	 *
	 * @param index the position of the element to remove
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public E remove(int index) {
	    if(index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException("Invalid index");
	    }
	    
	    // Current points to start where dummy node is
	    LinkedListNode<E> current = front;
	    // Traverse
	    for(int i = 0; i < index; i++) {
	        current = current.getNext();
	    }
	    
	    // Get data being removed
	    E oldValue = current.getNext().getData();
	    
	    // Check is node being removed is the tail
	    if(index == size - 1) {
	        tail = current;
	    }
	    // Make next reference of current skip the node being removed
	    current.setNext(current.getNext().getNext());

	    // Decrement after removing
	    size--;

	    return oldValue;
	}
	
	/**
	 * Replaces the element at a specific index in the list.
	 *
	 * @param index the position of the element to replace
	 * @param element the new element
	 * @return the original element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public E set(int index, E element) {
	    if(index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException("Invalid index");
	    }

	    // Begin after dummy node
	    LinkedListNode<E> current = front.getNext();
	    // Traverse
	    for(int i = 0; i < index; i++) {
	        current = current.getNext();
	    }
	    // Get the current data
	    E oldValue = current.getData();
	    // Replace the old data with the new element
	    current.setData(element);
	    return oldValue;
	}

	/**
	 * Returns the number of elements in the list.
	 *
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
    
    /**
     * The LinkedListNode class.
     *
     * @param <E> the element type
     */
    private static class LinkedListNode<E> {
        
        /** The data to store. */
        private E data;
        
        /** The next node in the list. */
        private LinkedListNode<E> next;
        
        /**
         * Instantiates a new linked list node.
         *
         * @param data the data to store in the node
         */
        public LinkedListNode(E data) {
            this(data, null);
        }

        /**
         * Sets the data.
         *
         * @param element the new data
         */
        public void setData(E element) {
        	this.data = element;	
		}

		/**
		 * Instantiates a new linked list node.
		 *
		 * @param data the data
		 * @param next the next
		 */
		public LinkedListNode(E data, LinkedListNode<E> next) {
            this.data = data;
            this.next = next;
        }

        /**
         * Gets the data.
         *
         * @return the data
         */
        public E getData() {
            return data;
        }
        
        /**
         * Gets the next.
         *
         * @return the next
         */
        public LinkedListNode<E> getNext() {
            return next;
        }

        /**
         * Sets the next.
         *
         * @param next the new next
         */
        public void setNext(LinkedListNode<E> next) {
            this.next = next;
        }
        
    }
    
    /**
     * The ElementIterator Class
     * An Iterator to traverse through the elements of the singly-linked list.
     */
    private class ElementIterator implements Iterator<E> {
        
        /** Keep track of the next node that will be processed. */
        private LinkedListNode<E> current;
        
        /** The previous. */
        private LinkedListNode<E> previous;
        
        /** The remove OK. */
        private boolean removeOK;
        
        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
			previous = front;
			current = front.getNext();
        }

        /**
         * Checks if the list has more elements.
         *
         * @return true, if successful
         */
        @Override
        public boolean hasNext() {
        	return current != null;
        }

        /**
         * Returns the next element in the list.
         *
         * @return the e element
         * @throws NoSuchElementException if there are no more elements to return
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // Get data from current node
            E result = current.getData();
            // Update previous to point to current
            previous = current;
            // Move current to next node
            current = current.getNext();
            // Current element can be removed
            removeOK = true;
            // Return the data.
            return result;
        }
         
        /**
         * Remove is not supported.
         * @throws UnsupportedOperationException if remove is called
         */
        @Override    
        public void remove() {
    	    // DO NOT CHANGE THIS METHOD
            throw new UnsupportedOperationException(
                "This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
        }
    }
    
}