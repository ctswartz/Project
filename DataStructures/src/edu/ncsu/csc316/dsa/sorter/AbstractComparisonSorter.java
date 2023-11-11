package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * The AbstractComparisonSorter class.
 * Used for sorting algorithms that use comparison-based sorting.
 *
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the generic type of elements to be sorted
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

    /** The comparator. */
    private Comparator<E> comparator;
    
    /**
     * Instantiates a new abstract comparison sorter.
     *
     * @param comparator the comparator
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Sets the comparator.
     * If null is passed, uses natural ordering.
     *
     * @param comparator the new comparator to set
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    /**
     * Defines natural order comparison.
     */
    private class NaturalOrder implements Comparator<E> {
        
        /**
         * Compares two elements based on natural order.
         *
         * @param first the first element to compare
         * @param second the second element to compare
         * @return the result of first to second,
         * negative if less than, 0 if equal, positive if greater than
         */
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * Compares two elements based on specified comparator 
     * or natural order if none are specified.
     *
     * @param first the first element to compare
     * @param second the second element to compare
     * @return the result of first to second,
     * negative if less than, 0 if equal, positive if greater than
     */
    public int compare(E first, E second) {
        return comparator.compare(first,  second);
    }
}