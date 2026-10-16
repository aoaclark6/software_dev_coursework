import java.io.IOException;
import java.util.List;
import java.io.FileWriter;

class threadClass extends Thread {
    public player player;
    public List<cardDeck> listOfDecksSyncList;
    public int noPlayers;

    threadClass(player player, List<cardDeck> listOfDecksSyncList,int noPlayers) {
        this.player = player;
        this.listOfDecksSyncList = listOfDecksSyncList;
        this.noPlayers = noPlayers;

    }
    threadClass(List<cardDeck> listOfDecksSyncList){this.listOfDecksSyncList = listOfDecksSyncList;}

    public void run() {
//        Boolean game_over = Boolean.FALSE;
//        while (!game_over) {

        for (int y=0; y<2; y++){
            if (player.playerCardsSyncList.get(0).cardValue.equals(player.playerCardsSyncList.get(1).cardValue)) {
                if (player.playerCardsSyncList.get(0).cardValue.equals(player.playerCardsSyncList.get(2).cardValue)) {
                    if (player.playerCardsSyncList.get(0).cardValue.equals(player.playerCardsSyncList.get(3).cardValue)) {
//                        game_over = true;
                        System.out.println("player " + Integer.toString(player.playerIndex) + " wins");
//                        for (int i=0; i<4; i++){
//
//                            try{
//                                FileWriter logWriter = new FileWriter (("src/player_" + (i+1) + "_log.txt"), true);
//                                logWriter.write(System.lineSeparator());
//                                logWriter.write("Player " + player.playerIndex + " has won!");
//                                logWriter.write(System.lineSeparator());
//                                logWriter.write("Player " + (i+1) + " exits");
//                                logWriter.close();
//                                System.out.println("Successfully wrote to Player " + player.playerIndex + "'s log file.");
//                            } catch (IOException e) {
//                                System.out.println("An error occurred with Player " + player.playerIndex + "'s draw to their log");
//                            }
//                        }


                    }
                }
            }

            System.out.println("player " + player.playerIndex + " cards " + player.playerCardsSyncList.get(0).cardValue + " " + player.playerCardsSyncList.get(1).cardValue + " " + player.playerCardsSyncList.get(2).cardValue + " " + player.playerCardsSyncList.get(3).cardValue + " " +
                    "deck " + player.playerIndex + " cards " + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(0).cardValue + " " + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(1).cardValue + " " + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(2).cardValue + " " + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(3).cardValue + " ");


            for (int i = 0; i < 4; i++) {
                if (Integer.parseInt(player.playerCardsSyncList.get(i).cardValue) != player.playerIndex) {
//                    System.out.println("player index is " + player.playerIndex + " and card being checked is " + player.playerCardsSyncList.get(i).cardValue);

                    //drawing card
                    card drewCard = listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.getFirst();
                    listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.removeFirst();
                    player.playerCardsSyncList.add(drewCard);

//                    try{
//
//                        FileWriter logWriter = new FileWriter (("src/player_" + player.playerIndex + "_log.txt"), true);
//                        logWriter.write(System.lineSeparator());
//                        logWriter.write("Player " + player.playerIndex + " drew a " + drewCard.cardValue + " from deck " + player.playerIndex);
//                        logWriter.close();
//                        System.out.println("Successfully wrote to Player " + player.playerIndex + "'s log file.");
//                    } catch (IOException e) {
//                        System.out.println("An error occurred with Player " + player.playerIndex + "'s draw to their log");
//
//                    }


                    //discarding card
                    card discardedCard = player.playerCardsSyncList.get(i);
                    player.playerCardsSyncList.remove(i);
                    if (player.playerIndex == noPlayers) {
                        listOfDecksSyncList.get(0).deckCardsSyncList.add(discardedCard);
                    } else {
                        listOfDecksSyncList.get(player.playerIndex).deckCardsSyncList.add(discardedCard);
                    }

                    String currentHand = player.playerCardsSyncList.get(0).cardValue + " " + player.playerCardsSyncList.get(1).cardValue + " " + player.playerCardsSyncList.get(2).cardValue + " " + player.playerCardsSyncList.get(3).cardValue + " ";
                    String currentDeck = listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(0).cardValue + " " + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(1).cardValue + " " + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(2).cardValue + " " + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(3).cardValue;


//                    try{
//                        FileWriter logWriter = new FileWriter (("src/player_" + player.playerIndex + "_log.txt"), true);
//                        logWriter.write(System.lineSeparator());
//                        if (player.playerIndex < 4){
//                          logWriter.write("Player " + player.playerIndex + " discarded a " + discardedCard.cardValue + " to deck" + player.playerIndex+1);
//                          }
//                        else{
//                          logWriter.write("Player " + player.playerIndex + " discarded a " + discardedCard.cardValue + " to deck 1");
//                          }
//                        logWriter.write(System.lineSeparator());
//                        logWriter.write("Player " + player.playerIndex + "'s current hand: " + currentHand);
//                        logWriter.close();
//                        System.out.println("Successfully wrote to Player " + player.playerIndex + "'s log file.");
//                    } catch (IOException e) {
//                        System.out.println("An error occurred with Player " + player.playerIndex + "'s discard to their log");
//                    }


                    System.out.println("player " + player.playerIndex + " cards " + player.playerCardsSyncList.get(0).cardValue + " " + player.playerCardsSyncList.get(1).cardValue + " " + player.playerCardsSyncList.get(2).cardValue + " " + player.playerCardsSyncList.get(3).cardValue + " " +
                            "deck " + player.playerIndex + " cards " + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(0).cardValue + " " + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(1).cardValue + " " + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(2).cardValue + " " + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(3).cardValue + " ");


                    break;

                }

            }

            //cardDeck.deckCardsSyncList

//      }
        }
    }
}


