package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

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

}
