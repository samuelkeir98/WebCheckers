package com.webcheckers.model;

import java.util.*;

public class Board implements Iterable<Row> {

    private static final int NUM_ROWS = 8;

    private Player curPlayer;
    private Player redPlayer;
    private Set<Piece> redPieces;
    private Player whitePlayer;
    private Set<Piece> whitePieces;
    private List<Row> rows;

    public Board(Player redPlayer, Player whitePlayer){
        this.redPlayer = redPlayer;
        this.redPieces = new HashSet<>();
        this.whitePlayer = whitePlayer;
        this.whitePieces = new HashSet<>();
        this.curPlayer = redPlayer;
        this.rows = new ArrayList<>(NUM_ROWS);
        for(int i = 0;i<NUM_ROWS;i++){
            rows.add(new Row());
        }


    }

    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }
}
