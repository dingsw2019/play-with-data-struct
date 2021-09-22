package solution4

import (
	. "github.com/dingsw2019/play-with-data-struct/go/05-Recursion/04-LinkedList-and-Recursion/listnode"
)

/**
203 移除链表元素
https://leetcode-cn.com/problems/remove-linked-list-elements/

递归移除元素
*/
func removeElements(head *ListNode, val int) *ListNode {
	// 链表尾部
	if head == nil {
		return nil
	}
	// 定位到最后一个元素
	head.Next = removeElements(head.Next, val)
	// 从后往前开始处理, 跳过等于val的元素, 其余元素挂接到res
	if head.Val == val {
		return head.Next
	} else {
		return head
	}
}
