import java.util.List;

class threadClass extends Thread {
    private player player;

    private List<cardDeck> listOfDecksSyncList;

    threadClass(player player) {
        this.player = player;
    }
    threadClass(List<cardDeck> listOfDecksSyncList){this.listOfDecksSyncList = listOfDecksSyncList;}

    public void run() {
//        Boolean game_over = Boolean.FALSE;
//        while (!game_over) {
            if (player.playerCardsSyncList.get(0).cardValue.equals(player.playerCardsSyncList.get(1).cardValue)) {
                if (player.playerCardsSyncList.get(0).cardValue.equals(player.playerCardsSyncList.get(2).cardValue)) {
                    if (player.playerCardsSyncList.get(0).cardValue.equals(player.playerCardsSyncList.get(3).cardValue)) {
//                        game_over = true;
                        System.out.println("player " + Integer.toString(player.playerIndex) + " wins");
                    }
                }
            }

            for(int i=0; i<4; i++){
                if (Integer.parseInt( player.playerCardsSyncList.get(i).cardValue) != player.playerIndex){
                    card discardedCard = player.playerCardsSyncList.get(i);
                    System.out.println("player index is " + player.playerIndex + " and card being checked is " + player.playerCardsSyncList.get(i).cardValue);
                    player.playerCardsSyncList.remove(i);
//                    cardDeck.deckCardsSyncList.get(i+1).add(discardedCard);

                    System.out.println(listOfDecksSyncList);

                    listOfDecksSyncList.get(i+1).deckCardsSyncList.add(discardedCard);


                    break;

                }
            }

            //cardDeck.deckCardsSyncList

//        }



    }





}


