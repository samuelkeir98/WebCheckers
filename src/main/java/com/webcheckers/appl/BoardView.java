package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Color;
import com.webcheckers.model.Player;
import com.webcheckers.model.Row;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * BoardView class
 */
public class BoardView implements Iterable<RowView> {

	private List<RowView> rowViews;
	private Board board;

	public BoardView(Board board, Color player){
		rowViews = new ArrayList<>();
		this.board = board;
		for(Row row:board){
			if(player == Color.RED){
				rowViews.add(new RowView(row,player));
			}else{
				rowViews.add(0,new RowView(row,player));
			}
		}
	}

	@Override
	public Iterator<RowView> iterator() {
		return rowViews.iterator();
	}

	public Color whoseTurn(){
		return board.whoseTurn();
	}


}
