package edu.isu.cs2235.structures.impl;

import edu.isu.cs2235.structures.Node;
import edu.isu.cs2235.structures.impl.LinkedBinaryTree;
import edu.isu.cs2235.structures.impl.BinaryTreeNode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isaac Griffith
 */
public class LinkedBinaryTreeTest {

    private LinkedBinaryTree<Integer> fixture;

    public LinkedBinaryTreeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        fixture = new LinkedBinaryTree<>();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createNode method, of class LinkedBinaryTree.
     */
    @Test
    public void testCreateNode() {
        assertNotNull(fixture.createNode(0, null, null, null));
    }

    @Test
    public void testCreateNode_2() {
        BinaryTreeNode<Integer> root = fixture.createNode(0, null, null, null);
        BinaryTreeNode<Integer> node = fixture.createNode(0, root, null, null);
        assertNotNull(node);

        assertNull(root.getLeft());
        assertNull(root.getRight());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNode_3() {
        fixture.createNode(null, null, null, null);
    }

    /**
     * Test of setRoot method, of class LinkedBinaryTree.
     */
    @Test
    public void testSetRoot() {
        assertNull(fixture.root());

        Node<Integer> node = fixture.setRoot(0);
        assertNotNull(node);
        assertEquals(node, fixture.root());
    }

    /**
     * Test of setRoot method, of class LinkedBinaryTree.
     */
    @Test
    public void testSetRoot_2() {
        assertNull(fixture.root());

        assertNull(fixture.setRoot(null));
        assertTrue(fixture.isEmpty());
    }

    /**
     * Test of insert method, of class LinkedBinaryTree.
     */
    @Test
    public void testInsert() {
        fixture.setRoot(0);
        assertNotNull(fixture.insert(1, fixture.root()));
        assertEquals(new Integer(1), fixture.left(fixture.root()).getElement());
    }

    /**
     * Test of insert method, of class LinkedBinaryTree.
     */
    @Test
    public void testInsert_2() {
        fixture.setRoot(0);
        Node<Integer> left = fixture.addLeft(fixture.root(), 1);

        assertNotNull(fixture.insert(2, fixture.root()));
        assertEquals(new Integer(2), fixture.right(fixture.root()).getElement());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsert_3() {
        fixture.setRoot(0);
        fixture.addLeft(fixture.root(), 1);
        fixture.addRight(fixture.root(), 2);
        fixture.insert(3, fixture.root());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsert_4() {
        fixture.setRoot(0);

        fixture.insert(2, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsert_5() {
        fixture.setRoot(0);

        fixture.insert(null, fixture.root());
    }

    /**
     * Test of validate method, of class LinkedBinaryTree.
     */
    @Test
    public void testValidate() {
        Node<Integer> root = fixture.setRoot(0);
        assertNotNull(fixture.validate(root));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidate_2() {
        fixture.validate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidate_3() {
        fixture.setRoot(null);
        fixture.validate(fixture.createNode(0, null, null, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidate_4() {
        fixture.validate(new Node<Integer>() {
            public Integer getElement() {
                return 0;
            }

            public void setElement(Integer i) {

            }

            public Node<Integer> getParent() {
                return null;
            }
        });
    }

    /**
     * Test of root method, of class LinkedBinaryTree.
     */
    @Test
    public void testRoot() {
        assertNull(fixture.root());
    }

    @Test
    public void testRoot_2() {
        fixture.setRoot(0);
        assertNotNull(fixture.root());
        assertEquals(new Integer(0), fixture.root().getElement());
    }

    /**
     * Test of parent method, of class LinkedBinaryTree.
     */
    @Test
    public void testParent() {
        fixture.setRoot(0);
        assertNull(fixture.parent(fixture.root()));

        Node<Integer> one = fixture.addLeft(fixture.root(), 1);
        assertNotNull(fixture.parent(one));
        assertEquals(new Integer(0), fixture.parent(one).getElement());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParent_1() {
        fixture.parent(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParent_2() {
        fixture.parent(new Node<Integer>() {
            public Integer getElement() {
                return 0;
            }

            public void setElement(Integer i) {

            }

            public Node<Integer> getParent() {
                return null;
            }
        });
    }

    /**
     * Test of size method, of class LinkedBinaryTree.
     */
    @Test
    public void testSize() {
        assertEquals(0, fixture.size());
        fixture.setRoot(0);
        assertEquals(1, fixture.size());
    }

    @Test
    public void testSize_2() {
        fixture.setRoot(0);
        fixture.addLeft(fixture.root(), 1);
        assertEquals(2, fixture.size());
    }

    @Test
    public void testSize_3() {
        fixture.setRoot(0);
        fixture.addRight(fixture.root(), 1);
        assertEquals(2, fixture.size());
    }

    @Test
    public void testSize_4() {
        fixture.setRoot(0);
        fixture.addRight(fixture.root(), 1);
        fixture.insert(2, fixture.root());
        assertEquals(3, fixture.size());
    }

    @Test
    public void testSize_5() {
        fixture.setRoot(0);
        fixture.addRight(fixture.root(), 1);
        fixture.insert(2, fixture.root());
        fixture.setRoot(null);
        assertEquals(0, fixture.size());
    }

    @Test
    public void testSize_6() {
        fixture.setRoot(0);
        fixture.addRight(fixture.root(), 1);
        fixture.insert(2, fixture.root());
        fixture.setRoot(1);
        assertEquals(1, fixture.size());
    }

    @Test
    public void testSize_7() {
        fixture.setRoot(0);
        fixture.addRight(fixture.root(), 1);
        fixture.insert(2, fixture.root());
        fixture.remove(1, fixture.root());
        assertEquals(2, fixture.size());
    }

    @Test
    public void testSize_8() {
        fixture.setRoot(0);
        fixture.addRight(fixture.root(), 1);
        fixture.insert(2, fixture.root());
        assertEquals(3, fixture.size());
    }

    /**
     * Test of size method, of class LinkedGeneralTree.
     */
    @Test
    public void testSize_9() {
        fixture = new LinkedBinaryTree();
        assertEquals("Size is not correct for an empty tree.", 0, fixture.size());
    }

    /**
     * Test of size method, of class LinkedGeneralTree.
     */
    @Test
    public void testSize_10() {
        fixture.setRoot(null);
        assertEquals("Size is not correct after removing root.", 0, fixture.size());
    }

    /**
     * Test of left method, of class LinkedBinaryTree.
     */
    @Test
    public void testLeft() {
        fixture.setRoot(0);
        assertNull(fixture.left(fixture.root()));
        fixture.addLeft(fixture.root(), 1);
        assertNotNull(fixture.left(fixture.root()));
        assertNull(fixture.left(fixture.left(fixture.root())));
    }

    /**
     * Test of right method, of class LinkedBinaryTree.
     */
    @Test
    public void testRight() {
        fixture.setRoot(0);
        assertNull(fixture.right(fixture.root()));
        fixture.addRight(fixture.root(), 1);
        assertNotNull(fixture.right(fixture.root()));
        assertNull(fixture.right(fixture.right(fixture.root())));
    }

    /**
     * Test of addLeft method, of class LinkedBinaryTree.
     */
    @Test
    public void testAddLeft() {

    }

    /**
     * Test of addRight method, of class LinkedBinaryTree.
     */
    @Test
    public void testAddRight() {

    }

    /**
     * Test of set method, of class LinkedBinaryTree.
     */
    @Test
    public void testSet() {
        fixture.setRoot(4);

        fixture.set(fixture.root(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSet_2() {
        fixture.set(null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSet_3() {
        fixture.set(fixture.createNode(20, null, null, null), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSet_4() {
        fixture.setRoot(0);
        fixture.set(fixture.root(), null);
    }

    /**
     * Test of remove method, of class LinkedGeneralTree.
     */
    @Test
    public void testRemove() {
        Node<Integer> root = fixture.setRoot(4);
        Node<Integer> left = fixture.insert(2, root);
        Node<Integer> two = fixture.insert(6, root);

        fixture.insert(1, left);
        fixture.insert(3, left);

        fixture.insert(5, two);
        fixture.insert(7, two);
        assertTrue("Returned false on removal of external node", fixture.remove(1, left));
    }

    /**
     * Test of remove method, of class LinkedGeneralTree.
     */
    @Test
    public void testRemove_2() {
        Node<Integer> root = fixture.setRoot(4);
        Node<Integer> left = fixture.insert(2, root);
        Node<Integer> two = fixture.insert(6, root);

        fixture.insert(1, left);
        fixture.insert(3, left);

        fixture.insert(5, two);
        fixture.insert(7, two);
        assertTrue("Returned false on removal of internal node", fixture.remove(left.getElement(), fixture.root()));
    }

    /**
     * Test of remove method, of class LinkedGeneralTree.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemove_3() {
        Node<Integer> root = fixture.setRoot(4);
        Node<Integer> left = fixture.insert(2, root);
        Node<Integer> two = fixture.insert(6, root);

        fixture.insert(1, left);
        fixture.insert(3, left);

        fixture.insert(5, two);
        fixture.insert(7, two);
        fixture.remove(null, left);
    }

    /**
     * Test of remove method, of class LinkedGeneralTree.
     */
    @Test
    public void testRemove_4() {
        Node<Integer> root = fixture.setRoot(4);
        Node<Integer> left = fixture.insert(2, root);
        Node<Integer> two = fixture.insert(6, root);

        fixture.insert(1, left);
        fixture.insert(3, left);

        fixture.insert(5, two);
        fixture.insert(7, two);
        assertFalse("Returned true on removal of non-root item with null parent", fixture.remove(5, null));
    }

    /**
     * Test of numChildren method, of class LinkedGeneralTree.
     */
    @Test
    public void testNumChildren() {
        Node<Integer> root = fixture.setRoot(4);
        Node<Integer> left = fixture.insert(2, root);
        Node<Integer> two = fixture.insert(6, root);

        fixture.insert(1, left);
        fixture.insert(3, left);

        fixture.insert(5, two);
        fixture.insert(7, two);
        assertEquals("Wrong number of children returned for node", 2, fixture.numChildren(left));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNumChildren_2() {
        fixture.numChildren(null);
    }
}
