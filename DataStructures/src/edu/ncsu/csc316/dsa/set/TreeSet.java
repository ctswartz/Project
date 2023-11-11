package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
//import edu.ncsu.csc316.dsa.map.search_tree.BinarySearchTreeMap;
import edu.ncsu.csc316.dsa.map.search_tree.AVLTreeMap;

/**
 * The TreeSet is implemented as an AVL Map
 * data structure to support efficient set abstract data type behaviors.
 * 
 * Using an AVL Map tree ensures worst-case runtime of
 * O(logn) for {@link Set#add}, {@link Set#remove}, and {@link Set#contains};
 * O(nlogn) worst-case runtime for {@link Set#addAll}, {@link Set#removeAll},
 * and {@link Set#retainAll}; and O(1) worst-case runtime for {@link Set#size}
 * and {@link Set#isEmpty}.
 * 
 * The TreeSet class is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the set
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {
    // Since we will delegate to an existing balanced search tree, the entries will
    // be ordered.
    // As a result, we must also restrict our tree set to use Comparable elements.
    
    /** The tree. */
    private Map<E, E> tree;

    /**
     * Constructs a new TreeSet.
     */
    public TreeSet() {
    	tree = new AVLTreeMap<E, E>();
    }

    /**
     * Returns an iterator over elements of type {@code E}.
     * 
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    /**
     * Adds the specified element to this set if it is not already present.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns.
     * 
     * @param value element to be added to this set
     */
    @Override
    public void add(E value) {
    	tree.put(value, value);
    }

    /**
     * Returns true if the set contains the specified element.
     *
     * @param value the value
     * @return true, if successful
     */
    @Override
    public boolean contains(E value) {
    	return tree.get(value) != null;
    }

    /**
     * Removes and returns the specified element from this set if it is present.
     *
     * @param value the value
     * @return the e
     */
    @Override
    public E remove(E value) {
    	return tree.remove(value);
    }

    /**
     * Get the size.
     *
     * @return the int representation of the size
     */
    @Override
    public int size() {
    	return tree.size();
    }
}
