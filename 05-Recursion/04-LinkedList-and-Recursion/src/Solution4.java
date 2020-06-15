/**
 *  几乎和链表相关的所有操作, 都可以使用递归的形式完成
 *  建议对链表的增删改查, 进行递归实现
 *
 *  其他结构
 *      双端链表
 *      循环双端链表
 *      数组链表
 */
public class Solution4 {

    // 递归删除
    public ListNode removeElements(ListNode head, int val){
        // 结束条件
        if (head == null)
            return null;

        // 递归, 将当前节点(head)和其下一个节点(res)维持在一个时空, 是不是很神奇
//        ListNode res = removeElements(head.next,val);
//        if (head.val == val) {
//            return res;
//        } else {
//            head.next = res;
//            return head;
//        }

        head.next = removeElements(head.next,val);
        return (head.val == val) ? head.next : head;
    }

    public static void main(String[] args) {

        int [] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution4()).removeElements(head,6);
        System.out.println(res);
    }

}
