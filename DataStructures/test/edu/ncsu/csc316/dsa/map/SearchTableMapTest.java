package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
//import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for SearchTableMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 */
public class SearchTableMapTest {

    private Map<Integer, String> map;
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SearchTableMap<Integer, String>();
        studentMap = new SearchTableMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertEquals("string3", map.put(3, "newString3"));
        assertEquals("newString3", map.get(3));

        // Add multiple values
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(8, "string8"));
        assertNull(map.put(9, "string9"));
        assertNull(map.put(10, "string10"));

        assertEquals("SearchTableMap[3, 6, 7, 8, 9, 10]", map.toString());
    }

    /**
     * Test the output of the get(k) behavior
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        // Entry doesn't exist, should return null
        assertNull(map.get(10));
    }

    /**
     * Test the output of the remove(k) behavior
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        // Remove existing keys
        assertEquals("string3", map.remove(3));
        assertNull(map.get(3));
        assertEquals("string5", map.remove(5));
        assertNull(map.get(5));
        assertEquals("string2", map.remove(2));
        assertNull(map.get(2));
        assertEquals("string4", map.remove(4));
        assertNull(map.get(4));
        assertEquals("string1", map.remove(1));
        assertNull(map.get(1));

        // Entry doesn't exist, should return null
        assertNull(map.remove(10));
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        // Suggestions: since search table map keys are Comparable,
        // make sure the search table works with Comparable objects like Students

        // Test initial empty state
        assertTrue(studentMap.isEmpty());

        // Test adding entries
        assertNull(studentMap.put(s1, 1));
        assertNull(studentMap.put(s2, 2));
        assertNull(studentMap.put(s3, 3));
        assertNull(studentMap.put(s4, 4));
        assertNull(studentMap.put(s5, 5));

        assertEquals(5, studentMap.size());

        assertEquals(1, (int) studentMap.get(s1));
        assertEquals(2, (int) studentMap.get(s2));
        assertEquals(3, (int) studentMap.get(s3));
        assertEquals(4, (int) studentMap.get(s4));
        assertEquals(5, (int) studentMap.get(s5));

        // overwrite
        assertEquals(2, (int) studentMap.put(s2, 22));
        assertEquals(22, (int) studentMap.get(s2));

        // remove
        assertEquals(1, (int) studentMap.remove(s1));
        assertNull(studentMap.get(s1));
        assertEquals(4, studentMap.size());
        
        assertNotNull(studentMap.get(s2));
        assertNull(studentMap.get(s1));
        
    }
    
    /**
     * Test the output of the iterator behavior, including expected exceptions
     */ 
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, (int) it.next());
        assertEquals(2, (int) it.next());
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));

        entry = it.next();
        assertEquals(2, (int)(entry.getKey()));
        assertEquals("string2", (String)(entry.getValue()));
        
        entry = it.next();
        assertEquals(3, (int)(entry.getKey()));
        assertEquals("string3", (String)(entry.getValue()));
        
        entry = it.next();
        assertEquals(4, (int)(entry.getKey()));
        assertEquals("string4", (String)(entry.getValue()));
        
        entry = it.next();
        assertEquals(5, (int)(entry.getKey()));
        assertEquals("string5", (String)(entry.getValue()));

        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */  
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        
        assertEquals("string1", it.next());
        assertEquals("string2", it.next());
        assertEquals("string3", it.next());
        assertEquals("string4", it.next());
        assertEquals("string5", it.next());

        assertFalse(it.hasNext());
    }
}