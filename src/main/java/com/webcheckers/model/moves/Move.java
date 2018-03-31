package com.webcheckers.model.moves;

import com.webcheckers.model.Color;
import com.webcheckers.model.Piece;

import java.io.Serializable;
import java.util.Objects;

/**
 * Value object representing one move
 * @author Adrian Postolache
 * Tested by Sam Keir
 */
public class Move implements Serializable{

    Position start, end;
    Type type;

    /**
     * Represents a piece moving.
     * @param piece The piece to move.
     * @param direction The direction on the board a piece is moving.
     * @param type The type of piece(Single or KIng).
     * @param color The player color.
     */
    public Move(Piece piece, Direction direction, Type type,Color color){
        this(piece.getPosition(),
                new Position(piece.getPosition().getRow() + direction.getRow() * type.getSpaces() * color.getMovementFactor(),
                        piece.getPosition().getCell() + direction.getCol() * type.getSpaces() * color.getMovementFactor()),
                type);
    }

    /**
     * Represents a piece moving.
     * @param start Where the Piece is located before it is moved.
     * @param end Where the Piece should be moved to.
     * @param type The type of piece(Single or KIng).
     */
    public Move(Position start, Position end, Type type){
        this.start = start;
        this.end = end;
        this.type = type;
    }

    /**
     * Represent a piece moving.
     * @param start The pieces starting position.
     * @param end   The pieces ending position.
     */
    public Move(Position start,Position end){
        this(start,end,Type.OTHER);
    }

    /**
     * Hashes based on the start and end position.
     * @return The hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(start.hashCode(), end.hashCode());
    }

    /**
     * Returns the type of the piece.
     * @return The type of the piece.
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns the starting position.
     * @return The starting position.
     */
    public Position getStart() {
        return start;
    }

    /**
     * Returns the ending position.
     * @return The ending position.
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Represent whether a move is a one-tile step or two-tile jump.
     * Includes those values to keep the actual code generic.
     */
    public enum Type{
        STEP(1), //Regular, one-tile movement
        JUMP(2), //Attacking, two-tile movement
        OTHER(0); //Used for player-input moves that may or may not be valid
        /**
         * How many spaces this type of move will take.
         */
        private int spaces;

        /**
         * Sets how many spaces a move will take.
         * @param spaces The amount of spaces a move will take
         */
        Type(int spaces){
            this.spaces=spaces;
        }

        /**
         * Gets the number of spaces.
         * @return The number of spaces.
         */
        public int getSpaces() {
            return spaces;
        }
    }

    /**
     * Sees if two moves are the same
     * @param obj The object we are comparing to
     * @return True if equal false if not equal.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Move){
            Move move = (Move) obj;
            return start.equals(move.start) && end.equals(move.end);
        }
        return false;
    }

    /**
     * Converts a move to string form
     * @return The string representation of a move
     */
    @Override
    public String toString() {
        return "Start: "+ start +" EndPos: "+ end ;
    }
}
