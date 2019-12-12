package edu.isu.cs2235;

import java.util.Scanner;
import edu.isu.cs2235.ClassificationTree;

/**
 * Driver for the classification program
 *
 * @author Isaac Griffith
 * @author Katherine Wilsdon
 */
public class Driver {

    static Scanner in = new Scanner(System.in);

    /**
     * Runs the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Provide a file for loading.");
        System.out.print("File name: ");
        String file = in.next();
        ClassificationTree tree = new ClassificationTree(file);
        String more = "Y";
        while (more.equals("Y")) {
            System.out.print("\nDo you have another animal to identify? (Y/N) > ");
            more = in.next().toUpperCase();

            if (more.equals("Y")) {
                tree.identify();
            } else if (more.equals("N")) {
                System.out.println("Enter a file name to save the tree to: ");
                System.out.print("File name: ");
                String output = in.next();
                tree.save(output);
            }
        }

    }
}
