package edu.isu.cs2235.structures.impl;

import edu.isu.cs2235.structures.Queue;

/**
 * A Queue ADT class
 *
 * @author Katherine Wilsdon
 * @param <E> Element type held in this Queue
 */
public class LinkedQueue<E> implements Queue<E> {

    DoublyLinkedList<E> list = new DoublyLinkedList<>();

    /**
     * Creates a linked queue
     */
    public LinkedQueue() {}

    /**
     * @return The number of elements in the queue
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * @return tests whether the queue is empty.
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Inserts an element at the end of the queue.
     *
     * @param element Element to be inserted.
     */
    @Override
    public void offer(E element) {
        if (element != null)
            list.addLast(element);
    }

    /**
     * @return The value first element of the queue (with out removing it), or
     * null if empty.
     */
    @Override
    public E peek() {
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }

    /**
     * @return The value of the first element of the queue (and removes it), or
     * null if empty.
     */
    @Override
    public E poll() {
        if (list.isEmpty())
            return null;
        else
            return list.removeFirst();
    }

    /**
     * Prints the contents of the queue starting at top, one item per line. Note
     * this method should not empty the contents of the queue.
     */
    @Override
    public void printQueue() {
        for (int i = 0; i < list.size(); ++i)
            System.out.println(list.get(i));
    }

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
    @Override
    public void transfer(Queue<E> into) {
        if (into != null) {
            this.reverse();
            while (list.size() > 0) {
                into.offer(list.first());
                list.removeFirst();
            }
        }
    }

    /**
     * Reverses the contents of this queue.
     */
    @Override
    public void reverse() {
        if (list.size() > 1) {
            LinkedStack<E> holder = new LinkedStack<>();
            int listSize = this.size();
            for (int i = 0; i < listSize; ++i)
                holder.push(list.removeFirst());
            for (int i = 0; i < listSize; ++i)
                list.addLast(holder.pop());
        }
    }

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
    @Override
    public void merge(Queue<E> from) {
        if (from != null) {
            LinkedQueue<E> fromHolder = new LinkedQueue<>();
            LinkedQueue<E> listHolder = new LinkedQueue<>();
            int listSize = this.size();
            int fromSize = from.size();

            for (int i = 0; i < fromSize; ++i){
                fromHolder.offer(from.peek());
                from.poll();
            }

            for (int i = 0; i < listSize; ++i) {
                listHolder.offer(list.first());
                list.removeFirst();
            }

            for (int i = 0; i < listSize; ++i) {
                list.addLast(listHolder.peek());
                listHolder.poll();
            }

            for (int i = 0; i < fromSize; ++i) {
                list.addLast(fromHolder.peek());
                from.offer(fromHolder.poll());
            }
        }
    }
}
