import java.util.Arrays;
import java.util.ArrayList;

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

    public boolean isEdge(int position){
        boolean isTopEdge = position < this.rowSize;
        boolean isBottomEdge = position >= this.totalBoardSize - this.rowSize;
        boolean isLeftEdge = position % this.rowSize == 0;
        boolean isRightEdge = (position - (this.boardSize + 1)) % this.rowSize == 0;
        return isTopEdge || isBottomEdge ||  isLeftEdge || isRightEdge;
    }

    public boolean playBlue(int position, boolean displayNeighbors){
        // take the position and find where its at in range not including the edges done
        // Then find all the neighbors of the position
        // Then union all neighbors with the position requested
        // Then add to the other array that keeps track of colors and fun stuff
        ArrayList<Integer> neighbors = getNeighborsBlue(convertToIndex(position));

        if(displayNeighbors){
            int index = Arrays.asList(board).indexOf(position);
            if(index >= 0){
                board[index] = BLUE;
            }
            System.out.println(neighbors.toString());
        }

        return true;
    }

    public boolean playRed(int position, boolean displayNeighbors){
        ArrayList<Integer> neighbors = getNeighborsRed(convertToIndex(position));
    
        if(displayNeighbors){
            int index = Arrays.asList(board).indexOf(position);
            if(index >= 0){
                board[index] = RED;
            }
            System.out.println(neighbors.toString());
        }
        return false;
    }

    private int convertToIndex(int position){
        int count = 1;
        int index = 0;
        for(int i = 0; i < board.length; i++){
            if(!isEdge(i)){
                count++;
            }
            if(count == position){
                index = (i + 1);
            }
        }
        return index;
    }

    // given an index, each neighbor index is found then each neighbor value is returned
    private ArrayList<Integer> getNeighborsRed(int index){
        ArrayList<Integer> allNeighbors = new ArrayList<>();
        
        Integer left = index - 1;
        Integer right = index + 1;
        Integer top = index - rowSize;
        Integer topRight = index - (rowSize - 1);
        Integer bottomLeft = index + (rowSize - 1);
        Integer bottom = index + rowSize;

        boolean isLeftEdge = left % this.rowSize == 0;
        boolean isRightEdge = (right - (this.boardSize + 1)) % this.rowSize == 0;

        if(!isRightEdge){
            allNeighbors.add(board[right]);
            allNeighbors.add(board[topRight]);
        }
        if(!isLeftEdge){
            allNeighbors.add(board[left]);
            allNeighbors.add(board[bottomLeft]);
        }

        allNeighbors.add(board[top]);
        allNeighbors.add(board[bottom]);

        return allNeighbors;
    }

    private ArrayList<Integer> getNeighborsBlue(int index){
        ArrayList<Integer> allNeighbors = new ArrayList<>();

        Integer left = index - 1;
        Integer right = index + 1;
        Integer top = index- rowSize;
        Integer topRight = index - (rowSize - 1);
        Integer bottomLeft = index + (rowSize - 1);
        Integer bottom = index + rowSize;

        boolean isTopEdge = top < this.rowSize;
        boolean isBottomEdge = bottom >= this.totalBoardSize - this.rowSize;

        if(!isTopEdge){
            allNeighbors.add(board[top]);
            allNeighbors.add(board[topRight]);
        }
        if(!isBottomEdge){
            allNeighbors.add(board[bottom]);
            allNeighbors.add(board[bottomLeft]);
        }

        allNeighbors.add(board[left]);
        allNeighbors.add(board[right]);
        

        return allNeighbors;
    }

    private boolean isOccupied(int position){
        boolean isOccupied = false;
        if(board[position] == BLUE || board[position] == RED){
            isOccupied = true;
        }
        return isOccupied;
    }
}
