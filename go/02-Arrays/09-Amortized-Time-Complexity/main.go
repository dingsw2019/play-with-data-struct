package main

import (
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/02-Arrays/09-Amortized-Time-Complexity/array"
)

func main() {

	arr := array.New(10)
	for i := 0; i < 10; i++ {
		arr.AddLast(i)
	}
	fmt.Println(arr)

	arr.Add(1, 100)
	fmt.Println(arr)

	arr.AddFirst(-1)
	fmt.Println(arr)

	arr.Remove(2)
	fmt.Println(arr)

	arr.RemoveElement(4)
	fmt.Println(arr)

	arr.RemoveFirst()
	fmt.Println(arr)

	for i := 0; i < 4; i++ {
		arr.RemoveFirst()
		fmt.Println(arr)
	}
}
