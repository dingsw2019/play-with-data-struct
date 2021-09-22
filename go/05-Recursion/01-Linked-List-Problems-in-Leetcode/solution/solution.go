package solution

import (
	. "github.com/dingsw2019/play-with-data-struct/go/05-Recursion/01-Linked-List-Problems-in-Leetcode/listnode"
)

/**
203 移除链表元素
https://leetcode-cn.com/problems/remove-linked-list-elements/

给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
输入：head = [1,2,6,3,4,5,6], val = 6
输出：[1,2,3,4,5]

示例 2：
输入：head = [], val = 1
输出：[]

示例 3：
输入：head = [7,7,7,7], val = 7
输出：[]

提示：
	列表中的节点数目在范围 [0, 104] 内
	1 <= Node.val <= 50
	0 <= val <= 50
*/
func removeElements(head *ListNode, val int) *ListNode {

	// 找到第一个不等于val的元素
	for head!=nil && head.Val == val {
		delNode := head
		head = head.Next
		delNode.Next = nil
	}

	if head == nil {
		return nil
	}

	// 删除目标值的元素
	prev := head
	for prev.Next != nil {
		if prev.Next.Val == val {
			// 连续相同的目标值, 不需要移动
			cur := prev.Next
			prev.Next = cur.Next
			cur = nil
		} else {
			prev = prev.Next
		}
	}

	return head
}