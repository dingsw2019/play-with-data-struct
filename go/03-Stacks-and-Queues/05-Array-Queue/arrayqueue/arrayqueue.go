package arrayqueue

import (
	"bytes"
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/03-Stacks-and-Queues/05-Array-Queue/array"
)

type ArrayQueue struct {
	array *array.Array
}

func New(capacity int) *ArrayQueue {
	return &ArrayQueue{array: array.New(capacity)}
}
func (aq *ArrayQueue) GetSize() int {
	return aq.array.GetSize()
}

func (aq *ArrayQueue) IsEmpty() bool {
	return aq.array.IsEmpty()
}
func (aq *ArrayQueue) GetCapacity() int {
	return aq.array.GetCapacity()
}
func (aq *ArrayQueue) Enqueue(e interface{}) {
	aq.array.AddLast(e)
}
func (aq *ArrayQueue) Dequeue() interface{} {
	return aq.array.RemoveFirst()
}
func (aq *ArrayQueue) GetFront() interface{} {
	return aq.array.GetFirst()
}
func (aq *ArrayQueue) String() string {
	var buf bytes.Buffer

	buf.WriteString("front queue: [")
	for i := 0; i < aq.array.GetSize(); i++ {
		buf.WriteString(fmt.Sprintf("%v", aq.array.Get(i)))
		if i != aq.array.GetSize()-1 {
			buf.WriteString(", ")
		}
	}
	buf.WriteString("] tail")
	return buf.String()
}
