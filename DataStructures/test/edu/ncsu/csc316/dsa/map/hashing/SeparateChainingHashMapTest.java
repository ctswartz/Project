package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for SeparateChainingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a separate chaining hash map data structure .
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 */
public class SeparateChainingHashMapTest {

    /** The map. */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of a separate chaining hash map before each test case executes.
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are TESTING.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        // Remember that our secondary map (an AVL tree) is a search
        // tree, which means the entries should be sorted in order within
        // that tree
        map = new SeparateChainingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior.
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        // Add some collisions to check that entries
        // are placed in the correct buckets
        
        // same as 3
        assertNull(map.put(10, "string10"));
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior.
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        
        map.put(3, "string3");
        assertEquals("string3", map.get(3));
        assertNull(map.get(10));
    }
    
    /**
     * Test the output of the remove(k) behavior.
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        
        map.put(3, "string3");
        assertEquals("string3", map.remove(3));
        assertNull(map.get(3));
        assertNull(map.remove(10));
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions.
     */   
    @Test
    public void testIterator() {
        // Add some entries to the hash map
        map.put(3, "string3");
        map.put(4, "string4");
        map.put(10, "string10");
        
        Iterator<Integer> it = map.iterator();

        assertTrue(it.hasNext());
        assertEquals(3, (int)it.next());
        assertEquals(10, (int)it.next());
        assertEquals(4, (int)it.next());
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the entrySet() behavior.
     */   
    @Test
    public void testEntrySet() {
        //  Add some entries to the hash map
        map.put(3, "string3");
        map.put(4, "string4");
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();        

        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the values() behavior.
     */   
    @Test
    public void testValues() {
        // Add some entries to the hash map
        map.put(3, "string3");
        map.put(4, "string4");
        
        Iterator<String> it = map.values().iterator();

        assertTrue(it.hasNext());
        assertEquals("string3", it.next());
        assertEquals("string4", it.next());
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the put method with collisions and resizing.
     */
    @Test
    public void testPutWithCollisions() {
        map.put(3, "string3");
        map.put(10, "string10");
        map.put(4, "string4");
        assertEquals(3, map.size());

        // These keys will hash to the same index as the keys above and thus will collide.
        map.put(17, "string17");
        map.put(24, "string24");
        
        assertEquals(5, map.size());
        assertEquals("string3", map.get(3));
        assertEquals("string10", map.get(10));
        assertEquals("string4", map.get(4));
        assertEquals("string17", map.get(17));
        assertEquals("string24", map.get(24));
    }

    /**
     * Test the capacity method.
     */
    @Test
    public void testCapacity() {
        assertEquals(7, ((SeparateChainingHashMap<Integer, String>) map).capacity());
        
        for (int i = 0; i < 10; i++) {
            map.put(i, "value" + i);
        }
        assertTrue(((SeparateChainingHashMap<Integer, String>) map).capacity() > 7);
    }
    
    /**
     * Test default constructor.
     */
    @Test
    public void testDefaultConstructor() {
        map = new SeparateChainingHashMap<>();
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    /**
     * Test constructor with capacity.
     */
    @Test
    public void testConstructorWithCapacity() {
        map = new SeparateChainingHashMap<>(10);
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    /**
     * Test constructor with testing flag.
     */
    @Test
    public void testConstructorWithTestingFlag() {
        map = new SeparateChainingHashMap<>(true);
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    /**
     * Test constructor with capacity and testing flag.
     */
    @Test
    public void testConstructorWithCapacityAndTestingFlag() {
        map = new SeparateChainingHashMap<>(10, true);
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }
}