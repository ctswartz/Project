//package edu.ncsu.csc316.dsa.map.search_tree;
//
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
//import edu.ncsu.csc316.dsa.map.search_tree.*;
//
///**
// * Test class for SplayTreeMap
// * Checks the expected outputs of the Map abstract data type behaviors when using
// * a splay tree data structure 
// *
// * @author Dr. King
// *
// */
//public class SplayTreeMapTest {
//
//    private BinarySearchTreeMap<Integer, String> tree;
//    
//    /**
//     * Create a new instance of a splay tree-based map before each test case executes
//     */     
//    @Before
//    public void setUp() {
//        tree = new SplayTreeMap<Integer, String>();
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
//        // splay scenarios. The textbook has examples
//        // that you can use to create your test cases
//        
//        // You should check the specific keys in each node after adding or
//        // removing from the tree. For example, you might use:
//        //  assertEquals(4, (int)tree.root().getElement().getKey());
//        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey()); 
//
//        // Insertion should splay the node to the root
//        tree.put(3, "C");
//        assertEquals(3, (int)tree.root().getElement().getKey());
//
//        tree.put(2, "B");
//        assertEquals(2, (int)tree.root().getElement().getKey());
//
//        tree.put(1, "A");
//        assertEquals(1, (int)tree.root().getElement().getKey());
//
//        tree.put(4, "D");
//        assertEquals(4, (int)tree.root().getElement().getKey());
//    }
//    
//    /**
//     * Test the output of the get(k) behavior
//     */ 
//    @Test
//    public void testGet() {
//        //TODO: complete this test case
//        tree.put(3, "C");
//        tree.put(2, "B");
//        tree.put(1, "A");
//        tree.put(4, "D");
//
//        // Access should splay the node to the root
//        assertEquals("A", tree.get(1));
//        assertEquals(1, (int)tree.root().getElement().getKey());
//
//        assertEquals("B", tree.get(2));
//        assertEquals(2, (int)tree.root().getElement().getKey());
//    }
//    
//    /**
//     * Test the output of the remove(k) behavior
//     */     
//    @Test
//    public void testRemove() {
//        //TODO: complete this test case
//        // You should create test cases to check all the
//        // splay scenarios. The textbook has examples
//        // that you can use to create your test cases
//        
//        // You should check the specific keys in each node after adding or
//        // removing from the tree. For example, you might use:
//        //  assertEquals(4, (int)tree.root().getElement().getKey());
//        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
//    	
//        assertEquals(0, tree.size());
//        assertTrue(tree.isEmpty());
//
//        // Insert some elements
//        tree.put(3, "C");
//        tree.put(2, "B");
//        tree.put(1, "A");
//        tree.put(4, "D");
//        
//        
//
//    }
//    
//    @Test
//    public void testAccess() {
//        tree.put(3, "C");
//        tree.put(2, "B");
//        tree.put(1, "A");
//        tree.put(4, "D");
//        
//        // Access should splay the node to the root
//        tree.get(2); // Accessing 2 should splay it to the root
//        assertEquals(2, (int)tree.root().getElement().getKey());
//    }
//    
////    @Test
////    public void testDelete() {
////        tree.put(3, "C");
////        tree.put(2, "B");
////        tree.put(1, "A");
////        tree.put(4, "D");
////        
////        // Delete should splay the parent of the deleted node
////        tree.remove(2); // Deleting 2 should splay 1 to the root
////        assertEquals(1, (int)tree.root().getElement().getKey());
////    }
//}