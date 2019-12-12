package edu.isu.cs2235.traversals.commands;

import edu.isu.cs2235.structures.Node;
import edu.isu.cs2235.structures.Tree;
import edu.isu.cs2235.Datum;

/**
 * A Traversal Command which traveses a tree of Datum objects and sets their number to the number in which they were visited.
 * @author Isaac Griffith
 */
public class EnumerationCommand extends TraversalCommand<Datum> {

    private int current;

    /**
     * Constructs a new Enumeration Command and sets the initial value of current to 0;
     */
    public EnumerationCommand() {
        current = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Tree<Datum> tree, Node<Datum> node) {
        node.getElement().setNumber(current);
        current += 1;
    }

}
