package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * The UpTreeDisjointSetForest is implemented as a forest of linked up-trees.
 * Using balanced union, {@link DisjointSetForest#union} has worst-case runtime
 * of O(1). Using path-compression find, {@link DisjointSetForest#find} has
 * worst-case O(logn), but over time has worst-case runtime O(log*(n))
 * [log-star].
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the disjoint set
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

    // We need a secondary map to quickly locate an entry within the forest of
    // up-trees
    // NOTE: The textbook implementation does not include this
    // functionality; instead, the textbook implementation leaves
    // the responsibility of tracking positions to the client in a
    /** The map. */
    // separate map structure
    private Map<E, UpTreeNode<E>> map;

    /**
     * Constructs a new DisjointSetForest.
     */
    public UpTreeDisjointSetForest() {
        // Use an efficient map!
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }

    /**
     * An UpTreeNode maintains an element, a reference to the node's parent, and (if
     * it's the root of an up-tree) the number of nodes stored within that up-tree.
     *
     * @author Dr. King
     * @param <E> the element type
     */
    private static class UpTreeNode<E> implements Position<E> {

        /** The element. */
        private E element;
        
        /** The parent. */
        private UpTreeNode<E> parent;
        
        /** The count. */
        private int count;

        /**
         * Constructs a new UpTreeNode with the given element, a reference to itself as
         * the parent, and a count of 1.
         * 
         * @param element the element to store in the new UpTreeNode
         */
        public UpTreeNode(E element) {
            setElement(element);
            setParent(this);
            setCount(1);
        }

        /**
         * Sets the element of the UpTreeNode to the given element.
         *
         * @param element the element to store in the UpTreeNode
         */
        public void setElement(E element) {
            this.element = element;
        }

        /**
         * Gets the element.
         *
         * @return the element
         */
        @Override
        public E getElement() {
            return element;
        }

        /**
         * Sets the parent of the UpTreeNode to the given UpTreeNode.
         *
         * @param parent the UpTreeNode to set as the current node's parent
         */
        public void setParent(UpTreeNode<E> parent) {
            this.parent = parent;
        }

        /**
         * Returns a reference to the parent of the current UpTreeNode.
         *
         * @return a reference to the parent of the current UpTreeNode
         */
        public UpTreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the number of nodes contained in the UpTree rooted at the current
         * UpTreeNode.
         *
         * @param count the new count
         */
        public void setCount(int count) {
            this.count = count;
        }

        /**
         * If the current UpTreeNode is the root of an up-tree, returns the number of
         * elements stored within the UpTree. Otherwise, if the current UpTreeNode is
         * not the root of an up-tree, count is undefined.
         * 
         * @return the number of elements stored within the UpTree, if the current
         *         UpTreeNode is the root; otherwise, count is undefined.
         */
        public int getCount() {
            return count;
        }
    }

    /**
     * Creates a new set containing the specified value. The new set is added to
     * the forest as a separate tree (node with itself as its parent). If the value
     * already exists in the forest, the behavior is dependent on the
     * implementation details or may result in an exception.
     *
     * @param value the value to be added in a new set
     * @return a Position representing the location of the new element in the forest
     */
    @Override
    public Position<E> makeSet(E value) {
        UpTreeNode<E> node = new UpTreeNode<>(value);
        map.put(value, node);
        return node;
    }

    /**
     * Locates and returns the representative Position of the set containing the
     * given value. If the value is not found in any set, the method may return
     * null or throw an exception, based on implementation or configuration.
     *
     * @param value the value whose set representative is to be found
     * @return the Position representing the set containing the value
     */
    @Override
    public Position<E> find(E value) {
        // NOTE: The textbook solution requires the client to keep
        // track of the location of each position in the forest.
        // Our implementation includes a Map to handle this for the
        // client, so we should allow the client to find the set
        // that contains a node by specifying the element
        UpTreeNode<E> node = map.get(value);
        if (node == null) {
            return null; // or throw exception depending on your requirement
        }
        return findHelper(node);
    }

    /**
     * The helper method for find operation that performs path compression.
     * It recursively traverses up the tree to find the root (set representative),
     * while also flattening the tree structure along the way for efficiency.
     *
     * @param current the current UpTreeNode being traversed
     * @return the root UpTreeNode of the set containing 'current'
     */
    private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
        // Implement path-compression find
        if (current.getParent() == current) {
            return current;
        } else {
            // Path compression: make this node point directly to the root
            UpTreeNode<E> root = findHelper(current.getParent());
            current.setParent(root);
            return root;
        }
    }

    /**
     * Merges the sets containing the given Positions if they are distinct,
     * linking the smaller set to the root of the larger set to maintain balance.
     * If both Positions belong to the same set, the method performs no action.
     *
     * @param s a Position representing an element in the first set
     * @param t a Position representing an element in the second set
     */
    @Override
    public void union(Position<E> s, Position<E> t) {
        UpTreeNode<E> a = validate(s);
        UpTreeNode<E> b = validate(t);
        
        if (a == b) {
            return;
        }

        if (a.getCount() < b.getCount()) {
            a.setParent(b);
            b.setCount(b.getCount() + a.getCount());
        } else {
            b.setParent(a);
            a.setCount(a.getCount() + b.getCount());
        }
    }

    /**
     * Validates that the provided Position is indeed an instance of UpTreeNode
     * specific to the UpTreeDisjointSetForest. Throws an IllegalArgumentException
     * if the validation fails.
     *
     * @param p the Position to validate
     * @return the UpTreeNode equivalent of the provided Position
     */
    private UpTreeNode<E> validate(Position<E> p) {
        if (!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>) p;
    }
}