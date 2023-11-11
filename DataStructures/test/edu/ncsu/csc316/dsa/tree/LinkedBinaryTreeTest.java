package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements.
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 * 
 */
public class LinkedBinaryTreeTest {

    /** The tree. */
    private LinkedBinaryTree<String> tree;
    
    /** The one. */
    private Position<String> one;
    
    /** The two. */
    private Position<String> two;
    
    /** The three. */
    private Position<String> three;
    
    /** The four. */
    private Position<String> four;
    
    /** The five. */
    private Position<String> five;
    
    /** The six. */
    private Position<String> six;
    
    /** The seven. */
    private Position<String> seven;
    
    /** The eight. */
    private Position<String> eight;
    
    /** The nine. */
    private Position<String> nine;
    
    /** The ten. */
    private Position<String> ten;

    /**
     * Create a new instance of a linked binary tree before each test case executes.
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     * 
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine.
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior.
     */     
    @Test
    public void testSet() {
        createTree();
        
        assertEquals("one", tree.set(one, "new"));
        assertEquals("new", one.getElement());
    }
    
    /**
     * Test the output of the size() behavior.
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior.
     */     
    @Test
    public void testNumChildren() {
        createTree();
        
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(1, tree.numChildren(three)); 
        assertEquals(2, tree.numChildren(four));
        assertEquals(2, tree.numChildren(ten));
        assertEquals(0, tree.numChildren(six));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(five));  
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
    }

    /**
     * Test the output of the parent(p) behavior.
     */   
    @Test
    public void testParent() {
        createTree();
        
        assertEquals(one, tree.parent(two));
        assertEquals(one, tree.parent(three));
        assertEquals(two, tree.parent(six));
        assertEquals(two, tree.parent(ten));
        assertEquals(three, tree.parent(four));
        assertEquals(ten, tree.parent(seven));
        assertEquals(ten, tree.parent(five));
        assertEquals(four, tree.parent(eight));
        assertEquals(four, tree.parent(nine));
    }

    /**
     * Test the output of the sibling behavior.
     */     
    @Test
    public void testSibling() {
        createTree();
        
        assertNull(tree.sibling(one)); 
        
        assertEquals(three, tree.sibling(two));
        assertEquals(two, tree.sibling(three));
        
        assertEquals(ten, tree.sibling(six));
        assertEquals(six, tree.sibling(ten));
        assertEquals(nine, tree.sibling(eight));
        assertEquals(eight, tree.sibling(nine));
    }

    /**
     * Test the output of the isInternal behavior.
     */     
    @Test
    public void testIsInternal() {
        createTree();
        
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(three));
        assertTrue(tree.isInternal(four));
        assertTrue(tree.isInternal(ten));
        assertFalse(tree.isInternal(six));
        assertFalse(tree.isInternal(seven));
        assertFalse(tree.isInternal(five));
        assertFalse(tree.isInternal(eight));
        assertFalse(tree.isInternal(nine));
    }

    /**
     * Test the output of the isLeaf behavior.
     */     
    @Test
    public void isLeaf() {
        createTree();
        
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(nine));
        
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(two));
        assertFalse(tree.isLeaf(three));
        assertFalse(tree.isLeaf(four));
        assertFalse(tree.isLeaf(ten));

    }

    /**
     * Test the output of the isRoot(p).
     */     
    @Test
    public void isRoot() {
        createTree();
        
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(four));
        assertFalse(tree.isRoot(five));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(seven));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(nine));
        assertFalse(tree.isRoot(ten));
    }
    
    /**
     * Test the output of the preOrder traversal behavior.
     */     
    @Test
    public void testPreOrder() {
        createTree();
        
        Iterator<Position<String>> preOrder = tree.preOrder().iterator();
        
        assertTrue(preOrder.hasNext());
        assertEquals(one, preOrder.next());
        assertEquals(two, preOrder.next());
        assertEquals(six, preOrder.next());
        assertEquals(ten, preOrder.next());
        assertEquals(seven, preOrder.next());
        assertEquals(five, preOrder.next());
        assertEquals(three, preOrder.next());
        assertEquals(four, preOrder.next());
        assertEquals(eight, preOrder.next());
        assertEquals(nine, preOrder.next());
        assertFalse(preOrder.hasNext());
    }

    /**
     * Test the output of the postOrder traversal behavior.
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> postOrder = tree.postOrder().iterator();
        
        assertTrue(postOrder.hasNext());
        assertEquals(six, postOrder.next());
        assertEquals(seven, postOrder.next());
        assertEquals(five, postOrder.next());
        assertEquals(ten, postOrder.next());
        assertEquals(two, postOrder.next());
        assertEquals(eight, postOrder.next());
        assertEquals(nine, postOrder.next());
        assertEquals(four, postOrder.next());
        assertEquals(three, postOrder.next());
        assertEquals(one, postOrder.next());
        assertFalse(postOrder.hasNext());
    }
    
    /**
     * Test the output of the inOrder traversal behavior.
     */     
    @Test
    public void testInOrder() {
        createTree();
        
        Iterator<Position<String>> inOrder = tree.inOrder().iterator();
        
        assertTrue(inOrder.hasNext());
        assertEquals(six, inOrder.next());
        assertEquals(two, inOrder.next());
        assertEquals(seven, inOrder.next());
        assertEquals(ten, inOrder.next());
        assertEquals(five, inOrder.next());
        assertEquals(one, inOrder.next());
        assertEquals(eight, inOrder.next());
        assertEquals(four, inOrder.next());
        assertEquals(nine, inOrder.next());
        assertEquals(three, inOrder.next());
        assertFalse(inOrder.hasNext());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree.
     */     
    @Test
    public void testEmptyTree() {
        assertTrue(tree.isEmpty());
        assertNull(tree.root());
        assertEquals(0, tree.size());
    }
    
    /**
     * Test level order.
     */
    @Test
    public void testLevelOrder() {
        createTree();
        
        Iterator<Position<String>> levelOrder = tree.levelOrder().iterator();
        
        assertTrue(levelOrder.hasNext());
        assertEquals(one, levelOrder.next());
        assertEquals(two, levelOrder.next());
        assertEquals(three, levelOrder.next());
        assertEquals(six, levelOrder.next());
        assertEquals(ten, levelOrder.next());
        assertEquals(four, levelOrder.next());
        assertEquals(seven, levelOrder.next());
        assertEquals(five, levelOrder.next());
        assertEquals(eight, levelOrder.next());
        assertEquals(nine, levelOrder.next());
        assertFalse(levelOrder.hasNext());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions.
     */      
    @Test
    public void testAddLeft() {
        createTree();
        
        Position<String> eleven = tree.addLeft(five, "eleven");
        assertEquals("eleven", eleven.getElement());
        
        try {
            tree.addLeft(two, "newLeft");
            fail();
        } catch (IllegalArgumentException e) {
            // exception
        }
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions.
     */      
    @Test
    public void testAddRight() {
        createTree();
        
        Position<String> twelve = tree.addRight(five, "twelve");
        assertEquals("twelve", twelve.getElement());
        
        Position<String> newRight = tree.addRight(three, "newRight");
        assertEquals("newRight", newRight.getElement());
        assertNotEquals(four, tree.right(three));

    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions.
     */         
    @Test
    public void testRemove() {
        createTree();
        
        tree.remove(nine);
        assertNull(tree.sibling(eight));
        
    }
    
    /**
     * Test position set iterator remove.
     */
    @Test
    public void testPositionSetIteratorRemove() {
        createTree();
        
        Iterator<Position<String>> it = tree.preOrder().iterator();
        it.next(); 
        
        try {
            it.remove();
            fail("Expected an UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // exception
        }
    }
    
    /**
     * Test to string.
     */
    @Test
    public void testToString() {
    	
        assertEquals("LinkedBinaryTree[\n]", tree.toString());

        createTree();
        
        String expected = "LinkedBinaryTree[\n" + 
                "one\n" + 
                " two\n" + 
                "  six\n" + 
                "  ten\n" + 
                "   seven\n" + 
                "   five\n" + 
                " three\n" + 
                "  four\n" + 
                "   eight\n" + 
                "   nine\n" + 
                "]";
        
        assertEquals(expected, tree.toString());
    }
    
    /**
     * Test add root on empty tree.
     */
    @Test
    public void testAddRootOnEmptyTree() {
        Position<String> root = tree.addRoot("root");
        assertEquals("root", root.getElement());
        assertEquals(1, tree.size());
    }
    
    /**
     * Test remove nodes.
     */
    @Test
    public void testRemoveNodes() {
        createTree();
        
        Position<String> eleven = tree.addRight(five, "eleven");
        tree.remove(eleven);
        assertNull(tree.right(five));
        
        Position<String> twelve = tree.addLeft(five, "twelve");
        tree.remove(twelve);
        assertNull(tree.left(five));
        
        tree = new LinkedBinaryTree<>();
        Position<String> onlyRoot = tree.addRoot("onlyRoot");
        tree.remove(onlyRoot);
        assertNull(tree.root());
    }
    
    /**
     * Test sibling of root.
     */
    @Test
    public void testSiblingOfRoot() {
        createTree();
        assertNull(tree.sibling(one));
    }
    
    /**
     * Test set root.
     */
    @Test
    public void testSetRoot() {
        createTree();
        
        Position<String> newRoot = new LinkedBinaryTree.BinaryTreeNode<>("newRoot");
        
        ((LinkedBinaryTree<String>) tree).setRoot(newRoot);
        assertEquals(newRoot, tree.root());
    }
    
}
