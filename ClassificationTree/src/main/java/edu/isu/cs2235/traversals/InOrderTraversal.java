package edu.isu.cs2235.traversals;

import edu.isu.cs2235.structures.BinaryTree;
import edu.isu.cs2235.structures.Node;
import edu.isu.cs2235.structures.Tree;
import edu.isu.cs2235.structures.impl.BinaryTreeNode;
import edu.isu.cs2235.structures.impl.LinkedBinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * A recursive implementation of the inorder tree traversal algorithm.
 *
 * @author Isaac Griffith
 * @author Katherine Wilsdon
 * @param <E> The type of data in the tree to be traversed
 */
public class InOrderTraversal<E> extends DepthFirstTraversal<E> {

    /**
     * Constructs a new InOrder tree traversal for the given tree.
     *
     * @param tree Tree to be traversed.
     * @throws IllegalArgumentException If the type of tree to be traversed is
     * not a subtype of AbstractBinaryTree
     */
    public InOrderTraversal(Tree<E> tree) throws IllegalArgumentException {
        super(tree);
        if (!(tree instanceof LinkedBinaryTree)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * The recursive method called to extract the subtree that is next in the
     * traversal.
     * @param p Node whose subtree is needed.
     * @param snapshot List of nodes comprising the traversal
     */
    @Override
    public void subtree(Node<E> p, List<Node<E>> snapshot) {
        if (snapshot == null)
            throw new IllegalArgumentException("List cannot be null");
        if (p == null)
            throw new IllegalArgumentException("Node cannot be null");
        BinaryTreeNode<E> node = (BinaryTreeNode) this.tree.validate(p);
        List<Node<E>> list = snapshot;
        if (node.getLeft() == null && node.getRight() == null)
            list.add(node);
        else {
            Node<E> left = node.getLeft();
            if (left == null)
                list.add(node);
            else
                subtree(left, list);
            list.add(node);
            Node<E> right = node.getRight();
            if (right == null)
                list.add(node);
            else
                subtree(right, list);
        }
    }



}
