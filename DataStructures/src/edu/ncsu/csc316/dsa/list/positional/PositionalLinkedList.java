package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /**  A dummy/sentinel node representing at the front of the list *. */
    private PositionalNode<E> front;

    /**  A dummy/sentinel node representing at the end/tail of the list *. */
    private PositionalNode<E> tail;

    /**  The number of elements in the list *. */
    private int size;

    /**
     * Constructs an empty positional linked list.
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }

	/**
	 * Returns an iterator over elements in the list.
	 *
	 * @return the iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * Adds an element after a given position.
	 *
	 * @param p the p
	 * @param element the element
	 * @return the position
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E element) {
        PositionalNode<E> node = validate(p);
        return addBetween(element, node.getNext(), node);
	}

	/**
	 *  Adds an element before a given position.
	 *
	 * @param p the p
	 * @param element the element
	 * @return the position
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E element) {
        PositionalNode<E> node = validate(p);
        return addBetween(element, node, node.getPrevious());
	}
	
    /**
     * Helper method that inserts an element between two nodes.
     *
     * @param element element to insert
     * @param next Node after the insertion
     * @param prev Node before the insertion
     * @return the position of the inserted element
     */
    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
        PositionalNode<E> newNode = new PositionalNode<>(element, next, prev);
        prev.setNext(newNode);
        next.setPrevious(newNode);
        size++;
        return newNode;
    }

	/**
	 * Adds an element to the start of the list.
	 *
	 * @param element the element
	 * @return the position
	 */
	@Override
	public Position<E> addFirst(E element) {
		Position<E> position = addBetween(element, front.getNext(), front);
		return position;
	}

	/**
	 * Adds an element to the end of the list.
	 *
	 * @param element the element
	 * @return the position
	 */
	@Override
	public Position<E> addLast(E element) {
		Position<E> position = addBetween(element, tail, tail.getPrevious());
		return position;
	}

	/**
	 * Gets the position after the specified one.
	 *
	 * @param p the p
	 * @return the position
	 */
	@Override
	public Position<E> after(Position<E> p) {
        PositionalNode<E> node = validate(p);
        return node.getNext() == tail ? null : node.getNext();
	}

	/**
	 * Gets the position before the specified one
	 *
	 * @param p the p
	 * @return the position
	 */
	@Override
	public Position<E> before(Position<E> p) {
        PositionalNode<E> node = validate(p);
        return node.getPrevious() == front ? null : node.getPrevious();
	}

	/**
	 * Gets the first position in the list.
	 *
	 * @return the position
	 */
	@Override
	public Position<E> first() {
		return isEmpty() ? null : front.getNext();
	}

	/**
	 * Checks if the list is empty.
	 *
	 * @return true, if is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Gets the last position in the list.
	 *
	 * @return the position
	 */
	@Override
	public Position<E> last() {
		return isEmpty() ? null : tail.getPrevious();
	}

	/**
	 * Gives an iterable collection of positions in the list.
	 *
	 * @return the iterable
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	/**
	 * Removes the element at the specified position.
	 *
	 * @param p the position of the element to remove
	 * @return the removed element
	 */
	@Override
	public E remove(Position<E> p) {
	    PositionalNode<E> currentNode = validate(p);
	    PositionalNode<E> previousNode = currentNode.getPrevious();
	    PositionalNode<E> nextNode = currentNode.getNext();
	    previousNode.setNext(nextNode);
	    nextNode.setPrevious(previousNode);
	    size--;
	    E removedElement = currentNode.getElement();
	    currentNode.setElement(null);
	    currentNode.setNext(null);
	    currentNode.setPrevious(null);
	    return removedElement;
	}

	/**
	 * Replaces the element at the specified position with the given element.
	 *
	 * @param p the position of the element to replace
	 * @param element the new element
	 * @return the original element that was replaced
	 */
	@Override
	public E set(Position<E> p, E element) {
	    PositionalNode<E> currentNode = validate(p);
	    E originalElement = currentNode.getElement();
	    currentNode.setElement(element);
	    return originalElement;
	}

	/**
	 * Gets the size.
	 *
	 * @return the int the number of elements
	 */
	@Override
	public int size() {
		return size;
	}
	
    /**
     * The PositionalNode Class.
     * Represents a node in the positional linked list.
     *
     * @param <E> the element type to store
     */
    private static class PositionalNode<E> implements Position<E> {

        /** The element. */
        private E element;
        
        /** The next. */
        private PositionalNode<E> next;
        
        /** The previous. */
        private PositionalNode<E> previous;

        /**
         * Instantiates a new positional node.
         *
         * @param value the value
         */
        public PositionalNode(E value) {
            this(value, null);
        }

        /**
         * Instantiates a new positional node.
         *
         * @param value the value
         * @param next the next
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        /**
         * Instantiates a new positional node.
         *
         * @param value the value
         * @param next the next
         * @param prev the prev
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        /**
         * Sets the previous.
         *
         * @param prev the new previous
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
         * Gets the previous.
         *
         * @return the previous
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * Sets the next.
         *
         * @param next the new next
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
         * Gets the next.
         *
         * @return the next
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        /**
         * Gets the element.
         *
         * @return the element
         */
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the element.
         *
         * @param element the new element
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    
    /**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    /**
     * The PositionIterator class.
     * An iterator over positions in the list.
     */
    private class PositionIterator implements Iterator<Position<E>> {

        /** The current. */
        private Position<E> current;
        
        /** The remove OK. */
        private boolean removeOK;

        /**
         * Instantiates a new position iterator.
         */
        public PositionIterator() {
            current = front.getNext();
            removeOK = false;
        }

        /**
         * Checks for next.
         *
         * @return true, if successful
         */
        @Override
        public boolean hasNext() {
        	return current != tail;
        }

        /**
         * The next position.
         *
         * @return the position
         * @throws NoSuchElementException if there is no element
         */
        @Override
        public Position<E> next() {
            if (!hasNext()) throw new NoSuchElementException();
            Position<E> result = current;
            current = ((PositionalNode<E>) current).getNext();
            removeOK = true;
            return result;
        }

        /**
         * Remove is not supported.
         * @throws IllegalStateException if remove is called
         */
        @Override
        public void remove() {
            if (!removeOK) throw new IllegalStateException();
            PositionalLinkedList.this.remove(before(current));
            removeOK = false;
        }
    }
	
    
    
    /**
     * The ElementIterator Class.
     * An iterator over elements in the list.
     */
    private class ElementIterator implements Iterator<E> {

        /** The iterator. */
        private Iterator<Position<E>> it;

        /**
         * Instantiates a new element iterator.
         */
        public ElementIterator() {
            it = new PositionIterator();
        }

        /**
         * Checks for next element.
         *
         * @return true, if successful
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
         * Returns the next element.
         *
         * @return the e The element
         */
        @Override
        public E next() {
            return it.next().getElement();
        }

        /**
         * Removes the element.
         */
        @Override
        public void remove() {
            it.remove();
        }
    }
    
    /**
     * The PositionIterable. Class.
     * Provides an iterable collection of positions in the list.
     */
    private class PositionIterable implements Iterable<Position<E>> {
        
        /**
         * The iterator.
         *
         * @return the iterator
         */
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
    
    
    
	

}