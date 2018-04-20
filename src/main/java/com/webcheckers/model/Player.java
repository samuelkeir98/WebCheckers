package com.webcheckers.model;

/**
 * Player model class
 * @author Anthony Massicci
 * Tested by Samuel Keir
 */
public class Player {
    public static final int MAX_NAME_DISPLAY_LENGTH = 15;
    private final String name;

    /**
     * Constructor
     * @param name name of player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Grabs name of player
     */
    public String getName() {
        return (this.name.length()<MAX_NAME_DISPLAY_LENGTH ? this.name : this.name.substring(0,MAX_NAME_DISPLAY_LENGTH)+"...");
    }

    /**
     * Equals method for value object semantics
     * @param other other object to compare this object to.
     * @return equivalency
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Player))
            return false;

        Player otherPlayer = (Player)other;
        return otherPlayer.name.equals(this.name);
    }

    /**
     * Implementation for hashCode
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
