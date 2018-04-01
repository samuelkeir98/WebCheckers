package com.webcheckers.model.moves;

import com.webcheckers.model.Board;

public class MoveUndo extends MoveAction {

    public MoveUndo(Move move, Board board) {
        super(move, board);
    }

    @Override
    public void execute() {
        Move undo = new Move(move.end, move.start);
        board.makeMove(undo);
    }
}
