package edu.ncsu.csc316.dsa.list;

/**
 * A skeletal implementation of the List abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the list
 * abstract data type.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the list
 */
public abstract class AbstractList<E> implements List<E> {

    /**
     * Adds the provided element to the beginning of the list.
     *
     * @param element The element to add to the front.
     */
    @Override
    public void addFirst(E element) {
        add(0, element);
    }

    /**
     * Adds the provided element to the end of the list.
     *
     * @param element The element to add to the end.
     */
    @Override
    public void addLast(E element) {
        add(size(), element);
    }

    /**
     * Checks whether the provided index is a legal index based on the current state
     * of the list. This check should be performed when accessing any specific
     * indexes within the list.
     * 
     * @param index the index for which to check whether it is valid/legal in the
     *              current list or not
     */
    protected void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
        }
    }

    /**
     * Checks whether the provided index is a legal index based on the current state
     * of the list. This check should be performed when adding elements at specific
     * indexes within the list.
     * 
     * @param index the index for which to check whether it is valid/legal in the
     *              current list or not
     */
    protected void checkIndexForAdd(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
        }
    }

    /**
     * Retrieves the first element in the list.
     *
     * @return The first element in the list.
     */
    @Override
    public E first() {
        return get(0);
    }

    /**
     * Checks whether the list is empty.
     *
     * @return True if the list is empty; false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retrieves the last element in the list.
     *
     * @return The last element in the list.
     */
    @Override
    public E last() {
        return get(size() - 1);
    }

    /**
     * Removes and returns the first element in the list.
     *
     * @return The first element that was removed.
     */
    @Override
    public E removeFirst() {
        return remove(0);
    }

    /**
     * Removes and returns the last element in the list.
     *
     * @return The last element that was removed.
     */
    @Override
    public E removeLast() {
        return remove(size() - 1);
    }
}