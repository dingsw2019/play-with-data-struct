package array

import (
	"bytes"
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/utils"
)

type Array struct {
	data []interface{}
	size int
}

func New(capacity int) *Array {
	return &Array{
		data: make([]interface{}, capacity),
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

func (a *Array) Add(index int, e interface{}) {
	if index < 0 || index > a.size {
		panic("add failed, index out of range")
	}
	if a.size == len(a.data) {
		a.resize(2 * a.size)
	}
	for i := a.size - 1; i >= index; i-- {
		a.data[i+1] = a.data[i]
	}
	a.data[index] = e
	a.size++
}

func (a *Array) AddLast(e interface{}) {
	a.Add(a.size, e)
}

func (a *Array) AddFirst(e interface{}) {
	a.Add(0, e)
}

func (a *Array) Get(index int) interface{} {
	a.checkIndex(index)
	return a.data[index]
}

func (a *Array) GetLast() interface{} {
	return a.Get(a.size - 1)
}

func (a *Array) GetFirst() interface{} {
	return a.Get(0)
}

func (a *Array) Set(index int, e interface{}) {
	a.checkIndex(index)
	a.data[index] = e
}

func (a *Array) Contains(e interface{}) bool {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			return true
		}
	}
	return false
}

func (a *Array) Find(e interface{}) int {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			return i
		}
	}
	return -1
}

func (a *Array) FindAll(e interface{}) (indexes []int) {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			indexes = append(indexes, i)
		}
	}
	return
}

func (a *Array) Remove(index int) interface{} {
	a.checkIndex(index)
	e := a.data[index]
	for i := index + 1; i < a.size; i++ {
		a.data[i-1] = a.data[i]
	}
	a.size--
	a.data[a.size] = nil
	// 缩容, 避免左右震荡, capacity=1 时避免减成0
	if a.size == len(a.data)/4 && len(a.data)/2 != 0 {
		a.resize(len(a.data) / 2)
	}
	return e
}

func (a *Array) RemoveFirst() interface{} {
	return a.Remove(0)
}

func (a *Array) RemoveLast() interface{} {
	return a.Remove(a.size - 1)
}

func (a *Array) RemoveElement(e interface{}) {
	index := a.Find(e)
	if index != -1 {
		a.Remove(index)
	}
}

func (a *Array) RemoveAllElement(e interface{}) bool {
	start := a.Find(e)
	if start == -1 {
		return false
	}
	for i := start; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			a.Remove(i)
		}
	}
	return true
}

func (a *Array) resize(newCapacity int) {
	newData := make([]interface{}, newCapacity)
	for i := 0; i < a.size; i++ {
		newData[i] = a.data[i]
	}
	a.data = newData
}

func (a *Array) checkIndex(index int) {
	if index < 0 || index >= a.size {
		panic(fmt.Sprintf("index out of range, [0,%d]", a.size))
	}
}

func (a *Array) String() string {
	var buf bytes.Buffer

	buf.WriteString(fmt.Sprintf("Array: size = %d, capacity = %d\n", a.size, len(a.data)))
	buf.WriteString("[")
	for i := 0; i < a.size; i++ {
		buf.WriteString(fmt.Sprintf("%v", a.data[i]))
		if i != a.size-1 {
			buf.WriteString(", ")
		}
	}
	buf.WriteString("]")
	return buf.String()
}
