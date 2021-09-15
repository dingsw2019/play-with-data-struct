public class UnionFind4 implements UF{

    private int[] parent;
    private int[] rank;

    public UnionFind4(int size){
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i ++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int getSize() {
        return parent.length;
    }

    private int find(int k) {
        while (k != parent[k]) {
            parent[k] = parent[parent[k]];
            k = parent[k];
        }

        return k;
    }

    public boolean isConnected(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        return pRoot == qRoot;
    }


    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);
        
        if (pRoot == qRoot)
            return;

        if (rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else if (rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else {
            parent[pRoot] = qRoot;
            rank[pRoot]++;
        }
    }
}
