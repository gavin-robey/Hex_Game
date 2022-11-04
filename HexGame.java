import java.util.Arrays;

public class HexGame {
    private int boardSize;
    private int TOP_EDGE; 
    private int BOTTOM_EDGE;
    private int LEFT_EDGE;
    private int RIGHT_EDGE; 
    private int BLUE;
    private int RED;
    private int rowSize;
    private int totalBoardSize;
    private DisjointSet set;
    private Integer[] board;

    public HexGame(int boardSize){
        this.boardSize = boardSize;
        this.TOP_EDGE = (boardSize * boardSize) + 1;
        this.BOTTOM_EDGE = (boardSize * boardSize) + 2;
        this.LEFT_EDGE = (boardSize * boardSize) + 3;
        this.RIGHT_EDGE = (boardSize * boardSize) + 4;
        this.RED = (boardSize * boardSize) + 5;
        this.BLUE = (boardSize * boardSize) + 6;
        this.rowSize = boardSize + 2;
        this.totalBoardSize = rowSize * rowSize;
        this.set = new DisjointSet(totalBoardSize);
        this.board = new Integer[totalBoardSize];
        buildBoard();
    }

    private void buildBoard(){
        int total = 1;
        for(int i = 0; i < board.length; i++){
            if(i < rowSize){
                board[i] = TOP_EDGE;
            }else if(i >= totalBoardSize - rowSize){
                board[i] = BOTTOM_EDGE;
            }else if(i % rowSize == 0){
                board[i] = LEFT_EDGE;
            }else if((i - (boardSize + 1)) % rowSize == 0){
                board[i] = RIGHT_EDGE;
            }else{
                board[i] = total;
                total++;
            }
        }
    }

    public void printBoard(){
        for(int i = 0; i < board.length; i++){
            System.out.print(board[i] + " ");
            if((i - (boardSize + 1)) % (boardSize + 2) == 0){
                System.out.println();
            }
        }
    }

    public Integer[] getBoard(){
        return this.board;
    }

    public int getBoardSize(){
        return this.totalBoardSize;
    }

    public int getBlue(){
        return this.BLUE;
    }

    public int getRed(){
        return this.RED;
    }

    public boolean playBlue(int position, boolean displayNeighbors){
        int index = Arrays.asList(board).indexOf(position);
        if(index >= 0){
            board[index] = -position;
        }
        return true;
    }

    public boolean playRed(int position, boolean displayNeighbors){
        int index = Arrays.asList(board).indexOf(position);
        if(index >= 0){
            board[index] = position + (totalBoardSize * totalBoardSize);
        }
        return false;
    }
}
