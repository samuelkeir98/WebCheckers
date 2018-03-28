package com.webcheckers.model.moves;

/**
 * An interface for direction enums.
 * Allows for polymorphic storage and iteration, and thus fewer if statements probably
 * @author: Adrian Postolache axp3806@rit.edu
 * Tested by Sam Keir
 */
public interface Direction {

    /**
     * @return The row component of the direction
     */
    int getRow();

    /**
     * @return The column component of the direction
     */
    int getCol();
}
