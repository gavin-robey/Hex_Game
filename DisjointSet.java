public class DisjointSet {
    private int size;
    private Integer[] parent;
    private Integer[] numOfNodes;


    public DisjointSet(int size){
        this.size = size;
        this.parent = new Integer[size];
        this.numOfNodes = new Integer[size];
        buildTree();
    }

    public void print(){
        for(int i = 0; i < parent.length; i++){
            System.out.print(parent[i] + " ");
            if((i - (11 + 1)) % (11 + 2) == 0){
                System.out.println();
            }
        }
        // System.out.println(Arrays.toString(parent));
    }

    private void buildTree(){
        for(int i = 0; i < this.size; i++){
            numOfNodes[i] = -1;
            parent[i] = i;
        }
    }

    public Integer[] getParent(){
        return parent;
    }

    // smart union by size
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

    // Finds the root node and sets the current nodes parent to the root node
    public int find(int node){
        int foundParent = findParent(node);
        parent[node] = foundParent;
        return foundParent;
    }

    // recursivly finds the root node and returns the value of the root node
    private int findParent(int node){
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }
}