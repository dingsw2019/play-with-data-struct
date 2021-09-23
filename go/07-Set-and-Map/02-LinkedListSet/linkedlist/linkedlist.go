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

type LinkedList struct {
	dummyHead *Node
	size      int
}

func New() *LinkedList {
	return &LinkedList{
		dummyHead: &Node{},
	}
}

func (l *LinkedList) GetSize() int {
	return l.size
}

func (l *LinkedList) IsEmpty() bool {
	return l.size == 0
}

// 指定位置添加元素
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

// 首部添加元素
func (l *LinkedList) AddFirst(e interface{}) {
	l.Add(0, e)
}

// 尾部添加元素
func (l *LinkedList) AddLast(e interface{}) {
	l.Add(l.size, e)
}

// 获取指定位置的元素值
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
	return l.Get(l.size - 1)
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

// 包含元素
func (l *LinkedList) Contains(e interface{}) bool {
	cur := l.dummyHead.next
	for cur != nil {
		if utils.Compare(e, cur.e) == 0 {
			return true
		}
		cur = cur.next
	}
	return false
}

// 删除指定索引位的元素
func (l *LinkedList) Remove(index int) interface{} {
	if index < 0 || index >= l.size {
		panic("remove failed, index out of range")
	}
	prev := l.dummyHead
	for i := 0; i < index; i++ {
		prev = prev.next
	}
	delNode := prev.next
	prev.next = delNode.next
	delNode.next = nil
	l.size--

	return delNode.e
}

func (l *LinkedList) RemoveFirst() interface{} {
	return l.Remove(0)
}

func (l *LinkedList) RemoveLast() interface{} {
	return l.Remove(l.size - 1)
}

func (l *LinkedList) RemoveElement(e interface{}) {
	prev := l.dummyHead
	for prev.next != nil {
		if utils.Compare(prev.next.e, e) == 0 {
			delNode := prev.next
			prev.next = delNode.next
			delNode.next = nil
			l.size--
			break
		}
		prev = prev.next
	}
}

func (l *LinkedList) String() string {
	var buffer bytes.Buffer
	buffer.WriteString(fmt.Sprintf("size = %d, [", l.GetSize()))
	cur := l.dummyHead.next
	for i := 0; i < l.size; i++ {
		buffer.WriteString(fmt.Sprintf("%v->", cur.e))
		cur = cur.next
	}
	buffer.WriteString("nil")
	return buffer.String()
}
