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
            spaces.add(new Space(i,(i+index)%2==1));
        }
    }

    public void placePiece(Piece piece,int col){
        spaces.get(col).setPiece(piece);
    }

    public Piece getPieceAt(int index){
        return spaces.get(index).getPiece();
    }

    public boolean isValid(int index){
        return spaces.get(index).isValid();
    }

    public boolean isPieceAt(int index){
        return spaces.get(index).hasPiece();
    }

    /**
     * Checks if piece of certain color on space
     * @param index space to check
     * @param color color of piece to check
     * @return
     */
    public boolean isPieceAt(int index, Color color) {
        Space space = spaces.get(index);
        return (isPieceAt(index) && space.getPiece().getColor() == color);
    }

    public void removePiece(int index){
        spaces.get(index).removePiece();
    }


    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        String out = "";
        out += index;
        for(int i = 0; i<ROW_SIZE; i++){
            out+=spaces.get(i).toString();
        }
        return out;
    }
}