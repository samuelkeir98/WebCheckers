package com.webcheckers.model;

import com.webcheckers.model.moves.Move;
import com.webcheckers.model.moves.Position;

import java.util.Stack;

public class Game {
    /**
     * Matrices used to translate relative coordinates (like those of the player who sees the board rotated 180 degrees)
     * to actual coordinates that the board understands
     */
    private static final Position[][] redPlayMatrix = new Position[Board.NUM_ROWS][Row.ROW_SIZE];
    private static final Position[][] blackPlayMatrix = new Position[Board.NUM_ROWS][Row.ROW_SIZE];
    static {
        for (int row = 0; row < Board.NUM_ROWS; row++) {
            for (int col = 0; col < Row.ROW_SIZE; col++) {
                Position position = new Position(row, col);
                redPlayMatrix[row][col] = position;
                blackPlayMatrix[Board.NUM_ROWS - row - 1][Row.ROW_SIZE - col - 1] = position;
            }
        }
    }

    private Board board;
    private Player whitePlayer,redPlayer;
    private boolean gameOver;
    private Stack<Move> lastPlayed;

    //TODO
    public Game(Player redPlayer,Player whitePlayer){}


}