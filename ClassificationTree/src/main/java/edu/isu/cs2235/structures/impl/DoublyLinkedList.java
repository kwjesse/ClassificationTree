package edu.isu.cs2235.structures.impl;

import edu.isu.cs2235.structures.List;

/**
 * @author Katherine Wilsdon
 * @param <E> The Type of element to be contained
 */
public class DoublyLinkedList<E> implements List<E> {
    // A sentinel node at the beginning of the list
    private Node<E> head = new Node<>(null);

    // A sentinel node at the end of the list
    private Node<E> tail = new Node<>(null);

    // Size of the list
    private int count = 0;

    /**
     * A node for a Doubly Linked List that contains the node's data and the next and previous item in the list
     * @param <E>
     */
    private class Node<E> {
        E data;
        Node<E> next;
        Node<E> previous;

        // Sets the the next item in the list
        public void setNext(Node<E> next) {
            this.next = next;
        }

        // Sets the the previous item in the list
        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        // Returns the information or data contained in the node
        public E getData() {
            return data;
        }

        // Returns the next item in the list
        public Node<E> getNext() {
            return next;
        }

        // Returns the previous item in the list
        public Node<E> getPrevious() {
            return previous;
        }

        // Generate a Node class
        public Node(E data) {
            this.data = data;
            next = null;
        }
    }

    /**
     * Generates a Doubly Linked List
     */
    public DoublyLinkedList() {
        head.setNext(tail);
        tail.setPrevious(head);
    }

    /**
     * @return first element in the list or null if the list is empty.
     */
    @Override
    public E first() {
        if (size() <= 0)
            return null;
        else
            return head.getNext().getData();
    }

    /**
     * @return last element in the list or null if the list is empty.
     */
    @Override
    public E last() {
        if (size() <= 0)
            return null;
        else
            return tail.getPrevious().getData();
    }

    /**
     * Adds the provided element to the end of the list, only if the element is
     * not null.
     *
     * @param element Element to be added to the end of the list.
     */
    @Override
    public void addLast(E element) {
        if (element != null)
            addBetween(element, tail.getPrevious(), tail);
    }

    /**
     * Adds the provided element to the front of the list, only if the element
     * is not null.
     *
     * @param element Element to be added to the front of the list.
     */
    @Override
    public void addFirst(E element) {
        if (element != null) {
            if (size() == 0)
                addBetween(element, head, tail);
            else
                addBetween(element, head, head.getNext());
        }
    }

    /**
     * Removes the element at the front of the list.
     *
     * @return Element at the front of the list, or null if the list is empty.
     */
    @Override
    public E removeFirst() {
        if (size() <= 0)
            return null;
        return remove(0);
    }

    /**
     * Removes the element at the end of the list.
     *
     * @return Element at the end of the list, or null if the list is empty.
     */
    @Override
    public E removeLast() {
        if (size() <= 0)
            return null;
        return remove(size() - 1);
    }

    /**
     * Inserts the given element into the list at the provided index. The
     * element will not be inserted if either the element provided is null or if
     * the index provided is less than 0. If the index is greater than or equal
     * to the current size of the list, the element will be added to the end of
     * the list.
     *
     * @param element Element to be added (as long as it is not null).
     * @param index Index in the list where the element is to be inserted.
     */
    @Override
    public void insert(E element, int index) {
        if(index >= 0 && element != null) {
            if (index >= size())
                addLast(element);
            else if (index == 0)
                addFirst(element);
            else {
                Node<E> nextNode = getNode(index);
                addBetween(element, nextNode.getPrevious(), nextNode);
            }
        }
    }

    /**
     * Removes the element at the given index and returns the value.
     *
     * @param index Index of the element to remove
     * @return The value of the element at the given index, or null if the index
     * is greater than or equal to the size of the list or less than 0.
     */
    @Override
    public E remove(int index) {
        if(index < 0 || index >= size())
            return null;
        else {
            Node<E> toRemove = getNode(index);
            Node<E> prev = toRemove.getPrevious();
            Node<E> next = toRemove.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
            toRemove.setNext(null);
            toRemove.setPrevious(null);
            count--;
            return toRemove.getData();
        }
    }

    /**
     * Retrieves the value at the specified index. Will return null if the index
     * provided is less than 0 or greater than or equal to the current size of
     * the list.
     *
     * @param index Index of the value to be retrieved.
     * @return Element at the given index, or null if the index is less than 0
     * or greater than or equal to the list size.
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size())
            return null;
        Node<E> temp;
        if(index + 1 < size()/ 2.0) {
            temp = head;
            for (int i = -1; i < index; ++i) {
                temp = temp.getNext();
            }
        } else {
            temp = tail;
            for (int i = size(); i > index; --i) {
                temp = temp.getPrevious();
            }
        }
        return temp.getData();
    }

    /**
     * Retrieves the node at the specified index. Will return null if the index
     * provided is less than 0 or greater than or equal to the current size of
     * the list.
     *
     * @param index Index of the value to be retrieved.
     * @return Returns the node at a given index
     */
    private Node<E> getNode(int index) {
        if (index < 0 || index >= size())
            return null;
        Node<E> temp;
        if(index + 1 < size()/ 2.0) {
            temp = head;
            for (int i = -1; i < index; ++i) {
                temp = temp.getNext();
            }
        } else {
            temp = tail;
            for (int i = size(); i > index; --i) {
                temp = temp.getPrevious();
            }
        }
        return temp;
    }

    /**
     * @return The current size of the list. Note that 0 is returned for an
     * empty list.
     */
    @Override
    public int size() {
        if (isEmpty())
            return 0;
        else
            return count;
    }

    /**
     * @return true if there are no items currently stored in the list, false
     * otherwise.
     */
    @Override
    public boolean isEmpty() {
        return count <= 0;
    }

    /**
     * Prints the contents of the list in a single line separating each element
     * by a space to the default System.out
     */
    @Override
    public void printList() {
        for (int i = 0; i < size(); ++i)
            System.out.println(get(i));
    }

    /**
     * Adds new node in between the next and previous nodes
     * Citation: Goodrich, Michael T, et al. Data Structures & Algorithms in Java. 6th ed., John Wiley & Sons, Inc, 2014.
     *
     * @param data Data of a new new node
     * @param prev The previous node of the new node in the list
     * @param next The next node of the new node in the list
     */
    private void addBetween(E data, Node<E> prev, Node<E> next){
        Node<E> newNode = new Node<>(data);
        newNode.setPrevious(prev);
        newNode.setNext(next);
        prev.setNext(newNode);
        next.setPrevious(newNode);
        count++;
    }
}
