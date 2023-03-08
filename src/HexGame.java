import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.LinkedHashSet;

public class HexGame {
    private int boardSize; 
    private int rowSize; // The board size including edges
    private int totalBoardSize; // the board size squared 
    private Integer[] board; // Represents the board including edges
    private Set<Integer> insertedReds; // Keeps track of all red plays that have been inserted 
    private Set<Integer> insertedBlues; // Keeps track of all blue plays that have been inserted 
    private Set<Integer> occupied; // Keeps track of all occupied hexes 
    private DisjointSet redSet; // The red disjoint set 
    private DisjointSet blueSet; // the blue disjoint set 

    public HexGame(int boardSize){
        this.boardSize = boardSize;
        this.rowSize = boardSize + 2;
        this.totalBoardSize = rowSize * rowSize;
        this.board = new Integer[totalBoardSize];
        this.insertedBlues = new LinkedHashSet<>();
        this.insertedReds = new LinkedHashSet<>();
        this.occupied = new LinkedHashSet<>();
        this.redSet = new DisjointSet(totalBoardSize);
        this.blueSet = new DisjointSet(totalBoardSize);
        buildBoard();
    }

    /**
     * @return The created board including edges
     */
    public Integer[] getBoard(){
        return this.board;
    }

    /**
     * @return the boardSize
     */
    public int getBoardSize(){
        return this.boardSize;
    }

    /**
     * @param index a current index 
     * @return if the index is a topEdge then return true
     */
    private boolean isTopEdge(int index){ 
        return index < this.rowSize;
    }

    /**
     * @param index a current index 
     * @return if the index is a bottomEdge then return true
     */
    private boolean isBottomEdge(int index){ 
        return index >= this.totalBoardSize - this.rowSize; 
    }

    /**
     * @param index a current index 
     * @return if the index is a leftEdge then return true
     */
    private boolean isLeftEdge(int index){ 
        return index % this.rowSize == 0;
    }

    /**
     * @param index a current index 
     * @return if the index is a rightEdge then return true
     */
    private boolean isRightEdge(int index){ 
        return (index - (this.boardSize + 1)) % this.rowSize == 0; 
    }

    /**
     * @param index a current index 
     * @return if the index is an edge then return true
     */
    public boolean isEdge(int index){
        return isTopEdge(index) || isBottomEdge(index)||  isLeftEdge(index) || isRightEdge(index);
    }

    /**
     * @param position a position on the board, not an index of the board array
     * @return if the position is a blue play return true
     */
    public boolean blueContains(int position){
        return insertedBlues.contains(position);
    }

    /**
     * @param position a position on the board, not an index of the board array
     * @return if the position is a red play return true
     */
    public boolean redContains(int position){
        return insertedReds.contains(position);
    }

    /**
     * Calculates which indexes are edges and gives a specific value to blue edges and red edges
     */
    private void buildBoard(){
        int total = 1;
        for(int i = 0; i < board.length; i++){
            if(isTopEdge(i)){
                board[i] = (boardSize * boardSize) + 1;
            }else if(isBottomEdge(i)){
                board[i] = (boardSize * boardSize) + 2;
            }else if(isLeftEdge(i)){
                board[i] = (boardSize * boardSize) + 3;
            }else if(isRightEdge(i)){
                board[i] = (boardSize * boardSize) + 4;
            }else{
                board[i] = total;
                total++;
            }
        }
    }

    /**
     * Uses the occupied set to check if the given position is occupied
     * If the position is not occupied then all the neighbors are searched 
     * If a neighbor is occupied by a blue, then the current position is unioned with the occupied neighbor
     * 
     * @param position a position played by the blue player
     * @param displayNeighbors determines if the neighbors found is printed to the console
     * @return if find(leftEdge) == find(rightEdge) then blue has won and true is returned 
     */
    public boolean playBlue(int position, boolean displayNeighbors){
        ArrayList<Integer> neighbors = getNeighborsBlue(convertToIndex(position));
        
        if(!this.occupied.contains(position)){
            this.occupied.add(position);
            this.insertedBlues.add(position);

            for(Integer neighbor : neighbors){
                boolean isRightEdge = isRightEdge(Arrays.asList(board).indexOf(neighbor));
                boolean isLeftEdge = isLeftEdge(Arrays.asList(board).indexOf(neighbor));

                if(insertedBlues.contains(neighbor) || isLeftEdge){
                    blueSet.union(Arrays.asList(board).indexOf(neighbor), Arrays.asList(board).indexOf(position));
                }

                if(isRightEdge){
                    blueSet.union(Arrays.asList(board).indexOf(position), Arrays.asList(board).indexOf(neighbor));
                }
            } 
        }
        
        int rightEdge = Arrays.asList(board).indexOf((boardSize * boardSize) + 4);
        int leftEdge = Arrays.asList(board).indexOf((boardSize * boardSize) + 3);

        return blueSet.find(rightEdge) == blueSet.find(leftEdge);
    }

    /**
     * Uses the occupied set to check if the given position is occupied
     * If the position is not occupied then all the neighbors are searched 
     * If a neighbor is occupied by a red, then the current position is unioned with the occupied neighbor
     * 
     * @param position a position played by the blue player
     * @param displayNeighbors determines if the neighbors found is printed to the console
     * @return if find(leftEdge) == find(rightEdge) then red has won and true is returned 
     */
    public boolean playRed(int position, boolean displayNeighbors){
        ArrayList<Integer> neighbors = getNeighborsRed(convertToIndex(position));

        if(!this.occupied.contains(position)){
            this.occupied.add(position);
            this.insertedReds.add(position);

            for(Integer neighbor : neighbors){
                boolean isBottomEdge = isBottomEdge(Arrays.asList(board).indexOf(neighbor));
                boolean isTopEdge = isTopEdge(Arrays.asList(board).indexOf(neighbor));

                if(insertedReds.contains(neighbor) || isBottomEdge){
                    redSet.union(Arrays.asList(board).indexOf(neighbor), Arrays.asList(board).indexOf(position));
                }
                if(isTopEdge){
                    redSet.union(Arrays.asList(board).indexOf(position), Arrays.asList(board).indexOf(neighbor));
                } 
            }
        }

        int topEdge = Arrays.asList(board).indexOf((boardSize * boardSize) + 1);
        int bottomEdge = Arrays.asList(board).indexOf((boardSize * boardSize) + 2);
        
        return redSet.find(topEdge) == redSet.find(bottomEdge);
    }

    /**
     * Finds the index of the given position ignoring edges on the board
     * @param position A given position on the board
     * @return The index of the given position ignoring edges
     */
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

    /**
     * Finds all red neighbors of a given index
     * @param index an index to the list of all 
     * @return An ArrayList of all the neighbors found
     */
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

    /**
     * Finds all blue neighbors of a given index
     * @param index an index to the list of all 
     * @return An ArrayList of all the neighbors found
     */
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
