package com.webcheckers.model;

import com.webcheckers.model.moves.Move;
import com.webcheckers.model.moves.MoveAction;
import com.webcheckers.model.moves.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    private Stack<MoveAction> lastPlayed;

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
        turnBoard = new Board();
        lastPlayed = new Stack<>();
        movesMade = new ArrayList<>();
        turnBoard.addMoves();
    }

    /**
     * @return the current player's color
     */
    public Color getTurn(){return board.whoseTurn();}

    /**
     * Stores moves in stack and list to submit or back up moves
     * @param move
     */
    public void makeMove(Move move){
        //get move with info
        Move actualMove = turnBoard.getMove(move);
        //lastPlayed.push(actualMove);
        movesMade.add(actualMove);
        MoveAction action = turnBoard.makeMove(actualMove);
        lastPlayed.push(action);

        if(actualMove.getType() == Move.Type.JUMP) {
            Piece piece = turnBoard.getPiece(move.getEnd());
            turnBoard.addMoves(piece);
        }
    }

    /**
     * @return true if game is over, false otherwise
     */
    public boolean isGameOver(){
        return gameOver;
    }

    /**
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets board of certain player
     * @param player player to get board for
     * @return player's board
     */
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
     * Makes all moves stored in list and switches turn
     */
    public void submitTurn(){
        board.submitTurn(movesMade);
        turnBoard.submitTurn();
        movesMade.clear();
        lastPlayed.clear();
        turnBoard.addMoves();
        if(turnBoard.isGameOver()) {
            gameOver = true;
        }
    }

    /**
     * @return winner of the game
     */
    public Player getWinner(){
        return turnBoard.getWinner() == Color.RED ? redPlayer : whitePlayer;
    }

    /**
     * Backs up last move made
     */
    public boolean backUpMove() {
        if (lastPlayed.empty())
            return false;

        MoveAction action = lastPlayed.pop();
        action.execute();
        movesMade.remove(movesMade.size() - 1);
        return true;
    }

    /**
     * @return if current turn is over
     */
    public boolean isTurnOver() {
        return turnBoard.isTurnOver();
    }

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

    /**
     * set of available moves from board object
     * @return set of available moves
     */
    public Set<Move> availableMoves() {
        return turnBoard.getAvailableMoves();
    }

    /**
     * Ends a game
     */
    public void endGame() {
        gameOver = true;
    }

}