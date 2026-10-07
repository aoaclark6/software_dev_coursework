import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class cardGame {
        public static void startGame(){
            System.out.println("Does this even do anything");
            Scanner scannerObj = new Scanner (System.in);
            System.out.println("Enter the number of players: ");

            int noPlayers = Integer.parseInt(scannerObj.nextLine());


            System.out.println("Please enter the name of the file that you would like to use to load the card pack: ");

            String fileName = scannerObj.nextLine();



            try {
                long lineCount = Files.lines(Path.of("src", fileName)).count();
                System.out.println(lineCount);
                if (lineCount >= 8*noPlayers) {
                    System.out.println("Valid card pack");

                } else{
                    System.out.println("Invalid");
                }


                int n = 0;
                String line = Files.readAllLines(Path.of("src", fileName)).get(n);
                System.out.println(line);


            } catch (IOException e) {
                e.printStackTrace();
            }



            //return lineCount;
        }
}
