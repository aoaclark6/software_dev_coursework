public class player {
    card[] playerCards = new card[4];
    int playerIndex;
    public void setPlayerCards(card[] inputtedPlayerCards){
        this.playerCards = inputtedPlayerCards;
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












