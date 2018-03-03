package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Row implements Iterable<Space>{
    public static final int ROW_SIZE = 8;

    private List<Space> spaces;
    private int index;

    public Row(int index){
        this.index = index;
        spaces = new ArrayList<>(ROW_SIZE);
        for(int i = 0; i<ROW_SIZE; i++){
            spaces.add(new Space());
        }
    }

    public void placePiece(Piece piece,int col){
        spaces.get(col).setPiece(piece);
    }

    public Piece getPieceAt(int index){
        return spaces.get(index).getPiece();
    }

    public boolean isPieceAt(int index){
        return spaces.get(index).hasPiece();
    }


    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }

    public int getIndex() {
        return index;
    }
}
