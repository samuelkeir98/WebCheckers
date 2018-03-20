package com.webcheckers.model;

import com.webcheckers.model.moves.Jump;
import com.webcheckers.model.moves.Move;
import com.webcheckers.model.moves.Position;
import com.webcheckers.model.moves.Step;
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
     * The red player
     */
    private Player redPlayer;
    /**
     * All of the red player's pieces
     */
    private Set<Piece> redPieces;
    /**
     * The white Player
     */
    private Player whitePlayer;
    /**
     * All of whitey's pieces
     */
    private Set<Piece> whitePieces;
    /**
     * The collection of Rows
     */
    private List<Row> rows;

    /**
     * Builds a board in the starting configuration for Checkers
     * @param redPlayer Player 1
     * @param whitePlayer Player 2
     */
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
     * Get the player corresponding to the passed color
     * @param color color of the player
     * @return the corresponding player
     */
    public Player getPlayer(Color color){
        return (color == Color.RED ? redPlayer : whitePlayer);
    }


    /**
     * Can this piece make any jump moves
     * @param piece The iece to check
     * @return whether it can jump or not
     */
    //TODO
    boolean canJump(Piece piece){return false;}

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
        Position startPos = move.getStart();
        Position endPos = move.getEnd();
        if(getPiece(startPos)==null || getPiece(startPos).getColor()!=curTurn || !isValid(endPos)){
            return false;
        }
        return true;
    }


    /**
     * does a brief instantiation and move test
     * @param args
     */
    public static void main(String[] args){
        Board board = new Board(new Player("joe"),new Player("jim"));
        System.out.println(board);

        Move move = new Move(new Position(5,1),new Position(4,2),Move.Type.OTHER);
        board.makeMove(move);
		System.out.println(board);
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
		Position startPos = move.getStart();
        Position endPos = move.getEnd();

        Piece myPiece = getPiece(startPos);
        Row row = rows.get(startPos.getRow());
        row.removePiece(startPos.getCell());
        row = rows.get(endPos.getRow());
        row.placePiece(myPiece,endPos.getCell());
    }

    public void submitTurn(){
        this.curTurn = (curTurn == Color.RED ? Color.WHITE: Color.RED);
    }
}