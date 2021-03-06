public class BSTset<K extends Comparable<K>> implements Set<K> {

    private BST<K,Object> data;

    public BSTset(){
        data = new BST<>();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void add(K key) {
        data.add(key,null);
    }

    @Override
    public void remove(K key) {
        data.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return data.contains(key);
    }

}
