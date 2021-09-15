/**
 * 映射, 一般指一对一的关系,
 *  例如函数f(x) = 2*x+1, x 与 y就是 1对1的关系
 *      x = 1, y = 3
 *      x = 2, y = 5
 *      x = 3, y = 7
 *      x = 4, y = 9
 *
 * 字典更形象一些, 单词 ----> 释意
 * 其他应用
 *   名册      身份证号 ----> 人
 *   车辆管理   车牌号   ----> 车
 *   数据库     id     ----> 信息
 *   词频统计   单词    -----> 频率
 *
 * 字典的结构是键值对( Key,Value )
 * 根据键, 寻找值
 */
public interface Map<K, V> {

    void add(K key, V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key, V newValue);
    int getSize();
    boolean isEmpty();
}
