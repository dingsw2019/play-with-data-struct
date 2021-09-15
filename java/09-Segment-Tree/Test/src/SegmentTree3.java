public class SegmentTree3<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree3(E[] arr, Merger<E> merger){
        this.merger = merger;

        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i ++){
            data[i] = arr[i];
        }

        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0,0,arr.length-1);
    }

    private int leftChild(int index){
        return 2*index+1;
    }

    private int rightChild(int index){
        return 2*index+2;
    }

    // 构建线段树结构
    private void buildSegmentTree(int treeIndex, int l, int r){

        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftIndexTree = leftChild(treeIndex);
        int rightIndexTree = rightChild(treeIndex);

        // 缩小范围
        int mid = l + (r-l)/2;
        buildSegmentTree(leftIndexTree,l,mid);
        buildSegmentTree(rightIndexTree,mid+1,r);

        // 计算线段树的和
        tree[treeIndex] = merger.merge(tree[leftIndexTree],tree[rightIndexTree]);
    }

    // 查询区间
    public E query(int queryL,int queryR){
        if (queryL < 0 || queryL >= data.length ||
            queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("index Illegal");

        return query(0,0,data.length-1,queryL,queryR);
    }

    // 在以treeIndex为根的树中, 在[l,r]区间内, 查找[queryL,queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if (l == queryL && r == queryR){
            return tree[treeIndex];
        }

        int leftIndexTree = leftChild(treeIndex);
        int rightIndexTree = rightChild(treeIndex);

        // 缩小范围
        int mid = l + (r-l)/2;

        if (queryL >= mid+1) {
            // 全在右树
            return query(rightIndexTree,mid+1,r,queryL,queryR);
        }
        else if (queryR <= mid) {
            // 全在左树
            return query(leftIndexTree,l,mid,queryL,queryR);
        }  else {
        // 左右树都有
            E leftResult = query(leftIndexTree,l,mid,queryL,mid);
            E rightResult = query(rightIndexTree,mid+1,r,mid+1,queryR);

            return merger.merge(leftResult,rightResult);
        }
    }

    public void set(int index, E e){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("index Illegal");
        data[index] = e;
        set(0,0,data.length-1,index,e);
    }

    private void set(int treeIndex, int l, int r, int index, E e){
        if (r == l) {
            tree[treeIndex] = e;
            return;
        }

        int leftIndexTree = leftChild(treeIndex);
        int rightIndexTree = rightChild(treeIndex);

        // 缩小范围
        int mid = l + (r-l)/2;

        if (index >= mid + 1)
            set(rightIndexTree,mid+1,r,index,e);
        else
            set(leftIndexTree,l,mid,index,e);

        tree[treeIndex] = merger.merge(tree[leftIndexTree],tree[rightIndexTree]);
    }

}
