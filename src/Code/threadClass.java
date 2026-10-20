package Code;

import java.io.IOException;
import java.util.List;
import java.io.FileWriter;

class threadClass extends Thread {
    private player player;
    public List<cardDeck> listOfDecksSyncList;
    public int noPlayers;
    public int roundCounter = 1;

    threadClass(player player, List<cardDeck> listOfDecksSyncList, int noPlayers) {
        this.player = player;
        this.listOfDecksSyncList = listOfDecksSyncList;
        this.noPlayers = noPlayers;

    }

    void winCheck() {
        if (player.playerCardsSyncList.get(0).cardValue.equals(player.playerCardsSyncList.get(1).cardValue)) {
            if (player.playerCardsSyncList.get(0).cardValue.equals(player.playerCardsSyncList.get(2).cardValue)) {
                if (player.playerCardsSyncList.get(0).cardValue.equals(player.playerCardsSyncList.get(3).cardValue)) {
                    cardGame.winningPlayer = " ";
                    System.out.println("Code.player " + player.playerIndex + " wins");
                    for (int i=0; i<noPlayers; i++){
                        int playerLogToAppend = i+1;
                        try{
                            FileWriter logWriter = new FileWriter ("src/player_" + playerLogToAppend + "_log.txt", true);
                            logWriter.write(System.lineSeparator());
                            logWriter.write("Player " + player.playerIndex + " has won!");
                            logWriter.write(System.lineSeparator());
                            int beep = i+1;
                            logWriter.write("Player " + beep + " exits");
                            logWriter.close();
                            System.out.println("Successfully wrote to Player " + player.playerIndex + "'s log file.");
                        } catch (IOException e) {
                            System.out.println("An error occurred with Player " + player.playerIndex + "'s draw to their log");
                        }
                    }


                }
            }
        }
    }

    public void initialOutput() {
        String initialHand = " ";
        for (int j = 0; j < player.playerCardsSyncList.size(); j++) {
            initialHand = initialHand + player.playerCardsSyncList.get(j).cardValue + " ";
        }
        try {
            FileWriter logWriter = new FileWriter("src/player_" + player.playerIndex + "_log.txt", true);
            logWriter.write(System.lineSeparator());
            logWriter.write("Player " + player.playerIndex + "'s initial hand: " + initialHand);
            logWriter.close();
            System.out.println("Successfully wrote to Player " + player.playerIndex + "'s log file.");
        } catch (IOException e) {
            System.out.println("An error occurred with Player " + player.playerIndex + "'s draw to their log");

        }
    }

    public void deckOutput() {
        String deckContents = " ";
        for (int j = 0; j < listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.size(); j++) {
            deckContents = deckContents + listOfDecksSyncList.get(player.playerIndex - 1).deckCardsSyncList.get(j).cardValue + " ";
        }
        try {
            FileWriter logWriter = new FileWriter("src/deck_" + player.playerIndex + "_log.txt", true);
            logWriter.write(System.lineSeparator());
            logWriter.write("Deck " + player.playerIndex + "'s final contents: " + deckContents);
            logWriter.close();
            System.out.println("Successfully wrote to Deck " + player.playerIndex + "'s log file.");
        } catch (IOException e) {
            System.out.println("An error occurred with Deck " + player.playerIndex + "'s writing to file");

        }
    }

    public void gameRound () {
        //fetches the draw and discard deck
        cardDeck drawDeck = listOfDecksSyncList.get(player.playerIndex - 1);
        cardDeck discardDeck;
        if (player.playerIndex == noPlayers) {
            discardDeck = listOfDecksSyncList.get(0);
        } else {
            discardDeck = listOfDecksSyncList.get(player.playerIndex);
        }

        //prevents a deadlock
        cardDeck firstLock;
        cardDeck secondLock;

        if (drawDeck.deckIndex < discardDeck.deckIndex) {
            firstLock = drawDeck;
            secondLock = discardDeck;
        } else {
            firstLock = discardDeck;
            secondLock = drawDeck;
        }

        synchronized (firstLock) {
            synchronized (secondLock) {

                //drawing Code.card
                card drewCard = drawDeck.deckCardsSyncList.removeFirst();
                player.playerCardsSyncList.add(drewCard);

                try {

                    FileWriter logWriter = new FileWriter("src/player_" + player.playerIndex + "_log.txt", true);
                    logWriter.write(System.lineSeparator());
                    logWriter.write("Player " + player.playerIndex + " drew a " + drewCard.cardValue + " from deck " + player.playerIndex);
                    logWriter.close();
                    System.out.println("Successfully wrote to Player " + player.playerIndex + "'s log file.");
                } catch (IOException e) {
                    System.out.println("An error occurred with Player " + player.playerIndex + "'s draw to their log");

                }




                //discarding Code.card
                for (int i = 0; i < player.playerCardsSyncList.size(); i++) {
                    if (Integer.parseInt(player.playerCardsSyncList.get(i).cardValue) != player.playerIndex) {
                        card discardedCard = player.playerCardsSyncList.remove(i);
                        discardDeck.deckCardsSyncList.add(discardedCard);
                        //current hand of Code.player
                        String currentHand = " ";
                        for (int j = 0; j < player.playerCardsSyncList.size(); j++) {
                            currentHand = currentHand + player.playerCardsSyncList.get(j).cardValue + " ";
                        }
                        try {
                            FileWriter logWriter = new FileWriter("src/player_" + player.playerIndex + "_log.txt", true);
                            logWriter.write(System.lineSeparator());
                            if (player.playerIndex < noPlayers) {
                                int deckDiscardedTo = player.playerIndex + 1;
                                logWriter.write("Player " + player.playerIndex + " discarded a " + discardedCard.cardValue + " to deck " + deckDiscardedTo);
                            } else {
                                logWriter.write("Player " + player.playerIndex + " discarded a " + discardedCard.cardValue + " to deck 1");
                            }
                            logWriter.write(System.lineSeparator());
                            logWriter.write("Player " + player.playerIndex + "'s current hand: " + currentHand);
                            logWriter.write(System.lineSeparator());
                            logWriter.write(" ");
                            logWriter.close();
                            System.out.println("Successfully wrote to Player " + player.playerIndex + "'s log file.");
                        } catch (IOException e) {
                            System.out.println("An error occurred with Player " + player.playerIndex + "'s discard to their log");
                        }
                        break;
                    }


                }


            }
        }
    }




    public void run() {
        initialOutput();
        winCheck();
        while (cardGame.winningPlayer == null) {
            gameRound();
            winCheck();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        deckOutput();

//        System.out.println("Code.player " + Code.player.playerIndex + " cards " + Code.player.playerCardsSyncList.get(0).cardValue + " " + Code.player.playerCardsSyncList.get(1).cardValue + " " + Code.player.playerCardsSyncList.get(2).cardValue + " " + Code.player.playerCardsSyncList.get(3).cardValue + " " +
//                "deck " + Code.player.playerIndex + " cards " + listOfDecksSyncList.get(Code.player.playerIndex - 1).deckCardsSyncList.get(0).cardValue + " " + listOfDecksSyncList.get(Code.player.playerIndex - 1).deckCardsSyncList.get(1).cardValue + " " + listOfDecksSyncList.get(Code.player.playerIndex - 1).deckCardsSyncList.get(2).cardValue + " " + listOfDecksSyncList.get(Code.player.playerIndex - 1).deckCardsSyncList.get(3).cardValue + " ");


    }
}
