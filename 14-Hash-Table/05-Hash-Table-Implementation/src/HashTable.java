import java.util.TreeMap;

public class HashTable<K, V> {

    // 扩容, 容忍度
    private static final int upperTol = 10;
    // 缩容, 容忍度
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;

    private TreeMap<K,V>[] hashtable;
    // 节点数量
    private int size;
    // 素数, 用于计算哈希值
    private int M;

    public HashTable(int M){
        this.M  = M;
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

    public int getSize(){return size;}

    // 添加节点到哈希表
    public void add(K key, V value){
        // 计算哈希值
        TreeMap<K,V> map = hashtable[hash(key)];

        if (map.containsKey(key)) {
            // key 已存在, 更新 value
            map.put(key,value);

        } else {
            // key 不存在, 添加
            map.put(key,value);
            size++;

            // 扩容
            if (size >= upperTol * M)
                resize(2*M);
        }
    }

    // 删除节点key
    public V remove(K key){
        // 计算哈希值,并查找
        TreeMap<K,V> map = hashtable[hash(key)];
        V ret = null;
        // 节点存在,删除它
        if (map.containsKey(key)){
            ret = map.remove(key);
            size--;

            if (size < lowerTol*M && M / 2 >= initCapacity)
                resize(M / 2);
        }
        return ret;
    }

    // 给key设置新值
    public void set(K key, V newValue){
        TreeMap<K,V> map = hashtable[hash(key)];
        // key不存在
        if (!map.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist");
        map.put(key,newValue);
    }

    // 是否包含节点key
    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    // 返回key的值
    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

    private void resize(int newM){
        // 生成新数组
        TreeMap<K,V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++)
            newHashTable[i] = new TreeMap<>();

        int oldM = M;
        this.M = newM;
        // 移动数据
        for (int i = 0; i < oldM; i++) {
            TreeMap<K,V> map = hashtable[i];
            for (K key: map.keySet())
                newHashTable[hash(key)].put(key,map.get(key));
        }

        this.hashtable = newHashTable;
    }
}
