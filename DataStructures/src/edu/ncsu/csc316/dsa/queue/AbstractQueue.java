package edu.ncsu.csc316.dsa.queue;

/**
 * A skeletal implementation of the Queue abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the queue abstract data type.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the queue
 */
public abstract class AbstractQueue<E> implements Queue<E> {

	/**
	 * Checks if queue is empty.
	 *
	 * @return true, if is empty
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}