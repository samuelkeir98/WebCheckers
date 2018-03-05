package com.webcheckers.appl;

import com.webcheckers.model.Color;
import com.webcheckers.model.Row;
import com.webcheckers.model.Space;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * RowView class
 */
public class RowView implements Iterable<Space> {

	private List<Space> spaces;
	private Row row;

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

	@Override
	public Iterator<Space> iterator() {
		return spaces.iterator();
	}

	public int getIndex(){
		return row.getIndex();
	}
}
