/**
 * 传统链表解决
 */
public class Solution {
    
    public static ListNode removeElements(ListNode head, int val){

        // 删除头部
        while (head != null && head.val == val){
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        // 是否为空
        if (head == null)
            return null;

        // 删除头部之后的元素
        ListNode prev = head;
        while (prev.next != null){
            if (prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;

            } else
                prev = prev.next;
        }

        // 返回
        return head;
    }

    public static void main(String[] args){

        int [] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements(head,6);
        System.out.println(res);
    }
}
