package linkedlistqueue

import (
	"bytes"
	"fmt"
)

type node struct {
	e    interface{}
	next *node
}

func (n *node) String() string {
	return fmt.Sprint(n.e)
}

type LinkedListQueue struct {
	head *node
	tail *node
	size int
}

func New() *LinkedListQueue {
	return &LinkedListQueue{}
}

func (lq *LinkedListQueue) GetSize() int {
	return lq.size
}

func (lq *LinkedListQueue) IsEmpty() bool {
	return lq.size == 0
}

func (lq *LinkedListQueue) Enqueue(e interface{}) {
	if lq.tail == nil {
		lq.tail = &node{e: e}
		lq.head = lq.tail
	} else {
		lq.tail.next = &node{e: e}
		lq.tail = lq.tail.next
	}
	lq.size++
}

func (lq *LinkedListQueue) Dequeue() interface{} {
	if lq.IsEmpty() {
		panic("cannot dequeue from an empty queue.")
	}
	node := lq.head
	lq.head = lq.head.next
	node.next = nil
	if node == nil {
		lq.tail = nil
	}
	lq.size--
	return node.e
}

func (lq *LinkedListQueue) GetFront() interface{} {
	if lq.IsEmpty() {
		panic("cannot dequeue from an empty queue.")
	}
	return lq.head.e
}

func (lq *LinkedListQueue) String() string {
	var buffer bytes.Buffer
	buffer.WriteString("Queue: front ")

	cur := lq.head
	for cur != nil {
		buffer.WriteString(fmt.Sprintf("%v ->", cur.e))
		cur = cur.next
	}
	buffer.WriteString("NULL tail")
	return buffer.String()
}
