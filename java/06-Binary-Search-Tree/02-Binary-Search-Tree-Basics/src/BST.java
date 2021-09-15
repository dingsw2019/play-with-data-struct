/**
 * 为什么要用树结构
 *      将数据使用树结构存储后, 出奇的高效
 *      二分搜索树 (Binary Search Tree)
 *      平衡二叉树: AVL; 红黑树
 *      堆; 并查集
 *      线段树; Trie(字典树, 前缀树)
 */
public class BST<E extends Comparable<E>> {
    
    private class Node {
        public E e;
        public Node left, right;
        
        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }
    
    private Node root;
    private int size;
    
    public BST(){
        root = null;
        size = 0;
    }
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
}
