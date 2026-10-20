package Code;

import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.File;


public class cardGame {

    static String winningPlayer = null;

    public static player[] createListOfPlayers(int numPlayers){
        player[] listOfPlayers = new player[numPlayers];
        for (int n=1; n<= numPlayers; n++){
            player player = new player();
            player.playerIndex = n;
            listOfPlayers[n-1] = player;
        }
        return listOfPlayers;
    }

    public static cardDeck[] createListOfDecks(int numPlayers, List<cardDeck> listOfDecksSyncList){
        cardDeck[] listOfDecks = new cardDeck[numPlayers];
        for (int n=1; n<= numPlayers; n++){
            cardDeck deck = new cardDeck();
            deck.deckIndex = n;
            listOfDecks[n-1] = deck;
            listOfDecksSyncList.add(listOfDecks[n-1]);

        }
        return listOfDecks;
    }


    public static void startGame(){
        // saves players input as noPlayers
        Scanner scannerObj = new Scanner (System.in);
        boolean validPlayerNumber = false;
        System.out.println(validPlayerNumber);
        int noPlayers;
        System.out.println("Enter the number of players: ");
        noPlayers = Integer.parseInt(scannerObj.nextLine());
//        String winningPlayer = null;

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
        File filepath = new File (fileName);

        while (! filepath.exists()){
            System.out.println("Please enter the name of the file that you would like to use to load the card pack: ");
            fileName = scannerObj.nextLine();
            filepath = new File(fileName);
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
//                File deckLog = new File("deck_"+(i+1)+"_log.txt");
//                try{
//                    if (playerLog.createNewFile()) {
//                        System.out.println("File created: " + playerLog.getName());
//                    }
//                    else{
//                        System.out.print("File was not created for Player I guess?");
//                    }
//                    if (deckLog.createNewFile()) {
//                       System.out.println("File created: " + deckLog.getName());
//                    }
//                    else {
//                        System.out.println("File was not created for deck");
//                    }
//                } catch(IOException e){
//                        System.out.print("Error occurred in creating a file for player " + (i+1));
//                    }
//            }

        try {

            //looks for the file and check if it has a valid layout for the number of players
            long lineCount = Files.lines(Path.of(fileName)).count();
            System.out.println(lineCount);
            if (lineCount >= 8*noPlayers) {
                System.out.println("Valid card pack");

            } else{
                System.out.println("Invalid");
            }

            //creates the correct amount of player objects for the amount of players and stores them in the list of players

            listOfPlayers = createListOfPlayers(noPlayers);

            //creates the correct amount of deck objects for the amount of players and stores them in the list of decks

            listOfDecks = createListOfDecks(noPlayers,listOfDecksSyncList);

            //this loop assigns the cards to the player
            for (int n=1; n<= noPlayers; n++){
                card[] listOfPlayerCards = new card[4];
                String[] listOfPlayerCardValues = new String[4];
                card[] listOfDeckCards = new card[4];
                String[] listOfDeckCardValues = new String[4];


                for (int x=1; x<= 4; x++) {
                    card currentCard = new card();
                    String line = Files.readAllLines(Path.of(fileName)).get(((x-1)*noPlayers)+n-1);
                    currentCard.setCardValue(line);
                    listOfPlayerCards[x-1] = currentCard;
                    listOfPlayerCardValues[x-1] = line;

                    card currentCard2 = new card();
                    String line2 = Files.readAllLines(Path.of(fileName)).get(((x-1)*noPlayers)+n-1+4*noPlayers);
                    currentCard2.setCardValue(line2);
                    listOfDeckCards[x-1] = currentCard2;
                    listOfDeckCardValues[x-1] = line2;


//                        System.out.println(Arrays.toString(listOfPlayerCardValues));
//                        System.out.println(Arrays.toString(listOfDeckCardValues));
//                        System.out.println(listOfDecks[n-1].deckIndex);
//                        System.out.println(listOfPlayers[n-1].playerIndex);


                }
                listOfPlayers[(n-1)].setPlayerCards(listOfPlayerCards);
                listOfDecks[(n-1)].setDeckCards(listOfDeckCards);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//
//                    try{
//                        FileWriter logWriter = new FileWriter ("src/player_" + player.playerIndex + "_log.txt");
//                        logWriter.write("Player " + player.playerIndex + "'s initial hand: " + Arrays.toString(listOfPlayerCardValues);
//                        logWriter.close();
//                        System.out.println("Successfully wrote to Player " + player.playerIndex + "'s log file.");
//                   } catch (IOException e) {
//                        System.out.println("An error occurred with Player " + player.playerIndex + "'s draw to their log");
//
//                    }




        Thread[] listOfThreads = new Thread[noPlayers];
        for (int n=1; n<= noPlayers; n++) {
            Thread t = new threadClass(listOfPlayers[n - 1],listOfDecksSyncList,noPlayers);
            listOfThreads[n - 1] = t;
        }
        for (int n=1; n<= noPlayers; n++){
            listOfThreads[n - 1].start();
        }

    }
}