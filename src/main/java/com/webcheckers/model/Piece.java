package com.webcheckers.model;

import com.webcheckers.model.moves.Direction;
import com.webcheckers.model.moves.KingDirection;
import com.webcheckers.model.moves.NormalDirection;
import com.webcheckers.model.moves.Position;

import java.util.Objects;

public class Piece {

	private static int pieceID = 0;
	public static final String RED_CHARACTER = "R";
	public static final String WHITE_CHARACTER = "W";

	private static final Object lock = new Object();
	private Direction directions;
	private Position position;
	private Color color;
	private int id;
	private Type type;
	public Piece(Position position,Color color){
		this.position = position;
		synchronized (lock) {
			this.id = pieceID++;
		}
		this.type = Type.SINGLE;
		this.color = color;
	}

	public void becomeKing(){
		this.type = Type.KING;
	}

	public Position getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

	public Type getType() {
		return type;
	}

	public void unbecomeKing(){ this.type = Type.SINGLE;}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Piece && id == ((Piece) obj).id;
	}

	Direction[] getDirections(){
		return (type == Type.SINGLE ? NormalDirection.values() : KingDirection.values());
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return (color == Color.RED ? RED_CHARACTER : WHITE_CHARACTER);
	}

	enum Type{
		SINGLE(),
		KING
	}
}