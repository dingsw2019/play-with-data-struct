public class MaxHeap2<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap2(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap2(){
        data = new Array<>();
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 计算父节点的索引
    private int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("index-0");
        return (index - 1) / 2;
    }

    // 左树节点的索引
    private int leftChild(int index){
        return 2*index+1;
    }

    // 右树节点的索引
    private int rightChild(int index){
        return 2*index+2;
    }

    // 添加节点, 节点添加到数组末尾, 然后上浮找到合适自己的位置
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    // 节点上浮
    private void siftUp(int k){

        while(k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0){
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
        // 最大值与最小值交换
        data.swap(0,data.getSize()-1);

        // 删除最大值
        data.removeLast();

        // 最小值下沉, 找到自己合适的位置
        siftDown(0);

        return ret;
    }

    // 节点下沉
    private void siftDown(int k){
        while(leftChild(k) < data.getSize()){
            // 取出最大值, 因为完全二叉树, j++ 就是右子树的索引
            int j = leftChild(k);
            if (j+1 < data.getSize() && data.get(j+1).compareTo(data.get(j)) > 0)
                j++;

            // k 与其最大的子节点比较大小, 如果大于等于子节点,中止执行
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            // 子节点大于父节点, 交换位置
            data.swap(k,j);
            k = j;
        }
    }

    // 替换最大值为e, 返回原最大值
    public E replace(E e){
        E ret = findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }
}
