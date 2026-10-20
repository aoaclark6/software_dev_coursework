package Code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class player {
    card[] playerCards = new card[4];
    List<card> playerCardsSyncList =
            Collections.synchronizedList(new ArrayList<card>());
    int playerIndex;
    public void setPlayerCards(card[] inputtedPlayerCards){
        this.playerCards = inputtedPlayerCards;
        System.out.println(inputtedPlayerCards);
        this.playerCardsSyncList.add(inputtedPlayerCards[0]);
        this.playerCardsSyncList.add(inputtedPlayerCards[1]);
        this.playerCardsSyncList.add(inputtedPlayerCards[2]);
        this.playerCardsSyncList.add(inputtedPlayerCards[3]);
//        System.out.println(playerCardsSyncList);
    }

    public void setPlayerIndex(int inputtedPlayerIndex){
        this.playerIndex = inputtedPlayerIndex;
    }

    public card[] returnPlayerCards(){
        return playerCards;
    }

    public int returnPlayerIndex(){
        return playerIndex;
    }
}












