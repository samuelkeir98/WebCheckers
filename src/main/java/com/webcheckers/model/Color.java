package com.webcheckers.model;

/** Represents color of player's pieces */
public enum Color {
    RED(1),
    WHITE(-1);

    /** used to allow the player on top to move down the board properly */
    private int movementFactor;

    /**
     * Creates a color with movement factor of color
     * @param movementFactor factor to correct a piece's movement
     */
    Color(int movementFactor){
        this.movementFactor = movementFactor;
    }

    /**
     * @return movement factor of color
     */
    public int getMovementFactor() {
        return movementFactor;
    }
}