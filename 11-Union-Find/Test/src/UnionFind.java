/**
 * 并查集实现
 * 优化1: 路径压缩, find 时,将节点A 挂靠到 其爷爷节点上,这样节点A与其父节点在同一层, 将 3层深度压缩为 2层
 * 优化2: 深度小的向深度大的集合合并, 已达到减少深度的目的。
 */
public class UnionFind implements UF{

    private int[] parent;
    private int[] rank;

    public UnionFind(int size){
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++){
            parent[i] = i;  // 初始化, 每个集合都有1个元素
            rank[i] = 1;    // 每个集合只有1个元素, 所以深度是 1
        }
    }

    // 查找元素p的根节点, (根节点指向自己, 所以根节点的父节点是其自己)
    // 优化1: 路径压缩
    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p Illegal");

        while (p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }

        return p;
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    // 元素p 与 元素q 是否在同一个集合中
    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    // 元素p 与 元素q所在集合合并
    // 优化2: 减少深度, 深度小的集合挂在深度大的集合上
    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        // 深度不同时, 无需增加深度, 最坏情况深度小的集合挂靠后和深度大的集合深度相同
        if (rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if (rank[qRoot] < rank[pRoot])
            parent[qRoot] = pRoot;
        else {// 深度相同, 谁挂靠谁都一样, 深度+1
            parent[qRoot] = pRoot;
            rank[pRoot]++;
        }
    }
}
