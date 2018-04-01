package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.moves.Jump;
import com.webcheckers.model.moves.Move;
import com.webcheckers.model.moves.Position;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("Model-tier")
public class BoardTester {

	@Test
	public void initTest(){
		Board board = new Board();
		assertEquals(board.whoseTurn(),Color.RED);
		assertEquals(board.getOpponent(),Color.WHITE);
		for(int i = 0; i<Board.NUM_ROWS; i++){
			for(int j = 0; j<Row.ROW_SIZE; j++){
				Piece piece = board.getPiece(new Position(i,j));
				if(i<3) {
					if ((i + j) % 2 == 1) {
						assertNotNull(piece);
						assertEquals( Color.WHITE,piece.getColor());
					} else {
						assertNull(piece);
					}
				}else if(i<5){
					assertNull(piece);
				}else{
					if ((i + j) % 2 == 1) {
						assertNotNull(piece);
						assertEquals( Color.RED,piece.getColor());
					} else {
						assertNull(piece);
					}
				}
			}
		}
	}

	@Test
	public void stepMoveTest(){

		Move move1 = new Move(new Position(7,7),new Position(6,6));
		Move invalid = new Move(new Position(7,7),new Position(5,5));
		Board board = Board.emptyBoard();
		Piece piece = new Piece(new Position(7,7),Color.RED);
		board.placePiece(piece, new Position(7,7));
		assertEquals(piece,board.getPiece(new Position(7,7)));
		board.addMoves();
		assertFalse(board.isTurnOver());
		assertTrue(board.isValidMove(move1));
		assertFalse(board.isValidMove(invalid));
		Move actual = board.getMove(move1);
		board.makeMove(actual);
		assertNull(board.getPiece(new Position(7,7)));
		assertEquals(piece,board.getPiece(new Position(6,6)));
	}

	@Test
	public void kingStepMoveTest(){
		Board board = Board.emptyBoard();
		Position start = new Position(3,3);
		Piece king = new Piece(start,Color.RED);
		king.becomeKing();
		board.placePiece(king,start);
		board.addMoves();
		Position[] validEnds = {new Position(4,4),new Position(2,4),new Position(2,2),new Position(4,2)};
		Position[] invalidEnds = {new Position(5,5),new Position(1,1),new Position(1,5),new Position(5,1)};

		for(Position position:validEnds){
			Move move = new Move(start,position);
			assertTrue(board.isValidMove(move));
		}
		for(Position position:invalidEnds){
			Move move = new Move(start,position);
			assertFalse(board.isValidMove(move));
		}
	}

	@Test
	public void jumpMoveTest(){
		Board board = Board.emptyBoard();
		Position start = new Position(5,5);
		Position end = new Position(3,3);
		Position step = new Position(4,6);
		Position jumped = new Position(4,4);
		Piece piece = new Piece(start,Color.RED);
		Piece jumpee = new Piece(jumped,Color.WHITE);
		board.placePiece(piece,start);
		board.placePiece(jumpee,jumped);
		board.addMoves(piece);
		Move move = new Move(start,end);
		Move stepMove = new Move(start,step);
		Move onPieceMove = new Move(start,jumped);
		assertFalse(board.isValidMove(stepMove));
		assertFalse(board.isValidMove(onPieceMove));
		assertTrue(board.isValidMove(move));
		Move actual = board.getMove(move);
		assertEquals(jumpee,((Jump)actual).getJumped());
		board.makeMove(actual);
		assertEquals(piece,board.getPiece(end));
		assertNull(board.getPiece(jumped));
		assertNull(board.getPiece(start));
	}

	@Test
	public void regularSubmitTurn(){
		Board board = new Board();
		assertEquals(Color.RED,board.whoseTurn());
		board.submitTurn();
		assertEquals(Color.WHITE,board.whoseTurn());
	}

	@Test
	public void moveSubmitTurn() {
		Position start = new Position(7, 7);
		Position end1 = new Position(6, 6);
		Position end2 = new Position(5,5);
		Piece piece = new Piece(start,Color.RED);
		Board board = Board.emptyBoard();
		board.placePiece(piece,start);
		List<Move> moves = Arrays.asList(new Move(start,end1),new Move(end1,end2));
		board.submitTurn(moves);
		assertEquals(Color.WHITE,board.whoseTurn());
		assertEquals(piece,board.getPiece(end2));
		assertNull(board.getPiece(end1));
		assertNull(board.getPiece(start));

	}
}
