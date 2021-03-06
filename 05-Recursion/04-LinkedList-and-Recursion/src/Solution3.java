import java.util.List;

public class Solution3 {

    public static ListNode removeElements(ListNode head, int val){

        // 添加虚拟头节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // 删除
        ListNode prev = dummyHead;
        while (prev.next != null){

            if (prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else
                prev = prev.next;
        }

        // 返回
        return dummyHead.next;
    }

    public static void main(String[] args){

        int [] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3()).removeElements(head,6);
        System.out.println(res);
    }
}
