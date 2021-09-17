package main

import (
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/03-Stacks-and-Queues/02-Array-Stack/arraystack"
)

func main() {

	//arr := array.New(10)
	//for i := 0; i < 10; i ++ {
	//	arr.AddLast(i)
	//}
	//fmt.Println(arr)
	//
	//arr.AddLast(100)
	//arr.AddFirst(-1)
	//arr.AddFirst(6)
	//fmt.Println(arr)
	//
	//fmt.Println(arr.Find(7))
	//
	//arr.RemoveLast()
	//fmt.Println(arr)
	//
	//arr.RemoveAllElement(6)
	//fmt.Println(arr)
	//
	//for i := 0; i < 5; i ++ {
	//	arr.Remove(i)
	//}
	//fmt.Println(arr)

	stack := arraystack.New(10)
	fmt.Println(stack)
	for i := 0; i < 10; i ++ {
		stack.Push(i)
		fmt.Println(stack)
	}

	stack.Pop()
	fmt.Println(stack)
}