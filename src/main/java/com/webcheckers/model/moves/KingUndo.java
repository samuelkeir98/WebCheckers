package com.webcheckers.model.moves;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;

import java.util.Objects;

public class KingUndo extends MoveAction {
    private Piece piece;

    public KingUndo(Move move, Board board, Piece piece) {
        super(move, board);

        Objects.requireNonNull(piece);
        this.piece = piece;
    }

    @Override
    public void execute() {
        Move undo = new Move(move.end, move.start);
        piece.unbecomeKing();
        board.makeMove(undo);
        board.resetTurnOver();
        board.addMoves(piece);
    }
}
