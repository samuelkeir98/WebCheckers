package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.moves.Position;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("Model-tier")
public class RowTester {
	public static final int INDEX_CONSTANT = 0;
	@Test
	public void initTest(){
		Row row = new Row(INDEX_CONSTANT);

		assertEquals(INDEX_CONSTANT,row.getIndex());
		for(int i = INDEX_CONSTANT;i<Row.ROW_SIZE;i++){
			assertFalse(row.isPieceAt(i));
			if((i+INDEX_CONSTANT)%2 == 1){
				assertTrue(row.isValid(i));
			}else{
				assertFalse(row.isValid(i));
			}
			assertNull(row.getPieceAt(i));
		}
	}

	@Test
	public void placePieceTest(){
		Row row = new Row(INDEX_CONSTANT+1);
		Piece piece = mock(Piece.class);
		row.placePiece(piece,INDEX_CONSTANT);
		assertTrue(row.isPieceAt(INDEX_CONSTANT));
		assertFalse(row.isValid(INDEX_CONSTANT));
		assertEquals(piece,row.getPieceAt(INDEX_CONSTANT));
		row.removePiece(INDEX_CONSTANT);
		assertFalse(row.isPieceAt(INDEX_CONSTANT));
		assertNull(row.getPieceAt(INDEX_CONSTANT));
	}

	@Test
	public void colorPieceTest(){
		Row row = new Row(INDEX_CONSTANT+1);
		Piece piece = new Piece(mock(Position.class),Color.RED);
		row.placePiece(piece,INDEX_CONSTANT);
		assertTrue(row.isPieceAt(INDEX_CONSTANT,Color.RED));
		assertFalse(row.isPieceAt(INDEX_CONSTANT,Color.WHITE));
	}






}
