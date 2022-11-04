import java.util.Arrays;

public class HexGame {
    private int boardSize;
    private int BLUE;
    private int RED;
    private int rowSize;
    private int totalBoardSize;
    private Integer[] board;

    public HexGame(int boardSize){
        this.boardSize = boardSize;
        this.RED = (boardSize * boardSize) + 5;
        this.BLUE = (boardSize * boardSize) + 6;
        this.rowSize = boardSize + 2;
        this.totalBoardSize = rowSize * rowSize;
        this.board = new Integer[totalBoardSize];
        buildBoard();
    }

    private void buildBoard(){
        int total = 1;
        for(int i = 0; i < board.length; i++){
            if(i < this.rowSize){
                board[i] = (boardSize * boardSize) + 1;
            }else if(i >= this.totalBoardSize - this.rowSize){
                board[i] = (boardSize * boardSize) + 2;
            }else if(i % this.rowSize == 0){
                board[i] = (boardSize * boardSize) + 3;
            }else if((i - (this.boardSize + 1)) % this.rowSize == 0){
                board[i] = (boardSize * boardSize) + 4;
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

    public Integer[] getBoard(){return this.board;}
    public int getBoardSize(){ return this.boardSize;}
    public int getBlue(){return this.BLUE;}
    public int getRed(){return this.RED;}

    public boolean isEdge(int i){
        boolean isTopEdge = i < this.rowSize;
        boolean isBottomEdge = i >= this.totalBoardSize - this.rowSize;
        boolean isLeftEdge = i % this.rowSize == 0;
        boolean isRightEdge = (i - (this.boardSize + 1)) % this.rowSize == 0;
        return isTopEdge || isBottomEdge ||  isLeftEdge || isRightEdge;
    }

    public boolean playBlue(int position, boolean displayNeighbors){
        // take the position and find where its at in range not including the edges
        // Then find all the neighbors of the position
        // Then union all neigbors with the poistion requested
        // Then add to the other array that keeps track of colors and fun stuff
        int index = Arrays.asList(board).indexOf(position);
        if(index >= 0){
            board[index] = BLUE;
        }
        return true;
    }

    public boolean playRed(int position, boolean displayNeighbors){
        int index = Arrays.asList(board).indexOf(position);
        if(index >= 0){
            board[index] = RED;
        }
        return false;
    }
}
