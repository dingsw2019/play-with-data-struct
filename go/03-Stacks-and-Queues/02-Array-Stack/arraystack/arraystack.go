package arraystack

import (
	"bytes"
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/03-Stacks-and-Queues/02-Array-Stack/array"
)

type ArrayStack struct {
	array *array.Array
}

func New(capacity int) *ArrayStack {
	return &ArrayStack{
		array: array.New(capacity),
	}
}

func (as *ArrayStack) GetSize() int {
	return as.array.GetSize()
}

func (as *ArrayStack) IsEmpty() bool {
	return as.array.IsEmpty()
}

// 入栈
func (as *ArrayStack) Push(e interface{}) {
	as.array.AddLast(e)
}

// 出栈
func (as *ArrayStack) Pop() interface{} {
	return as.array.RemoveLast()
}

// 查看栈顶值
func (as *ArrayStack) Peek() interface{} {
	return as.array.GetLast()
}

func (as *ArrayStack) String() string {
	var buf bytes.Buffer

	buf.WriteString(fmt.Sprintf("Stack: ["))
	for i := 0; i < as.array.GetSize(); i++ {
		buf.WriteString(fmt.Sprintf("%v", as.array.Get(i)))
		if i != as.array.GetSize()-1 {
			buf.WriteString(", ")
		}
	}
	buf.WriteString("]")
	return buf.String()
}
