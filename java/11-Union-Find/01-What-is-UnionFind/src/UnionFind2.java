public class UnionFind2 implements UF{

    private int[] parent;

    public UnionFind2(int size){

        parent = new int[size];

        // 初始化, 每个集合只有一个元素
        for (int i = 0; i < size; i++)
            parent[i] = i;
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    // 查找元素p的根节点, O(h)
    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound");

        while(p != parent[p])
            p = parent[p];

        return p;
    }

    // 元素p与元素q是否在同一个集合, O(h)
    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    // 合并元素p和元素q的集合, O(h)
    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        parent[pRoot] = qRoot;
    }
}
