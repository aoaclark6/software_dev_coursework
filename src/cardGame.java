import java.util.Scanner;

public class cardGame {
        public static void startGame(){
            System.out.println("Does this even do anything");
            Scanner scannerObj = new Scanner (System.in);
            System.out.println("Enter the number of players: ");

            int noPlayers = Integer.parseInt(scannerObj.nextLine());


            System.out.println("Please enter the name of the file that you would like to use to load the card pack: ");

            String fileName = scannerObj.nextLine();

        }
}
