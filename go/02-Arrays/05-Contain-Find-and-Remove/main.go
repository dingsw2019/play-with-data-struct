package main

import (
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/02-Arrays/05-Contain-Find-and-Remove/array"
)

func main() {

	a := array.New(20)
	for i := 0; i < 10; i++ {
		a.AddLast(i)
	}
	fmt.Println(a.String())

	n := a.Find(8)
	fmt.Println(n)

	a.AddFirst(8)
	fmt.Println(a.String())

	fAll := a.FindAll(8)
	fmt.Println(fAll)

	a.RemoveLast()
	fmt.Println(a.String())

	a.RemoveElement(7)
	fmt.Println(a.String())

	a.RemoveAllElement(8)
	fmt.Println(a.String())
}
