package main

import (
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/02-Arrays/03-Add-Element-in-Array/array"
)

func main() {

	a := array.New(5)

	fmt.Println(a)
	fmt.Println(a.GetCapacity(), a.GetSize(), a.IsEmpty())
}
