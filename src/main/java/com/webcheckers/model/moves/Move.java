package com.webcheckers.model.moves;

import com.webcheckers.model.Color;
import com.webcheckers.model.Piece;

import java.util.Objects;

/**
 * Value object representing one move
 * @author Adrian Postolache
 */
public class Move {

    Position start, end;
    Type type = Type.OTHER;
    public Move(Piece piece, Direction direction, Type type,Color color){
        this(piece.getPosition(),
                new Position(piece.getPosition().getRow() + direction.getRow() * type.getSpaces() * color.getMovementFactor(),
                        piece.getPosition().getCell() + direction.getCol() * type.getSpaces() * color.getMovementFactor()),
                type);
    }

    public Move(Position start, Position end, Type type){
        this.start = start;
        this.end = end;
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start.hashCode(), end.hashCode());
    }

    public Type getType() {
        return type;
    }

    public Position getStart() {
        return start;
    }

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

        Type(int spaces){
            this.spaces=spaces;
        }

        public int getSpaces() {
            return spaces;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Move){
            Move move = (Move) obj;
            return start.equals(move.start) && end.equals(move.end);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Start: "+ start +" EndPos: "+ end +" Type: "+ type.toString();
    }
}
