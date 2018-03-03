package com.webcheckers.model.moves;

import com.webcheckers.model.Color;
import com.webcheckers.model.Piece;

/**
 * Value object representing one move
 * @author Adrian Postolache
 */
public abstract class Move {

    /**
     * Represent whether a move is a one-tile step or two-tile jump.
     * Includes those values to keep the actual code generic.
     */
    public enum Type{
        STEP(1), //Regular, one-tile movement
        JUMP(2); //Attacking, two-tile movement

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

    Position startPos,endPos;
    Type type;

    protected Move(Piece piece, Direction direction, Type type,Color color){
        this(piece.getPosition(),
                new Position(piece.getPosition().getRow() + direction.getRow() * type.getSpaces() * color.getMovementFactor(),
                        piece.getPosition().getCol() + direction.getCol() * type.getSpaces() * color.getMovementFactor()),
                type);
    }

    protected Move(Position startPos,Position endPos, Type type){
        this.startPos = startPos;
        this.endPos = endPos;
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
