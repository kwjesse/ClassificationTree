package edu.isu.cs2235.traversals;

import edu.isu.cs2235.structures.Node;
import edu.isu.cs2235.structures.Tree;
import edu.isu.cs2235.structures.impl.BinaryTreeNode;
import edu.isu.cs2235.structures.impl.LinkedQueue;
import edu.isu.cs2235.traversals.commands.TraversalCommand;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of the Iterative BreadthFirstSearch algorithm.
 *
 * @author Isaac Griffith
 * @author Katherine Wilsdon
 */
public class BreadthFirstTraversal<E> extends AbstractTraversal<E> {

    /**
     * Constructs a new BreadthFirst Traversal for the given tree.
     *
     * @param tree The tree to traverse
     */
    public BreadthFirstTraversal(Tree<E> tree) {
        super(tree);
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
     * The method called to extract the subtree that is next in the
     * traversal.
     * Citation: https://algorithms.tutorialhorizon.com/breadth-first-searchtraversal-in-a-binary-tree/
     *
     * @param start Node whose subtree is needed
     * @return List of nodes comprising the traversal
     */
    @Override
    public Iterable<Node<E>> traverseFrom(Node start) {
        if (start == null)
            throw new IllegalArgumentException("Node cannot be null");
        ArrayList<Node<E>> list = new ArrayList<>();
        LinkedQueue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        BinaryTreeNode<E> node = (BinaryTreeNode) this.tree.validate(start);
        if (node == null)
            return list;
        list.add(node);
        queue.offer(node);
        while (queue.size() > 0){
            BinaryTreeNode<E> currentNode = queue.poll();
            if (currentNode.getLeft() != null) {
                list.add(currentNode.getLeft());
                queue.offer(currentNode.getLeft());
            }
            if(currentNode.getRight() != null) {
                list.add(currentNode.getRight());
                queue.offer(currentNode.getRight());
            }
        }
        return list;
    }

    /**
     * Sets the traversal command
     *
     * @param cmd the command to be set
     */
    @Override
    public void setCommand(TraversalCommand cmd) {
        this.command = cmd;
    }
}
