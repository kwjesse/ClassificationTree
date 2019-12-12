package edu.isu.cs2235.structures;

/**
 * Stack ADT Interface
 *
 * A Stack is a linear datastructure which provides access to only the first
 * item in the structure. This provides a Last In First Out (LIFO) semantic to
 * its operation.
 *
 * @author Isaac Griffith
 * @param <E> Element type held in this Stack
 */
public interface Stack<E> {
    /**
     * Adds the provided item to the top of the stack. Note that if the item is
     * null, nothing occurs.
     *
     * @param element Element added to the top of the stack, unless this item is
     * null.
     */
    void push(E element);

    /**
     * Returns the value of the top item in the stack, without removing it. If
     * the stack is empty then null is returned.
     *
     * @return The value of the item at the top of the stack, or null if the
     * stack is empty.
     */
    E peek();

    /**
     * Removes the top item from the stack and returns it's value. If the stack
     * is currently empty, null is returned.
     *
     * @return The value of the top item in the stack, or null if the stack is
     * empty.
     */
    E pop();

    /**
     * @return The current number of items in this stack.
     */
    int size();

    /**
     * A test to determine if this Stack is currently empty.
     *
     * @return True if this stack is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Tranfers the contents of this stack into the provided stack. The contents
     * of this stack are to found in reverse order at the top of the provided
     * stack. This stack should be empty once the transfer is completed. Note
     * that if the provided stack is null, nothing is to happen.
     *
     * @param to The new stack onto which the reversed order of contents from
     * this stack are to be transferred to the top of, unless the provided stack
     * is null.
     */
    void transfer(Stack<E> to);

    /**
     * Reverses the contents of this stack.
     */
    void reverse();

    /**
     * Merges the contents of the provided stack onto the bottom of this stack.
     * The order of both stacks must be preserved in the order of this stack
     * after the method call. Furthermore, the provided stack must still contain
     * its original contents in their original order after the method is
     * complete. If the provided stack is null, no changes should occur.
     *
     * @param other Stack whose contents are to be merged onto the bottom of
     * this stack.
     */
    void merge(Stack<E> other);

    /**
     * Prints the contents of the stack starting at top, one item per line. Note
     * this method should not empty the contents of the stack.
     */
    void printStack();
}
