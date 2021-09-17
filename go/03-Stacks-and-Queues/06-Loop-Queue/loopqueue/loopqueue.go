package loopqueue

import (
	"bytes"
	"fmt"
)

type LoopQueue struct {
	data  []interface{}
	size  int
	front int
	tail  int
}

func New(capacity int) *LoopQueue {
	return &LoopQueue{
		data: make([]interface{}, capacity+1),
	}
}

func (lq *LoopQueue) GetCapacity() int {
	return len(lq.data) - 1
}

func (lq *LoopQueue) GetSize() int {
	return lq.size
}
func (lq *LoopQueue) IsEmpty() bool {
	return lq.front == lq.tail
}
func (lq *LoopQueue) Enqueue(e interface{}) {
	// 队满
	if (lq.tail+1)%len(lq.data) == lq.front {
		lq.resize(lq.GetCapacity() * 2)
	}
	lq.data[lq.tail] = e
	lq.tail = (lq.tail + 1) % len(lq.data)
	lq.size++
}
func (lq *LoopQueue) Dequeue() interface{} {
	// 队空
	if lq.IsEmpty() {
		panic("cannot dequeue from empty queue")
	}
	e := lq.data[lq.front]
	lq.data[lq.front] = nil
	lq.front = (lq.front + 1) % len(lq.data)
	lq.size--

	if lq.size == lq.GetCapacity()/4 && lq.GetCapacity()/2 != 0 {
		lq.resize(lq.GetCapacity() / 2)
	}
	return e
}

func (lq *LoopQueue) GetFront() interface{} {
	if lq.IsEmpty() {
		panic("Queue is empty")
	}
	return lq.data[lq.front]
}

func (lq *LoopQueue) resize(capacity int) {
	newData := make([]interface{}, capacity)
	for i := 0; i < lq.size; i++ {
		newData[i] = lq.data[(i+lq.front)%len(lq.data)]
	}
	lq.data = newData
	lq.front = 0
	lq.tail = lq.size
}

func (lq *LoopQueue) String() string {
	var buffer bytes.Buffer

	buffer.WriteString(fmt.Sprintf("Queue: size = %d, capacity = %d\n", lq.size, lq.GetCapacity()))
	buffer.WriteString("front [")
	for i := lq.front; i != lq.tail; i = (i + 1) % len(lq.data) {
		// fmt.Sprint 将 interface{} 类型转换为字符串
		buffer.WriteString(fmt.Sprint(lq.data[i]))
		if (i+1)%len(lq.data) != lq.tail {
			buffer.WriteString(", ")
		}
	}
	buffer.WriteString("] tail")

	return buffer.String()
}
