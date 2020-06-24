/**
 * 取出堆中最大元素
 *      1. 取出第一个元素, 就是最大的元素
 *      2. 将最后一个元素填充到第一个元素
 *      3. 找到 k 节点的左右节点的最大的一个节点
 *      4. 如果 k 节点小于子节点中最大的节点, 交换位置
 *
 *
 * heapify(堆化) : 将任意数组整理成堆的形状
 * 如何做?
 *      1.找到最后一个非叶子节点, 设为L节点(最后一个节点的父节点就是最后一个非叶子节点)
 *      2.然后遍历 L 到 0节点, 每个节点执行需要siftDown
 *      3.全部遍历完成, 数组变成最大堆
 *
 * 这种方式抛弃了所有叶子节点, 所以比遍历数组, 然后add到堆
 * 要更快的实现堆。
 * 将 n 个元素逐一插入到一个空堆中, 算法复杂度是O(nlogn)
 * heapify的过程, 算法复杂度为 O(n)
 */
public class MaxHeap<E extends Comparable<E>> {

   private Array<E> data;

   public MaxHeap(int capacity){
       data = new Array<>(capacity);
   }

   public MaxHeap(){
       data = new Array<>();
   }
   
   public MaxHeap(E[] arr){
       data = new Array<>(arr);
       for (int i = parent(arr.length-1); i >= 0; i--)
           siftDown(i);
   }

   public int size(){
       return data.getSize();
   }

   public boolean isEmpty(){
       return data.isEmpty();
   }

   private int parent(int index){
       if (index == 0)
           throw new IllegalArgumentException("index-0 doesn't have parent.");
       return (index-1)/2;
   }

   private int leftChild(int index){
       return index*2+1;
   }

   private int rightChild(int index){
       return index*2+2;
   }

   public void add(E e){
       data.addLast(e);
       siftUp(data.getSize()-1);
   }

   private void siftUp(int k){

       // 父节点的值小于当前节点的值, 交换节点, 然后继续向上比较
       while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
           // 交换节点
           data.swap(parent(k),k);
           // 向上比较
           k = parent(k);
       }
   }

   // 查堆中的最大元素
   public E findMax(){
        if(data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        return data.get(0);
   }

   // 取出堆中最大元素
   public E extractMax(){

       // 查看最大元素
       E ret = findMax();

       // 交换头尾元素
       data.swap(0,data.getSize()-1);
       data.removeLast();
       siftDown(0);

       return ret;
   }

   private void siftDown(int k){

       while (leftChild(k) < data.getSize()) {
           int j = leftChild(k); // data[k] 与 data[j]交换位置
           if (j + 1 < data.getSize() && data.get(j+1).compareTo(data.get(j)) > 0)
               j++;
           // data[j] 是 leftChild 和 rightChild 中的最大值

           if (data.get(k).compareTo(data.get(j)) >= 0)
               break;

           data.swap(k,j);
           k = j;
       }
   }

   // 取出堆中的最大元素, 并替换成元素e
   public E replace(E e){
       E ret = findMax();
       data.set(0,e);
       siftDown(0);
       return ret;
   }
}
