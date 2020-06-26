/**
 * 数组实现线段树
 * @param <E>
 */
public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){

        this.merger = merger;
        data = (E[])new Object[arr.length];

        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0,0,arr.length-1);
    }

    // 在数组 index 位置上存储 [l,r] 范围的结果
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 结束条件
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        // 计算子节点索引位置
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        // 缩小范围, 递归构建线段树结构
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftChildIndex,l,mid);
        buildSegmentTree(rightChildIndex,mid+1,r);
        
        // 计算线段节点的值, 根据不同功能, 方法不同
        tree[treeIndex] = merger.merge(tree[leftChildIndex],tree[rightChildIndex]);
        
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index Illegal");

        return data[index];
    }

    private int leftChild(int index){
        return 2*index+1;
    }

    private int rightChild(int index){
        return 2*index+2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length - 1)
                res.append(", ");
        }

        res.append(']');
        return res.toString();
    }
}
