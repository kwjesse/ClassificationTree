package edu.isu.cs2235.traversals;

import edu.isu.cs2235.structures.Node;
import edu.isu.cs2235.structures.Tree;
import edu.isu.cs2235.structures.impl.LinkedBinaryTree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fate
 */
public class BreadthFirstTraversalTest {

    private TreeTraversal<Integer> fixture;
    private Tree<Integer> tree;
    private Node<Integer> left;

    public BreadthFirstTraversalTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<>();

        Node<Integer> root = tree.setRoot(1);
        left = tree.insert(2, root);
        Node<Integer> two = tree.insert(3, root);

        tree.insert(4, left);
        tree.insert(5, left);

        tree.insert(6, two);
        tree.insert(7, two);

        fixture = new BreadthFirstTraversal<>(tree);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of traverse method, of class BreadthFirstTraversal.
     */
    @Test
    public void testTraverse() {
        Iterable<Node<Integer>> trav = fixture.traverse();
        int i = 1;

        for (Node<Integer> node : trav) {
            assertEquals("In order not followed correctly.", new Integer(i), node.getElement());
            i++;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTraverse_2() {
        tree = new LinkedBinaryTree<>();
        fixture = new BreadthFirstTraversal(tree);
        Iterable<Node<Integer>> trav = fixture.traverse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTraverse_3() {
        tree = null;
        fixture = new BreadthFirstTraversal(tree);
    }

    /**
     * Test of traverseFrom method, of class BreadthFirstTraversal.
     */
    @Test
    public void testTraverseFrom() {
        Integer[] vals = {2, 4, 5};

        int i = 0;
        for (Node<Integer> n : fixture.traverseFrom(left)) {
            assertEquals(vals[i], n.getElement());
            i++;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTraverseFrom_2() {
        fixture.traverseFrom(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTraverseFrom_3() {
        tree.remove(left.getElement(), tree.root());
        fixture.traverseFrom(left);
    }
}
