package com.webcheckers.model;

/**
 * Player model class
 *
 * @author Anthony Massicci
 */
public class Player {
    private final String name;

    /**
     * constructor
     * @param name name of player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * grabs name of player
     */
    public String getName() {
        return this.name;
    }

    /**
     * equals method for value object semantics
     * @param other other object to compare this object to.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Player))
            return false;

        Player otherPlayer = (Player)other;
        return otherPlayer.name == this.name;
    }

    /**
     * implementation for hashCode
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
