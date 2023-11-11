package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency.
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 */
public class UnorderedLinkedMapTest {

    /** The map. */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes.
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior.
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());

        // Test overwriting a value
        map.put(3, "new3");
        assertEquals("new3", map.get(3));
        
        // Test ordering after overwriting a value
        //assertEquals("UnorderedLinkedMap[3, 1, 4, 2, 5]", map.toString());
    }

    /**
     * Test the output of the get(k) behavior.
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
       
        assertEquals("string5", map.get(5));
        assertEquals("UnorderedLinkedMap[5, 1, 4, 2, 3]", map.toString());
        
        assertEquals("string3", map.get(3));
        assertEquals("UnorderedLinkedMap[3, 5, 1, 4, 2]", map.toString());
        
        assertEquals("string4", map.get(4));
        assertEquals("UnorderedLinkedMap[4, 3, 5, 1, 2]", map.toString());
        
        assertEquals("string2", map.get(2));
        assertEquals("UnorderedLinkedMap[2, 4, 3, 5, 1]", map.toString());
        
        // Key doesn't exist, should return null
        assertNull(map.get(10));
    }
    
    /**
     * Test the output of the remove(k) behavior.
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        // middle
        assertEquals("string2", map.remove(2));
        assertEquals("UnorderedLinkedMap[1, 4, 5, 3]", map.toString());
        
        // front
        assertEquals("string1", map.remove(1));
        assertEquals("UnorderedLinkedMap[4, 5, 3]", map.toString());
        
        // Key doesn't exist, should return null
        assertNull(map.remove(10));
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions.
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Entry<Integer, String>> entryIterator = map.entrySet().iterator();
        assertTrue(entryIterator.hasNext());
        assertEquals(Integer.valueOf(1), entryIterator.next().getKey());
        assertEquals(Integer.valueOf(4), entryIterator.next().getKey());
        assertEquals(Integer.valueOf(2), entryIterator.next().getKey());
        assertEquals(Integer.valueOf(5), entryIterator.next().getKey());
        assertEquals(Integer.valueOf(3), entryIterator.next().getKey());
        
        // no more values
        assertFalse(entryIterator.hasNext());
        
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions.
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<Entry<Integer, String>> set = map.entrySet();
        for(Entry<Integer, String> entry : set) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
        }
    }

    /**
     * Test the output of the values() behavior, including expected exceptions.
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<String> values = map.values();
        for(String value : values) {
            assertTrue(value.startsWith("string"));
        }
    }
}