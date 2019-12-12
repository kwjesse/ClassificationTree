package edu.isu.cs2235;

import java.util.Objects;

/**
 * A datum class to hold data for a node in the classification tree. A Datum
 * contains a number (the number of the node in the tree, to be used on output)
 * and a prompt used to describe the datum.
 *
 * @author Isaac Griffith
 */
public class Datum implements Comparable<Datum> {

    private String prompt;
    private int number;

    /**
     * Construct a new Datum Object with the given Prompt, and an initial number
     * of 0.
     *
     * @param prompt The prompt.
     */
    public Datum(String prompt) {
        this(prompt, 0);
    }

    /**
     * Constructs a new Datum Object with the given Prompt and Node NUmber.
     *
     * @param prompt The prompt
     * @param number The number
     */
    public Datum(String prompt, int number) {
        this.prompt = prompt;
        this.number = number;
    }

    /**
     * Returns the prompt associated with this datum object.
     *
     * @return The Datum objects prompt.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Returns the number associated with this datum object, note that this can
     * be changed.
     *
     * @return The current node number for this datum object.
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number The new number for this datum object
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.prompt);
        hash = 79 * hash + this.number;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Datum other = (Datum) obj;
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.prompt, other.prompt)) {
            return false;
        }
        return true;
    }

    /**
     * Returns the current prompt stored in this object. {@inheritDoc}
     */
    @Override
    public String toString() {
        return prompt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Datum o) {
        return this.getPrompt().compareTo(o.getPrompt());
    }

}
