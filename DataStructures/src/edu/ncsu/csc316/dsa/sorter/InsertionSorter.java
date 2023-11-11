package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

//import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 * 
 * @param <E> the generic element type
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Instantiates a new insertion sorter.
     * Accepts custom comparators.
     *
     * @param comparator the comparator
     */
    public InsertionSorter(Comparator<E> comparator) {
    	super(comparator);
    }
    
    /**
     * Default constructor using natural ordering.
     * Instantiates a new insertion sorter.
     */
    public InsertionSorter() {
    	super(null);
    }

	/**
     * Sort using the insertion sort algorithm.
     *
     * @param data the array of elements to sort
     */
    public void sort(E[] data) {
    	
    	// Start at second element
        for (int i = 1; i < data.length; i++) {
        	
        	//Store current element
            E value = data[i];
            // Make position to place the element
            int j = i - 1;
            
            // Move greater elements up to make room for "value"
            while (j >= 0 && data[j].compareTo(value) > 0) {
            	// Shift the greater element right
                data[j + 1] = data[j];
                // Move to previous position
                j = j - 1;
            }
            // Put "value" in correct position
            data[j + 1] = value;
        }
    }
}
