import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class cardGame {
        public static void startGame(){


            Scanner scannerObj = new Scanner (System.in);
            System.out.println("Enter the number of players: ");

            int noPlayers = Integer.parseInt(scannerObj.nextLine());


            System.out.println("Please enter the name of the file that you would like to use to load the card pack: ");

            String fileName = scannerObj.nextLine();

            player[] listOfPlayers = new player[noPlayers];
            cardDeck[] listOfDecks = new cardDeck[noPlayers];


            try {
                long lineCount = Files.lines(Path.of("src", fileName)).count();
                System.out.println(lineCount);
                if (lineCount >= 8*noPlayers) {
                    System.out.println("Valid card pack");

                } else{
                    System.out.println("Invalid");
                }


                for (int n=1; n<= noPlayers; n++){
                    player player = new player();
                    player.playerIndex = n;
                    System.out.println(player);
                    listOfPlayers[n-1] = player;
                }

                for (int n=1; n<= noPlayers; n++){
                    cardDeck deck = new cardDeck();
                    deck.deckIndex = n;
                    System.out.println(deck);
                    listOfDecks[n-1] = deck;
                }
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
                        listOfPlayers[(n-1)/2].playerCards = listOfCards;
                    }
                    if( (n % 2) == 0) {
                        listOfDecks[(n-2)/2].deckCards = listOfCards;
                    }






                }
                System.out.println(listOfDecks[2].deckCards[0].cardValue);
                System.out.println(listOfDecks[2].deckCards[1].cardValue);
                System.out.println(listOfDecks[2].deckCards[2].cardValue);
                System.out.println(listOfDecks[2].deckCards[3].cardValue);




            } catch (IOException e) {
                e.printStackTrace();
            }










            //return lineCount;
        }
}
