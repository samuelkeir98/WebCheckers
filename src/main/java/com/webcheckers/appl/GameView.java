package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

/**
 * The homepage representation of a GameView
 */
public class GameView {

    private Game game;

    public GameView(Game game){
        this.game = game;
    }

    public Player getRedPlayer(){return game.getRedPlayer();}

    public Player getWhitePlayer(){return game.getWhitePlayer();}

    /**
     * gets a string representation of this game
     * @return above
     */
    public String display(){
        return getRedPlayer().getName()+" vs. "+ getWhitePlayer().getName();
    }



}
