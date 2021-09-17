package queue

type Queue interface {
	GetSize() int
	GetCapacity() int
	IsEmpty() bool
	Enqueue(interface{})
	Dequeue() interface{}
	GetFront() interface{}
}
