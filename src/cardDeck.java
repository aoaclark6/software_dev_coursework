import java.util.*;
public class cardDeck {
    card[] deckCards = new card[4];

    List<card> deckCardsSyncList =
            Collections.synchronizedList(new ArrayList<card>());

    List<cardDeck> listOfDecksSyncList =
            Collections.synchronizedList(new ArrayList<cardDeck>());



    int deckIndex;
    public void setDeckCards(card[] inputtedDeckCards){
        this.deckCards = inputtedDeckCards;
        this.deckCardsSyncList.add(inputtedDeckCards[0]);
        this.deckCardsSyncList.add(inputtedDeckCards[1]);
        this.deckCardsSyncList.add(inputtedDeckCards[2]);
        this.deckCardsSyncList.add(inputtedDeckCards[3]);
//        System.out.println(deckCardsSyncList);
    }


    public card[] returnDeckCards(){
        return deckCards;
    }
}
