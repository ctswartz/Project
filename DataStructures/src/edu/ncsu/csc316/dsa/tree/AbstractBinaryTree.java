package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
    
    /**
     * In order traversal. 
     *
     * @return the iterable collection of positions
     */
    @Override
    public Iterable<Position<E>> inOrder() {
        PositionCollection traversal = new PositionCollection();
        
        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }
        
        return traversal;
    }

    /**
     * In order helper. Recursively traverses the tree in order.
     *
     * @param p the position
     * @param traversal the traversal collection
     */
    private void inOrderHelper(Position<E> p, PositionCollection traversal) {
        if (p != null) {
            // Go through left side
            inOrderHelper(left(p), traversal);
            // Find current node
            traversal.add(p);
            // Go through right side
            inOrderHelper(right(p), traversal);
        }
    }
    
    /**
     * Returns the number of siblings for the position.
     *
     * @param p the position
     * @return the position of the sibling or null if none exist
     */
    @Override
	public Position<E> sibling(Position<E> p) {
		// Validate the position and node
		AbstractTreeNode<E> node = validate(p);
		
		// Check parent node, the root node had no sibling
		if (parent(node) == null) {
			return null;
		}
		
	    // Validate parent node
	    AbstractTreeNode<E> parent = validate(parent(node));
	    
	    // If the node is the left child, return the right child
	    if (node == left(parent)) {
	        return right(parent);
	    } else {
	    	// Otherwise, return the left child
	        return left(parent);
	    }
	}

	/**
	 * Returns the number of children for the position.
	 *
	 * @param p the position
	 * @return the int the number of children
	 */
	@Override
    public int numChildren(Position<E> p) {
    	// Validate the position and node
    	AbstractTreeNode<E> node = validate(p);
    	
    	// Counter for children
    	int count = 0;
    	
    	// Check for left child
    	if (left(node) != null) {
    		count++;
    	}
    	
    	//Check for right child
    	if (right(node) != null) {
    		count++;
    	}
    	return count;
    	
    	
    }
    
    /**
     * Returns an iterable collection of children that belong to the position.
     *
     * @param p the position
     * @return the iterable collection
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}