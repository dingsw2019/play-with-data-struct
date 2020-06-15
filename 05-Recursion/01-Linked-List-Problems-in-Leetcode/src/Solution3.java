/**
 * 创建虚拟头节点, 节省代码量
 */
public class Solution3 {

    public ListNode removeElements(ListNode head, int val) {

        // 创建虚拟头节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // 删除节点
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                
            } else
                prev = prev.next;
        }

        return dummyHead.next;
    }
}
