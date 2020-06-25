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

    public boolean isEmpty(){return data.isEmpty();}

    // 计算父节点的索引
    public int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("index-0");
        return (index - 1) / 2;
    }

    // 计算左子节点的索引
    public int leftChild(int index){
        return (index * 2) + 1;
    }

    // 计算右子节点的索引
    public int rightChild(int index){
        return (index * 2) + 2;
    }

    // 添加节点
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    // 向上比较
    public void siftUp(int k){
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }

    // 查看最大值
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

    // 向下移动, 与最大子节点交换
    public void siftDown(int k){
        while (leftChild(k) < data.getSize()){
            int j = leftChild(k);
            if (j+1 < data.getSize() && data.get(j+1).compareTo(data.get(j)) > 0)
                j++;

            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            data.swap(k,j);
            k = j;
        }
    }

    // 将堆中最大元素替换成 e
    public E replace(E e){
        E ret = findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }
}
