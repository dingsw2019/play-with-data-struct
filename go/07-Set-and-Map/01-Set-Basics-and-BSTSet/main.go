package main

import (
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/07-Set-and-Map/01-Set-Basics-and-BSTSet/BSTSet"
	"github.com/dingsw2019/play-with-data-struct/go/utils"
	"path/filepath"
)

func main() {

	filename, _ := filepath.Abs("pride-and-prejudice.txt")
	fmt.Println(filename)
	words1 := utils.ReadFile(filename)

	set1 := BSTSet.New()
	for _, word := range words1 {
		set1.Add(word)
	}
	fmt.Println(fmt.Sprintf("傲慢与偏见, 总单词数:%d, 词汇数:%d\n", len(words1), set1.GetSize()))

	filename, _ = filepath.Abs("a-tale-of-two-cities.txt")
	fmt.Println(filename)
	words2 := utils.ReadFile(filename)
	set2 := BSTSet.New()
	for _, word := range words2 {
		set2.Add(word)
	}
	fmt.Println(fmt.Sprintf("双城记, 总单词数:%d, 词汇数:%d\n", len(words2), set2.GetSize()))

}
