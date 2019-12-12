package edu.isu.cs2235.traversals.commands;

import edu.isu.cs2235.structures.Node;
import edu.isu.cs2235.structures.Tree;

/**
 *
 * @author Isaac Griffith
 */
public abstract class TraversalCommand<E> {

    public abstract void execute(Tree<E> tree, Node<E> node);
}
