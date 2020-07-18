public class SegmentTree4<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree4(E[] arr, Merger<E> merger) {

        int n = arr.length;
        this.merger = merger;
        data = (E[])new Object[n];
        for (int i = 0; i < n; i ++)
            data[i] = arr[i];

        tree = (E[])new Object[4 * n];
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
            tree[treeIndex] = data[l];
            return;
        }

        int leftIndexTree = leftChild(treeIndex);
        int rightIndexTree = rightChild(treeIndex);

        int mid = l + (r-l)/2;
        buildSegmentTree(leftIndexTree,l,mid);
        buildSegmentTree(rightIndexTree,mid+1,r);

        tree[treeIndex] = merger.merge(tree[leftIndexTree],tree[rightIndexTree]);
    }

    public E query(int queryL, int queryR){
        if (queryL < 0 || queryL >= data.length ||
            queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("index illegal");

        return query(0,0,data.length-1,queryL,queryR);
    }

    private E query(int treeIndex,int l,int r,int queryL,int queryR) {
        if (l == queryL && r == queryR)
            return tree[treeIndex];

        int leftIndexTree = leftChild(treeIndex);
        int rightIndexTree = rightChild(treeIndex);

        int mid = l + (r-l)/2;
        if (queryL >= mid+1) {
            return query(rightIndexTree,mid+1,r,queryL,queryR);
        } else if (queryR <= mid) {
            return query(leftIndexTree,l,mid,queryL,queryR);
        }
        E leftResult = query(leftIndexTree,l,mid,queryL,mid);
        E rightResult = query(rightIndexTree,mid+1,r,mid+1,queryR);

        return merger.merge(leftResult,rightResult);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("index Illegal");
        data[index] = e;
        set(0,0,data.length-1,index,e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int leftIndexTree = leftChild(treeIndex);
        int rightIndexTree = rightChild(treeIndex);

        int mid = l + (r-l)/2;
        if (index >= mid+1)
            set(rightIndexTree,mid+1,r,index,e);
        else
            set(leftIndexTree,l,mid,index,e);

        tree[treeIndex] = merger.merge(tree[leftIndexTree],tree[rightIndexTree]);
    }

    @Override
    public String toString(){
        StringBuilder res= new StringBuilder();
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
