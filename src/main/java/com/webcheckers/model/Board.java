package com.webcheckers.model;

import com.webcheckers.model.moves.Move;
import com.webcheckers.model.moves.Position;

import java.util.*;

public class Board implements Iterable<Row> {

    public static final int NUM_ROWS = 8;

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

        for(int row = 0; row<3; row++) {
            for (int i = 0; i < Row.ROW_SIZE; i++) {
                if((i+row)%2 == 0) {
                    Piece piece = new Piece(new Position(row,i),Color.BLACK); //TODO include proper Piece constructor
                    whitePieces.add(piece);
                    rows.get(row).placePiece(piece,i);
                }
            }
        }

        for(int row = NUM_ROWS-3; row<NUM_ROWS; row++) {
            for (int i = 0; i < Row.ROW_SIZE; i++) {
                if((i+row)%2 == 0) {
                    Piece piece = new Piece(new Position(row,i),Color.RED); //TODO include proper Piece constructor
                    redPieces.add(piece);
                    rows.get(row).placePiece(piece,i);
                }
            }
        }
    }

    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }

    //TODO
    boolean isGameOver(){return false;}

    //TODO
    Player getWinner(){return null;}

    //TODO
    void undo(Move move){}

    //TODO
    void makeMove(Move move){}
}
