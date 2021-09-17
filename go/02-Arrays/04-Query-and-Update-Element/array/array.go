package array

import "fmt"

type Array struct {
	data []int
	size int
}

func New(capacity int) *Array {
	return &Array{
		data: make([]int, capacity),
	}
}

func (a *Array) GetCapacity() int {
	return len(a.data)
}

func (a *Array) GetSize() int {
	return a.size
}

func (a *Array) IsEmpty() bool {
	return a.size == 0
}

func (a *Array) AddLast(e int) {
	a.Add(a.size, e)
}

func (a *Array) AddFirst(e int) {
	a.Add(0, e)
}

func (a *Array) Add(index, e int) {
	if a.size == len(a.data) {
		panic("add failed")
	}
	if index < 0 || index >= len(a.data) {
		panic("index error")
	}
	for i := a.size - 1; i >= index; i-- {
		a.data[i+1] = a.data[i]
	}
	fmt.Println(fmt.Sprintf("i:%d, e:%d", index, e))
	a.data[index] = e
	a.size++
}

func (a *Array) Get(index int) int {
	if index < 0 || index > len(a.data) {
		panic("index error")
	}
	return a.data[index]
}

func (a *Array) Set(index, e int) {
	if index < 0 || index > len(a.data) {
		panic("index error")
	}
	a.data[index] = e
}
