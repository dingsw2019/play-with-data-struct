/**
 * 最大堆
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    // node 的父节点的索引
    private int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("index-0");
        return (index-1)/2;
    }

    // node 的左子树的索引
    private int leftChild(int index){
        return 2*index+1;
    }

    // node 的右子树的索引
    private int rightChild(int index){
        return 2*index+2;
    }

    // 添加节点
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    // 节点上浮
    private void siftUp(int k){
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }

    // 返回最大值
    public E findMax(){
        if (data.isEmpty())
            throw new IllegalArgumentException("empty");
        return data.get(0);
    }

    // 弹出最大值
    public E extractMax(){
        E ret = findMax();
        data.swap(0,data.getSize()-1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    // 从节点 k 开始执行下沉
    private void siftDown(int k){
        while (leftChild(k) < data.getSize()) {
            // k的左右子树比较, 找出最大的子树
            int j = leftChild(k);
            if (j+1 < data.getSize() && data.get(parent(k)).compareTo(data.get(k)) < 0)
                j++;

            // 子树与 k 比较, k小就向下走
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            // k 向下走
            data.swap(k,j);
            k = j;
        }
    }

    // 最大值更新为 e
    public E replace(E e){
        E ret = findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }
}
