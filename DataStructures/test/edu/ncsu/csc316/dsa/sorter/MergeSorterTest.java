package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the MergeSorter class.
 * 
 * @author Courtney T Swartz (ctswartz)
 */
public class MergeSorterTest {

    /** The integer sorter. */
    private MergeSorter<Integer> integerSorter;
    
    /** The string sorter. */
    private MergeSorter<String> stringSorter;

    /**
     * Set up a list of integers and strings to test.
     */
    @Before
    public void setUp() {
        integerSorter = new MergeSorter<>();
        stringSorter = new MergeSorter<>();
    }

    /**
     * Test sort with integers.
     */
    @Test
    public void testSortWithIntegers() {
        Integer[] data = { 7, 1, 4, 18, 9 };
        
        integerSorter.sort(data); 
        // 1, 4, 7, 9, 18
        assertEquals(1, (int) data[0]);
        assertEquals(4, (int) data[1]);
        assertEquals(7, (int) data[2]);
        assertEquals(9, (int) data[3]);
        assertEquals(18, (int) data[4]);
    }

    /**
     * Test sort with strings.
     */
    @Test
    public void testSortWithStrings() {
        String[] data = { "blue", "red", "green", "yellow", "violet" };
        // blue, green, red, violet, yellow
        stringSorter.sort(data);
        assertEquals("blue", data[0]);
        assertEquals("green", data[1]);
        assertEquals("red", data[2]);
        assertEquals("violet", data[3]);
        assertEquals("yellow", data[4]);
    }

    /**
     * Test sort empty list.
     */
    @Test
    public void testSortEmptyList() {
        Integer[] data = {};
        
        integerSorter.sort(data);
        assertEquals(0, data.length);
    }
}
