public class SegmentTree5<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree5(E[] arr, Merger<E> merger) {

        this.merger = merger;
        int n = arr.length;

        data = (E[])new Object[n];
        data = arr;
        for (int i = 0; i < n; i ++)
            data[i] = arr[i];

        tree = (E[])new Object[4*n];
        buildSegmentTree(0,0,n-1);
    }

    private int leftChild(int index){
        return 2*index+1;
    }

    private int rightChild(int index){
        return 2*index+2;
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {

        if (l == r) {
            data[treeIndex] = data[l];
            return;
        }

        int mid = l + (r-l)/2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        buildSegmentTree(leftChildIndex,l,mid);
        buildSegmentTree(rightChildIndex,mid+1,r);

        tree[treeIndex] = merger.merge(tree[leftChildIndex],tree[rightChildIndex]);
    }

    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL > data.length ||
            queryR < 0 || queryR > data.length || queryL > queryR)
            throw new IllegalArgumentException("index Illegal");

        return query(0,0,data.length-1,queryL,queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {

        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        int mid = l + (r-l)/2;
        // 全在左边
        if (queryR <= mid) {
            return query(leftChildIndex,l,mid,queryL,queryR);

        // 全在右边
        } else if (queryL >= mid+1){

            return query(rightChildIndex,mid+1,r,queryL,queryR);
        } else {

            E leftResult = query(leftChildIndex,l,mid,queryL,mid);
            E rightResult = query(leftChildIndex,mid+1,r,mid+1,queryR);

            return merger.merge(leftResult,rightResult);
        }
    }

    public void set(int index, E e) {

        data[index] = e;
        set(0,0,data.length-1,index,e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {

        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r-l)/2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        if (index >= mid+1)
            set(rightChildIndex,mid+1,r,index,e);
        else
            set(leftChildIndex,l,mid,index,e);

        tree[treeIndex] = merger.merge(tree[leftChildIndex],tree[rightChildIndex]);
    }
}
