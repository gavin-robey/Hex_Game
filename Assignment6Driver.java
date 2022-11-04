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
        testGame();
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

    private static void testGame() {
        HexGame game = new HexGame(11);
        
        System.out.println("--- red ---");
        game.playRed(1, true);
        game.playRed(11, true);
        game.playRed(122 - 12, true);
        game.playRed(122 - 11, true);
        game.playRed(122 - 10, true);
        game.playRed(121, true);
        game.playRed(61, true);

        System.out.println("--- blue ---");
        game.playBlue(1, true);
        game.playBlue(2, true);
        game.playBlue(11, true);
        game.playBlue(12, true);
        game.playBlue(121, true);
        game.playBlue(122 - 11, true);
        game.playBlue(62, true);

        System.out.println();
        printGrid(game);
    }

    private static void printGrid(HexGame game) {
        Integer[] board = game.getBoard();
        int elementCount = 1;
        String boardElements = "";
        int indentCount = 1;
        for(int i = 0; i < board.length; i++){
            if(!game.isEdge(i)){
                if(board[i] == game.getBlue()){
                    boardElements += ANSI_BLUE + "B " + ANSI_RESET;
                }
                else if(board[i]  == game.getRed()){
                    boardElements += ANSI_RED + "R " + ANSI_RESET;
                }
                else{
                    boardElements += "0 ";
                }
                
                if(elementCount % game.getBoardSize() == 0){
                    boardElements += "\n";
                    for(int indents = 0; indents < indentCount; indents++){
                        boardElements += " ";
                    }
                    indentCount++;
                }
                elementCount++;
            }
        }
        System.out.print(boardElements);
    }
}
