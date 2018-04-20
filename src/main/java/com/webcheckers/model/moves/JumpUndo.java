package com.webcheckers.model.moves;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;

import java.util.Objects;

public class JumpUndo extends MoveAction {
    private Piece piece;

    public JumpUndo(Move move, Board board, Piece piece) {
        super(move, board);

        Objects.requireNonNull(piece);
        this.piece = piece;
    }
    @Override
    public void execute() {
        Move undo = new Move(move.end, move.start);
        board.makeMove(undo);
        board.placePiece(piece, piece.getPosition());
        board.resetTurnOver();
        piece.setPosition(move.start);
        board.addJumps(piece);
    }
}
