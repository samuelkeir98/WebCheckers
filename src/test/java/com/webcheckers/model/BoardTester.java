package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.moves.Move;
import com.webcheckers.model.moves.Position;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("Model-Tier")
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

}
