package com.webcheckers.model;

import com.webcheckers.model.moves.Direction;
import com.webcheckers.model.moves.KingDirection;
import com.webcheckers.model.moves.NormalDirection;
import com.webcheckers.model.moves.Position;

import java.util.Objects;

public class Piece {

	/** Used to set unique piece IDs */
	private static int pieceID = 0;

	/** Textual representation of red piece */
	public static final String RED_CHARACTER = "R";

	/** Textual representation of white piece */
	public static final String WHITE_CHARACTER = "W";

	/** Lock to ensure pieces initialized on at a time */
	private static final Object lock = new Object();

	/** Directions a piece can move in */
	private Direction directions;

	/** Position of the piece */
	private Position position;

	/** Color of the piece */
	private Color color;

	/** Unique ID of the piece */
	private int id;

	/** Type of the piece */
	private Type type;

	/** Types a piece can be */
	enum Type{
		SINGLE(),
		KING
	}

	/**
	 * Initializes a piece
	 * @param position Position to set piece to
	 * @param color Color of piece
	 */
	public Piece(Position position,Color color){
		this.position = position;
		synchronized (lock) {
			this.id = pieceID++;
		}
		this.type = Type.SINGLE;
		this.color = color;
	}

	/**
	 * promotes a piece to a KING type
	 */
	public void becomeKing(){
		this.type = Type.KING;
	}

	/**
	 * @return the position of the piece
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @return the piece's color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return the type of move a piece is
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Reverts a king promotion when backing up move
	 */
	public void unbecomeKing(){ this.type = Type.SINGLE;}

	/**
	 * @return list of directions piece can move in
	 */
	Direction[] getDirections(){
		return (type == Type.SINGLE ? NormalDirection.values() : KingDirection.values());
	}

	/**
	 * Sets piece's position
	 * @param position position to set piece to
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Used for comparison of Pieces
	 * @param obj piece to compare to
	 * @return true if Piece IDs match, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Piece && id == ((Piece) obj).id;
	}

	/**
	 * Generates unique code for different piece objects
	 * @return hashcode of Piece
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * String representation of a Piece respective of color
	 * @return Piece's string representation
	 */
	@Override
	public String toString() {
		return (color == Color.RED ? RED_CHARACTER : WHITE_CHARACTER);
	}
}