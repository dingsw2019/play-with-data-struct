public class RepeatQueue<E> implements Queue<E> {

    private Array<E> array;

    public RepeatQueue(){
        array = new Array<>();
    }

    public void enqueue(E e) {
        array.addLast(e);
    }

    public E dequeue() {
        return array.removeFirst();
    }

    public E getFront() {
        return array.getFirst();
    }
    
    public int getSize() {
        return array.getSize();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

}
