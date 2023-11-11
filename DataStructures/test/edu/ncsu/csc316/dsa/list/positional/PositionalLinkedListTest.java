package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the PositionalLinkedList class methods and behaviors.
 * 
 * @author Courtney T Swartz (ctswartz)
 */
public class PositionalLinkedListTest {
	
    /** The list. */
    private PositionalLinkedList<String> list;
    
    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        list = new PositionalLinkedList<>();
    }
    
    /**
     * Test add first.
     */
    @Test
    public void testAddFirst() {
        assertTrue(list.isEmpty());
        list.addFirst("first");
        assertEquals(1, list.size());
        assertEquals("first", list.first().getElement());
    }

    /**
     * Test add last.
     */
    @Test
    public void testAddLast() {
        list.addLast("first");
        assertEquals(1, list.size());
        assertEquals("first", list.last().getElement());
    }

    /**
     * Test add before.
     */
    @Test
    public void testAddBefore() {
        Position<String> p = list.addLast("second");
        list.addBefore(p, "first");
        assertEquals(2, list.size());
        assertEquals("first", list.first().getElement());
        assertEquals("second", list.last().getElement());
    }

    /**
     * Test add after.
     */
    @Test
    public void testAddAfter() {
        Position<String> p = list.addFirst("first");
        list.addAfter(p, "second");
        assertEquals(2, list.size());
        assertEquals("first", list.first().getElement());
        assertEquals("second", list.last().getElement());
    }

    /**
     * Test remove.
     */
    @Test
    public void testRemove() {
        Position<String> p = list.addFirst("first");
        list.remove(p);
        assertTrue(list.isEmpty());
    }

    /**
     * Test set.
     */
    @Test
    public void testSet() {
        Position<String> p = list.addFirst("first");
        String old = list.set(p, "second");
        assertEquals("first", old);
        assertEquals("second", list.first().getElement());
    }

    /**
     * Test before and after.
     */
    @Test
    public void testBeforeAndAfter() {
        Position<String> p1 = list.addFirst("first");
        Position<String> p2 = list.addLast("second");
        assertNull(list.before(p1));
        assertEquals(p2, list.after(p1));
        assertEquals(p1, list.before(p2));
        assertNull(list.after(p2));
    }
    
    /**
     * Test position iterator constructor and has next.
     */
    @Test
    public void testPositionIteratorConstructor() {
        list.addFirst("first");
        list.addLast("second");
        list.addLast("third");

        Iterator<Position<String>> iterator = list.positions().iterator();
        
        assertTrue(iterator.hasNext());
        
        iterator.next();  // first
        iterator.next();  // second
        iterator.next();  // third
        
        assertFalse(iterator.hasNext());
    }


    /**
     * Test position iterator next.
     */
    @Test
    public void testPositionIteratorNext() {
        PositionalLinkedList<String> list1 = new PositionalLinkedList<>();
        list1.addLast("first");
        list1.addLast("second");
        Iterator<Position<String>> it = list1.positions().iterator();

        assertEquals("first", it.next().getElement());
        assertEquals("second", it.next().getElement());
    }

    /**
     * Test position iterator remove without next.
     */
    @Test(expected = IllegalStateException.class)
    public void testPositionIteratorRemoveWithoutNext() {
        PositionalLinkedList<String> list2 = new PositionalLinkedList<>();
        Iterator<Position<String>> it = list2.positions().iterator();
        try {
            it.remove();
        } finally {
            assertEquals(0, list2.size());
        }
    }

    /**
     * Test position iterator remove.
     */
    @Test
    public void testPositionIteratorRemove() {
        PositionalLinkedList<String> list3 = new PositionalLinkedList<>();
        list3.addLast("first");
        Iterator<Position<String>> it = list3.positions().iterator();
        it.next();
        it.remove();
        assertEquals(0, list3.size());
    }

    /**
     * Test position iterator double remove.
     */
    @Test(expected = IllegalStateException.class)
    public void testPositionIteratorDoubleRemove() {
        PositionalLinkedList<String> list4 = new PositionalLinkedList<>();
        list4.addLast("first");
        Iterator<Position<String>> it = list4.positions().iterator();
        it.next();
        it.remove();
        try {
            it.remove();
        } finally {
            assertEquals(0, list4.size());
        }

    }
    
    /**
     * Test element iterator has next.
     */
    @Test
    public void testElementIteratorHasNext() {
        assertFalse(list.iterator().hasNext());

        list.addFirst("first");
        Iterator<String> it = list.iterator();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    /**
     * Test element iterator next.
     */
    @Test
    public void testElementIteratorNext() {
        list.addFirst("first");
        list.addLast("second");
        Iterator<String> it = list.iterator();

        assertEquals("first", it.next());
        assertEquals("second", it.next());
        
        assertThrows(NoSuchElementException.class, () -> {
            it.next();  // Should throw exception because there are no more elements
        });
    }

    /**
     * Test element iterator remove.
     */
    @Test
    public void testElementIteratorRemove() {
        list.addFirst("first");
        list.addLast("second");
        Iterator<String> it = list.iterator();

        it.next();
        it.remove();
        assertEquals(1, list.size());
        assertEquals("second", list.first().getElement());

        it = list.iterator();
        it.next();
        it.remove();
        assertTrue(list.isEmpty());

    }
    
    /**
     * Test element iterator more.
     */
    @Test
    public void testElementIteratorMore() {
        list.addFirst("first");
        list.addLast("second");
        list.addLast("third");
        Iterator<String> it = list.iterator();

        assertEquals("first", it.next());
        assertEquals("second", it.next());
        //should remove "second"
        it.remove();

        assertEquals(2, list.size());
        assertEquals("first", list.first().getElement());
        assertEquals("third", list.last().getElement());

        it = list.iterator();
        assertEquals("first", it.next());
        assertEquals("third", it.next());
    }
    
    

}
