package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;
import java.util.EmptyStackException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for LinkedStack.
 * Checks the expected outputs of the Stack abstract data type behaviors when using
 * a singly-linked list data structure
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 */
public class LinkedStackTest {

    private Stack<String> stack;
    
    /**
     * Create a new instance of a linked list-based stack before each test case executes
     */
    @Before
    public void setUp() {
        stack = new LinkedStack<String>();
    }
    
    /**
     * Test the output of the push(e) behavior
     */ 
    @Test
    public void testPush() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        
        stack.push("one");
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        
        stack.push("two");
        assertEquals(2, stack.size());
        
        stack.push("three");
        assertEquals(3, stack.size());
        
        stack.push("four");
        assertEquals(4, stack.size());
    }

    /**
     * Test the output of the pop() behavior, including expected exceptions
     */
    @Test
    public void testPop() {
        assertEquals(0, stack.size());
        
        try {
            stack.pop();
            fail("EmptyStackException should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyStackException);
        }
        
        stack.push("one");
        stack.push("two");
        stack.push("three");
        // one two three
        
        assertEquals("three", stack.pop()); // one two 
        assertEquals(2, stack.size());
        assertEquals("two", stack.pop()); // one
        assertEquals(1, stack.size());
        assertEquals("one", stack.pop()); // (empty)
        assertEquals(0, stack.size());
    }

    /**
     * Test the output of the top() behavior, including expected exceptions
     */
    @Test
    public void testTop() { 
        assertEquals(0, stack.size());
        
        try {
            stack.top();
            fail("EmptyStackException should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyStackException);
        }
        
        stack.push("one");
        assertEquals("one", stack.top());

        stack.push("two");
        assertEquals("two", stack.top());

        stack.pop();
        assertEquals("one", stack.top());
    }

}