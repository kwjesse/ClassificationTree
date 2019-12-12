package edu.isu.cs2235.traversals;

import edu.isu.cs2235.structures.Node;
import edu.isu.cs2235.structures.Tree;
import edu.isu.cs2235.structures.impl.BinaryTreeNode;

import java.util.List;

/**
 * An implementation of a DepthFirst PreOrder Traversal for a tree.
 *
 * @author Isaac Griffith
 * @author Katherine Wilsdon
 * @param <E> The type of data stored in the tree to be traversed.
 */
public class PreOrderTraversal<E> extends DepthFirstTraversal<E> {

    /**
     * Constructs a new PreOrder traversal for the provided tree.
     *
     * @param tree Tree to be traversed.
     */
    public PreOrderTraversal(Tree<E> tree) {
        super(tree);
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
            list.add(node);
            Node<E> left = node.getLeft();
            if (left == null)
                list.add(node);
            else
                subtree(left, list);
            Node<E> right = node.getRight();
            if (right == null)
                list.add(node);
            else
                subtree(right, list);
        }
    }
}
