package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure.
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 */
public class UpTreeDisjointSetForestTest {

    /** The set. */
    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes.
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior.
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        
        assertNotEquals(set.find("one"), set.find("two"));
    }

    /**
     * Test the output of the union-find behaviors.
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        Position<String> nine = set.makeSet("nine");
        Position<String> ten = set.makeSet("ten");
        
        assertEquals(one, set.find("one"));
        assertEquals(set.find("one"), set.find("one"));
        
        // Union sets and test find
        set.union(set.find("one"), set.find("two"));
        assertEquals(set.find("one"), set.find("two"));
        
        set.union(set.find("one"), set.find("three"));
        assertEquals(set.find("one"), set.find("three"));

        set.union(set.find("one"), set.find("two"));
        assertEquals(set.find("one"), set.find("two"));
        
        set.union(set.find("four"), set.find("five"));
        assertEquals(set.find("four"), set.find("five"));
        
        set.union(set.find("one"), set.find("four"));
        assertEquals(set.find("one"), set.find("four"));

        assertEquals(set.find("one"), set.find("five"));
        assertNotEquals(set.find("one"), set.find("six"));
        
        // Join all remaining into a single set
        set.union(set.find("six"), set.find("seven"));
        set.union(set.find("eight"), set.find("nine"));
        set.union(set.find("six"), set.find("eight"));
        set.union(set.find("one"), set.find("six"));
        set.union(set.find("ten"), set.find("one"));
        
        // all unified
        assertEquals(set.find("ten"), set.find("nine"));
        assertEquals(set.find("seven"), set.find("three"));
        assertEquals(set.find("one"), set.find("eight"));
        
        // Assert initial state of disjoint sets
        assertEquals(one.getElement(), "one");
        assertEquals(two.getElement(), "two");
        // ... and so on for the other positions if needed
        
        // Perform unions
        set.union(one, two); // Union one and two
        set.union(three, four); // Union three and four
        set.union(five, six); // Union five and six
        set.union(seven, eight); // Union seven and eight
        set.union(nine, ten); // Union nine and ten
        
        // Now perform nested unions to connect them all
        set.union(one, three);
        set.union(five, seven);
        set.union(nine, one); // Connects all to 'one'

        

    }
}