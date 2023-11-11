package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;

/**
 * A skeletal implementation of the Tree abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the tree
 * abstract data type.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the tree
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
    /**
     * Checks if is internal.
     *
     * @param p the position
     * @return true, if is internal
     */
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    /**
     * Checks if is leaf.
     *
     * @param p the position
     * @return true, if is leaf
     */
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }
    
    /**
     * Checks if is root.
     *
     * @param p the position
     * @return true, if is root
     */
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * Replaces the element at the specified position with the new element
     * and returns the element that was replaced.
     * 
     * @param p the position at which to set the element
     * @param value the new value to set
     * @return the original element that is being replaced
     */
    @Override
    public E set(Position<E> p, E value) {
    	// Validate the position and node
    	AbstractTreeNode<E> node = validate(p);
    	// Store original element
    	E original = node.getElement();
    	// Set the new element
    	node.setElement(value);
    	// Return the element that was replaced.
    	return original;
    }   
    
    /**
     * Pre order traversal.
     *
     * @return the iterable
     */
    @Override
    public Iterable<Position<E>> preOrder() {
        PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }

    /**
     * Pre order helper.
     *
     * @param p the p
     * @param traversal the traversal
     */
    private void preOrderHelper(Position<E> p, PositionCollection traversal) {
        traversal.add(p);
        for (Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    }    
    
    /**
     * Post order traversal.
     *
     * @return the iterable
     */
    @Override
    public Iterable<Position<E>> postOrder() {
        PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            postOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    /**
     * Post order helper.
     *
     * @param p the p
     * @param traversal the traversal
     */
    private void postOrderHelper(Position<E> p, PositionCollection traversal) {
        for (Position<E> c : children(p)) {
            postOrderHelper(c, traversal);
        }
        traversal.add(p);
    }
    
    /**
     * Level order traversal.
     *
     * @return the iterable
     */
    @Override
    public Iterable<Position<E>> levelOrder() {
        PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            Queue<Position<E>> queue = new ArrayBasedQueue<>(); 
            queue.enqueue(root()); 
            while (!queue.isEmpty()) {
                Position<E> current = queue.dequeue(); 
                traversal.add(current); 
                for (Position<E> child : children(current)) {
                    queue.enqueue(child); 
                }
            }
        }
        return traversal;

    }
    
    /**
     * Safely casts a Position, p, to be an AbstractTreeNode.
     * 
     * @param p the position to cast to a AbstractTreeNode
     * @return a reference to the AbstractTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  AbstractTreeNode
     */
    protected abstract AbstractTreeNode<E> validate(Position<E> p);
    
    /**
     * The Class AbstractTreeNode.
     *
     * @param <E> the element type
     */
    protected abstract static class AbstractTreeNode<E> implements Position<E> {

        /** The element. */
        private E element;
        
        /**
         * Instantiates a new abstract tree node.
         *
         * @param element the element
         */
        public AbstractTreeNode(E element) {
            setElement(element);
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
         * Sets the element.
         *
         * @param element the new element
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * To string helper.
     *
     * @param sb the sb
     * @param indent the indent
     * @param root the root
     */
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
    
    /**
     * PositionCollection implements the {@link Iterable} interface to allow traversing
     * through the positions of the tree. PositionCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     * @author Courtney T Swartz (ctswartz)
     *
     */
    protected class PositionCollection implements Iterable<Position<E>> {

        /** The list. */
        private List<Position<E>> list;

        /**
         * Construct a new PositionCollection.
         */
        public PositionCollection() {
            list = new SinglyLinkedList<Position<E>>();
        }

        /**
         * Add a position to the collection. Null positions or positions with null
         * elements are not added to the collection
         * 
         * @param position the position to add to the collection
         */
        public void add(Position<E> position) {
            if (position != null && position.getElement() != null) {
                list.addLast(position);
            }
        }

        /**
         * Return an iterator for the PositionCollection.
         *
         * @return the new PositionSetIterator
         */
        public Iterator<Position<E>> iterator() {
            return new PositionSetIterator();
        }

        /**
         * The Class PositionSetIterator.
         */
        private class PositionSetIterator implements Iterator<Position<E>> {

            /** The it. */
            private Iterator<Position<E>> it;

            /**
             * Instantiates a new position set iterator.
             */
            public PositionSetIterator() {
                it = list.iterator();
            }

            /**
             * Checks for next.
             *
             * @return true, if successful
             */
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            /**
             * Next.
             *
             * @return the position
             */
            @Override
            public Position<E> next() {
                return it.next();
            }

            /**
             * Removes is not supported.
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }
}