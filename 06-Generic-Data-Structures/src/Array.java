import java.util.Objects;

/**
 * 数组
 * 优点：快速定位, 索引 O(1) 查找
 * 缺点：添加,删除需要移动数据。数据量大时不可接受
 *
 * 第一版数组, 只能存放整型. 但数组作为一种数据结构, 应该支持存放任意类型数据
 * 而可以存放任意类型的结构,包含用户自定义类型, 称为泛型。
 *
 * Java 泛型需要传入类对象(基本数据类型也要传入类对象)
 *
 * 基本数据类型的类对象:
 * Boolean, Byte, Char, Short, Integer, Long, Float, Double
 *
 * 动态数组
 *  长度为4的数组A: [66,88,99,100]
 *  创建一个新数组B,长度是原来的2倍, [null,null,null,null,null,null,null,null]
 *  将数组A中元素, 移动到数组B
 *  将指针指向数组B, 系统会自动回收数组A
 *
 * 简单的时间复杂度
 *  O(1), O(n), O(lgn), O(nlogn), O(n^2)
 *  大O描述的是算法的运行时间和输入数据之间的关系
 *
 *  addLast(e)      O(1)
 *  addFirst(e)     O(n)
 *  add(index,e)    头部添加O(n), 尾部添加O(1), 综合来看, O(n/2) = O(n)
 *  整体来看, 添加函数的时间复杂度是 O(n), 综合看就看最坏的情况, 所以是 O(n)
 *
 *  removeLast(e)   O(1)
 *  removeFirst(e)  O(n)
 *  remove(index,e) O(n/2) = O(n)
 *  整体来看, 删除函数的时间复杂度是 O(n)
 *
 *  resize(n)       O(n)
 *
 *  set(index,e)    O(1)
 *  get(index)      O(1)
 *  contains(e)     O(n)
 *  find(e)         O(n)
 *
 *  整体来看
 *      增   O(n)
 *      删   O(n)
 *      改   已知索引 O(1), 未知索引 O(n)
 *      查   已知索引 O(1), 未知索引 O(n)
 *
 *
 * resize 的复杂度分析
 *
 *  添加操作由以下方法组成, 如果只有addLast, 那么添加的时间复杂度是O(1), 但是因为
 *  resize是 O(n)的, 所以添加整体是 O(n)的, 但是 resize 不是每次都调用, 所以 O(n)并不合适
 *  可以使用均摊复杂度计算
 *      addLast(e)      O(1)
 *      addFirst(e)     O(n)
 *      add(index,e)    头部添加O(n), 尾部添加O(1), 综合来看, O(n/2) = O(n)
 *      resize(n)       O(n)
 *
 *  均摊复杂度, 计算 resize 的时间复杂度
 *      假设 capacity=8, 9次addLast操作, 触发1次resize,
 *      resize总共进行了 17次基本操作(原数组8次取数据, 新数组9次添加数据)
 *      平均每次 addLast操作, 进行2次基本操作 (17/9)
 *
 *      假设 capacity = n, n+1次addLast, 触发resize, 总共进行 2n+1次基本操作(n+n+1)
 *      平均每次addLast操作, 进行2次基本操作 (2n+1)/(n+1)
 *
 *  如果数组添加只使用 addLast, 再加上均摊复杂度看 resize, 那么添加的时间复杂度是 O(1) 的
 *
 *
 * 复杂度震荡
 *   addLast 将数组从 10扩容到 20, 然后调用removeLast, 数组又从 20 变成 10了
 *   这种反复容量边界来回扩容缩容的操作,就会导致添加和删除的时间复杂度变成 O(n)
 *
 *   出现这种问题的原因：removeLast 时 resize 过于着急 (Eager)
 *   解决方案：remove时使用 Lazy 方式, 不要一超过限制就缩容, 给个缓冲区
 *           比如, 当 size == capacity/4 时, 才将capacity减半.
 *           此时 capacity 还有 一般空间无元素
 *           将扩容与缩容的阈值, 拉开差距, 就可避免震荡
 *
 */
public class Array<E> {

    // 存储数据
    private E[] data;

    // 已用长度
    private int size;

    // 构造函数, 初始化数组参数
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }


    // 无参数的构造函数
    public Array(){
        this(10);
    }

    // 获取数组长度
    public int getCapacity() {
        return data.length;
    }

    // 获取已用长度
    public int getSize() {
        return size;
    }

    // 是否空数组
    public boolean isEmpty(){
        return size == 0;
    }

    // 在指定索引添加元素
    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        // 扩容, 扩容固定值,
        // 固定值大,在总量小的场景太浪费
        // 固定值小, 在总量大的场景频率太高
        if (size == data.length) {
            resize(2*data.length);
        }

        // 移动index之后的元素
        for (int i = size-1; i >= index; i--) {
            data[i+1] = data[i];
        }

        // 添加新元素
        data[index] = e;
        size++;
    }

    // 数组尾添加元素
    public void addLast(E e) {
        add(size,e);
    }

    // 数组头添加元素
    public void addFirst(E e) {
        add(0,e);
    }

    // 获取index索引位置的元素
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. index is illegal");

        return data[index];
    }


    // 修改index索引位置的元素
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. index is illegal.");

        data[index] = e;
    }


    // 数组是否包含元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }

        return false;
    }


    // 查找元素并返回其索引
    // 元素不存在, 返回 -1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }


    // 删除index位置的元素, 返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = data[index];
        for (int i=index+1; i<size; i++)
            data[i-1] = data[i];

        size--;
        data[size] = null;

        // 缩容
        if (size == (data.length / 4) && (data.length / 2) != 0)
            resize(data.length/2);

        return ret;
    }


    // 删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    // 删除最后一个元素
    public E removeLast() {
        return remove(size-1);
    }

    // 删除元素
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    // Override, 告诉编辑器这是一个覆盖父类的方法,
    // 如果父类不存在此方法, 会提示
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    // 重新申请数组内存空间
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        for (int i=0; i<size; i++)
            newData[i] = data[i];
        data = newData;
    }
}
