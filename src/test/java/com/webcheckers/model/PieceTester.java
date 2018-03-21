package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import com.webcheckers.model.moves.*;

@Tag("Model-Tier")
public class PieceTester {

	/**
	 * Checks that the id system for pieces works in making each piece unique
	 */
	@Test
	public void idNonEquivalenceTest(){
		Piece p1 = new Piece(null,null);
		Piece p2 = new Piece(null,null);
		assertNotEquals(p1,p2);
		assertNotEquals(p1,new Object());
		assertNotEquals(p1.hashCode(),p2.hashCode());
	}

	/**
	 * Tests that the piece fields are properly initialized
	 */
	@Test
	public void fieldInitializationTest(){
		Position pos = mock(Position.class);
		Color color = Color.RED;
		Piece piece = new Piece(pos,color);

		assertEquals(piece.getPosition(),pos);
		assertEquals(piece.getColor(),color);
		assertEquals(piece.getType(), Piece.Type.SINGLE);

		pos = mock(Position.class);
		color = Color.WHITE;
		piece = new Piece(pos,color);

		assertEquals(piece.getPosition(),pos);
		assertEquals(piece.getColor(),color);
		assertEquals(piece.getType(), Piece.Type.SINGLE);
	}

	/**
	 * Tests setting the position of a piece
	 */
	@Test
	public void setPositionTest(){
		Piece piece = new Piece(null,null);
		Position dest = mock(Position.class);
		piece.setPosition(dest);
		assertEquals(piece.getPosition(),dest);
	}

	/**
	 * Tests that kinging a piece makes its type KING
	 */
	@Test
	public void kingPiece(){
		Piece piece = new Piece(null,null);
		piece.becomeKing();
		assertEquals(piece.getType(), Piece.Type.KING);
		piece.unbecomeKing();
		assertEquals(piece.getType(), Piece.Type.SINGLE);
	}

	/**
	 * Tests that a piece's string representations are appropriate to their color
	 */
	@Test
	public void stringTester(){
		Piece p1 = new Piece(null,Color.RED);
		Piece p2 = new Piece(null,Color.WHITE);

		assertEquals(p1.toString(),Piece.RED_CHARACTER);
		assertEquals(p2.toString(),Piece.WHITE_CHARACTER);
	}

	/**
	 * Tests that a piece's directions are what they should be based on the piece's type
	 */
	@Test
	public void directionTest(){
		Piece single = new Piece(null,null);
		assertArrayEquals(single.getDirections(),NormalDirection.values());
		Piece king = new Piece(null,null);
		king.becomeKing();
		assertArrayEquals(king.getDirections(),KingDirection.values());
	}
}
