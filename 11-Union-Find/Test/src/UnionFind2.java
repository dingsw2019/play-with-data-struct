/**
 * 并查集, 适合集合合并, 无需像数组遍历添加
 * 并查集使用树结构, 数据将两颗树挂在一起就可以了
 *
 * 时间复杂度比 logN还快, 树深度越小越快
 */
public class UnionFind2 implements UF{

    // 子父节点关联关系
    private int[] parent;

    // 集合的深度
    private int[] rank;

    public UnionFind2(int size){

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

    // 返回元素p 的根节点
    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p Illegal");

        while (p != parent[p]){
            // 路径压缩优化
            parent[p] = parent[parent[p]];
            p = parent[p];
        }

        return p;
    }

    // 元素p和q 是否在同一集合中
    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    // 合并元素p和q所在的集合
    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        // 深度小的向深度大的合并, 以减少深度
        if (rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if (rank[qRoot] < rank[pRoot])
            parent[qRoot] = pRoot;
        else { // 深度相同, 深度+1
            parent[qRoot] = pRoot;
            rank[pRoot]++;
        }
    }
}
