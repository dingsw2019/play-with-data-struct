/**
 * 线段树, 适合区间操作, 如区间内的最大值,最小值,平均值,求和
 * 原始数据存储在叶子节点, 非叶子节点都是对区间的处理
 * 线段树是一个平衡二叉树，最大深度与最小深度相差 1
 */
public class SegmentTree2<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree2(E[] arr, Merger<E> merger){
        this.merger = merger;

        data = (E[])new Object[arr.length];
        // 复制值到data
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        // 申请树空间, 4倍 数组空间
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0,0,arr.length-1);
    }

    private int leftChild(int index){
        return 2*index+1;
    }
    private int rightChild(int index){
        return 2*index+2;
    }

    // 在 tree 的 treeIndex索引位, 计算区间[l,r]的值, 并存入节点, 递归算法
    private void buildSegmentTree(int treeIndex, int l, int r){

        // 递归结束条件
        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }

        // 计算 treeIndex 的左右子节点的索引位置
        int leftIndexTree = leftChild(treeIndex);
        int rightIndexTree = rightChild(treeIndex);

        // 计算区间中点, 用于分割区间
        int mid = l + (r-l)/2;
        // 处理左子树
        buildSegmentTree(leftIndexTree,l,mid);
        // 处理右子树
        buildSegmentTree(rightIndexTree,mid+1,r);

        // 计算区间和
        tree[treeIndex] = merger.merge(tree[leftIndexTree],tree[rightIndexTree]);
    }

    // 查询区间在 [queryL,queryR] 的值
    public E query(int queryL, int queryR){
        if (queryL < 0 || queryL >= data.length ||
            queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("index Illegal");

        return query(0,0,data.length-1,queryL,queryR);
    }
    // 在以 treeIndex 为根的树的 [l,r]区间, 搜索[queryL,queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR){

        // 结束条件
        if (l == queryL && r == queryR)
            return tree[treeIndex];

        // 计算 treeIndex 的左右子节点的索引
        int leftIndexTree = leftChild(treeIndex);
        int rightIndexTree = rightChild(treeIndex);

        // 计算区间中点, 用于分割区间
        int mid = l + (r-l)/2;

        // 搜索区间全部在右子树
        if (queryL >= mid + 1)
            return query(rightIndexTree,mid+1,r,queryL,queryR);
        // 搜索区间全部在左子树
        else if (queryR <= mid)
            return query(leftIndexTree,l,mid,queryL,queryR);
        // 搜索区间在左右子树都有
        else {

            // 左子树
            E leftResult = query(leftIndexTree,l,mid,queryL,mid);
            // 右子树
            E rightResult = query(rightIndexTree,mid+1,r,mid+1,queryR);

            return merger.merge(leftResult,rightResult);
        }
    }

    // 更新tree中索引为 index 的值, 并更新其上层节点的值
    public void set(int index, E e){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("index Illegal");

        data[index] = e;
        set(0,0,data.length-1,index,e);
    }

    private void set(int treeIndex, int l, int r, int index, E e){
        // 结束条件
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        // 计算 treeIndex 的左右子节点的索引
        int leftIndexTree = leftChild(treeIndex);
        int rightIndexTree = rightChild(treeIndex);

        // 计算区间中点, 用于分割区间
        int mid = l + (r-l)/2;

        // index 在右子树
        if (index >= mid + 1)
            set(rightIndexTree,mid+1,r,index,e);
        // index 在左子树
        else // index <= mid
            set(leftIndexTree,l,mid,index,e);

        // 更新上层节点的值
        tree[treeIndex] = merger.merge(tree[leftIndexTree],tree[rightIndexTree]);
    }
}
