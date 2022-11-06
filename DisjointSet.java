public class DisjointSet {
    private int size;
    private Integer[] parent;
    private Integer[] numOfNodes; // Disjoint set using size of the root node


    public DisjointSet(int size){
        this.size = size;
        this.parent = new Integer[size];
        this.numOfNodes = new Integer[size];
        buildTree();
    }

    /**
     * Builds a new tree with default values equal to the index
     * The number of nodes will be set to -1
     */
    private void buildTree(){
        for(int i = 0; i < this.size; i++){
            numOfNodes[i] = -1;
            parent[i] = i;
        }
    }

    /**
     * @return the array of parents 
     */
    public Integer[] getParent(){
        return parent;
    }

    /**
     * If the nodes have the same root then nothing happens
     * If node1 is less than node2 then node2 becomes the parent of node1
     * If node2 is less than node1 then node1 becomes the parent of node2
     * Otherwise, node2 becomes the parent of node1
     * @param node1 The first node that is being unioned
     * @param node2 The second node that is being unioned
     */
    public void union(int node1, int node2){
        int root1 = find(node1);
        int root2 = find(node2);

        if(root1 == root2) {
            return;
        }

        if(numOfNodes[root1] > numOfNodes[root2]){
            parent[root1] = root2;
            numOfNodes[root2] = numOfNodes[root2] - 1;
        }

        else if (numOfNodes[root2] > numOfNodes[root1]){
            parent[root2] = root1;
            numOfNodes[root1] = numOfNodes[root1] - 1;
        }

        else{
            parent[root1] = root2;
            numOfNodes[root2] = numOfNodes[root2] - 1;
        }
    }

    /**
     * Calls the recursive findParent method and sets the root found to be the new parent of the given node
     * @param node The root node is searched from this given node
     * @return The root node of the given node
     */
    public int find(int node){
        int foundParent = findParent(node);
        parent[node] = foundParent;
        return foundParent;
    }

    /**
     * If the given node is not root node then recursively call find() until this condition is met
     * @param node The root node is searched from this given node
     * @return The root node of the given node
     */
    private int findParent(int node){
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }
}