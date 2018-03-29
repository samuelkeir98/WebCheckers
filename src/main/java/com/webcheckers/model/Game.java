package com.webcheckers.model;

import com.webcheckers.model.moves.Move;
import com.webcheckers.model.moves.Position;

import java.util.Stack;

public class Game {

    private Board board;
    private Player whitePlayer,redPlayer;
    private boolean gameOver;
    private Stack<Move> lastPlayed;

    //TODO
    public Game(Player redPlayer,Player whitePlayer){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        board = new Board();
        gameOver = false;
        lastPlayed = new Stack<>();
    }

    public Color getTurn(){return board.whoseTurn();}

    public void makeMove(Move move){
        //if(board.isValidMove(move)){
            board.makeMove(move);
        //}
    }

    public void storeMove(Move move) {
        //if(board.isValidMove(move)) {
            lastPlayed.push(move);
        //}
    }

    public boolean isGameOver(){
        return board.isGameOver();
    }

    public Board getBoard(){
        return board;
    }

    public boolean isValidMove(Move move){
        return board.isValidMove(move);
    }

    public void generateMoves() {
        board.addMoves();
    }

    public void submitTurn(){
        while(!lastPlayed.empty()) {
            makeMove(lastPlayed.pop());
        }
        lastPlayed.clear();
        board.submitTurn();
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