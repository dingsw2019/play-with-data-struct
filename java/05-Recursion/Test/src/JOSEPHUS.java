/**
 * 约瑟夫环
 */
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Random;
public class JOSEPHUS {

    public void test(int n){

        CircularDummyLinkedList<Integer> linkedList = new CircularDummyLinkedList<>();

        // 生成 n 个数
        for (int i = 0; i < n; i++)
            linkedList.addLast(i);

        System.out.println("生成链表：" + linkedList);

        // 记录移除元素
        int [] remove = new int[n];
        Random random = new Random();
        int i = 0, offset = 0;

        // 获取随机的起始元素
        Node<Integer> startNode = linkedList.getRandomNode();

        // 随机步数
        do{
            offset = random.nextInt(linkedList.getSize());
        }while(offset == 0);

        while (!linkedList.isEmpty()) {

            // 计算删除的元素的索引
            Node<Integer> remNode = linkedList.getOffsetNode(startNode,offset);
            System.out.print("startNode: " + startNode.e  + ", offset: " + offset + ", remNode:" + remNode.e);

            // 下一次的开始元素
            startNode = linkedList.getNextNode(remNode);

            // 删除元素
            int remVal = linkedList.removeNode(remNode);

            // 记录删除元素
            remove[i++] = remVal;

            System.out.print(", nextNode: " + startNode.e + ", removeVal: " + remVal + "\n");
            if (!linkedList.isEmpty())
                System.out.println(linkedList);
        }

        // 打印移除顺序
        System.out.println("移除数量:" + remove.length + ", 移除顺序：");
        for (int j=0; j<remove.length; j++)
            System.out.print(remove[j] + ",");

    }

    public Node<Integer> recursion(CircularDummyLinkedList<Integer> linkedList,Node<Integer> startNode, int offset){

        if (linkedList.getSize() == 1)
            return startNode;

        Node<Integer> remNode = linkedList.getOffsetNode(startNode,offset);

        // 下一次的开始元素
        startNode = linkedList.getNextNode(remNode);

        // 删除元素
        int remVal = linkedList.removeNode(remNode);

        return recursion(linkedList,startNode,offset);

    }


    public int matchRecursion(int n, int m){
        if (n < 1 || m < 1)
            return 0;

        return lastInCycle(n,m) + 1;
    }
    public int lastInCycle(int n, int m){
        if (n == 1)
            return 0;
        return (lastInCycle(n-1,m) + m) % n;
    }

    public static void main(String[] args) {

        // 链表实现约瑟夫环
//        (new JOSEPHUS()).test(20);

        // 循环链表递归
//        int n = 5;
//        int offset = 3;
//        CircularDummyLinkedList<Integer> linkedList = new CircularDummyLinkedList<>();
//        // 生成 n 个数
//        for (int i = 0; i < n; i++)
//            linkedList.addLast(i);
//
//        Node<Integer> startNode = linkedList.dummyHead.next;
//        Node<Integer> life = (new JOSEPHUS()).recursion(linkedList,startNode,offset);
//
//        System.out.println("lifeNode: " + life.e);

        // 数学递归
        int n = (new JOSEPHUS()).matchRecursion(10,3);
        System.out.println("life: " + n);
    }

}
