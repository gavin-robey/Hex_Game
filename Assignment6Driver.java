import java.io.File;
import java.util.Scanner;

public class Assignment6Driver {
    public static void main(String[] args) {

        //testGame();
        //playGame("moves1.txt");
        //System.out.println();
        //playGame("moves2.txt");
    }

    private static void playGame(String filename) {
        File file = new File(filename);
        try (Scanner input = new Scanner(file)) {
            // TODO: Write some good stuff here
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the moves file: " + ex);
        }
    }

    //
    // TODO: You can use this to compare with the output show in the assignment while working on your code
    private static void testGame() {
//        HexGame game = new HexGame(11);
//
//        System.out.println("--- red ---");
//        game.playRed(1, true);
//        game.playRed(11, true);
//        game.playRed(122 - 12, true);
//        game.playRed(122 - 11, true);
//        game.playRed(122 - 10, true);
//        game.playRed(121, true);
//        game.playRed(61, true);
//
//        System.out.println("--- blue ---");
//        game.playBlue(1, true);
//        game.playBlue(2, true);
//        game.playBlue(11, true);
//        game.playBlue(12, true);
//        game.playBlue(121, true);
//        game.playBlue(122 - 11, true);
//        game.playBlue(62, true);
//
//        printGrid(game);
//    }

    // TODO: Complete this method
//    private static void printGrid(HexGame game) {
//    }

}
