package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure .
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 */
public class AVLTreeMapTest {

    /** The tree. */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes.
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior.
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        
        // Test single rotation cases (Right-Right and Left-Left)

        tree.put(1, "A");
        tree.put(2, "B");
        tree.put(3, "C");
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());

        tree = new AVLTreeMap<Integer, String>();
        tree.put(3, "C");
        tree.put(2, "B");
        tree.put(1, "A");
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());

        // Test double rotation cases (Right-Left and Left-Right)
        tree = new AVLTreeMap<Integer, String>();
        tree.put(3, "C");
        tree.put(1, "A");
        tree.put(2, "B");
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());

        tree = new AVLTreeMap<Integer, String>();
        tree.put(1, "A");
        tree.put(3, "C");
        tree.put(2, "B");
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior.
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        
        tree.put(1, "1");
        tree.put(2, "2");
        
        assertEquals("1", tree.get(1));
        assertEquals("2", tree.get(2));
        assertNull(tree.get(5));
    }
    
    /**
     * Test the output of the remove(k) behavior.
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());  

        tree.put(40, "A");
        tree.put(20, "B");
        tree.put(60, "C");
        tree.put(10, "D");
        tree.put(30, "E");
        tree.put(50, "F");
        tree.put(70, "G");

        assertEquals("E", tree.remove(30));
        assertEquals(6, tree.size());
        assertEquals(40, (int)tree.root().getElement().getKey());
        assertEquals(20, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(60, (int)tree.right(tree.root()).getElement().getKey());

        assertEquals("B", tree.remove(20));
        assertEquals(5, tree.size());
        assertEquals(40, (int)tree.root().getElement().getKey());
        assertEquals(10, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(60, (int)tree.right(tree.root()).getElement().getKey());
    }
    
    /**
     * Test imbalance.
     */
    @Test
    public void testImbalance() {
        tree.put(50, "50");
        tree.put(30, "30");
        tree.put(70, "70");
        tree.put(10, "10");
        tree.put(40, "40");
        tree.put(60, "60");
        tree.put(80, "80");
        
        tree.put(5, "5");
        tree.put(3, "3");

        // Verify the structure of the tree after balancing
        assertEquals(50, (int)tree.root().getElement().getKey());
    }

    /**
     * Test boundary cases.
     */
    @Test
    public void testBoundaryCases() {
        tree.put(Integer.MAX_VALUE, "max");
        tree.put(Integer.MIN_VALUE, "min");
        assertEquals("max", tree.get(Integer.MAX_VALUE));
        assertEquals("min", tree.get(Integer.MIN_VALUE));
    }

    /**
     * Test key replacement.
     */
    @Test
    public void testKeyReplacement() {
        tree.put(50, "oldValue");
        assertEquals("oldValue", tree.get(50));
        tree.put(50, "newValue");
        assertEquals("newValue", tree.get(50));
        assertEquals(1, tree.size());
    }
 
    /**
     * Test constructor with comparator.
     */
    @Test
    public void testConstructorWithComparator() {
        Comparator<Integer> customComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1); 
            }
        };
        
        BinarySearchTreeMap<Integer, String> customTree = new AVLTreeMap<Integer, String>(customComparator);
        assertTrue(customTree.isEmpty());

        customTree.put(1, "A");
        customTree.put(2, "B");
        customTree.put(3, "C");
        assertEquals(2, (int) customTree.root().getElement().getKey());
        assertEquals(3, (int) customTree.left(customTree.root()).getElement().getKey()); 
        assertEquals(1, (int) customTree.right(customTree.root()).getElement().getKey()); 
    }
}
