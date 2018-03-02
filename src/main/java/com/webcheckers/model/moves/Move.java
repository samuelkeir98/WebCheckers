package com.webcheckers.model.moves;

import com.webcheckers.model.Piece;

public abstract class Move {
    public enum Type{
        STEP(1),
        JUMP(2);

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

    protected Move(Piece piece, Direction direction, Type type){
        this(piece.getPosition(),
                new Position(piece.get))
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
