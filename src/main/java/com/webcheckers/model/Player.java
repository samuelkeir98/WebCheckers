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
}
