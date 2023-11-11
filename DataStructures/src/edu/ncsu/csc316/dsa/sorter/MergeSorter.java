package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator.
     *
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering.
     */ 
    public MergeSorter() {
        this(null);
    }
    
    /**
     * Sorts the data using merge sort.
     *
     * @param data the array to sort
     */
    @Override
    public void sort(E[] data) {
    	
    	// An array of size 1 or 0 is already sorted
        if (data.length < 2) {
            return; 
        }

        // Find the middle index of the array
        int middle = data.length / 2;

        
        @SuppressWarnings("unchecked")
        // Make the left half
		E[] left = (E[]) new Comparable[middle];
        
        // Copy the first half into the left array
        for (int i = 0; i < middle; i++) {
            left[i] = data[i];
        }

        @SuppressWarnings("unchecked")
        // Make the right half
		E[] right = (E[]) new Comparable[data.length - middle];
        
        // Copy the second half into the right array
        for (int i = middle; i < data.length; i++) {
            right[i - middle] = data[i];
        }

        // Sort the left half with recursion
        sort(left);
        // Sort the right half with recursion
        sort(right);

        // Merge the halves back into the original array after sorting
        merge(left, right, data);
    }

    /**
     * Merges two sorted arrays into a single array that is sorted.
     *
     * @param s1 the sorted left half
     * @param s2 the sorted right half
     * @param s the original array, sorted
     */
    private void merge(E[] s1, E[] s2, E[] s) {
    	
        int left = 0;
        int right = 0;
        int i = 0;
        
        // Traverse until all elements are checked
        while (left + right < s.length) {
        	// Check all elements in right array
        	// OR if the current element in the left is smaller
        	// than the current element in the right
            if (right == s2.length || 
                (left < s1.length && compare(s1[left], s2[right]) < 0)) {
            	// Take element from left
                s[i] = s1[left];
                left++;
            } else {
            	// Take element from right
                s[i] = s2[right];
                right++;
            }
            // Next position in the original array
            i++;
        }
    }

}