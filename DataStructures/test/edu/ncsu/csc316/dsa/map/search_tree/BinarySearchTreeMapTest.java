package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure .
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 * 
 */
public class BinarySearchTreeMapTest {

    /** The tree. */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes.
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior.
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        tree.put(2, "two");
        assertEquals(2, tree.size());
        assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());

        tree.put(0, "zero");
        assertEquals(3, tree.size());
        assertEquals(0, (int)tree.left(tree.root()).getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior.
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        tree.put(2,  "two");
        assertEquals(2, tree.size());
        
        tree.put(0,  "zero");
        assertEquals(3, tree.size());
        
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("zero", tree.get(0));
        assertNull(tree.get(3));
    }

    /**
     * Test the output of the remove(k) behavior.
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        // Removing non-existent key
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        // Remove root when it's the only node
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        
        // You should create tests to ensure removing works
        // in all special cases:
        //   - removing the root
        //   - removing from a node that has only a left child
        //   - removing from a node that has only a right child
        //   - removing from a node that has both children
        // etc.
        
        // Removing from a node that has only a left child
        tree.put(10, "ten");
        tree.put(5, "five");
        assertEquals(2, tree.size());
        assertEquals("ten", tree.remove(10));
        assertEquals(1, tree.size());
        assertEquals("five", tree.root().getElement().getValue());
        
        // Reset 
        tree = new BinarySearchTreeMap<Integer, String>();
        
        // Removing from a node that has only a left child
        tree.put(10, "ten");
        tree.put(15, "fifteen");
        assertEquals(2, tree.size());
        assertEquals("ten", tree.remove(10));
        assertEquals(1, tree.size());
        assertEquals("fifteen", tree.root().getElement().getValue());

        // Reset
        tree = new BinarySearchTreeMap<Integer, String>();
        
        // Removing from a node that has both children
        tree.put(10, "ten");
        tree.put(5, "five");
        tree.put(15, "fifteen");
        assertEquals(3, tree.size());
        assertEquals("ten", tree.remove(10));
        assertEquals(2, tree.size());
        assertEquals("fifteen", tree.root().getElement().getValue());
        assertNotNull(tree.get(5));   
    }


    /**
     * Test set and get properties.
     */
    @Test
    public void testSetAndGetProperties() {
        tree.put(1, "one");
        tree.setProperty(tree.root(), 5);
        assertEquals(5, tree.getProperty(tree.root()));
    }

    /**
     * Test remove with right subtree min.
     */
    @Test
    public void testRemoveWithRightSubtreeMin() {
        tree.put(30, "thirty");
        tree.put(20, "twenty");
        tree.put(40, "forty");
        tree.put(35, "thirty-five");
        tree.put(50, "fifty");
        assertEquals("thirty", tree.remove(30)); 
        assertNull(tree.get(30));
        assertEquals("thirty-five", tree.root().getElement().getValue());
    }
    
    /**
     * Test large insertion.
     */
    @Test
    public void testLargeInsertion() {
        for (int i = 0; i < 100; i++) {
            tree.put(i, "value" + i);
        }

        assertEquals(100, tree.size());
        assertEquals("value50", tree.get(50));
    }
    
    /**
     * Test rotation.
     */
    @Test
    public void testRotation() {
        tree.put(10, "ten");
        tree.put(5, "five");
        tree.put(15, "fifteen");
        tree.put(3, "three");
        tree.put(8, "eight");
        tree.put(13, "thirteen");
        tree.put(18, "eighteen");
        
        tree.put(2, "two");
        tree.put(1, "one");

        assertNotNull(tree.get(1));
    }
    

    /**
     * Test large removal.
     */
    @Test
    public void testLargeRemoval() {
        for (int i = 0; i < 100; i++) {
            tree.put(i, "value" + i);
        }

        for (int i = 50; i < 100; i++) {
            assertEquals("value" + i, tree.remove(i));
        }

        assertEquals(50, tree.size());
    }

    /**
     * Test entry set.
     */
    @Test
    public void testEntrySet() {
        tree.put(10, "ten");
        tree.put(5, "five");
        tree.put(15, "fifteen");

        Iterable<Map.Entry<Integer, String>> entries = tree.entrySet();
        Iterator<Map.Entry<Integer, String>> iter = entries.iterator();

        assertTrue(iter.hasNext());
        assertEquals("five", iter.next().getValue());
        assertEquals("ten", iter.next().getValue());
        assertEquals("fifteen", iter.next().getValue());
        assertFalse(iter.hasNext());
    }
    
    /**
     * Test case for removing a key that is not in the map.
     */
    @Test
    public void testRemoveNonExistentKey() {
        assertEquals(0, tree.size());
        assertNull(tree.remove(5));
        assertEquals(0, tree.size());
    }

    /**
     * Test case for replacing an existing key with a new value.
     */
    @Test
    public void testReplaceExistingKey() {
        tree.put(1, "one");
        assertEquals("one", tree.put(1, "newOne"));
        assertEquals("newOne", tree.get(1));
    }

    /**
     * Test case for inserting and removing keys in a specific order
     * to verify that the tree structure remains correct.
     */
    @Test
    public void testInsertAndRemoveOrder() {
        tree.put(4, "four");  // Root
        tree.put(2, "two");  // Left child
        tree.put(6, "six");  // Right child
        tree.put(1, "one");  // Left child of left child
        tree.put(3, "three");  // Right child of left child
        tree.put(5, "five");  // Left child of right child
        tree.put(7, "seven");  // Right child of right child

        assertEquals(7, tree.size());

        // Remove leaf node "three" (right child of left child)
        assertEquals("three", tree.remove(3));
        assertEquals(6, tree.size());

        // Remove node "two" (left child of root)
        assertEquals("two", tree.remove(2));
        assertEquals(5, tree.size());

        // Remove root node "four"
        assertEquals("four", tree.remove(4));
        assertEquals(4, tree.size());

        // Check if the tree structure is still correct
        assertNull(tree.get(4)); // Root should be removed
        assertNull(tree.get(2)); // Left child of root should be removed
        assertNull(tree.get(3)); // Right child of left child should be removed

        // Check the values of remaining nodes
        assertEquals("one", tree.get(1));
        assertEquals("six", tree.get(6));
        assertEquals("five", tree.get(5));
        assertEquals("seven", tree.get(7));
    }
    
    /**
     * Test left rotation.
     */
    @Test
    public void testLeftRotation() {
        // Create a tree that requires a left rotation
        tree.put(3, "C");
        tree.put(2, "B");
        tree.put(4, "D");

        assertEquals("C", tree.root().getElement().getValue());
        assertEquals("B", tree.left(tree.root()).getElement().getValue());
        assertEquals("D", tree.right(tree.root()).getElement().getValue());
    }

    /**
     * Test right rotation.
     */
    @Test
    public void testRightRotation() {
        // Create a tree that requires a right rotation
        tree.put(2, "B");
        tree.put(3, "C");
        tree.put(1, "A");

        assertEquals("B", tree.root().getElement().getValue());
        assertEquals("A", tree.left(tree.root()).getElement().getValue());
        assertEquals("C", tree.right(tree.root()).getElement().getValue());
    }
    

    /**
     * Test complex removal.
     */
    @Test
    public void testComplexRemoval() {
        tree.put(50, "fifty");
        tree.put(30, "thirty");
        tree.put(70, "seventy");
        tree.put(20, "twenty");
        tree.put(40, "forty");
        tree.put(35, "thirty-five");
        tree.put(45, "forty-five");

        // Node with key 30 has two children, neither left nor right provides a minimal key.
        assertEquals("thirty", tree.remove(30));
        assertEquals("thirty-five", tree.get(35));
        assertNull(tree.get(30));
    }
    
    /**
     * Test to string.
     */
    @Test
    public void testToString() {
        tree.put(10, "ten");
        tree.put(5, "five");
        tree.put(15, "fifteen");
        assertNotNull(tree.toString());
        //assertTrue(tree.toString().contains("ten"));
    }

    /**
     * Test direct rotate and restructure.
     */
    @Test
    public void testDirectRotateAndRestructure() {
        tree.put(10, "ten");
        tree.put(5, "five");
        tree.put(15, "fifteen");
        tree.put(1, "one");
        tree.put(7, "seven");
        
        // Trigger rotations and restructuring manually to test them directly.
        tree.rotate(tree.left(tree.root()));
        assertEquals("five", tree.root().getElement().getValue());
//
//        tree.restructure(tree.right(tree.root()));
//        assertEquals("ten", tree.root().getElement().getValue());
    }

    /**
     * Test edge cases.
     */
    @Test
    public void testEdgeCases() {
        // Test expanding a leaf.
        tree.put(10, "ten");
        Position<Map.Entry<Integer, String>> position = tree.root();
        tree.put(5, "five");
        assertEquals(2, tree.size());
        assertFalse(tree.isLeaf(position));
        
        // Test lookup for non-existing key.
        assertNull(tree.get(20));
    }


}