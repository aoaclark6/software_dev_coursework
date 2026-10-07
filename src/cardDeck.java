public class cardDeck {
    card[] deckCards = new card[4];
    public void setDeckCards(card[] inputtedDeckCards){
        this.deckCards = inputtedDeckCards;
    }

    public card[] returnDeckCards(){
        return deckCards;
    }
}
