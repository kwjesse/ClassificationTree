package edu.isu.cs2235.structures.impl;

import edu.isu.cs2235.structures.BinaryTree;
import edu.isu.cs2235.structures.Node;
import edu.isu.cs2235.structures.Tree;
import edu.isu.cs2235.traversals.PreOrderTraversal;

import java.util.ArrayList;

/**
 * A Linked Binary Tree Class
 *
 * @author Katherine Wilsdon
 * @param <E> The Type of element to be contained
 */
public class LinkedBinaryTree<E> implements Tree<E>, BinaryTree<E> {

    public LinkedBinaryTree() {
    }

    private BinaryTreeNode<E> root = null;
    private int size = 0;
    /**
     * Returns the left child of the provided node.
     *
     * @param p The parent node of whom the left child is desired.
     * @return The left child of the provided node, can be null if no such child
     * exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        if(node != null)
            return node.getLeft();
        else
            return null;
    }

    /**
     * Returns the right child of the provided node.
     *
     * @param p The parent node of whom the right child is desired.
     * @return The right child of the provided node, can be null if no such
     * child exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        if(node != null)
            return node.getRight();
        else
            return null;
    }

    /**
     * Returns the sibling node of the provided node, if such a sibling exists.
     * That is, if the right node is provided the left node will be returned
     * from the same parent.
     * Citation: Goodrich, Michael T, et al. Data Structures & Algorithms in Java. 6th ed., John Wiley & Sons, Inc, 2014.
     *
     * @param p The node of whom a sibling is requested.
     * @return The sibling of the provided node, or null if no such sibling
     * exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> sibling(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        BinaryTreeNode<E> parent = node.getParent();
        if (parent == null)
            return null;
        if (p == left(parent))
            return right(parent);
        else
            return left(parent);
    }

    /**
     * Adds the provided element as a new node to the left side of the provided
     * node.
     * Citation: Goodrich, Michael T, et al. Data Structures & Algorithms in Java. 6th ed., John Wiley & Sons, Inc, 2014.
     *
     * @param p The node to which the element is to be added as the left child.
     * @param element Element to be added
     * @return The newly created left child of the provided node
     * @throws IllegalArgumentException If the provided node is invalid, if the
     * provided element is null, or if the provided node already has a left
     * child.
     */
    @Override
    public Node<E> addLeft(Node<E> p, E element) throws IllegalArgumentException {
        BinaryTreeNode<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a left child");
        BinaryTreeNode<E> child = new BinaryTreeNode<>();
        child.setElement(element);
        child.setParent(parent);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Adds the provided element as a new node to the right side of the provided
     * node.
     * Citation: Goodrich, Michael T, et al. Data Structures & Algorithms in Java. 6th ed., John Wiley & Sons, Inc, 2014.
     *
     * @param p The node to which the element is to be added as the right child.
     * @param element Element to be added
     * @return The newly created right child of the provided node
     * @throws IllegalArgumentException If the provided node is invalid, if the
     * provided element is null, or if the provided node already has a right
     * child.
     */
    @Override
    public Node<E> addRight(Node<E> p, E element) throws IllegalArgumentException {
        BinaryTreeNode<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        BinaryTreeNode<E> child = new BinaryTreeNode<>();
        child.setElement(element);
        child.setParent(parent);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Create a node with a parent, left child, and right child
     *
     * @param element Element to be added
     * @param parent The node to which the element is to be added to.
     * @param left  The node's left child
     * @param right The node's right child
     * @return The created node
     */
    public BinaryTreeNode<E> createNode(E element, BinaryTreeNode<E> parent, BinaryTreeNode<E> left, BinaryTreeNode<E> right){
        if (element == null)
            throw new IllegalArgumentException("The provided element was null");
        BinaryTreeNode<E> node = new BinaryTreeNode<>();
        node.setElement(element);
        node.setParent(parent);
        node.setLeft(left);
        node.setRight(right);
        return node;
    }

    /**
     * @return The root node of this tree or null if the Tree is empty.
     */
    @Override
    public Node<E> root() {
        if (this.root != null) {
            BinaryTreeNode<E> root = (BinaryTreeNode<E>)this.root;
            return root;
        } else
            return null;
    }

    /**
     * Sets the tree's root node to the provided item, by creating a new node
     * (unless the given item is the same as the current root's item). Note that
     * this must also reset the size of the tree to the correct value if the
     * current node is replaced.
     *
     * @param item New item for the root node.
     * @return The new root node.
     */
    @Override
    public Node<E> setRoot(E item) {
        if (item == null) {
            this.root = null;
            size = -1;
        } else if (!this.isEmpty()){
            this.root.setElement(item);
            this.root.setLeft(null);
            this.root.setRight(null);
            this.root.setParent(null);
            size = 0;
        }else {
            this.root = new BinaryTreeNode<>();
            this.root.setElement(item);
        }
        size++;
        return root();
    }

    /**
     * Returns the parent node of the node provided, or null if the node is also
     * the root of the tree.
     *
     * @param p Node whose parent is being requested.
     * @return The parent of the provided node, or null if the provided node is
     * the root.
     * @throws IllegalArgumentException If the node is invalid
     */
    @Override
    public Node<E> parent(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Returns an iterable collection of the children attached to the provided
     * node.
     * Citation: Goodrich, Michael T, et al. Data Structures & Algorithms in Java. 6th ed., John Wiley & Sons, Inc, 2014.
     * @param p The node whose children are requested.
     * @return An iterable collection of the children attached to the provided
     * node.
     * @throws IllegalArgumentException If the provided node is invalid
     */
    @Override
    public Iterable<Node<E>> children(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        ArrayList<Node<E>> children = new ArrayList<>(2);
        if (node.getLeft() != null)
            children.add(node.getLeft());
        if (node.getRight() != null)
            children.add(node.getRight());
        return children;
    }

    /**
     * Returns the number of children currently attached to the provided node.
     * Citation: Goodrich, Michael T, et al. Data Structures & Algorithms in Java. 6th ed., John Wiley & Sons, Inc, 2014.
     *
     * @param p Node whose number of children is requested.
     * @return The number of children attached to the provided node.
     * @throws IllegalArgumentException If the node is invalid.
     */
    @Override
    public int numChildren(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        int count = 0;
        if (node.getLeft() != null)
            count++;
        if (node.getLeft() != null)
            count++;
        return count;
    }

    /**
     * Tests whether the node is an internal node or not. That is whether the
     * node has children.
     *
     * @param p The node to test.
     * @return True if the node is an internal node, false otherwise.
     * @throws IllegalArgumentException If the node is invalid.
     */
    @Override
    public boolean isInternal(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        if (node.getLeft() != null || node.getRight() != null)
            return true;
        else
            return false;
    }

    /**
     * Tests whether the node is an external node of the tree. That is whether
     * the node has no children and thus is a leaf of the tree.
     *
     * @param p The node to test.
     * @return True if the node is a leaf node, false otherwise.
     * @throws IllegalArgumentException If the node is invalid
     */
    @Override
    public boolean isExternal(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        if (node.getLeft() == null && node.getRight() == null)
            return true;
        else
            return false;
    }

    /**
     * Tests whether this node is the root node of the tree. That is that the
     * provided node has children but not parent.
     *
     * @param p Node to test.
     * @return True if the node is the root of the tree.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public boolean isRoot(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = validate(p);
        if (node.getParent() == null && (node.getLeft() != null || node.getRight() != null))
            return true;
        else
            return false;
    }

    /**
     * Inserts the item into the tree under the provided node. If the provided
     * node is null the item becomes the new root of the tree, beware.
     *
     * @param item Item to be inserted into the tree.
     * @param p The parent node of the tree, if null the item becomes the new
     * root so beware.
     * @return the item tht was inserted
     * @throws IllegalArgumentException if the provided parent node is invalid,
     * or the provided value is null.
     */
    @Override
    public Node<E> insert(E item, Node<E> p) {
        BinaryTreeNode<E> parent = validate(p);
        if (item == null)
            throw new IllegalArgumentException("The provided element was null");
        if (parent.getLeft() != null && parent.getRight() != null)
            throw new IllegalArgumentException("There are 2 children of this node already");
        BinaryTreeNode<E> node = new BinaryTreeNode<>();
        node.setElement(item);
        BinaryTreeNode<E> root = (BinaryTreeNode<E>)root();
        // Set the root node when a root node already exists
        if (parent == null && root != null) {
            BinaryTreeNode<E> child = root;
            if (parent.getLeft() == null) {
                node.setLeft(child);
                node.setParent(null);
                child.setParent(node);
            } else if (parent.getRight() == null) {
                node.setRight(child);
                node.setParent(null);
                child.setParent(node);
            }
        } // Set the root node when a root node does not exist
        else if (parent == null && root == null){
            this.setRoot(item);
        } // When the parent node is not the root
        else if (parent != null){
            if(parent.getLeft() == null){
                parent.setLeft(node);
                node.setParent(parent);
            } else if (parent.getRight() == null){
                parent.setRight(node);
                node.setParent(parent);
            }
        }
        size++;
        return node;
    }

    /**
     * Removes the given item from the provided parent node.
     *
     * @param item Item to be removed from the list of children of the provided
     * node.
     * @param p Parent node.
     * @return the item that was removed
     * @throws IllegalArgumentException If the provided parent node is not valid
     * or the value is null.
     */
    @Override
    public boolean remove(E item, Node<E> p) throws IllegalArgumentException {
        boolean wasRemoved = false;
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");
        if (p == null)
            return wasRemoved;
        BinaryTreeNode<E> parent = validate(p);
        if (parent.getLeft().getElement() != item && parent.getRight().getElement() != item)
            throw new IllegalArgumentException("The provided item is not a child of the provided parent node");
        // When the parent node is not the root node
        if (parent != null){
            BinaryTreeNode<E> node;
            // If the left child
            if(parent.getLeft().getElement() == item){
                node = parent.getLeft();
                // If the left child is an internal node
                if(isInternal(node)) {
                    removeInternalNode(node);
                    wasRemoved = true;
                    return wasRemoved;
                }
                parent.setLeft(null);
                node.setParent(node);
                size--;
                wasRemoved = true;
            } // If the right child
            else if (parent.getRight().getElement() == item){
                node = parent.getRight();
                // If the right child is an internal node
                if(isInternal(node)) {
                    removeInternalNode(node);
                    wasRemoved = true;
                    return wasRemoved;
                }
                parent.setRight(null);
                node.setParent(node);
                size--;
                wasRemoved = true;
            }
        }
        return wasRemoved;
    }

    /**
     * Recursive method that removes an internal node
     *
     * @param p the node to remove
     */
    public void removeInternalNode(Node<E> p) {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
        // When the node does not have any children
        if (node.getLeft() == null && node.getRight() == null) {
            node.setParent(node);
            node.setElement(null);
            this.size--;
        } //When the node does have children
        else {
            // Remove all left and right child nodes
            Node<E> left = node.getLeft();
            removeInternalNode(left);
            Node<E> right = node.getRight();
            removeInternalNode(right);
            node.setParent(node);
            node.setElement(null);
            this.size--;
        }
    }

    /**
     * @return The number of nodes currently in the tree.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return true if the tree contains no nodes (that is the root = null),
     * false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.size <= 0;
    }

    /**
     * Updates the value of the node to the provided value. Throws an
     * IllegalArgumentException if the value is null, the node is null, or the
     * node is not in this Tree.
     *
     * @param node Node whose value is to be updated.
     * @param element New value for the node.
     * @return The removed element
     * @throws IllegalArgumentException If the provided node is invalid, or the
     * element value is null.
     */
    @Override
    public E set(Node<E> node, E element) throws IllegalArgumentException {
        if (element == null)
            throw new IllegalArgumentException("The provided element was null");
        if(node == null)
            throw new IllegalArgumentException("The provided node cannot be null");
        BinaryTreeNode<E> p = (BinaryTreeNode<E>) node;
        if (node.getParent() == node)
            throw new IllegalArgumentException("p is no longer in the tree");
        if(node.getParent() == null && root() != node)
            throw new IllegalArgumentException("Cannot set root to a node that isn't the root");
        E temp = p.getElement();
        p.setElement(element);
        return temp;
    }

    /**
     * Validates that the provided node is not null, is of a subtype of Node
     * supported by the implementing tree class, and is currently in this tree.
     * If these conditions are not met then an IllegalArgumentException is
     * thrown.
     * Citation: Goodrich, Michael T, et al. Data Structures & Algorithms in Java. 6th ed., John Wiley & Sons, Inc, 2014.
     *
     * @param p The node to be validated.
     * @return A node of the expected type specific to the implementing tree.
     * @throws IllegalArgumentException Thrown if the provided node is null, not
     * in the current tree, or is not of a type supported by the current tree.
     */
    @Override
    public BinaryTreeNode<E> validate(Node<E> p) throws IllegalArgumentException {
        if (!(p instanceof BinaryTreeNode))
            throw new IllegalArgumentException("The provided type is not supported by the current tree");
        if(p == null)
            throw new IllegalArgumentException("The provided node cannot be null");
        BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
        if (node.getParent() == node)
            throw new IllegalArgumentException("p is no longer in the tree");
        BinaryTreeNode<E> root = (BinaryTreeNode<E>) this.root;
        boolean doesNodeExist = doesNodeExist(root, node.getElement());
        if (!doesNodeExist)
            throw new IllegalArgumentException("The provided node is not in the current tree");
        return node;
    }

    /**
     * Determine if the provided element exists in the tree
     * Citation: https://www.geeksforgeeks.org/search-a-node-in-binary-tree/
     *
     * @param node The node, whose element is checked equals the item
     * @param item Item to be checked whether it exists in the tree
     * @return
     */
    private boolean doesNodeExist(BinaryTreeNode<E> node, E item){
        if (node == null)
            return false;
        if (node.getElement() == item)
            return true;

        // Check whether the element exists on the left and right subtrees
        boolean doesExistLeftOfNode = doesNodeExist(node.getLeft(), item);
        boolean doesExistRightOfNode = doesNodeExist(node.getRight(), item);
        return doesExistLeftOfNode || doesExistRightOfNode;
    }

    /**
     * Calculates the depth of the given node in the current tree.
     * Citation: https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/
     *
     * @param node Node whose depth is to be calculated
     * @return Depth of the node in the tree.
     */
    @Override
    public int depth(Node<E> node) throws IllegalArgumentException {
        if (node == null)
            return 0;
        else {
            BinaryTreeNode<E> p = validate(node);
            int leftDepth = depth(p.getLeft());
            int rightDepth = depth(p.getRight());

            if (leftDepth > rightDepth)
                return leftDepth + 1;
            else
                return rightDepth + 1;
        }
    }

    /**
     * Recursively calculates the size of a subtree rooted at the provided node.
     *
     * @param node Node whose subtree size is to be calculated
     * @return Size of the subtree (excluding the root)
     */
    @Override
    public int subTreeSize(Node<E> node) throws IllegalArgumentException {
        if (node == null)
            throw new IllegalArgumentException("The provided node cannot be null");
        BinaryTreeNode<E> p = validate(node);
        int count = 0;
        if (p.getLeft() == null && p.getRight() == null)
            count++;
        else {
            count++;
            Node<E> left = p.getLeft();
            if (left == null)
                count++;
            else
                subTreeSize(left);
            Node<E> right = p.getRight();
            if (right == null)
                count++;
            else
                subTreeSize(right);
        }
        return count;
    }

    /**
     * Checks if the provided node is the last child of it's parent node. Note
     * that the root node always returns true.
     *
     * @param node Node to check.
     * @return True if the node is the last child of it's parent node or is the
     * root, false otherwise.
     * @throws IllegalArgumentException If the provided node is not valid.
     */
    @Override
    public boolean isLastChild(Node<E> node) throws IllegalArgumentException {
        BinaryTreeNode<E> p = validate(node);
        if(node == this.root)
            return true;
        else if (p.getLeft() == null && p.getRight() == null)
            return true;
        else
            return false;
    }
}
