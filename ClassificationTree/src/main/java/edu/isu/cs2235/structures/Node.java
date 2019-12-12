package edu.isu.cs2235.structures;

/**
 * Node is the interface for all internal nodes for trees and graphs.
 *
 * @author Isaac Griffith
 * @param <E> The Type of element to be contained
 */
public interface Node<E> {

    /**
     * @return The element contained in this node.
     */
    E getElement();

    /**
     * Sets the new value of this node to the provided one. Thows an
     * IllegalArgumentException if the provided value is null.
     *
     * @param element New value to be contained in this node.
     */
    void setElement(E element) throws IllegalArgumentException;

    /**
     * @return The parent node of this class. Can be null.
     */
    Node<E> getParent();
}
