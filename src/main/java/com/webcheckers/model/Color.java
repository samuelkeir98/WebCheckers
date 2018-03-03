package com.webcheckers.model;

public enum Color {
    RED(1),
    BLACK(-1);

    /**
     * used to allow the player on top to move down the board properly
     */
    private int movementFactor;

    Color(int movementFactor){
        this.movementFactor = movementFactor;
    }

    public int getMovementFactor() {
        return movementFactor;
    }
}
