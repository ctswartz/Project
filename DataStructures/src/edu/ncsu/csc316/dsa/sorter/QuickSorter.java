package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** Track client's chosen PivotSelector. */
	private PivotSelector selector;
	
	/** The Constant FIRST_ELEMENT_SELECTOR. */
	public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
	
	/** The Constant LAST_ELEMENT_SELECTOR. */
	public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
	
	/** The Constant MIDDLE_ELEMENT_SELECTOR. */
	public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
	
	/** The Constant RANDOM_ELEMENT_SELECTOR. */
	public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
	
    /**
     * Constructs a new QuickSorter with a provided custom Comparator and a
     * specified PivotSelector strategy.
     *
     * @param comparator a custom comparator to use when sorting
     * @param selector   the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }

    /**
     * Constructs a new QuickSorter using the natural ordering of elements. Pivots
     * are selected using the provided PivotSelector strategy
     * 
     * @param selector the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and the
     * default random pivot selection strategy.
     *
     * @param comparator a custom comparator to use when sorting
     */
    public QuickSorter(Comparator<E> comparator) {
        this(comparator, null);
    }

    /**
     * Constructs a new QuickSorter that uses an element's natural ordering and uses
     * the random pivot selection strategy.
     */
    public QuickSorter() {
        this(null, null);
    }
    
    /**
     * Sets the selector.
     *
     * @param selector the new selector
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            this.selector = new RandomElementSelector();
        } else {
            this.selector = selector;
        }
    }
    
    /**
     * Sorts the data.
     *
     * @param data the data to sort.
     */
    @Override
    public void sort(E[] data) {
        quicksort(data, 0, data.length - 1);
    }

    /**
     * Sorts the data using quick sort algorithm.
     *
     * @param data the Array to sort
     * @param low the lower section
     * @param high the higher section
     */
    private void quicksort(E[] data, int low, int high) {
    	
    	// Base case
        if (low < high) {
        	// Partition data and get pivot
            int pivot = partition(data, low, high);
            // Sort left side
            quicksort(data, low, pivot - 1);
            // Sort right side
            quicksort(data, pivot + 1, high);
        }
    }

    /**
     * Partitions the data using the pivot strategy selected.
     *
     * @param data the array
     * @param low the lower section
     * @param high the higher section
     * @return the index of the pivot location
     */
    private int partition(E[] data, int low, int high) {

    	// Selector strategy
        int pivot = selector.selectPivot(low, high);

        // Move the pivot to the end
        swap(data, pivot, high);
        
        // Find the final position of the pivot
        int helper = partitionHelper(data, low, high);
        return helper;
    }

    /**
     * Partition helper.
     *
     * @param data the Array
     * @param low the lower section
     * @param high the higher section
     * @return the index of the pivot location
     */
    private int partitionHelper(E[] data, int low, int high) {

    	// Pivot taken from last element
        E pivot = data[high];
        // Separate at the starting index
        int separate = low; 
        // Traverse and reposition based on pivot
        for (int j = low; j <= high - 1; j++) {
        	// Compare current element with pivot
            if (compare(data[j], pivot) <= 0) {
            	// Swap separate element with current element
                swap(data, separate, j);
                // Move separate one to right
                separate = separate + 1;
            }
        }
        // Swap separate with pivot
        swap(data, separate, high);
        // Return the final pivot position
        return separate;
    }

    /**
     * Swaps two elements in the array.
     *
     * @param data the array
     * @param index1 the first index
     * @param index2 the second index
     */
    private void swap(E[] data, int index1, int index2) {
    	
    	// Store value at index1 in tempoarary
        E temporary = data[index1];
        // Set value at index1 to the value at index2
        data[index1] = data[index2];
        // Set value at index2 to original value of index1
        data[index2] = temporary;
    }    
    
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting.
     *
     * @author Dr. King
     * @author Courtney T Swartz (ctswartz)
     */
    public static class FirstElementSelector implements PivotSelector {

        /**
         * Select pivot.
         *
         * @param low the low
         * @param high the high
         * @return the int
         */
        @Override
        public int selectPivot(int low, int high) {
            return low;
        }
    }
    
    /**
     * LastElementSelector chooses the last index of the array as the index of the
     * pivot element that should be used when sorting.
     *
     * @author Dr. King
     * @author Courtney T Swartz (ctswartz)
     */
    public static class LastElementSelector implements PivotSelector {

        /**
         * Select pivot.
         *
         * @param low the low
         * @param high the high
         * @return the int
         */
        @Override
        public int selectPivot(int low, int high) {
            return high;
        }
    }
    
    /**
     * MiddleElementSelector chooses the middle index of the array as the index of the
     * pivot element that should be used when sorting.
     *
     * @author Dr. King
     * @author Courtney T Swartz (ctswartz)
     */
    public static class MiddleElementSelector implements PivotSelector {

        /**
         * Select pivot.
         *
         * @param low the low
         * @param high the high
         * @return the int
         */
        @Override
        public int selectPivot(int low, int high) {
        	int middle = (high + low) / 2;
            return middle;
        }
    }
    
    /**
     * RandomElementSelector returns a random index of the array, between low and high
     * inclusive.
     *
     * @author Dr. King
     * @author Courtney T Swartz (ctswartz)
     */
    public static class RandomElementSelector implements PivotSelector {

        /**
         * Select pivot.
         *
         * @param low the low
         * @param high the high
         * @return the int
         */
        @Override
        public int selectPivot(int low, int high) {
        	
        	Random generate = new Random();
        	
        	int random = generate.nextInt(high - low) + low;
        	
            return random;
        }
    }
    
    /**
     * Defines the behaviors of a PivotSelector.
     *
     * @author Dr. King
     * @author Courtney T Swartz (ctswartz)
     */
    private interface PivotSelector {
        
        /**
         * Returns the index of the selected pivot element.
         *
         * @param low  - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
}