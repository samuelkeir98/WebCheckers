package com.webcheckers.model;

import com.webcheckers.model.moves.Move;
import com.webcheckers.model.moves.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a WebCheckers game
 */
public class Game {

    /** Board game is played on */
    private Board board;

    /** Board of curent turn */
    private Board turnBoard;

    /** Players of the game */
    private Player whitePlayer,redPlayer;

    /** Tells if game is over or not */
    private boolean gameOver;

    /** Stack of moves to back up if necessary */
    private Stack<Move> lastPlayed;

    /** List of moves made in current turn to be submitted */
    private List<Move> movesMade;

    /** The end zone of the board for red pieces */
    private static int redEnd = 7;

    /** The end zne of  the board for white pieces */
    private static int whiteEnd = 0;

    /**
     * Initializes a game
     * @param redPlayer Player 1 in game
     * @param whitePlayer Player 2 in game
     */
    public Game(Player redPlayer,Player whitePlayer){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        gameOver = false;
        board = new Board();
        turnBoard = new Board();
        gameOver = false;
        lastPlayed = new Stack<>();
        movesMade = new ArrayList<>();
    }

    /**
     * @return The current player's color
     */
    public Color getTurn(){return board.whoseTurn();}

    /**
     * Stores moves in stack and list to submit or back up moves
     * @param move The move to be stored
     */
    public void makeMove(Move move){
        //get move with info
        Move actualMove = turnBoard.getMove(move);
        lastPlayed.push(actualMove);
        movesMade.add(actualMove);
        turnBoard.makeMove(actualMove);
    }

    /**
     * Turns a normal piece into a king piece
     */
    public void makeKing(Piece piece){
        if(piece.getColor() == Color.RED){
            if(piece.getPosition().getRow() == redEnd){
                piece.becomeKing();
            }
        }
        if(piece.getColor() == Color.WHITE){
            if(piece.getPosition().getRow() == whiteEnd){
                piece.becomeKing();
            }
        }
    }

    /**
     * @return True if game is over, false otherwise
     */
    public boolean isGameOver(){
        return gameOver || board.isGameOver();
    }

    /**
     * @return The game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets board of certain player
     * @param player Player to get board for
     * @return Player's board
     */
    public Board getBoard(Player player){
        return (player.equals(getCurPlayer()) ? turnBoard : board);
    }

    /**
     * Checks if a move is valid
     * @param move Move to check
     * @return True if move follows rules, false otherwise
     */
    public boolean isValidMove(Move move){
        return turnBoard.isValidMove(move);
    }

    /**
     * Generates all the legal move the current player can make
     */
    public void generateMoves() {
        turnBoard.addMoves();
    }

    /**
     * Makes all moves stored in list and switches turn
     */
    public void submitTurn(){
        board.submitTurn(movesMade);
        turnBoard.submitTurn();
        movesMade.clear();
        lastPlayed.clear();
    }

    /**
     * @return Winner of the game
     */
    public Player getWinner(){
        return board.getWinner();
    }

    /**
     * Backs up last move made
     */
    //TODO
    public void backUpMove(){}

    /**
     * @return If current turn is over
     */
    public boolean isTurnOver() {
        return turnBoard.isTurnOver();
    }

    /**
     * Tells if first move made for current player
     * @return If the move has been made
     */
    public boolean isMoveMade() {
        return board.isMoveMade();
    }

    /**
     * @return Player whose turn it is
     */
    public Player getCurPlayer(){
        return (board.whoseTurn() == Color.RED ? redPlayer : whitePlayer);
    }

    /**
     * @return Player 2 of game
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * @return Player 1 of game
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * Checks if player is in game
     * @param player Player to check for
     * @return True if player is in game, false otherwise
     */
    public boolean hasPlayer(Player player) {
        return player.equals(redPlayer) || player.equals(whitePlayer);
    }

}