package edu.isu.cs2235.traversals;

import edu.isu.cs2235.structures.Node;
import edu.isu.cs2235.structures.Tree;
import edu.isu.cs2235.structures.impl.LinkedBinaryTree;
import java.util.ArrayList;
import java.util.List;
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
public class PreOrderTraversalTest {

    private DepthFirstTraversal<Integer> fixture;
    private Tree<Integer> tree;
    private Node<Integer> left;

    public PreOrderTraversalTest() {
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
        Node<Integer> two = tree.insert(5, root);

        tree.insert(3, left);
        tree.insert(4, left);

        tree.insert(6, two);
        tree.insert(7, two);

        fixture = new PreOrderTraversal<>(tree);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of subtree method, of class PostOrderTraversal.
     */
    @Test
    public void testSubtree() {
        List<Node<Integer>> list = new ArrayList<>();
        fixture.subtree(left, list);
        assertEquals("Subtree size incorrect", 3, list.size());
        int i = 2;
        for (Node<Integer> node : list) {
            assertEquals("PostOrder traversal of sublist incorrect", new Integer(i), node.getElement());
            i++;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtree_2() {
        List<Node<Integer>> list = new ArrayList<>();
        fixture.subtree(null, list);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtree_3() {
        fixture.subtree(left, null);
    }

    /**
     * Test of traverse method, of class PostOrderTraversal
     */
    @Test
    public void testTraverse() {
        Iterable<Node<Integer>> trav = fixture.traverse();
        int i = 1;
        for (Node<Integer> node : trav) {
            assertEquals("Pre order not followed correctly.", new Integer(i), node.getElement());
            i++;
        }
    }

    @Test
    public void testTraverse_2() {
        tree = new LinkedBinaryTree<>();
        fixture = new PreOrderTraversal(tree);
        Iterable<Node<Integer>> trav = fixture.traverse();
        for (Node<Integer> node : trav) {
            fail("Traveral should be empty");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTraverse_3() {
        tree = null;
        fixture = new PreOrderTraversal(tree);
    }

    /**
     * Test of subTreeTraverse method in PreOrderTraversal
     */
    @Test
    public void testSubTreeTraversal() {
        Iterable<Node<Integer>> trav = fixture.subTreeTraverse(left);
        int i = 2;
        for (Node<Integer> node : trav) {
            assertEquals("PreOrder traversal of sublist incorrect", new Integer(i), node.getElement());
            i++;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubTreeTraversal_2() {
        Iterable<Node<Integer>> trav = fixture.subTreeTraverse(null);
    }
}
