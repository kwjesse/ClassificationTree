package edu.isu.cs2235;

import edu.isu.cs2235.structures.BinaryTree;
import edu.isu.cs2235.structures.Node;
import edu.isu.cs2235.structures.impl.BinaryTreeNode;
import edu.isu.cs2235.structures.impl.LinkedBinaryTree;
import edu.isu.cs2235.traversals.*;
import edu.isu.cs2235.traversals.commands.EnumeratedSaveCommand;
import edu.isu.cs2235.traversals.commands.EnumerationCommand;
import edu.isu.cs2235.traversals.commands.TraversalCommand;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * A very simple classification tree example using a BinaryTree and console
 * input.
 *
 * @author Isaac Griffith
 * @author Katherine Wilsdon
 */
public class ClassificationTree {

    private BinaryTree<Datum> tree;
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> animalsWithNot = new ArrayList<>();
    ArrayList<String> animals = new ArrayList<>();

    /**
     * Constructs a new Animal tree class which manages an underlying animal
     * tree
     * @param file
     */
    public ClassificationTree(String file) {
        tree = new LinkedBinaryTree<>();
        load(file);
    }

    /**
     * Main method which controls the identification and tree management loop.
     */
    public void identify() {
        LinkedBinaryTree<Datum> tree = (LinkedBinaryTree) this.tree;
        BinaryTreeNode<Datum> root = (BinaryTreeNode) tree.root();
        find(root);
    }

    /**
     * Recursive method that controls the identification and tree management loop
     * @param node current animal node
     */
    private void find(BinaryTreeNode<Datum> node){
        LinkedBinaryTree<Datum> tree = (LinkedBinaryTree) this.tree;
        BinaryTreeNode<Datum> parent = node.getParent();

        // Get prompt from node and ask the user if the animal is the prompt
        String prompt = node.getElement().getPrompt();
        System.out.print("Is this animal "+ prompt + "? (Y/N)  > ");
        String output = scanner.next();
        output = output.toUpperCase();

        // If this animal is the prompt
        if (output.equals("Y")){
            animalsWithNot.add(prompt);
            animals.add(prompt);
            // If the animal does or does not have a left child
            if (node.getLeft() == null){
                System.out.println("Good.");
                animals.clear();
                animalsWithNot.clear();
            } else if (node.getLeft() != null){
                find(node.getLeft());
            }
        } // If the animal is not the prompt
        else if (output.equals("N")){
            animalsWithNot.add("not " + prompt);
            animals.add(prompt);

            // If the animal does or does not have a right child
            if (node.getRight() == null){
                System.out.print("I don't know any");
                for (int i = 0; i < animalsWithNot.size() - 1; ++i){
                    if (i == 0)
                        System.out.print(" " + animalsWithNot.get(i));
                    else
                        System.out.print(", " + animalsWithNot.get(i));
                }
                String notAnimal = animals.get(animals.size() - 1);
                System.out.println(" animals that aren't a " + notAnimal + ".");

                // Ask user for a new animal and store the response in a datum
                System.out.print("What is the new animal?  > ");
                String animal = scanner.next();
                animal = animal.toLowerCase();
                Datum newAnimal = new Datum(animal);

                // Ask user for a new characteristic and store the response in a datum
                System.out.print("What characteristic does a " + animal + " have that a " + animals.get(animals.size() - 1) + " does not?  > ");
                String characteristic = scanner.next();
                characteristic = characteristic.toLowerCase();
                Datum newCharacteristic = new Datum(characteristic);

                // Remove the current node, insert the characteristic node, insert the old node, insert the new animal node
                tree.remove(node.getElement(), node.getParent());
                Node<Datum> newChar = tree.insert(newCharacteristic, parent);
                Node<Datum> oldNode = tree.insert(newAnimal, newChar);
                Node<Datum> newAnim = tree.insert(node.getElement(), newChar);

                // Reorder the element number
                PreOrderTraversal preOrder = new PreOrderTraversal(tree);
                List<Node<Datum>> preOrderList = (List<Node<Datum>>)preOrder.traverseFrom(tree.root());
                EnumerationCommand enumerationCommand = new EnumerationCommand();
                for (Node<Datum> item : preOrderList){
                    enumerationCommand.execute(tree, item);
                }
                animals.clear();
                animalsWithNot.clear();
            } else if (node.getLeft() != null){
                find(node.getRight());
            }
        }
    }


    /**
     * Saves a tree to a file.
     * @param file the name of the file to save
     */
    public void save(String file) {
        try {
            FileWriter filewriter = new FileWriter(file, false);
            PrintWriter printWriter = new PrintWriter(filewriter);
            LinkedBinaryTree<Datum> tree = (LinkedBinaryTree) this.tree;

            // Write the nodes to the file in pre-order traversal
            AbstractTraversal<Datum> preOrderTraversal = new PreOrderTraversal<>(tree);
            List<Node<Datum>> preOrderList = (List<Node<Datum>>) preOrderTraversal.traverseFrom(tree.root());
            EnumeratedSaveCommand saveCommand = new EnumeratedSaveCommand(printWriter);
            preOrderTraversal.setCommand(saveCommand);
            for (int i = 0; i < preOrderList.size(); ++i){
                saveCommand.execute(tree, preOrderList.get(i));
            }
             printWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Loads a tree from the given file, if an exception occurs during file
     * operations, a hardcoded basic tree will be loaded instead.
     * @param file the file name to load
     */
    public void load(String file) {

        try {
            // Get a list containing each line in the file
            Path path = Paths.get(file);
            List<String> lines = Files.readAllLines(path);
            this.tree = new LinkedBinaryTree<>();
            LinkedBinaryTree<Datum> tree = (LinkedBinaryTree) this.tree;
            for (String line : lines) {
                String[] splitLine = line.split(":");
                int parentNumber = Integer.parseInt(splitLine[0]);
                // If the node is the root
                if (parentNumber == -1){
                    Datum datum = new Datum(splitLine[3]);
                    datum.setNumber(Integer.parseInt(splitLine[1]));
                    tree.setRoot(datum);
                } else{
                    Datum datum = new Datum(splitLine[3]);
                    datum.setNumber(Integer.parseInt(splitLine[1]));
                    PreOrderTraversal<Datum> preOrderTraversal = new PreOrderTraversal<>(tree);
                    List<Node<Datum>> preOrderList = (List<Node<Datum>>)preOrderTraversal.traverseFrom(tree.root());
                    // Find the parent and insert the datum
                    int index = 0;
                    for (Node<Datum> b : preOrderList){
                        Datum element = b.getElement();
                        if (element.getNumber() == parentNumber)
                            break;
                        else
                            index++;
                    }
                    BinaryTreeNode<Datum> parent = (BinaryTreeNode<Datum>) preOrderList.get(index);
                    tree.insert(datum, parent);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
