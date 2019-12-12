package edu.isu.cs2235.structures.impl;

import edu.isu.cs2235.structures.Node;

/**
 * A Binary Tree Node Class
 *
 * @author Katherine Wilsdon
 * @param <E> The Type of element to be contained
 */
public class BinaryTreeNode<E> implements Node<E> {
    private E element;
    private BinaryTreeNode<E> parent;
    private BinaryTreeNode<E> left;
    private BinaryTreeNode<E> right;

    public BinaryTreeNode() {}

    /**
     * @return The parent node of this class. Can be null.
     */
    @Override
    public BinaryTreeNode<E> getParent() {
        if (parent == null)
            return null;
        else
            return parent;
    }

    /**
     * @return The left node of this class
     */
    public BinaryTreeNode<E> getLeft() {
        if (left == null)
            return null;
        else
            return left;
    }

    /**
     * @return The right node of this class
     */
    public BinaryTreeNode<E> getRight() {
        if (right == null)
            return null;
        else
            return right;
    }

    /**
     * @return The element contained in this node.
     */
    @Override
    public E getElement() {
        return element;
    }

    /**
     * Sets the parent node of this class
     *
     * @param parent The parent node
     */
    public void setParent(BinaryTreeNode<E> parent) {
        this.parent = parent;
    }

    /**
     * Sets the left node of this class
     *
     * @param left The left node
     */
    public void setLeft(BinaryTreeNode<E> left) {
        this.left = left;
    }

    /**
     * Sets the right node of this class
     *
     * @param right The right node
     */
    public void setRight(BinaryTreeNode<E> right) {
        this.right = right;
    }

    /**
     * Sets the new value of this node to the provided one. Throws an
     * IllegalArgumentException if the provided value is null.
     *
     * @param element New value to be contained in this node.
     */
    @Override
    public void setElement(E element) throws IllegalArgumentException {
        this.element = element;
    }
}
