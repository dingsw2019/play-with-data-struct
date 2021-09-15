public class UnionFind4 implements UF{

    private int[] parent;

    // rank[i] 表示以 i 为根的集合所表示的树的层数
    private int[] rank;

    public UnionFind4(int size){

        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    // 找到元素p的跟节点
    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p Illegal");

        while (p != parent[p])
            p = parent[p];

        return p;
    }

    // 元素p与元素q是否在同一集合中
    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    // 元素p与元素q,各自所在集合合并
    // 深度小的集合向深度大的集合合并
    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        } else { // rank[pRoot] >= rank[qRoot]
            // 深度相等, 谁指向谁都可以
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}
