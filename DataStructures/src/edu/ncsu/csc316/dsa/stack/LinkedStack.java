package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * The Linked Stack is implemented as a singly-linked list data structure to
 * support efficient, O(1) worst-case Stack abstract data type behaviors.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {

    /**  Delegate to our existing singly linked list class *. */
    private SinglyLinkedList<E> list;

    /**
     * Construct a new singly-linked list to use when modeling the last-in-first-out
     * paradigm for the stack abstract data type.
     */
    public LinkedStack() {
        list = new SinglyLinkedList<E>();
    }
    
    /**
     * Push an element on to the top of the stack.
     *
     * @param value the element being pushed onto the stack.
     */
    @Override
    public void push(E value) {
        list.addFirst(value);
    }

    /**
     * Pops/removes and returns the top element from the stack.
     *
     * @return the element on the top of the stack.
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        E stackPop = list.removeFirst();
        return stackPop;
    }

    /**
     * Returns the element on top of the stack
     * but does not remove it.
     *
     * @return the element on the top of the stack.
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E top() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        E stackTop = list.first();
        return stackTop;
    }

    /**
     * Returns the size of the stack.
     *
     * @return the number of elements in the stack.
     */
    @Override
    public int size() {
    	int stackSize = list.size();
        return stackSize;
    }
}