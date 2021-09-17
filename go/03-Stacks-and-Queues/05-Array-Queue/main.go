package main

import (
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/03-Stacks-and-Queues/05-Array-Queue/arrayqueue"
)

func main() {

	arrayQueue := arrayqueue.New(10)
	for i := 0; i < 10; i++ {
		arrayQueue.Enqueue(i)
		fmt.Println(arrayQueue)
		if i%3 == 2 {
			arrayQueue.Dequeue()
			fmt.Println(arrayQueue)
		}
	}
}
