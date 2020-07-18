// 最大堆
public class MaxHeap<E extends Comparable<E>> {

    private E[] data;
    private int size;
    private int capacity;

    // 构建堆
    public MaxHeap(int capacity){
        size = 0;
        this.capacity = capacity;
        data = (E[])new Comparable[capacity];
    }

    public MaxHeap(){
        this(10);
    }

    // 堆化
    public MaxHeap(E[] arr){
        int size = arr.length;
        data = (E[]) new Object[size];
        for (int i = parent(size-1); i >= 0; i --)
            shiftDown(i);

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int parent(int index){
        return (index-1)/2;
    }

    private int leftChild(int index){
        return 2*index+1;
    }

    private int rightChild(int index){
        return 2*index+2;
    }

    public void add(E e){
        assert size + 1 < capacity;
        data[size] = e;
        shiftUp(size++);
    }

    private void shiftUp(int k){
        E e = data[k];
        while(k > 0 && e.compareTo(data[parent(k)]) > 0) {
//            swap(k,parent(k));
            data[k] = data[parent(k)];
            k = parent(k);
        }
        data[k] = e;
    }

    public E findMax(){
        if (isEmpty())
            throw new IllegalArgumentException("empty");
        return data[0];
    }

    public E extractMax(){

        E ret = data[0];
        swap(0,size-1);

        size--;
        shiftDown(0);

        return ret;
    }

    private void shiftDown(int k){
        E e = data[k];
        while (leftChild(k) < size) {
            int j = leftChild(k);
            if (j+1 < size && data[j+1].compareTo(data[j]) > 0)
                j++;

            if (e.compareTo(data[j]) >= 0) break;

//            swap(k,j);
            data[k] = data[j];
            k = j;
        }
        data[k] = e;
    }

    private void swap(int i, int j) {
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    public E replace(E e){
        E ret = findMax();
        data[0] = e;
        shiftDown(0);
        return ret;
    }

    public static void main(String[] args) {

        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(100);
        int N = 100; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for( int i = 0 ; i < N ; i ++ )
            maxHeap.add( new Integer((int)(Math.random() * M)) );

        Integer[] arr = new Integer[N];
        // 将maxheap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从大到小的顺序取出来的
        for( int i = 0 ; i < N ; i ++ ){
            arr[i] = maxHeap.extractMax();
            System.out.print(arr[i] + " ");
        }

        System.out.println();

        // 确保arr数组是从大到小排列的
        for( int i = 1 ; i < N ; i ++ )
            assert arr[i-1] >= arr[i];
    }
}
