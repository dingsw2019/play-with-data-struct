package main

import (
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/04-Linked-List/07-Implement-Queue-in-LinkedList/linkedlistqueue"
	"github.com/dingsw2019/play-with-data-struct/go/04-Linked-List/07-Implement-Queue-in-LinkedList/queue"
	"math/rand"
	"time"
)

// 测试使用queue运行opCount个enqueueu和dequeue操作所需要的时间，单位：秒
func testQueue(queue queue.Queue, opCount int) float64 {
	startTime := time.Now()

	for i := 0; i < opCount; i++ {
		queue.Enqueue(rand.Int())
	}
	for i := 0; i < opCount; i++ {
		queue.Dequeue()
	}

	return time.Now().Sub(startTime).Seconds()
}

func main() {
	opCount := 100000

	linkedListQueue := linkedlistqueue.New()
	time := testQueue(linkedListQueue, opCount)
	fmt.Println("linkedListQueue, time:", time, "s")
}
