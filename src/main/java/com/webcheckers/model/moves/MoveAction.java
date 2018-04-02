package com.webcheckers.model.moves;

import com.webcheckers.model.Board;

import java.util.Objects;

/**
 * Abstract class for Undo command classes
 * @author: Anthony Massicci
 */
public abstract class MoveAction {

    // class fields
    protected Move move;
    protected Board board;

    public MoveAction(Move move, Board board) {
        Objects.requireNonNull(move);
        Objects.requireNonNull(board);

        this.move = move;
        this.board = board;
    }

    public abstract void execute();
}
