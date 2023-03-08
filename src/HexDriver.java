import java.io.File;
import java.util.Scanner;

public class HexDriver{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";    

    public static void main(String[] args) {
        // testGame();
        // System.out.println();
        playGame("moves1.txt");
        System.out.println();
        playGame("moves2.txt");    
    }

    /**
     * Initializes a new HexGame and reads a file of moves
     * This method then loops over each move and calls playBlue or playRed to see if there is a winner
     * If there is a winner, the loop stops, and the result is printed to the console
     * Then the complete board is printed to the console
     * 
     * @param filename A file of moves that will be played
     * @return void
     */
    private static void playGame(String filename) {
        HexGame game = new HexGame(11);
        File file = new File(filename);
        boolean redWon = false;
        boolean blueWon = false;
        int lastPosRed = 0;
        int lastPosBlue = 0;
        try (Scanner input = new Scanner(file)) {
            int count = 0;
            while (input.hasNext()) {
                int play = input.nextInt();

                // ensures a play is valid on any size of board
                if(play <= (game.getBoardSize() * game.getBoardSize())){
                    if(count % 2 == 0){
                        lastPosBlue = play;
                        blueWon = game.playBlue(lastPosBlue, false);
                        count++;
                    }else{
                        lastPosRed = play;
                        redWon = game.playRed(lastPosRed, false);
                        count++;
                    } 
                    if(redWon || blueWon){
                        break;
                    }
                }
            }

            if(redWon){
                System.out.println("Red wins with move at position " + lastPosRed + "!!");
            }else if(blueWon){
                System.out.println("Blue wins with move at position " + lastPosBlue + "!!");
            }
            printGrid(game);
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the moves file: " + ex);
        }
    }

    /**
     * Used for debugging to see if neighbors for a given index are correct
     * Then prints out the finished game
     * 
     * @return void
     */
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

    /**
     * Loops over the entire board and prints each blank hex as a 0 
     * If a blue is selected, then a blue B is printed out
     * If a red is selected, then a red R is printed out
     * 
     * @param game a HexGame object used to access the current game
     * @return void 
     */
    private static void printGrid(HexGame game) {
        Integer[] board = game.getBoard();
        int elementCount = 1;
        String boardElements = "";
        int indentCount = 1;
        for(int i = 0; i < board.length; i++){
            if(!game.isEdge(i)){
                if(game.blueContains(board[i])){
                    boardElements += ANSI_BLUE + "B " + ANSI_RESET;
                }
                else if(game.redContains(board[i])){
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
