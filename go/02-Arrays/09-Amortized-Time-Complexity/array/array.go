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

// 获取容量
func (a *Array) GetCapacity() int {
	return len(a.data)
}

// 获取元素数量
func (a *Array) GetSize() int {
	return a.size
}

// 是否空数组
func (a *Array) IsEmpty() bool {
	return a.size == 0
}

// 任意位置添加元素
func (a *Array) Add(index int, e interface{}) {
	if index < 0 || index > a.size {
		panic("add failed, index out of range")
	}
	// 扩容
	if a.size == len(a.data) {
		a.resize(2 * a.size)
	}
	// 移动元素
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

// 获取元素
func (a *Array) Get(index int) interface{} {
	a.checkIndex(index)
	return a.data[index]
}

// 更改元素值
func (a *Array) Set(index int, e interface{}) {
	a.checkIndex(index)
	a.data[index] = e
}

// 是否包含元素
func (a *Array) Contains(e interface{}) bool {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			return true
		}
	}
	return false
}

// 查找元素所在位置
func (a *Array) Find(e interface{}) int {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			return i
		}
	}
	return -1
}

// 查找相同元素的所有索引位
func (a *Array) FindAll(e interface{}) (indexes []int) {
	for i := 0; i < a.size; i++ {
		if utils.Compare(a.data[i], e) == 0 {
			indexes = append(indexes, i)
		}
	}
	return
}

// 删除元素
func (a *Array) Remove(index int) interface{} {
	a.checkIndex(index)
	e := a.data[index]
	for i := index; i < a.size; i++ {
		a.data[i] = a.data[i+1]
	}
	a.size--

	// 缩容, 1/4保证不会在临界值重复扩容缩容
	// size == 1 时, 保证resize不会为0
	if a.size == len(a.data)/4 && len(a.data)/2 != 0 {
		a.resize(len(a.data) / 2)
	}
	return e
}

// 头部删除
func (a *Array) RemoveFirst() interface{} {
	return a.Remove(0)
}

// 尾部删除
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

// 删除所有相同的指定元素
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

// 重设容量
func (a *Array) resize(newCapacity int) {
	newData := make([]interface{}, newCapacity)
	for i := 0; i < a.size; i++ {
		newData[i] = a.data[i]
	}
	a.data = newData
}

// 校验索引值
func (a *Array) checkIndex(index int) {
	if index < 0 || index >= a.size {
		panic(fmt.Sprintf("index out of range, required range [0,%d], but get %d", a.size, index))
	}
}

// 重写String
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
