public class LinkedListSet<K extends Comparable<K>> implements Set<K> {

    private LinkedList<K,Object> data;

    public LinkedListSet(){
        data = new LinkedList<>();
    }

    @Override
    public int getSize(){
        return data.getSize();
    }

    @Override
    public boolean isEmpty(){
        return data.isEmpty();
    }

    @Override
    public void add(K key){
        data.add(key,null);
    }

    @Override
    public boolean contains(K key){
        return data.contains(key);
    }

    @Override
    public void remove(K key){
        data.remove(key);
    }

}
