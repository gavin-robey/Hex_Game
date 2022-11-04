import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

public class HexGame {
    private int boardSize;
    private int BLUE;
    private int RED;
    private int rowSize;
    private int totalBoardSize;
    private Integer[] board;
    private Set<Integer> insertedReds;
    private Set<Integer> insertedBlues;
    private Set<Integer> occupied;

    public HexGame(int boardSize){
        this.boardSize = boardSize;
        this.RED = (boardSize * boardSize) + 5;
        this.BLUE = (boardSize * boardSize) + 6;
        this.rowSize = boardSize + 2;
        this.totalBoardSize = rowSize * rowSize;
        this.board = new Integer[totalBoardSize];
        this.insertedBlues = new LinkedHashSet<>();
        this.insertedReds = new LinkedHashSet<>();
        this.occupied = new LinkedHashSet<>();
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

    private boolean isTopEdge(int index){ return index < this.rowSize; }
    private boolean isBottomEdge(int index){ return index >= this.totalBoardSize - this.rowSize; }
    private boolean isLeftEdge(int index){ return index % this.rowSize == 0;}
    private boolean isRightEdge(int index){ return (index - (this.boardSize + 1)) % this.rowSize == 0; }

    public boolean isEdge(int index){
        return isTopEdge(index) || isBottomEdge(index)||  isLeftEdge(index) || isRightEdge(index);
    }

    public boolean blueContains(int position){
        return insertedBlues.contains(position);
    }

    public boolean redContains(int position){
        return insertedReds.contains(position);
    }

    public boolean playBlue(int position, boolean displayNeighbors){
        // take the position and find where its at in range not including the edges done
        // Then find all the neighbors of the position
        // Then union all neighbors with the position requested
        // Then add to the other array that keeps track of colors and fun stuff
        ArrayList<Integer> neighbors = getNeighborsBlue(convertToIndex(position));
        if(!this.occupied.contains(position)){
            this.occupied.add(position);
            this.insertedBlues.add(position);
        }

        if(displayNeighbors){
            System.out.println(neighbors.toString());
        }
        return true;
    }

    public boolean playRed(int position, boolean displayNeighbors){
        ArrayList<Integer> neighbors = getNeighborsRed(convertToIndex(position));
        if(!this.occupied.contains(position)){
            this.occupied.add(position);
            this.insertedReds.add(position);
        }
    
        if(displayNeighbors){
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

        if(!isRightEdge(right)){
            allNeighbors.add(board[right]);
            allNeighbors.add(board[topRight]);
        }
        if(!isLeftEdge(left)){
            allNeighbors.add(board[left]);
            allNeighbors.add(board[bottomLeft]);
        }

        allNeighbors.add(board[top]);
        allNeighbors.add(board[bottom]);

        // remove all duplicates
        Set<Integer> set = new LinkedHashSet<>();
        set.addAll(allNeighbors);
        allNeighbors.clear();
        allNeighbors.addAll(set);

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

        if(!isTopEdge(top)){
            allNeighbors.add(board[top]);
            allNeighbors.add(board[topRight]);
        }
        if(!isBottomEdge(bottom)){
            allNeighbors.add(board[bottom]);
            allNeighbors.add(board[bottomLeft]);
        }

        allNeighbors.add(board[left]);
        allNeighbors.add(board[right]);

        // remove all duplicates
        Set<Integer> set = new LinkedHashSet<>();
        set.addAll(allNeighbors);
        allNeighbors.clear();
        allNeighbors.addAll(set);

        return allNeighbors;
    }
}
