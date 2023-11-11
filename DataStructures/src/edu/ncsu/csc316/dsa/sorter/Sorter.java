package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior.
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 * 
 * @param <E> the generic type of elements to sort
 */
public interface Sorter<E> {
	
	/**
	 * Sorts the items.
	 *
	 * @param data the elements to sort
	 */
	void sort(E[] data);
}