package com.webcheckers.model.moves;

/**
 * An interface for direction enums.
 * Allows for polymorphic storage and iteration, and thus fewer if statements probably
 * @author: Adrian Postolache axp3806@rit.edu
 */
public interface Direction {

    /**
     * @return the row component of the direction
     */
    int getRow();

    /**
     * @return the column component of the direction
     */
    int getCol();
}
