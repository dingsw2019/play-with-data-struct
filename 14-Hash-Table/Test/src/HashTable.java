import java.util.TreeMap;

public class HashTable<K,V> {

    // 扩容, 容忍度
    private static final int upperTol = 10;
    // 缩容, 容忍度
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;

    private TreeMap<K,V>[] hashtable;
    private int size;
    // 素数, 用于计算哈希值
    private int M;

    // 初始化哈希表
    public HashTable(int M){
        this.M = M;
        size = 0;

        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++)
            hashtable[i] = new TreeMap<>();

    }

    public HashTable(){
        this(initCapacity);
    }

    // 返回哈希值
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return size;
    }

    // 添加元素(key,value)
    public void add(K key, V value){
        // 计算哈希值
        TreeMap<K,V> map = hashtable[hash(key)];
        // 哈希值的key已存在, 更新value
        if (map.containsKey(key)){

            map.put(key,value);
        } else {
            // 哈希值key不存在, 新增value
            map.put(key,value);
            size++;

            // 扩容检查
            if (size >= upperTol * M)
                resize(2*M);
        }
    }

    // 删除元素
    public V remove(K key){
        TreeMap<K,V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)){
            ret = map.remove(key);
            size--;
            if (size < lowerTol * M && M / 2 >= initCapacity)
                resize(M/2);
        }

        return ret;
    }

    // 给 key 设置新值
    public void set(K key, V newValue){
        TreeMap<K,V> map = hashtable[hash(key)];
        if (!map.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist");

        map.put(key,newValue);
    }

    // 包含元素
    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    // 返回key的值
    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

    // 重置哈希表容量
    private void resize(int newM){
        TreeMap<K,V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i ++)
            newHashTable[i] = new TreeMap<>();

        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++){
            TreeMap<K,V> map = hashtable[i];
            for (K key : map.keySet())
                newHashTable[hash(key)].put(key,map.get(key));
        }

        this.hashtable = newHashTable;
    }
}
