class Solution {

    public ListNode removeElements(ListNode head, int val) {

        // 删除头节点, 因为头节点没有前置节点
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null)
            return null;

        // 删除其他节点, 并重建指针指向
        ListNode prev = head;
        while(prev.next != null){

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
}