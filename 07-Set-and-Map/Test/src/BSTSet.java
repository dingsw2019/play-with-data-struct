public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> data;

    public BSTSet(){
        data = new BST<>();
    }

    @Override
    public int getSize(){
        return data.size();
    }

    @Override
    public boolean isEmpty(){
        return data.isEmpty();
    }

    @Override
    public void add(E e){
        data.add(e);
    }

    @Override
    public void remove(E e){
        data.remove(e);
    }

    @Override
    public boolean contains(E e){
        return data.contains(e);
    }
}
