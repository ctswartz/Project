package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap.
 *
 * @author Courtney T Swartz (ctswartz)
 */
public class LinearProbingHashMapTest {
	
    /** The map. */
    private LinearProbingHashMap<String, Integer> map;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        map = new LinearProbingHashMap<>();
    }
    
    /**
     * Test put and get.
     */
    @Test
    public void testPutAndGet() {
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertNull(map.put("one", 1));
        assertEquals((Integer) 1, map.get("one"));
        assertEquals(1, map.size());
        assertNull(map.get("two"));
    }

    /**
     * Test collision handling and retrieve.
     */
    @Test
    public void testCollisionHandlingAndRetrieve() {
        assertNull(map.put("one", 1));
        assertNull(map.put("two", 2));
        assertNull(map.put("collisionWithOne", 100));
        assertEquals(3, map.size());
        assertEquals((Integer) 1, map.get("one"));
        assertEquals((Integer) 100, map.get("collisionWithOne"));
    }

    /**
     * Test update value.
     */
    @Test
    public void testUpdateValue() {
        assertNull(map.put("one", 1));
        assertEquals((Integer) 1, map.get("one"));
        assertEquals((Integer) 1, map.put("one", 10));
        assertEquals((Integer) 10, map.get("one"));
    }

    /**
     * Test remove.
     */
    @Test
    public void testRemove() {
        assertNull(map.put("one", 1));
        assertEquals((Integer) 1, map.get("one"));
        assertEquals((Integer) 1, map.remove("one"));
        assertNull(map.get("one"));
        assertEquals(0, map.size());
    }

    /**
     * Test table expansion.
     */
    @Test
    public void testTableExpansion() {
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, i);
        }
        assertEquals(10, map.size());
        map.put("triggerExpansion", 100);
        assertEquals(11, map.size());
        assertEquals((Integer) 100, map.get("triggerExpansion"));
    }

    /**
     * Test deleted entry.
     */
    @Test
    public void testDeletedEntry() {
        assertNull(map.put("one", 1));
        assertEquals((Integer) 1, map.remove("one"));
        assertNull(map.get("one"));
        assertNull(map.put("one", 10));
        assertEquals((Integer) 10, map.get("one"));
    }
    
    /**
     * Test constructor with default capacity.
     */
    @Test
    public void testDefaultConstructor() {
        LinearProbingHashMap<String, Integer> defaultMap = new LinearProbingHashMap<>();
        assertTrue(defaultMap.isEmpty());
        assertEquals(AbstractHashMap.DEFAULT_CAPACITY, defaultMap.capacity());
    }
    
    /**
     * Test constructor with custom capacity.
     */
    @Test
    public void testCapacityConstructor() {
        int customCapacity = 20;
        LinearProbingHashMap<String, Integer> capacityMap = new LinearProbingHashMap<>(customCapacity);
        assertTrue(capacityMap.isEmpty());
        assertEquals(customCapacity, capacityMap.capacity());
    }
    
    /**
     * Test entrySet.
     */
    @Test
    public void testEntrySet() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        Iterable<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> it = entries.iterator();
        
        int count = 0;
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            assertTrue(entry.getKey().equals("one") || entry.getKey().equals("two") || entry.getKey().equals("three"));
            count++;
        }
        assertEquals(3, count);
    }
    
    /**
     * Test operations.
     */
    @Test
    public void testIsAvailable() {
        map.put("one", 1);
        assertEquals((Integer) 1, map.get("one"));
        map.remove("one");
        assertNull(map.get("one"));
        assertTrue(map.isEmpty()); 
        assertNull(map.put("one", 10)); 
        assertEquals((Integer) 10, map.get("one"));
    }
}
