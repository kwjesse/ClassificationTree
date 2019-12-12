package edu.isu.cs2235.structures;

/**
 * A Tree ADT
 *
 * @author Isaac Griffith
 * @param <E> The type of data to be stored in the Tree.
 */
public interface Tree<E> {

    /**
     * @return The root node of this tree or null if the Tree is empty.
     */
    Node<E> root();

    /**
     * Sets the tree's root node to the provided item, by creating a new node
     * (unless the given item is the same as the current root's item). Note that
     * this must also reset the size of the tree to the correct value if the
     * current node is replaced.
     *
     * @param item New item for the root node.
     * @return The new root node.
     */
    Node<E> setRoot(E item);

    /**
     * Returns the parent node of the node provided, or null if the node is also
     * the root of the tree.
     *
     * @param p Node whose parent is being requested.
     * @return The parent of the provided node, or null if the provided node is
     * the root.
     * @throws IllegalArgumentException If the node is invalid
     */
    Node<E> parent(Node<E> p) throws IllegalArgumentException;

    /**
     * Returns an iterable collection of the children attached to the provided
     * node.
     *
     * @param p The node whose children are requested.
     * @return An iterable collection of the children attached to the provided
     * node.
     * @throws IllegalArgumentException If the provided node is invalid
     */
    Iterable<Node<E>> children(Node<E> p) throws IllegalArgumentException;

    /**
     * Returns the number of children currently attached to the provided node.
     *
     * @param p Node whose number of children is requested.
     * @return The number of children attached to the provided node.
     * @throws IllegalArgumentException If the node is invalid.
     */
    int numChildren(Node<E> p) throws IllegalArgumentException;

    /**
     * Tests whether the node is an internal node or not. That is whether the
     * node has children.
     *
     * @param p The node to test.
     * @return True if the node is an internal node, false otherwise.
     * @throws IllegalArgumentException If the node is invalid.
     */
    boolean isInternal(Node<E> p) throws IllegalArgumentException;

    /**
     * Tests whether the node is an external node of the tree. That is whether
     * the node has no children and thus is a leaf of the tree.
     *
     * @param p The node to test.
     * @return True if the node is a leaf node, false otherwise.
     * @throws IllegalArgumentException If the node is invalid
     */
    boolean isExternal(Node<E> p) throws IllegalArgumentException;

    /**
     * Tests whether this node is the root node of the tree. That is that the
     * provided node has children but not parent.
     *
     * @param p Node to test.
     * @return True if the node is the root of the tree.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    boolean isRoot(Node<E> p) throws IllegalArgumentException;

    /**
     * Inserts the item into the tree under the provided node. If the provided
     * node is null the item becomes the new root of the tree, beware.
     *
     * @param item Item to be inserted into the tree.
     * @param p The parent node of the tree, if null the item becomes the new
     * root so beaware.
     * @return True if the item was able to be inserted, false otherwise (for
     * example the item was null)
     * @throws IllegalArgumentException if the provided parent node is invalid,
     * or the provided value is null.
     */
    Node<E> insert(E item, Node<E> p);

    /**
     * Removes the given item from the provided parent node.
     *
     * @param item Item to be removed from the list of children of the provided
     * node.
     * @param p Parent node.
     * @return true if the item was removed, false otherwise.
     * @throws IllegalArgumentException If the provided parent node is not valid
     * or the value is null.
     */
    boolean remove(E item, Node<E> p) throws IllegalArgumentException;

    /**
     * @return The number of nodes currently in the tree.
     */
    int size();

    /**
     * @return true if the tree contains no nodes (that is the root = null),
     * false otherwise.
     */
    boolean isEmpty();

    /**
     * Updates the value of the node to the provided value. Throws an
     * IllegalArgumentException if the value is null, the node is null, or the
     * node is not in this Tree.
     *
     * @param node Node whose value is to be updated.
     * @param element New value for the node.
     * @throws IllegalArgumentException If the provided node is invalid, or the
     * element value is null.
     */
    E set(Node<E> node, E element) throws IllegalArgumentException;

    /**
     * Validates that the provided node is not null, is of a subtype of Node
     * supported by the implementing tree class, and is currently in this tree.
     * If these conditions are not met then an IllegalArgumentException is
     * thrown.
     *
     * @param p The node to be validated.
     * @return A node of the expected type specific to the implementing tree.
     * @throws IllegalArgumentException Thrown if the provided node is null, not
     * in the current tree, or is not of a type supported by the current tree.
     */
    Node<E> validate(Node<E> p) throws IllegalArgumentException;

    /**
     * Calculates the depth of the given node in the current tree.
     *
     * @param node Node whose depth is to be calculated
     * @return Depth of the node in the tree.
     * @throws IllegalArgumentException If the provided node is invalid
     */
    int depth(Node<E> node) throws IllegalArgumentException;

    /**
     * Recusively calculates the size of a subtree rooted at the provided node.
     *
     * @param node Node whose subtree size is to be calculated
     * @return Size of the subtree (excluding the root)
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    int subTreeSize(Node<E> node) throws IllegalArgumentException;

    /**
     * Checks if the provided node is the last child of it's parent node. Note
     * that the root node always returns true.
     *
     * @param node Node to check.
     * @return True if the node is the last child of it's parent node or is the
     * root, false otherwise.
     * @throws IllegalArgumentException If the provided node is not valid.
     */
    boolean isLastChild(Node<E> node) throws IllegalArgumentException;
}
