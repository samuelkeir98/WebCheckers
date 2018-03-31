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
    private Board turnBoard;

    /** Players of the game */
    private Player whitePlayer,redPlayer;

    /** Tells if game is over or not */
    private boolean gameOver;

    /** Stack of moves to back up if necessary */
    private Stack<Move> lastPlayed;

    /** List of moves made in current turn to be submitted */
    private List<Move> movesMade;

    /**
     * Initializes a game
     * @param redPlayer player 1 in game
     * @param whitePlayer player 2 in game
     */
    public Game(Player redPlayer,Player whitePlayer){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        gameOver = false;
        board = new Board();
        turnBoard =new Board();
        gameOver = false;
        lastPlayed = new Stack<>();
        movesMade = new ArrayList<>();
    }

    /**
     * @return the current player's color
     */
    public Color getTurn(){return board.whoseTurn();}


    public void makeMove(Move move){
        if(turnBoard.isValidMove(move)){
            turnBoard.makeMove(move);
            movesMade.add(move);
            //add to undo stack
        }
    }

    /**
     * Stores moves in stack and list to submit or back up moves
     * @param move
     */
    public void storeMove(Move move) {
        if(board.isValidMove(move)) {
            Move actualMove = board.getMove(move);
            lastPlayed.push(actualMove);
            movesMade.add(actualMove);
        }
    }

    /**
     * @return true if game is over, false otherwise
     */
    public boolean isGameOver(){
        return gameOver || board.isGameOver();
    }

    /**
     * @return the game board
     */
    public Board getBoard(){
        return board;

    public Board getBoard(Player player){
        return (player.equals(getCurPlayer()) ? turnBoard : board);
    }

    /**
     * Checks if a move is valid
     * @param move move to check
     * @return true if move follows rules, false otherwise
     */
    public boolean isValidMove(Move move){
        return turnBoard.isValidMove(move);
    }

    /**
     * Generates all the legal move the current player can make
     */
    public void generateMoves() {
        board.addMoves();
    }

    /**
     * Makes all moves stored in list and switches turn
     */
    public void submitTurn(){
        for(Move move : movesMade) {
            makeMove(move);
        }
        lastPlayed.clear();
        board.submitTurn(movesMade);
        turnBoard.submitTurn();
        movesMade.clear();
    }

    /**
     * @return winner of the game
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
     * @return player whose turn it is
     */
    public Player getCurPlayer(){
        return (board.whoseTurn() == Color.RED ? redPlayer : whitePlayer);
    }

    /**
     * @return player 2 of game
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * @return player 1 of game
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * Checks if player is in game
     * @param player player to check for
     * @return true if player is in game, false otherwise
     */
    public boolean hasPlayer(Player player) {
        return player.equals(redPlayer) || player.equals(whitePlayer);
    }

}