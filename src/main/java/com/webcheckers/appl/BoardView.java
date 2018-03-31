package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Color;
import com.webcheckers.model.Player;
import com.webcheckers.model.Row;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * BoardView class that renders how board is viewed
 */
public class BoardView implements Iterable<RowView> {

	/** list of rowViews to display */
	private List<RowView> rowViews;

	/** checkers game board */
	private Board board;

	/**
	 * Determines view of the board respective to player color
	 * @param board board to view
	 * @param player player to view board as
	 */
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

	/**
	 * checks whose turn it is
	 * @return the color of the current player's turn
	 */
	public Color whoseTurn(){
		return board.whoseTurn();
	}

	/**
	 * @return iterator of rowViews List
	 */
	@Override
	public Iterator<RowView> iterator() {
		return rowViews.iterator();
	}

}
