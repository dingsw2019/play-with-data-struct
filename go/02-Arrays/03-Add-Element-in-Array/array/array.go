package array

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

// 数组尾部插入元素
func (a *Array) addLast(e int) {
	a.add(a.size, e)
}

// 数组头部插入元素
func (a *Array) addFirst(e int) {
	a.add(0, e)
}

// 指定 index 插入元素
func (a *Array) add(index, e int) {
	if a.size == len(a.data) {
		panic("add failed")
	}
	if index < 0 || index > a.size {
		panic("index error")
	}

	for i := a.size - 1; i >= index; i-- {
		a.data[i+1] = a.data[i]
	}
	a.data[index] = e
	a.size++
}
