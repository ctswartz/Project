package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.
 * Checks the expected outputs of the Queue abstract data type behaviors when using
 * a circular array-based data structure
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 */
public class ArrayBasedQueueTest {

    /** The queue. */
    private Queue<String> queue;
    
    /**
     * Create a new instance of a circular array-based queue before each test case executes.
     */ 
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }

    /**
     * Test the output of the enqueue(e) behavior.
     */     
    @Test
    public void testEnqueue() {
    	
    	// Empty
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        // Add "one"
        queue.enqueue("one");
        // Size increases to 1
        assertEquals(1, queue.size());
        // Queue is no longer empty
        assertFalse(queue.isEmpty());
        // Front = "one"
        assertEquals("one", queue.front());
        
        // Add "two"
        queue.enqueue("two");
        // Size increases to 2
        assertEquals(2, queue.size());
        // Front remains "one"
        assertEquals("one", queue.front());
        
        // Add "three"
        queue.enqueue("three");
        // Size increases to 3
        assertEquals(3, queue.size());
        // Front remains "one"
        assertEquals("one", queue.front());
    }
    
    /**
     * Test the output of the dequeue(e) behavior, including expected exceptions.
     */     
    @Test
    public void testDequeue() {
    	// Empty
        assertEquals(0, queue.size());
        
        // Attempting to dequeue empty queue throws an exception
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        // Add one, two, three, size is 3
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        
        // Dequeue one
        assertEquals("one", queue.dequeue());
        // Size is 2
        assertEquals(2, queue.size());
        // Now two is front
        assertEquals("two", queue.front());
        // Dequeue two
        assertEquals("two", queue.dequeue());
        // Size is 1
        assertEquals(1, queue.size());
        // Now three is front
        assertEquals("three", queue.front());
        // Dequeue three
        assertEquals("three", queue.dequeue());
        // Size is 0
        assertEquals(0, queue.size());
        // Queue is empty!
        assertTrue(queue.isEmpty());
    }
    
    /**
     * Test the output of the front() behavior, including expected exceptions.
     */     
    @Test
    public void testFront() {
    	
    	// Attempting to view the front of an empty queue throws an exception
        try {
            queue.front();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        queue.enqueue("one");
        // One should be front
        assertEquals("one", queue.front());
        
        queue.enqueue("two");
        // One should still be front
        assertEquals("one", queue.front());
        
        queue.enqueue("three");
        // One should still be front
        assertEquals("one", queue.front());

        // Dequeue one
        queue.dequeue();
        // Two should now be front
        assertEquals("two", queue.front());
    }

}