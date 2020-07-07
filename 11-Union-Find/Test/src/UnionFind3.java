/**
 * 并查集
 */
public class UnionFind3 implements UF {

    // 记录父子节点关系
    private int[] parent;

    // 记录深度
    private int[] rank;

    public UnionFind3(int size){

        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i ++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    // 获取元素所在集合
    // 优化: 路径压缩
    public int find(int p){

        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p Illegal");

        while (p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }

        return p;
    }

    // 两个元素是否在一个集合
    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    // 合并两个元素所在集合
    // 优化：深度小向深度大的压缩, 能减少树的深度
    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        // 深度小的向深度大的合并
        if (rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if (rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else {// 深度一样, 谁合并谁都一样
            parent[qRoot] = pRoot;
            rank[pRoot]++;
        }
    }
}
