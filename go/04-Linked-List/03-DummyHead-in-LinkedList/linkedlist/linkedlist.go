package linkedlist

type Node struct {
	e    interface{}
	next *Node
}

type LinkedList struct {
	dummyHead *Node
	size      int
}

// 获取链表中的元素个数
func (l *LinkedList) GetSize() int {
	return l.size
}

// 返回链表是否为空
func (l *LinkedList) IsEmpty() bool {
	return l.size == 0
}

//
func (l *LinkedList) Add(index int, e interface{}) {
	if index < 0 || index > l.size {
		panic("add failed, index out of range")
	}
	prev := l.dummyHead
	for i := 0; i < index; i++ {
		prev = prev.next
	}
	prev.next = &Node{
		e:    e,
		next: prev.next,
	}
	l.size++
}
func (l *LinkedList) AddFirst(e interface{}) {
	l.Add(0, e)
}
func (l *LinkedList) AddLast(e interface{}) {
	l.Add(l.size, e)
}
