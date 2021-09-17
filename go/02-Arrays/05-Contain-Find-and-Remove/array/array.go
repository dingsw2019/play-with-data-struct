package array

import (
	"bytes"
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/utils"
)

type Array struct {
	data []int
	size int
}

// 构造函数, 传入数组容量构造Array
func New(capacity int) *Array {
	return &Array{
		data: make([]int, capacity),
	}
}

// 获取数组的容量
func (a *Array) GetCapacity() int {
	return len(a.data)
}

// 获取元素数量
func (a *Array) GetSize() int {
	return a.size
}

// 数组是否为空
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

// 获取index位置的元素
func (a *Array) Get(index int) int {
	if index < 0 || index >= a.size {
		panic("get failed, index out of range")
	}
	return a.data[index]
}

// 更新index位置的元素
func (a *Array) Set(index, e int) {
	if index < 0 || index >= a.size {
		panic("set failed, index out of range")
	}
	a.data[index] = e
}

// 数组是否包含元素
func (a *Array) Contains(e int) bool {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			return true
		}
	}
	return false
}

// 查找元素的索引, 不存在元素返回 -1
func (a *Array) Find(e int) int {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			return i
		}
	}
	return -1
}

// 查找数组中所有为e的元素, 以切片形式返回, 不存在返回空
func (a *Array) FindAll(e int) (indexes []int) {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			indexes = append(indexes, i)
		}
	}
	return
}

// 删除元素, 返回删除元素
func (a *Array) Remove(index int) int {
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
func (a *Array) RemoveFirst() int {
	return a.Remove(0)
}

// 删除最后一个元素
func (a *Array) RemoveLast() int {
	return a.Remove(a.size - 1)
}

// 删除指定元素
func (a *Array) RemoveElement(e int) bool {
	i := a.Find(e)
	if i == -1 {
		return false
	}
	a.Remove(i)
	return true
}

// 删除所有相同的元素
func (a *Array) RemoveAllElement(e int) bool {
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

// 重写Array 的 string 方法
func (a *Array) String() string {
	var buf bytes.Buffer

	buf.WriteString(fmt.Sprintf("Array size = %d, capacity = %d\n", a.size, len(a.data)))
	buf.WriteString("[")
	for i := 0; i < a.size; i++ {
		buf.WriteString(fmt.Sprintf("%d", a.data[i]))
		if i != (a.size - 1) {
			buf.WriteString(", ")
		}
	}
	buf.WriteString("]")
	return buf.String()
}
