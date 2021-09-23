package main

import (
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/07-Set-and-Map/02-LinkedListSet/linkedlistset"
	"github.com/dingsw2019/play-with-data-struct/go/utils"
	"path/filepath"
)

func main() {

	ls1 := linkedlistset.New()
	filename, _ := filepath.Abs("pride-and-prejudice.txt")
	words1 := utils.ReadFile(filename)
	for _, word := range words1 {
		ls1.Add(word)
	}
	fmt.Println(fmt.Sprintf("傲慢与偏见, 总单词数:%d, 词汇数:%d", len(words1), ls1.GetSize()))

	ls2 := linkedlistset.New()
	filename, _ = filepath.Abs("a-tale-of-two-cities.txt")
	words2 := utils.ReadFile(filename)
	for _, word := range words2 {
		ls2.Add(word)
	}
	fmt.Println(fmt.Sprintf("双城记, 总单词数:%d, 词汇数:%d", len(words2), ls2.GetSize()))
}
