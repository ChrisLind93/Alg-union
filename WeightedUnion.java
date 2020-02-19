public class WeightedUnion {
    private int[] id;
    private int[] size;
    private int[] height;
    private int count;
    private int[] maximum;
    
    public WeightedUnion(int N) {
        id = new int[N];
        size = new int[N];
        height = new int[N];
        maximum = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
            height[i] = 1;
            maximum[i] = i;
        }
    }
    
    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }
    
    private int root(int p) {
        while(p != id[p]) {
            p = id[p];
        }
        return p;
    }
    
    public boolean isAllConnected() {
        return count == 1;
    }
    
    public int maximumInSameComponent(int p) {
        return maximum[root(p)];
    }
    
    public void union(int p, int q){
        int rootP = root(p);
        int rootQ = root(q);
      
        if (rootP == rootQ) return;
      
        if (size[rootP] >= size[rootQ]) {
            size[rootP] += size[rootQ];
            id[rootQ] = rootP;
            if (maximum[rootQ] > maximum[rootP]) {
                maximum[rootP] = maximum[rootQ];
            }
        }else {
            size[rootQ] += size[rootP];
            id[rootP] = rootQ;
            if (maximum[rootP] > maximum[rootQ]) {
                maximum[rootQ] = maximum[rootP];
            }
        }
        count--;
    }
    
    public void unionByHeight(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
      
        if (rootP == rootQ) return;
      
        if (height[rootP] > height[rootQ]) {
            id[rootQ] = rootP;
        }else if (height[rootP] < height[rootQ]) {
            id[rootP] = rootQ;
        }else {
            id[rootQ] = rootP;
            height[rootP]++;
        }
    }
}