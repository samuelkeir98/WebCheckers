package com.webcheckers.model;

import com.webcheckers.model.moves.Jump;
import com.webcheckers.model.moves.Move;
import com.webcheckers.model.moves.Position;
import com.webcheckers.model.moves.Step;

import java.util.*;

public class Board implements Iterable<Row> {

    public static final int NUM_ROWS = 8;

    private Color curTurn;
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
        this.curTurn = Color.RED;
        this.rows = new ArrayList<>(NUM_ROWS);
        for(int i = 0;i<NUM_ROWS;i++){
            rows.add(new Row(i));
        }

        for(int row = 0; row<3; row++) {
            for (int i = 0; i < Row.ROW_SIZE; i++) {
                if((i+row)%2 == 0) {
                    Piece piece = new Piece(new Position(row,i),Color.WHITE);
                    whitePieces.add(piece);
                    rows.get(row).placePiece(piece,i);
                }
            }
        }

        for(int row = NUM_ROWS-3; row<NUM_ROWS; row++) {
            for (int i = 0; i < Row.ROW_SIZE; i++) {
                if((i+row)%2 == 0) {
                    Piece piece = new Piece(new Position(row,i),Color.RED);
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

    Color whoseTurn(){return curTurn;}

    //TODO
    boolean canJump(Piece piece){return false;}

    Piece getPiece(Position position){
        return rows.get(position.getRow()).getPieceAt(position.getCol());
    }

    boolean isValid(Position position){
        return rows.get(position.getRow()).isValid(position.getCol());
    }

    //TODO
    boolean isValidMove(Move move){
        Position startPos = move.getStartPos();
        Position endPos = move.getEndPos();
        if(getPiece(startPos)==null || getPiece(startPos).getColor()!=curTurn || !isValid(endPos)){
            return false;
        }
        return true;
    }

    //TODO
    Set<Jump> getJumpMoves(){return null;}

    //TODO
    Set<Step> getStepMoves(){return null;}

    //TODO
    void undo(Move move){}

    public static void main(String[] args){
        Board board = new Board(new Player("joe"),new Player("jim"));
        System.out.println(board);

        Move move = new Move(new Position(5,1),new Position(4,2),Move.Type.OTHER);
        board.makeMove(move);
		System.out.println(board);
	}

    @Override
    public String toString() {
        String out = " ";
        for(int i = 0;i<Row.ROW_SIZE;i++){
            out+=i;
        }
        out+="\n";
        for(int i = 0; i<NUM_ROWS;i++) {
            out+=rows.get(i).toString() + "\n";
        }
        return out;
    }

    void makeMove(Move move){

        if(isValidMove(move)){
			Position startPos = move.getStartPos();
            Position endPos = move.getEndPos();

            Piece myPiece = getPiece(startPos);
            Row row = rows.get(startPos.getRow());
            row.removePiece(startPos.getCol());
            row = rows.get(endPos.getRow());
            row.placePiece(myPiece,endPos.getCol());
            if(curTurn == Color.RED){
                curTurn = Color.WHITE;
            } else{
                curTurn = Color.RED;
            }

        }

    }
}