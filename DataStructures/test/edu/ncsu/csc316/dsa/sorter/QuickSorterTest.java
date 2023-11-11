package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the QuickSorter class.
 * 
 * @author Courtney T Swartz (ctswartz)
 */

public class QuickSorterTest {

    /** The sorter. */
    private QuickSorter<Integer> sorter;

    /**
     * Set up a sorter to test.
     */
    @Before
    public void setUp() {
        sorter = new QuickSorter<>();
    }

    /**
     * Test quick sort first element.
     */
    @Test
    public void testQuickSortFirstElement() {
        sorter = new QuickSorter<>(QuickSorter.FIRST_ELEMENT_SELECTOR);
        Integer[] data = { 18, -80, 0, 45, -2, 6, 2, 25, 1 };
        sorter.sort(data);

        Integer[] expected = {-80, -2, 0, 1, 2, 6, 18, 25, 45};
        for (int i = 0; i < data.length; i++) {
            assertEquals(expected[i], data[i]);
        }
    }

    /**
     * Test quick sort last element.
     */
    @Test
    public void testQuickSortLastElement() {
        sorter = new QuickSorter<>(QuickSorter.LAST_ELEMENT_SELECTOR);
        Integer[] data = { 18, -80, 0, 45, -2, 6, 2, 25, 1 };
        sorter.sort(data);

        Integer[] expected = {-80, -2, 0, 1, 2, 6, 18, 25, 45};
        for (int i = 0; i < data.length; i++) {
            assertEquals(expected[i], data[i]);
        }
    }

    /**
     * Test quick sort middle element.
     */
    @Test
    public void testQuickSortMiddleElement() {
        sorter = new QuickSorter<>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
        Integer[] data = { 18, -80, 0, 45, -2, 6, 2, 25, 1 };
        sorter.sort(data);

        Integer[] expected = {-80, -2, 0, 1, 2, 6, 18, 25, 45};
        for (int i = 0; i < data.length; i++) {
            assertEquals(expected[i], data[i]);
        }
    }

    /**
     * Test quick sort random element.
     */
    @Test
    public void testQuickSortRandomElement() {
        sorter = new QuickSorter<>(QuickSorter.RANDOM_ELEMENT_SELECTOR);
        Integer[] data = { 18, -80, 0, 45, -2, 6, 2, 25, 1 };
        sorter.sort(data);

        Integer[] expected = {-80, -2, 0, 1, 2, 6, 18, 25, 45};
        for (int i = 0; i < data.length; i++) {
            assertEquals(expected[i], data[i]);
        }
    }
}
