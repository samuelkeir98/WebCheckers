package com.webcheckers.model;

import java.util.Iterator;
import java.util.List;

public class Row implements Iterable<Space>{
    public static final int ROW_SIZE = 8;

    private List<Space> spaces;

    private int index;

    public Row(int index) {
        this.index = index;
    }

    //TODO Use Position object after pull
    public void placePiece(Piece piece,int row,int col){}

    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }
}
