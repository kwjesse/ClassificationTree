package edu.isu.cs2235.traversals;

import edu.isu.cs2235.structures.Node;
import edu.isu.cs2235.structures.Tree;
import edu.isu.cs2235.structures.impl.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of TreeTraversal algorithms using a Template Design
 * Pattern.
 *
 * @author Isaac Griffith
 * @author Katherine Wilsdon
 * @param <E> The type of data stored in the tree.
 */
public abstract class DepthFirstTraversal<E> extends AbstractTraversal<E> {

    /**
     * Constructs a new TreeTraversal template for the given tree.
     *
     * @param tree Tree to traverse with this template.
     * @throws IllegalArgumentException If provided tree is null
     */
    public DepthFirstTraversal(Tree<E> tree) throws IllegalArgumentException {
        super(tree);
    }

    

    /**
     * Method which initiates the traversal of a tree from the provided start
     * node. This method returns an iterable container of nodes represting a
     * resulting traversal of the subtree.
     *
     * @param start The node at which to start the traversal
     * @return An iterable container of nodes representing the traversal of a
     * tree.
     */
    protected Iterable<Node<E>> subTreeTraverse(Node<E> start) {
        if (start == null)
            throw new IllegalArgumentException("Node cannot be null");
        return traverseFrom(start);
    }

    /**
     * Traverse the tree from the root
     * @return an iterable of nodes ordered by depth first traversal
     */
    @Override
    public Iterable<Node<E>> traverse() {
        BinaryTreeNode<E> root = (BinaryTreeNode<E>)this.tree.root();
        return traverseFrom(root);
    }

    /**
     * Traverse the tree from a provided node
     *
     * @param node Root of the subtree to start the traversal at.
     * @return an iterable of nodes ordered by depth first traversal
     */
    @Override
    public Iterable<Node<E>> traverseFrom(Node<E> node) {
        ArrayList<Node<E>> list = new ArrayList<>();
        if (node != null){
            subtree(node, list);
            return list;
        } else
            return list;
    }
    
    /**
     * The recursive method called to extract the subtree that is next in the
     * traversal.
     *
     * @param p Node whose subtree is needed.
     * @param snapshot List of nodes comprising the traversal
     * @throws IllegalArgumentException If p is null or not in the tree or the provided list is null.
     */
    protected abstract void subtree(Node<E> p, final List<Node<E>> snapshot) throws IllegalArgumentException;
}
