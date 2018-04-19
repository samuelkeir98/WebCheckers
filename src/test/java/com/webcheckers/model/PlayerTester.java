package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.moves.Position;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("Model-tier")
public class PlayerTester {
    static final String REG_NAME_1 = "Apple";
    static final String REG_NAME_2 = "Banana";
    static final String LONG_NAME = "aaaaaaaaaaaaaaaaaaa";
    @Test
    public void initTest(){
        Player player = new Player(REG_NAME_1);
        assertEquals(REG_NAME_1,player.getName());
    }

    @Test
    public void equalsTest(){
        Player player1 = new Player(REG_NAME_1);
        Player player2 =  new Player(REG_NAME_1);
        Player player3 = new Player(REG_NAME_2);
        assertTrue(player1.equals(player2));
        assertFalse(player1.equals(player3));
    }

    @Test
    public void hashTest(){
        Player player1 = new Player(REG_NAME_1);
        Player player2 =  new Player(REG_NAME_1);
        Player player3 = new Player(REG_NAME_2);
        assertTrue(player1.hashCode() !=player3.hashCode());
        assertTrue(player1.hashCode() == player2.hashCode());
    }

    @Test
    public void longNameTest(){
        Player player = new Player(LONG_NAME);
        assertEquals(LONG_NAME.substring(0,Player.MAX_NAME_DISPLAY_LENGTH)+"...",player.getName());
    }
}
