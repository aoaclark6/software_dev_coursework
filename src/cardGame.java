import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.File;


public class cardGame {
        public static void startGame(){

            // saves players input as noPlayers
            Scanner scannerObj = new Scanner (System.in);
            boolean validPlayerNumber = false;
            System.out.println(validPlayerNumber);
            int noPlayers;
            System.out.println("Enter the number of players: ");
            noPlayers = Integer.parseInt(scannerObj.nextLine());

//            while (!validPlayerNumber) {
//                System.out.println("Loop reached");
//                try {
//                    System.out.println("Enter the number of players: ");
//                    noPlayers = Integer.parseInt(scannerObj.nextLine());
//                    validPlayerNumber = true;
//                } catch (Exception e){
//                    System.out.println("Dude that's not an integer :(");
//                }
//            }



            //saves players input as fileName
            System.out.println("Please enter the name of the file that you would like to use to load the card pack: ");

            String fileName = scannerObj.nextLine();
            File filepath = new File ("src/" + fileName);

            while (! filepath.exists()){
                System.out.println("Please enter the name of the file that you would like to use to load the card pack: ");
                fileName = scannerObj.nextLine();
                filepath = new File("src/" + fileName);
                System.out.println(filepath);
                if (filepath.exists()){
                    break;
                }
            }

            //arrays of player and deck objects
            player[] listOfPlayers = new player[noPlayers];
            cardDeck[] listOfDecks = new cardDeck[noPlayers];
            List<cardDeck> listOfDecksSyncList =
                    Collections.synchronizedList(new ArrayList<cardDeck>());

            //Creates a new player log file for each player in the game
//            for (int i=0; i<4; i++){
//                File playerLog = new File("player_"+(i+1)+"_log.txt");
//                File deckLog = new File("deck_"+(i+1)+"_log.txt);
//                try{
//                    if (playerLog.createNewFile()) {
//                        System.out.println("File created: " + playerLog.getName());
//                    }
//                    else{
//                        System.out.print("File was not created for Player I guess?");
//                    }
//                    if (deckLog.createNewFile()) {
//                       System.out.println("File created: " + deckLog.getName();
//                    }
//                    else{
//                        System.out.println("File was not created for deck");
//                } catch(IOException e){
//                        System.out.print("Error occured in creating a file for player " + (i+1));
//                    }
//            }


            try {

                //looks for the file and check if it has a valid layout for the number of players
                long lineCount = Files.lines(Path.of("src", fileName)).count();
                System.out.println(lineCount);
                if (lineCount >= 8*noPlayers) {
                    System.out.println("Valid card pack");

                } else{
                    System.out.println("Invalid");
                }

                //creates the correct amount of player objects for the amount of players and stores them in the list of players

                for (int n=1; n<= noPlayers; n++){
                    player player = new player();
                    player.playerIndex = n;
//                    System.out.println(player);
                    listOfPlayers[n-1] = player;
                }

                //creates the correct amount of deck objects for the amount of players and stores them in the list of decks



                for (int n=1; n<= noPlayers; n++){
                    cardDeck deck = new cardDeck();
                    deck.deckIndex = n;
//                    System.out.println(deck);
                    listOfDecks[n-1] = deck;
                    listOfDecksSyncList.add(listOfDecks[n-1]);
                }


                //this loop assigns the cards to the player and deck
                for (int n=1; n<= noPlayers*2; n++){
                    card[] listOfCards = new card[4];
                    String[] listOfCardValues = new String[4];

                    for (int x=1; x<= 4; x++) {
                        card currentCard = new card();
                        String line = Files.readAllLines(Path.of("src", fileName)).get(((x-1)*8)+n-1);
                        currentCard.setCardValue(line);
                        listOfCards[x-1] = currentCard;
                        listOfCardValues[x-1] = line;
                    }
                    if( (n % 2) == 1) {
                        listOfPlayers[(n-1)/2].setPlayerCards(listOfCards);
                    }
                    if( (n % 2) == 0) {
                        listOfDecks[(n-2)/2].setDeckCards(listOfCards);
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }



            Thread[] listOfThreads = new Thread[noPlayers];
            for (int n=1; n<= noPlayers; n++) {
                Thread t = new threadClass(listOfPlayers[n - 1],listOfDecksSyncList,noPlayers);
                listOfThreads[n - 1] = t;
            }
            for (int n=1; n<= noPlayers; n++){
                listOfThreads[n - 1].start();
            }


//            Thread[] listOfThreads = new Thread[noPlayers];
//            for (int n=1; n<= noPlayers; n++){
//                Thread t = new threadClass(listOfPlayers[n-1]);
//                listOfThreads[n-1] = t;
//
//                t.start();
//            }

            //return lineCount;
        }
}
