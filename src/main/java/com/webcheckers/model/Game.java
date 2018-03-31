package com.webcheckers.model;

import com.webcheckers.model.moves.Move;
import com.webcheckers.model.moves.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {

    private Board board;
    private Board turnBoard;
    private Player whitePlayer,redPlayer;
    private boolean gameOver;
    private Stack<Move> lastPlayed;
    private List<Move> movesMade;

    //TODO
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

    public Color getTurn(){return board.whoseTurn();}


    public void makeMove(Move move){
        if(turnBoard.isValidMove(move)){
            turnBoard.makeMove(move);
            movesMade.add(move);
            //add to undo stack
        }
    }

    public boolean isGameOver(){
        return gameOver || board.isGameOver();
    }

    public Board getBoard(Player player){
        return (player.equals(getCurPlayer()) ? turnBoard : board);
    }

    public boolean isValidMove(Move move){
        return turnBoard.isValidMove(move);
    }

    public void submitTurn(){
        lastPlayed.clear();
        board.submitTurn(movesMade);
        turnBoard.submitTurn();
        movesMade.clear();
    }

    public Player getWinner(){
        return board.getWinner();
    }

    //TODO
    public void undoMove(){}

    public Player getCurPlayer(){
        return (board.whoseTurn() == Color.RED ? redPlayer : whitePlayer);
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    public boolean hasPlayer(Player player) {
        return player.equals(redPlayer) || player.equals(whitePlayer);
    }

}