package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Color;
import com.webcheckers.model.Row;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardView implements Iterable<RowView> {

	private List<RowView> rowViews;

	public BoardView(Board board, Color player){
		rowViews = new ArrayList<>();
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
}
