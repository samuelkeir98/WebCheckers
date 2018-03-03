package com.webcheckers.model;

import com.webcheckers.model.moves.Direction;
import com.webcheckers.model.moves.KingDirection;
import com.webcheckers.model.moves.NormalDirection;
import com.webcheckers.model.moves.Position;

import java.util.Objects;

public class Piece {

	private static int pieceID = 0;

	enum Type{
		NORMAL,
		KING
	}
	private Position position;
	private Color color;
	private int id;
	private Type type;


	public Piece(Position position,Color color){
		this.position = position;
		this.id = pieceID++;
		this.type = Type.NORMAL;
		this.color = color;
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

	Direction[] getDirections(){
		return (type == Type.NORMAL ? NormalDirection.values() : KingDirection.values());
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Piece){
			return id == ((Piece)obj).id;
		}
		return false;
	}
}