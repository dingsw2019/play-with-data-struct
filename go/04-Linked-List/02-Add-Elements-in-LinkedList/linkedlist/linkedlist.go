package linkedlist

type Node struct {
	e    interface{}
	next *Node
}

type LinkedList struct {
	head *Node
	size int
}

// 元素数量
func (l *LinkedList) GetSize() int {
	return l.size
}

// 是否为空
func (l *LinkedList) IsEmpty() bool {
	return l.size == 0
}

// 头部添加元素
func (l *LinkedList) AddFirst(e interface{}) {
	l.head = &Node{
		e:    e,
		next: l.head,
	}
	l.size++
}

// 任意位置添加元素
func (l *LinkedList) Add(index int, e interface{}) {

	if index < 0 || index > l.size {
		panic("add failed, index out of range")
	}

	if index == 0 {
		l.AddFirst(e)
	} else {
		prev := l.head
		for i := 0; i < index-1; i++ {
			prev = prev.next
		}
		prev.next = &Node{
			e:    e,
			next: prev.next,
		}
		l.size++
	}
}

// 尾部添加元素
func (l *LinkedList) AddLast(e interface{}) {
	l.Add(l.size, e)
}
