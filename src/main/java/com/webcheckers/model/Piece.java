package com.webcheckers.model;

import com.webcheckers.model.moves.Position;

public class Piece {
	enum Type{
		NORMAL,
		KING
	}
	private Position position;

	public Piece(int row, int col){
		position = new Position(row,col);
		//TODO Add team color functionality
	}

	public Position getPosition() {
		return position;
	}
}
