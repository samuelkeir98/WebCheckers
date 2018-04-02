package com.webcheckers.model;

import com.webcheckers.model.moves.*;

import java.util.*;

/**
 * Model object that holds and moves the pieces around, also managing if the game's over or not
 * @author: Adrian Postolache
 */
public class Board implements Iterable<Row> {

    /** Amount of rows in a board */
    public static final int NUM_ROWS = 8;

    /** The color of the player whose turn it is */
    private Color curTurn;

    /** Tells if turn is over */
    private boolean turnOver = false;

    /** All of the red player's pieces */
    private Set<Piece> redPieces;

    /** All of whitey's pieces */
    private Set<Piece> whitePieces;

    /** The collection of Rows */
    private List<Row> rows;

    /** Set of available moves */
    private Map<Move, Move> moves;

    /**
     * Builds a board in the starting configuration for Checkers
     */
    public Board(){
        this.redPieces = new HashSet<>();
        this.whitePieces = new HashSet<>();
        this.curTurn = Color.RED;
        this.rows = new ArrayList<>(NUM_ROWS);
        this.moves = new HashMap<>();
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

    public static Board emptyBoard(){
        Board board = new Board();
        for(int i = 0; i<NUM_ROWS;i++){
            for(int j = 0;j<Row.ROW_SIZE;j++){
            	board.rows.get(i).removePiece(j);
            }
        }

        board.redPieces.clear();
        board.whitePieces.clear();
        return board;
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
        return row >= 0 && row < NUM_ROWS && col >= 0 && col < NUM_ROWS;
    }

    /**
     * Finds and adds possible jump moves of a pieces to map
     */
    public void addJumps(Piece piece) {

        Position pos = piece.getPosition();
        Direction[] directions = piece.getDirections();
        for (Direction dir : directions) {
            int rowNum = pos.getRow() + dir.getRow() * curTurn.getMovementFactor();
            int col = pos.getCell() + dir.getCol() * curTurn.getMovementFactor();
            Row row = inBounds(rowNum, col) ? rows.get(rowNum) : null;

            //checks next row and column in directions of piece for an opponent piece
            if (row != null && row.isPieceAt(col, getOpponent())) {
                int rowNum2 = rowNum + dir.getRow() * curTurn.getMovementFactor();
                int col2 = col + dir.getCol() * curTurn.getMovementFactor();
                Row nextRow = inBounds(rowNum2, col2) ? rows.get(rowNum2) : null;
                Piece pieceTaken = row.getPieceAt(col);

                if (nextRow != null && !nextRow.isPieceAt(col2)) {
                    Jump jump = new Jump(piece, dir, curTurn, pieceTaken);
                    moves.put(jump, jump);
                }
            }
        }
    }

    /**
     * Finds and adds possible steps of a pieces to map
     */
    public void addSteps(Piece piece) {

        Position pos = piece.getPosition();
        Direction[] directions = piece.getDirections();
        for (Direction dir : directions) {
            int rowNum = pos.getRow() + dir.getRow() * curTurn.getMovementFactor();
            int col = pos.getCell() + dir.getCol() * curTurn.getMovementFactor();
            Row row = inBounds(rowNum, col) ? rows.get(rowNum) : null;

            if (row != null && !row.isPieceAt(col)) {
                Move step = new Step(piece, dir, curTurn);
                moves.put(step, step);
            }
        }
    }

    /**
     * Adds all valid moves of all pieces to map
     */
    public void addMoves() {

        Iterator iter = curTurn == Color.RED ? redPieces.iterator() : whitePieces.iterator();
        while(iter.hasNext()) {
            Piece piece = (Piece) iter.next();
            addJumps(piece);
        }

        if(moves.isEmpty()) {
            Iterator iter2 = curTurn == Color.RED ? redPieces.iterator() : whitePieces.iterator();
            while(iter2.hasNext()) {
                Piece piece = (Piece) iter2.next();
                addSteps(piece);
            }
        }

        if(moves.isEmpty()) {
            turnOver = true;
        }
    }

    /**
     * Adds moves for one piece
     * @param piece piece to get moves for
     */
    public void addMoves(Piece piece) {
        addJumps(piece);
        if(moves.isEmpty()) {
            turnOver = true;
        }
    }

    /**
     * Gets move with information using move from JSON
     * @param move move with only start and end from JSON
     * @return move with information
     */
    public Move getMove(Move move) {
        return moves.get(move);
    }

    /**
     * Gets the piece in the passed position
     * @param position the position to look at
     * @return the piece at that position, null if none
     */
    Piece getPiece(Position position){
        return rows.get(position.getRow()).getPieceAt(position.getCell());
    }

    public void placePiece(Piece piece,Position position){
    	if(piece!=null) {
			Set<Piece> set = (piece.getColor() == Color.RED ? redPieces : whitePieces);
			set.add(piece);
		}
        rows.get(position.getRow()).placePiece(piece,position.getCell());
    }

    /**
     * Checks if a move is legal to make in the current state of the board
     * @param move the move being tested
     * @return whether it can be made
     */
    boolean isValidMove(Move move){
        return moves.containsKey(move);
    }

    /**
     * Handles a step or jump move on board
     * @param move move to be made
     */
    public MoveAction makeMove(Move move){
        Position startPos = move.getStart();
        Position endPos = move.getEnd();

        Piece myPiece = getPiece(startPos);
        Row row = rows.get(startPos.getRow());
        row.removePiece(startPos.getCell());
        row = rows.get(endPos.getRow());

        myPiece.setPosition(endPos);
        row.placePiece(myPiece,endPos.getCell());

        moves.clear();
        if(move.getType() == Move.Type.JUMP) {
            Piece pieceTaken = ((Jump) move).getJumped();
            Row takenRow = rows.get(pieceTaken.getPosition().getRow());
            takenRow.removePiece(pieceTaken.getPosition().getCell());
            if(curTurn == Color.RED)
                whitePieces.remove(pieceTaken);
            else
                redPieces.remove(pieceTaken);

            return new JumpUndo(move, this, pieceTaken);
        } else {
            turnOver = true;
        }
        return new MoveUndo(move, this);
    }

    /**
     * @return if turn is over
     */
    public boolean isTurnOver() {
        return turnOver;
    }
    public void resetTurnOver() { turnOver = false; }

    /**
     * Switches turns and clears moves
     */
    public void submitTurn(){
        this.curTurn = (curTurn == Color.RED ? Color.WHITE: Color.RED);
        this.moves.clear();
        turnOver = false;
    }

    /**
     * Makes moves to board and submits turn
     * @param moves moves to make to board
     */
    public void submitTurn(List<Move> moves){
        for(Move move: moves){
            makeMove(move);
        }
        submitTurn();
    }

    /**
     * @return The iterator containing Rows
     */
    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }

}