/**
 * 数组实现并查集
 */
public class UnionFind1 implements UF{

    private int[] id;

    public UnionFind1(int size){

        id = new int[size];

        // 初始化, 每个元素都是一个独立的集合
        for (int i = 0; i < size; i++)
            id[i] = i;
    }

    @Override
    public int getSize(){
        return id.length;
    }

    // 查找p元素对应的集合编号, O(1)
    private int find(int p){
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("p Illegal");

        return id[p];
    }

    // 查找元素p和元素q是否所属一个集合, O(1)
    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合, O(n)
    @Override
    public void unionElements(int p, int q){

        int pID = find(p);
        int qID = find(q);

        if (pID == qID)
            return;

        // 合并过程需要遍历所有元素, 将两个元素的所属集合编码合并
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID)
                id[i] = qID;
    }
}
