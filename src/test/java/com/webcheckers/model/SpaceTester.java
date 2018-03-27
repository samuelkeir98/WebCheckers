package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("Model-Tier")
public class SpaceTester {
	private static final String TEST_STRING = "???";
	private static final String EMPTY_SPACE = " ";

	@Test
	public void initTest(){
		Space space = new Space(0,false);
		assertEquals(space.getCellIdx(),0);
		assertEquals(space.isValid(),false);
		Space space2 = new Space(7,true);
		assertEquals(space2.getCellIdx(),7);
		assertEquals(space2.isValid(),true);
	}

	@Test
	public void piecePlaceValidTest(){
		Space space = new Space(0,true);
		Piece piece = mock(Piece.class);
		assertEquals(space.isValid(),true);
		assertNull(space.getPiece());
		space.setPiece(piece);
		assertEquals(space.getPiece(),piece);
		assertEquals(space.isValid(),false);
		space.removePiece();
		assertNull(space.getPiece());
		assertEquals(space.isValid(),true);
	}

	@Test
	public void toStringTest(){
		Space space = new Space(0,true);
		assertEquals(EMPTY_SPACE,space.toString());
		Piece piece = mock(Piece.class);
		when(piece.toString()).thenReturn(TEST_STRING);
		space.setPiece(piece);
		assertEquals(TEST_STRING,space.toString());
		space.removePiece();
		System.out.println(space.getPiece());
		assertEquals(EMPTY_SPACE,space.toString());

	}
}
