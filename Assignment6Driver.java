import java.io.File;
import java.util.Scanner;

public class Assignment6Driver {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    
    public static void main(String[] args) {
        //testGame();
        //playGame("moves1.txt");
        //System.out.println();
        //playGame("moves2.txt");
        DisjointSet set = new DisjointSet(20);
        set.union(1, 4);
        set.union(1, 5);
        set.union(1, 5);
        set.union(6, 7);
        set.union(4, 7);
        // set.find(6);
        // set.find(5);
        set.print();
        
        // System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
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
    }
    // TODO: Complete this method
//    private static void printGrid(HexGame game) {
//    }
}
