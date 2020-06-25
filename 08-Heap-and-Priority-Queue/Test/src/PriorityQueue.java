public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> mapHeap;

    public PriorityQueue(){
        mapHeap = new MaxHeap<>();
    }

    @Override
    public int getSize(){
        return mapHeap.size();
    }

    @Override
    public boolean isEmpty(){
        return mapHeap.isEmpty();
    }

    @Override
    public E getFront(){
        return mapHeap.findMax();
    }

    @Override
    public void enqueue(E e){
        mapHeap.add(e);
    }

    @Override
    public E dequeue(){
        return mapHeap.extractMax();
    }

}
