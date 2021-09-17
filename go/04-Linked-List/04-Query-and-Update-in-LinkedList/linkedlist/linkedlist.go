package linkedlist

import (
	"bytes"
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/utils"
)

type Node struct {
	e    interface{}
	next *Node
}

func (n *Node) String() string {
	return fmt.Sprint(n.e)
}

type LinkedList struct {
	dummyHead *Node // 虚拟头节点, 不计入 size
	size      int
}

func New() *LinkedList {
	return &LinkedList{
		dummyHead: &Node{},
	}
}

// 获取链表中的元素个数
func (l *LinkedList) GetSize() int {
	return l.size
}

// 返回链表是否为空
func (l *LinkedList) IsEmpty() bool {
	return l.size == 0
}

// 添加元素
func (l *LinkedList) Add(index int, e interface{}) {
	if index < 0 || index > l.size {
		panic("add failed, index out of range")
	}
	prev := l.dummyHead
	for i := 0; i < index; i++ {
		prev = prev.next
	}
	prev.next = &Node{e, prev.next}
	l.size++
}
func (l *LinkedList) AddFirst(e interface{}) {
	l.Add(0, e)
}
func (l *LinkedList) AddLast(e interface{}) {
	l.Add(l.size, e)
}

// 获取元素
func (l *LinkedList) Get(index int) interface{} {
	if index < 0 || index >= l.size {
		panic("get failed, index out of range")
	}
	cur := l.dummyHead.next
	for i := 0; i < index; i++ {
		cur = cur.next
	}
	return cur.e
}
func (l *LinkedList) GetFirst() interface{} {
	return l.Get(0)
}
func (l *LinkedList) GetLast() interface{} {
	return l.Get(l.size)
}

func (l *LinkedList) Set(index int, e interface{}) {
	if index < 0 || index >= l.size {
		panic("set failed, index out of range")
	}
	cur := l.dummyHead.next
	for i := 0; i < index; i++ {
		cur = cur.next
	}
	cur.e = e
}

func (l *LinkedList) Contains(e interface{}) bool {
	cur := l.dummyHead.next
	for cur != nil {
		if utils.Compare(cur.e, e) == 0 {
			return true
		}
		cur = cur.next
	}
	return false
}

func (l *LinkedList) Remove(index int) interface{} {
	if index < 0 || index >= l.size {
		panic("remove failed, index out of range")
	}
	prev := l.dummyHead
	for i := 0; i < index; i++ {
		prev = prev.next
	}
	node := prev.next
	prev.next = node.next
	node.next = nil
	l.size--
	return node.e
}
func (l *LinkedList) RemoveFirst() interface{} {
	return l.Remove(0)
}
func (l *LinkedList) RemoveLast() interface{} {
	return l.Remove(l.size - 1)
}

func (l *LinkedList) String() string {
	var buf bytes.Buffer
	cur := l.dummyHead.next
	for cur != nil {
		buf.WriteString(fmt.Sprint(cur.e) + "->")
		cur = cur.next
	}
	buf.WriteString("NULL")
	return buf.String()
}
