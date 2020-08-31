public class RepeatStack<E> implements Stack<E>{

    private Array<E> array;
    
    public RepeatStack(){
        array = new Array<>();
    }

    public int getSize(){
        return array.getSize();
    }

    public boolean isEmpty(){
        return array.isEmpty();
    }

    public void push(E e){
        array.addLast(e);
    }

    public E pop(){
        return array.removeLast();
    }

    public E peek(){
        return array.getLast();
    }

}
