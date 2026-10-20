
package Testing;
import Code.cardDeck;
import Code.cardGame;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.After;

public class cardGameTest{
    @Test
    public void testCardGameExists(){
        cardGame game = new cardGame();

        assertNotNull(game);
    }


    @Test
    public void correctNumberOfPlayerObjects(){

        cardGame game = new cardGame();
        int numberOfPlayers = 4;

        assertEquals("player deck",(game.createListOfPlayers(4)).length,numberOfPlayers);

    }

    @Test
    public void correctNumberOfDeckObjects(){

        cardGame game = new cardGame();
        int numberOfPlayers = 4;
        List<cardDeck> listOfDecksSyncList =
                Collections.synchronizedList(new ArrayList<cardDeck>());

        assertEquals("player deck",(game.createListOfDecks(4,listOfDecksSyncList)).length,numberOfPlayers);

    }
}

