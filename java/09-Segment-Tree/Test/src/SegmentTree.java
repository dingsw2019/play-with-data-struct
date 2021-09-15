/**
 * 线段树是一个平衡二叉树, 平衡二叉树的最大深度与最小深度相差1
 * 线段树适合区间操作, 在一个区间内求最大值,最小值,求和等
 */
public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr,Merger<E> merger){

        this.merger = merger;

        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        tree = (E[])new Object[4*arr.length];
        buildSegmentTree(0,0,arr.length-1);
    }

    // 计算 index 节点的左子节点的索引位置
    // 前提是树是一颗完全二叉树, 根节点索引为 0
    private int leftChild(int index){
        return 2*index+1;
    }

    // 计算 index 节点的右子节点的索引位置
    // 前提是树是一颗完全二叉树, 根节点索引为 0
    private int rightChild(int index){
        return 2*index+2;
    }

    // 在 treeIndex 的索引位置存储 [l,r] 范围的值
    // 构建一颗线段树, 递归算法
    private void buildSegmentTree(int treeIndex, int l, int r){

        // 结束条件, 当区间的左右边界相等时
        // 表明已经无法拆分, 赋值给线段树的叶子节点
        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }

        // 计算 treeIndex 的左右子节点的索引位置
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 计算中间索引, 将区间平分
        int mid = l + (r-l) / 2; // 防溢出的方式

        // 递归, 先计算最靠近叶子节点的和
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);

        // 将左右子树的值相加就是和了
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    // 计算区间 [queryL,queryR] 的和
    public E query(int queryL, int queryR){
        if (queryL < 0 || queryL >= data.length ||
            queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("index Illegal");

        return query(0,0,data.length-1,queryL,queryR);
    }

    // 以 treeIndex 为根, 在[l,r]区间内搜索 [queryL,queryR] 的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR){

        // 结束递归的条件
        if (l == queryL && r == queryR)
            return tree[treeIndex];

        // 计算 treeIndex 的左右子节点的索引位置
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 计算[l,r]的中间索引
        int mid = l + (r-l)/2;

        // 搜索的范围全部在 treeIndex 的右子树
        if (queryL >= mid+1){
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        // 搜索的范围全部在 treeIndex 的左子树
        } else if (queryR <= mid) {
            return query(leftTreeIndex,l,mid,queryL,queryR);
        // 搜索的范围存在左右子树
        } else {

            // 获取左子树搜索范围的值, queryR已经大于mid了, 所以搜索的最大边界是mid
            E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
            // 获取右子树搜索范围的值, 右子树的起始索引是mid, 已经不是queryL了
            E rightResult = query(rightTreeIndex,mid+1,r,mid+1,queryR);

            return merger.merge(leftResult,rightResult);
        }
    }

    // 更新 index 索引位置的的值, 同时更新其上层节点
    public void set(int index, E e){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("index Illegal");
        data[index] = e;
        set(0,0,data.length-1,index,e);
    }

    // 递归实现, 更新 index 索引位置的的值, 同时更新其上层节点
    private void set(int treeIndex, int l, int r, int index, E e){

        // 递归结束条件, 找到叶子节点
        if (l == r){
            tree[treeIndex] = e;
            return;
        }

        // 计算 treeIndex 的左右子节点的索引位置
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 计算[l,r]的中间索引
        int mid = l + (r-l)/2;

        // index 在右子树
        if (index >= mid + 1)
            set(rightTreeIndex,mid+1,r,index,e);
        // index 在左子树
        else // index <= mid
            set(leftTreeIndex,l,mid,index,e);

        // 更新index这条路径上, 所有节点的值
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

}
