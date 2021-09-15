/**
 * 集合的时间复杂度分析
 *      LinkedListSet   BSTSet
 * 增    O(n)            O(logn) <平均> O(n) <最差>
 *
 * 查    O(n)            O(logn) <平均> O(n) <最差>
 *
 * 删    O(n)            O(logn) <平均> O(n) <最差>
 *
 * 当一颗只有左子树或右子树的二分搜索树, 它就退化成链表了, 复杂度也变成 O(n)了
 *
 * 二分搜索树的平均时间复杂度, 与其层数有关, O(h), h与n的关系
 *
 *      层数 : 元素数
 *       0  :  1
 *       1  :  2
 *       2  :  4
 *       3  :  8
 *       4  :  16
 *       ...
 *       h-1 : 2^(h-1)
 *
 *       h层, 一共多少节点 ?
 *       2^0 + 2^1 + 2^2 + 2^3 + 2^4 + ... + 2^h-1
 *           1 * (1-2^h)
 *        = --------------
 *           1 - 2
 *
 *        = 2^h - 1
 *
 *       2^h - 1 = n
 *             h = log2(n + 1)
 *               = O(log2n)
 */
public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

        prev.next = new Node(e,prev.next);
        size++;
    }

    public void addFirst(E e){
        add(0,e);
    }

    public E get(int index){
        
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");
            
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;

        return cur.e;
    }


    public boolean contains(E e){

        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }

        return false;
    }


    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public void removeElement(E e){

        Node prev = dummyHead;
        while (prev.next != null){
            if (prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if (prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }
}
