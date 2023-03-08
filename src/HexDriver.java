import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class HexDriver{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";    

    public static void main(String[] args) throws InterruptedException{
        playGame(); 
    }

    /**
     * Initializes a new HexGame and watches for console input
     * This method then loops over each move and calls playBlue or playRed to see if there is a winner
     * Each iteration is a "move" each iteration prints the updated board to the console
     * If there is a winner, the loop stops
     * 
     * @param filename A file of moves that will be played
     * @return void
     */
    private static void playGame() throws InterruptedException{
        Scanner scanner = new Scanner(System.in);
        HexGame game = new HexGame(11);
        userInstructions(game);

        boolean redWon = false;
        boolean blueWon = false;
        int lastPosRed = 0;
        int lastPosBlue = 0;
        boolean isBlue = true;
        try {
            int count = 0;
            while (true) {    
                System.out.println();
                System.out.print(isBlue ? "Blue enter a number 1-121: " : "Red enter a number 1-121: ");
                int play = scanner.nextInt();

                // ensures a play is valid on any size of board
                if(play <= (game.getBoardSize() * game.getBoardSize())){
                    if(count % 2 == 0){
                        lastPosBlue = play;
                        blueWon = game.playBlue(play, false);
                        System.out.println();
                        printGrid(game);
                        isBlue = false;
                        count++;
                    }else{
                        lastPosRed = play;
                        redWon = game.playRed(play, false);
                        System.out.println();
                        printGrid(game);
                        isBlue = true;
                        count++;
                    } 
                    if(redWon || blueWon){
                        break;
                    }
                }
            }
            if(redWon){
                System.out.println();
                System.out.println("Red wins with move at position " + lastPosRed + "!!");
            }else if(blueWon){
                System.out.println();
                System.out.println("Blue wins with move at position " + lastPosBlue + "!!");
            }

        }
        catch (Exception e) {
            System.out.println("Exiting program...");
        } finally {
            scanner.close();
        }
    }


    /**
     * Helper method in charge of displaying the instructions of the game to the user
     * @param game Hex game object used to print the grid
     * @throws InterruptedException Thrown by typeText
     */
    private static void userInstructions(HexGame game) throws InterruptedException{
        System.out.println();
        typeText("Welcome to Hex Game!", 10);
        typeText("The goal of this game is to connect one side of the board to the other by placing tiles", 10);
        typeText("There are two players, the Red Player and Blue Player", 10);
        typeText("Red's goal is to connect the top edge to the bottom edge", 10);
        typeText("Blue's goal is to connect the left edge to the right edge", 10);
        typeText("To place a tile simply enter the number in the grid of where your tile should be placed", 10);
        typeText("To exit the program type control+C", 10);
        System.out.println();
        printGrid(game);
    }

    /**
     * Outputs a line of text animated so that it looks like each letter is being typed to the console.
     *
     * @param text  the text to be animated
     * @param delay the delay between each character being printed (in milliseconds)
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public static void typeText(String text, int delay) throws InterruptedException {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            TimeUnit.MILLISECONDS.sleep(delay);
        }
        System.out.println();
    }

    /**
     * Loops over the entire board and prints each blank hex as a number 1-121
     * If a blue is selected, then the tile is converted to blue text
     * If a red is selected, then a the tile is converted to red text
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
                    boardElements += ANSI_BLUE + formatNumber(elementCount) + " " + ANSI_RESET;
                }
                else if(game.redContains(board[i])){
                    boardElements += ANSI_RED + formatNumber(elementCount) + " " + ANSI_RESET;
                }
                else{
                    boardElements += formatNumber(elementCount) + " ";
                }
                
                // used to add newline characters once the 11th tile is added to the string
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

    /**
     * Formats a number according to the following rules:
     * - If the number is less than 10, it appends "00" to the front of the number.
     * - If the number is less than 100 but greater than or equal to 10, it appends "0" to the front of the number.
     * - If the number is greater than or equal to 100, it simply returns the number as a string.
     *
     * @param number the number to format
     * @return a string representing the formatted number
     */
    public static String formatNumber(int number) {
        if (number < 10) {
            return "00" + number;
        } else if (number < 100) {
            return "0" + number;
        } else {
            return String.valueOf(number);
        }
    }
}
