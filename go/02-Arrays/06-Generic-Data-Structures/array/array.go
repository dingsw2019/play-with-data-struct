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

// 数组容量
func (a *Array) GetCapacity() int {
	return len(a.data)
}

// 元素数量
func (a *Array) GetSize() int {
	return a.size
}

// 是否为空
func (a *Array) IsEmpty() bool {
	return a.size == 0
}

// index位置添加元素
func (a *Array) Add(index int, e interface{}) {
	if a.size == len(a.data) {
		panic("add failed, array is full")
	}
	if index < 0 || index > a.size {
		panic("add failed, index out of range")
	}
	for i := a.size - 1; i >= index; i-- {
		a.data[i+1] = a.data[i]
	}
	a.data[index] = e
	a.size++
}

// 尾部添加元素
func (a *Array) AddLast(e interface{}) {
	a.Add(a.size, e)
}

// 头部添加元素
func (a *Array) AddFirst(e interface{}) {
	a.Add(0, e)
}

// 获取index的元素
func (a *Array) Get(index int) interface{} {
	if index < 0 || index >= a.size {
		panic("get failed, index out of range")
	}
	return a.data[index]
}

// 更新index的元素
func (a *Array) Set(index int, e interface{}) {
	if index < 0 || index >= a.size {
		panic("set failed, index out of range")
	}
	a.data[index] = e
}

// 包含元素
func (a *Array) Contains(e interface{}) bool {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			return true
		}
	}
	return false
}

// 查找元素的index
func (a *Array) Find(e interface{}) int {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			return i
		}
	}
	return -1
}

// 查找元素所有的index
func (a *Array) FindAll(e interface{}) (indexes []int) {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			indexes = append(indexes, i)
		}
	}
	return
}

// 删除index的元素,返回元素
func (a *Array) Remove(index int) interface{} {
	if index < 0 || index >= a.size {
		panic("remove failed, index out of range")
	}
	e := a.data[index]
	for i := index + 1; i < a.size; i++ {
		a.data[i-1] = a.data[i]
	}
	a.size--
	return e
}

// 删除第一个元素
func (a *Array) RemoveFirst() interface{} {
	return a.Remove(0)
}

// 删除最后一一个元素
func (a *Array) RemoveLast() interface{} {
	return a.Remove(a.size - 1)
}

// 删除指定元素
func (a *Array) RemoveElement(e interface{}) bool {
	index := a.Find(e)
	if index == -1 {
		return false
	}
	a.Remove(index)
	return true
}

// 删除所有指定的元素
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

// 重写 string
func (a *Array) String() string {
	var buf bytes.Buffer

	buf.WriteString(fmt.Sprintf("Array size = %d, capacity = %d\n", a.size, len(a.data)))
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
