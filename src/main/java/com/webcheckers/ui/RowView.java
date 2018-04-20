package com.webcheckers.ui;

import com.webcheckers.model.Color;
import com.webcheckers.model.Row;
import com.webcheckers.model.Space;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * RowView class that renders view of row respective to player
 */
public class RowView implements Iterable<Space> {

	/** Spaces in the row */
	private List<Space> spaces;

	/** Row to render view */
	private Row row;

	/**
	 * Determines how a row is seen based on player color
	 * @param row row to update view
	 * @param player color to render view for
	 */
	public RowView(Row row,Color player){
		this.row = row;
		spaces = new ArrayList<>();
		for(Space space: row){
			if(player == Color.RED){
				spaces.add(space);
			}else{
				spaces.add(0,space);
			}
		}
	}

	/**
	 * @return index of row
	 */
	public int getIndex(){
		return row.getIndex();
	}

	/**
	 * @return iterator of space List
	 */
	@Override
	public Iterator<Space> iterator() {
		return spaces.iterator();
	}
}
