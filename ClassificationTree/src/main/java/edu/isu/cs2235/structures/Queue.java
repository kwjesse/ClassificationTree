package edu.isu.cs2235.structures;

/**
 * An interface for a Queue ADT
 *
 * @author Isaac Griffith
 * @param <E> Element type to be stored in this Queue
 */
public interface Queue<E> {
    /**
     * @return The number of elements in the queue
     */
    int size();

    /**
     * @return tests whether the queue is empty.
     */
    boolean isEmpty();

    /**
     * Inserts an element at the end of the queue.
     *
     * @param element Element to be inserted.
     */
    void offer(E element);

    /**
     * @return The value first element of the queue (with out removing it), or
     * null if empty.
     */
    E peek();

    /**
     * @return The value of the first element of the queue (and removes it), or
     * null if empty.
     */
    E poll();

    /**
     * Prints the contents of the queue starting at top, one item per line. Note
     * this method should not empty the contents of the queue.
     */
    void printQueue();

    /**
     * Tranfers the contents of this queue into the provided queue. The contents
     * of this queue are to found in reverse order at the top of the provided
     * queue. This queue should be empty once the transfer is completed. Note
     * that if the provided queue is null, nothing is to happen.
     *
     * @param into The new queue onto which the reversed order of contents from
     * this queue are to be transferred to the top of, unless the provided queue
     * is null.
     */
    void transfer(Queue<E> into);

    /**
     * Reverses the contents of this queue.
     */
    void reverse();

    /**
     * Merges the contents of the provided queue onto the bottom of this queue.
     * The order of both queues must be preserved in the order of this queue
     * after the method call. Furthermore, the provided queue must still contain
     * its original contents in their original order after the method is
     * complete. If the provided queue is null, no changes should occur.
     *
     * @param from Queue whose contents are to be merged onto the bottom of
     * this queue.
     */
    void merge(Queue<E> from);
}
