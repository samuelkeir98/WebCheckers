package com.webcheckers.model;

import com.webcheckers.model.moves.*;

import java.util.*;

/**
 * Model object that holds and moves the pieces around, also managing if the game's over or not
 * @author: Adrian Postolache
 */
public class Board implements Iterable<Row> {

    /**
     * Amount of rows in a board
     */
    public static final int NUM_ROWS = 8;

    /**
     * The color of the player whose turn it is
     */
    private Color curTurn;
    /**
     * All of the red player's pieces
     */
    private Set<Piece> redPieces;
    /**
     * All of whitey's pieces
     */
    private Set<Piece> whitePieces;
    /**
     * The collection of Rows
     */
    private List<Row> rows;

    /**
     * Set of available moves
     */
    private Set<Move> moves;

    /**
     * Builds a board in the starting configuration for Checkers
     */
    public Board(){
        this.redPieces = new HashSet<>();
        this.whitePieces = new HashSet<>();
        this.curTurn = Color.RED;
        this.rows = new ArrayList<>(NUM_ROWS);
        this.moves = new HashSet<>();
        for(int i = 0;i<NUM_ROWS;i++){
            rows.add(new Row(i));
        }

        for(int row = 0; row<3; row++) {
            for (int i = 0; i < Row.ROW_SIZE; i++) {
                if((i+row)%2 == 1) {
                    Piece piece = new Piece(new Position(row,i),Color.WHITE);
                    whitePieces.add(piece);
                    rows.get(row).placePiece(piece,i);
                }
            }
        }

        for(int row = NUM_ROWS-3; row<NUM_ROWS; row++) {
            for (int i = 0; i < Row.ROW_SIZE; i++) {
                if((i+row)%2 == 1) {
                    Piece piece = new Piece(new Position(row,i),Color.RED);
                    redPieces.add(piece);
                    rows.get(row).placePiece(piece,i);
                }
            }
        }
    }

    /**
     * @return The iterator containing Rows
     */
    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }

    /**
     * Tells whether or not the game is over
     * @return
     */
    //TODO
    boolean isGameOver(){return false;}

    /**
     * Gets the winner of this board
     * @return The Player who is the winner, or null if game isn't over.
     */
    //TODO
    Player getWinner(){return null;}

    /**
     * @return the color of the player whose turn it is
     */
    public Color whoseTurn(){return curTurn;}

    /**
     * @return color of opponent
     */
    public Color getOpponent() {
        return curTurn == Color.RED ? Color.WHITE : Color.RED;
    }

    /**
     * Helper method to check if dimension of space is in bounds of board
     * @param row row to check
     * @param col column to check
     * @return true if row and column are within board's dimensions, false otherwise
     */
    public boolean inBounds(int row, int col) {
        return row > 0 && row < NUM_ROWS && col > 0 && col < NUM_ROWS;
    }

    /**
     * Finds and adds possible jump moves of a piece to set
     * @param piece piece to find jump moves for
     */
    public void addJumps(Piece piece) {
        Position pos = piece.getPosition();
        Direction[] directions = piece.getDirections();
        for(Direction dir : directions) {
            int rowNum = pos.getRow() + dir.getRow() * curTurn.getMovementFactor();
            int col = pos.getCell() + dir.getCol() * curTurn.getMovementFactor();
            Row row = inBounds(rowNum, col) ? rows.get(rowNum) : null;

            //checks next row and column in directions of piece for an opponent piece
            if(row != null && row.isPieceAt(col, getOpponent())) {
                int rowNum2 = rowNum + dir.getRow() * curTurn.getMovementFactor();
                int col2 = col + dir.getCol() * curTurn.getMovementFactor();
                Row nextRow = inBounds(rowNum2, col2) ? rows.get(rowNum2) : null;
                Piece pieceTaken =  row.getPieceAt(col);


                if(nextRow != null && !nextRow.isPieceAt(col2)) {
                    Move jump = new Jump(piece, dir, curTurn, pieceTaken);
                    moves.add(jump);
                }
            }
        }
    }

    /**
     * Finds and adds possible steps of a piece to set
     * @param piece piece to check available steps
     */
    public void addSteps(Piece piece) {
        Position pos = piece.getPosition();
        Direction[] directions = piece.getDirections();
        for(Direction dir : directions) {
            int rowNum = pos.getRow() + dir.getRow() * curTurn.getMovementFactor();
            int col = pos.getCell() + dir.getCol() * curTurn.getMovementFactor();
            Row row = inBounds(rowNum, col) ? rows.get(rowNum) : null;

            if (row != null && !row.isPieceAt(col)) {
                Move step = new Step(piece, dir, curTurn);
                moves.add(step);
                System.out.println(step);
            }
        }
    }

    /**
     * Adds all valid moves of all pieces to set
     */
    public void addMoves() {
        Iterator iter = curTurn == Color.RED ? redPieces.iterator() : whitePieces.iterator();
        while(iter.hasNext()) {
            this.addJumps((Piece) iter.next());
        }

        //only add steps if no jumps are possible
        if(moves.isEmpty()) {
            Iterator iter2 = curTurn == Color.RED ? redPieces.iterator() : whitePieces.iterator();
            while(iter2.hasNext()) {
                this.addSteps((Piece) iter2.next());
            }
            System.out.println();
        }

    }

    /**
     * Gets the piece in the passed position
     * @param position the position to look at
     * @return the piece at that position, null if none
     */
    Piece getPiece(Position position){
        return rows.get(position.getRow()).getPieceAt(position.getCell());
    }

    /**
     * Tells whether or not the specified position is a valid place for a piece to move
     * @param position position to check
     * @return whether or not a piece can move there
     */
    boolean isValid(Position position){
        return rows.get(position.getRow()).isValid(position.getCell());
    }

    /**
     * Checks if a move is legal to make in the current state of the board
     * @param move the move being tested
     * @return whether it can be made
     */
    boolean isValidMove(Move move){
        return moves.contains(move);
    }
    /**
     * Reverts a move
     * Expects that the move is the last one made
     * Will deny impossible moves
     * @param move move to undo
     */
    //TODO
    void undo(Move move){}

    /**
     * Represents the board in an easy to read format
     * @return formatted board string
     */
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

    public void makeMove(Move move){

        if(isValidMove(move)){
			Position startPos = move.getStart();
            Position endPos = move.getEnd();

            Piece myPiece = getPiece(startPos);
            Row row = rows.get(startPos.getRow());
            row.removePiece(startPos.getCell());
            row = rows.get(endPos.getRow());
            row.placePiece(myPiece,endPos.getCell());

        }

    }
    public void submitTurn(){
        this.curTurn = (curTurn == Color.RED ? Color.WHITE: Color.RED);
        System.out.println(moves);
        this.moves.removeAll(moves);
    }
}