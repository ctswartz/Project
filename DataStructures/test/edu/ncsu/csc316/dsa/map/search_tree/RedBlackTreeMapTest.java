//package edu.ncsu.csc316.dsa.map.search_tree;
//
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import edu.ncsu.csc316.dsa.Position;
//import edu.ncsu.csc316.dsa.map.Map.Entry;
//
///**
// * Test class for RedBlackTreeMap
// * Checks the expected outputs of the Map abstract data type behaviors when using
// * a red-black tree data structure 
// *
// * @author Dr. King
// *
// */
//public class RedBlackTreeMapTest {
//
//    private BinarySearchTreeMap<Integer, String> tree;
//    
//    
//    /**
//     * Create a new instance of a red-black tree-based map before each test case executes
//     */  
//    @Before
//    public void setUp() {
//        tree = new RedBlackTreeMap<Integer, String>();
//    }
//    
//    /**
//     * Test the output of the put(k,v) behavior
//     */     
//    @Test
//    public void testPut() {
//        assertEquals(0, tree.size());
//        assertTrue(tree.isEmpty());
//        
//        //TODO: complete this test case
//        // You should create test cases to check all the
//        // rules for red-black trees. The textbook has examples
//        // that you can use to create your test cases
//
//        // You should check the specific keys in each node after adding or
//        // removing from the tree. For example, you might use:
//        //  assertEquals(4, (int)tree.root().getElement().getKey());
//        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());     
//    
//        // Insert root node
//        assertNull(tree.put(4, "four"));
//        assertEquals(1, tree.size());
//        assertEquals(4, (int)tree.root().getElement().getKey());
//        assertFalse(isRed(tree.root()));
//        
//        // Insert left child (violates the Red property)
//        assertNull(tree.put(2, "two"));
//        assertTrue(isRed(tree.left(tree.root())));
//        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
//        
////        // Insert left child's left child (violates the Red property and causes a trinode restructuring)
////        assertNull(tree.put(1, "one"));
////        assertFalse(isRed(tree.left(tree.root())));
////        assertEquals(2, (int)tree.root().getElement().getKey());
////        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
//    
//    }
//    
//    /**
//     * Test the output of the get(k) behavior
//     */     
//    @Test
//    public void testGet() {
//        //TODO: complete this test case
//        tree.put(4, "four");
//        tree.put(2, "two");
//        tree.put(6, "six");
//        
//        assertEquals("four", tree.get(4));
//        assertEquals("two", tree.get(2));
//        assertEquals("six", tree.get(6));
//        assertNull(tree.get(1));
//    }
//    
//    /**
//     * Test the output of the remove(k) behavior
//     */     
//    @Test
//    public void testRemove() {
//        assertTrue(tree.isEmpty());
//        assertEquals(0, tree.size());
//        assertTrue(tree.isEmpty());
//        
//        //TODO: complete this test case
//        // You should create test cases to check all the
//        // rules for red-black trees. The textbook has examples
//        // that you can use to create your test cases
//        
//        // You should check the specific keys in each node after adding or
//        // removing from the tree. For example, you might use:
//        //  assertEquals(4, (int)tree.root().getElement().getKey());
//        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());       
//        tree.put(4, "four");
//        tree.put(2, "two");
//        tree.put(6, "six");
//        
//        assertEquals("six", tree.remove(6));
//        assertEquals("two", tree.remove(2));
//        assertEquals("four", tree.remove(4));
//        assertNull(tree.remove(2)); // removing again should return null
//        
//        assertTrue(tree.isEmpty());
//        assertEquals(0, tree.size());
//    }
//    
//    private boolean isRed(Position<Entry<Integer, String>> p) {
//        return tree.getProperty(p) == 1;
//    }
//}