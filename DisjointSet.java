public class DisjointSet {
    private int size;
    private int[] subSet;

    public DisjointSet(int size){
        this.size = size;
        this.subSet = new int[size];
        buildTree();
    }

    public void print(){
        for(int i = 0; i < subSet.length; i++){
            System.out.println("|" + i + "|" + subSet[i] +"|");
        }
    }

    private void buildTree(){
        for(int i = 0; i < this.size; i++){
            subSet[i] = -1;
        }
    }

    // smart union by size
    public void union(int node1, int node2){
        if(subSet[node1] <= subSet[node2]){
            subSet[node2] += subSet[node1];
            subSet[node1] = node2;
        }else{
            int parent = subSet[node1];
            if(parent >= 0){
                subSet[parent] += subSet[node2];
                subSet[node2] = subSet[node1];
            }else{
                subSet[node2] += subSet[node1];
                subSet[node1] = node2;
            }
        }
    }

    public int find(int node){
        if(subSet[node] > -1){
            subSet[node] = find(subSet[node]);
        }
        return node;   
    }
}