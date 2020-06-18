import java.util.Arrays;
import java.util.Random;

/**
 * 约瑟夫环
 */
import java.util.Random;
public class JOSEPHUS {

    public void test(int n){

        CircularDummyLinkedList<Integer> linkedList = new CircularDummyLinkedList<>();

        // 生成 n 个数
        for (int i = 0; i < n; i++)
            linkedList.addLast(i);

        System.out.println("生成链表：" + linkedList);

        int [] remove = new int[n];
        // 移除元素
        Random random = new Random();
        int i = 0;
        int startId = random.nextInt(linkedList.getSize());
        Node<Integer> startNode = linkedList.getNode(startId);
        
        
        int offset = 0;
        while (!linkedList.isEmpty()) {

            // 随机步数
            do{
                offset = random.nextInt(10);
            }while(offset <= 0);

            // 计算删除的元素的索引
            Node<Integer> remNode = linkedList.getOffset(startNode,offset);

            System.out.print("index: " + startNode.e  + ", offset: " + offset + ", remNode:" + remNode.e);
                    // 计算下一轮开始的索引
            startNode = linkedList.getNextNode(remNode);

            System.out.print(", nextNode: " + startNode.e);

            // 删除元素
            int remVal = linkedList.removeNode(remNode);

            // 记录删除元素
            remove[i++] = remVal;

            System.out.print(", removeVal: " + remVal + "\n");

            if (!linkedList.isEmpty())
                System.out.println(linkedList);
        }

        // 返回移除顺序
        System.out.println("移除数量:" + remove.length + ", 移除顺序：");
        for (int j=0; j<remove.length; j++)
            System.out.print(remove[j] + ",");

    }

    public static void main(String[] args) {

        (new JOSEPHUS()).test(6);
    }
}
