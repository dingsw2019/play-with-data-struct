package main

import (
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/07-Set-and-Map/03-Time-Complexity-of-Set/BSTSet"
	"github.com/dingsw2019/play-with-data-struct/go/07-Set-and-Map/03-Time-Complexity-of-Set/linkedlistset"
	"github.com/dingsw2019/play-with-data-struct/go/07-Set-and-Map/03-Time-Complexity-of-Set/set"
	"github.com/dingsw2019/play-with-data-struct/go/utils"
	"time"

	"path/filepath"
)

func testSet(file string, set set.Set) time.Duration {

	ts := time.Now()

	filename, _ := filepath.Abs(file)
	words := utils.ReadFile(filename)
	for _, word := range words {
		set.Add(word)
	}
	fmt.Println(fmt.Sprintf("%s, 总单词数:%d, 词汇量:%d",file, len(words), set.GetSize()))

	return time.Now().Sub(ts)
}

/**
pride-and-prejudice.txt, 总单词数:124448, 词汇量:7018
BST use : 113.16072ms

pride-and-prejudice.txt, 总单词数:124448, 词汇量:7018
LinkedList use : 16.585011635s

 */
func main() {

	filename := "pride-and-prejudice.txt"

	bstSet := BSTSet.New()
	time1 := testSet(filename, bstSet)
	fmt.Println("BST use :", time1)

	linkedListSet := linkedlistset.New()
	time2 := testSet(filename, linkedListSet)
	fmt.Println("LinkedList use :", time2)
}
