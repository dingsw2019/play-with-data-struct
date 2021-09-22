package solution

import (
	. "github.com/dingsw2019/play-with-data-struct/go/05-Recursion/01-Linked-List-Problems-in-Leetcode/listnode"
)

/**
203 移除链表元素
https://leetcode-cn.com/problems/remove-linked-list-elements/

只改变指向, 删除元素的指向不变更
*/
func removeElements(head *ListNode, val int) *ListNode {

	// 找到第一个不等于val的元素
	for head != nil && head.Val == val {
		head = head.Next
	}

	if head == nil {
		return nil
	}

	// 删除目标值的元素
	prev := head
	for prev.Next != nil {
		if prev.Next.Val == val {
			prev.Next = prev.Next.Next
		} else {
			prev = prev.Next
		}
	}

	return head
}
