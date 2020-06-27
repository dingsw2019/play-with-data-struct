public class SegmentTree<E> {

    private E[] tree;
    private E[] data;

    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;

        data = (E[])new Object[arr.length];

        for(int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        tree = (E[])new Object[4*arr.length];
        buildSegmentTree(0,0,arr.length-1);
    }

    // 以 treeIndex 为根, 构建 [l,r] 的线段树
    private void buildSegmentTree(int treeIndex, int l, int r){
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r-l)/2;
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);
        
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    private int leftChild(int index){
        return 2*index+1;
    }
    private int rightChild(int index){
        return 2*index+2;
    }

    // 计算区间 [l,r] 的值
    public E query(int queryL, int queryR){
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("index Illegal");

        return query(0,0,data.length-1,queryL,queryR);
    }

    // 以 treeIndex 为根在[l,r]内, 搜索[queryL,queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if (l == queryL  && r ==queryR)
            return tree[treeIndex];

        int mid = l + (r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid+1)
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        else if (queryR <= mid)
            return query(leftTreeIndex,l,mid,queryL,queryR);

        E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
        E rightResult = query(rightTreeIndex,mid+1,r,mid+1,queryR);

        return merger.merge(leftResult,rightResult);
    }

    public void set(int index,E e){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("index Illegal");

        data[index] = e;
        set(0,0,data.length-1,index,e);
    }
    // 在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e){
        // 找到要修改的节点
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l +(r-l)/2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid+1)
            set(rightTreeIndex,mid+1,r,index,e);
        else // index <= mid
            set(leftTreeIndex,l,mid,index,e);

        // 更新父节点
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
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
