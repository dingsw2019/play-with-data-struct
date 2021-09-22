package solution3

import (
	. "github.com/dingsw2019/play-with-data-struct/go/05-Recursion/01-Linked-List-Problems-in-Leetcode/listnode"
)

/**
203 移除链表元素
https://leetcode-cn.com/problems/remove-linked-list-elements/

只改变指向, 删除元素的指向不变更
加入 dummyHead, 不用特殊判断链表起始位置
*/
func removeElements(head *ListNode, val int) *ListNode {

	dummyHead := &ListNode{Next: head}
	// 删除目标值的元素
	prev := dummyHead
	for prev.Next != nil {
		if prev.Next.Val == val {
			prev.Next = prev.Next.Next
		} else {
			prev = prev.Next
		}
	}

	return dummyHead.Next
}
