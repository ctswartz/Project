package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure .
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 */
public class HeapAdaptablePriorityQueueTest {

    /** The heap. */
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes.
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior.
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
//        Entry<Integer, String> e7 = heap.insert(7, "seven");
//        Entry<Integer, String> e6 = heap.insert(6, "six");
//        Entry<Integer, String> e5 = heap.insert(5, "five");
//        Entry<Integer, String> e4 = heap.insert(4, "four");
//        Entry<Integer, String> e3 = heap.insert(3, "three");
//        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
//        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(2, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(2, heap.size());

        // Test replacing key
        heap.replaceKey(e1, 9);
        assertEquals(9, (int)e1.getKey());
        assertNotEquals("one", heap.min().getValue());

        // Test invalid replaceKey operation
        Entry<Integer, String> fakeEntry = new Entry<>() {
            @Override public Integer getKey() { return 99; }
            @Override public String getValue() { return "ninety-nine"; }
        };
        try {
            heap.replaceKey(fakeEntry, 0);
            fail();
        } catch (Exception e) {
            // Expected exception
        }
        
    }
    
    /**
     * Test the output of the replaceValue behavior.
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        // Replace values for each entry
        heap.replaceValue(e8, "EIGHT");
        heap.replaceValue(e7, "SEVEN");
        heap.replaceValue(e6, "SIX");
        heap.replaceValue(e5, "FIVE");
        heap.replaceValue(e4, "FOUR");
        heap.replaceValue(e3, "THREE");
        heap.replaceValue(e2, "TWO");
        heap.replaceValue(e1, "ONE");
        heap.replaceValue(e0, "ZERO");

        // Check if minimum remains the same after replacements
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("ZERO", heap.min().getValue());
        assertEquals(9, heap.size());

        // Assert that the values for each entry have been replaced correctly
        assertEquals("EIGHT", e8.getValue());
        assertEquals("SEVEN", e7.getValue());
        assertEquals("SIX", e6.getValue());
        assertEquals("FIVE", e5.getValue());
        assertEquals("FOUR", e4.getValue());
        assertEquals("THREE", e3.getValue());
        assertEquals("TWO", e2.getValue());
        assertEquals("ONE", e1.getValue());
        assertEquals("ZERO", e0.getValue());
       
    }

    /**
     * Test the output of the remove behavior.
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(8, heap.size());

        heap.remove(e1);
        assertEquals(2, (int)heap.min().getKey());
        assertEquals("two", heap.min().getValue());
        assertEquals(7, heap.size());

        heap.remove(e2);
        assertEquals(3, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(6, heap.size());

        heap.remove(e3);
        assertEquals(4, (int)heap.min().getKey());
        assertEquals("four", heap.min().getValue());
        assertEquals(5, heap.size());

        heap.remove(e4);
        assertEquals(5, (int)heap.min().getKey());
        assertEquals("five", heap.min().getValue());
        assertEquals(4, heap.size());

        heap.remove(e5);
        assertEquals(6, (int)heap.min().getKey());
        assertEquals("six", heap.min().getValue());
        assertEquals(3, heap.size());

        heap.remove(e6);
        assertEquals(7, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(2, heap.size());

        heap.remove(e7);
        assertEquals(8, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(1, heap.size());

        heap.remove(e8);
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());

        // Attempt to remove a non-existent entry
        try {
            heap.remove(e1); // e1 has already been removed
            fail("Removing an entry that is no longer in the heap should throw an exception.");
        } catch (Exception e) {
            // Expected exception
        }
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities.
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
//        Student s3 = new Student("S","H", 3, 1, 3, "sh3");
//        Student s4 = new Student("J","J", 4, 1, 4, "jj4");
//        Student s5 = new Student("L","B", 5, 1, 5, "lb5");
        
        Entry<Student, String> e1 = sHeap.insert(s1, "Student 1");
        Entry<Student, String> e2 = sHeap.insert(s2, "Student 2");
        
        // Test that the minimum (s1) is at the root of the heap
        assertEquals(s1, sHeap.min().getKey());
        
        // Test replaceKey
        sHeap.replaceKey(e1, new Student("J", "K", 10, 1, 10, "jk10")); // Change s1 ID to 10
        assertEquals(s2, sHeap.min().getKey()); // Now s2 should be the minimum
        
        // Test replaceValue
        sHeap.replaceValue(e2, "Updated Student 2");
        assertEquals("Updated Student 2", e2.getValue());       

    }
}