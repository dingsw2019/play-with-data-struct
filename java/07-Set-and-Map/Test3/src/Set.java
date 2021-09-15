public interface Set<K> {

    int getSize();
    boolean isEmpty();
    void add(K key);
    void remove(K key);
    boolean contains(K key);
}
