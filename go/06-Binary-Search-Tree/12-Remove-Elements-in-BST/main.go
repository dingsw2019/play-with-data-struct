package main

import (
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/06-Binary-Search-Tree/12-Remove-Elements-in-BST/BST"
	"math/rand"
	"time"
)

func main() {

	bst := BST.New()

	// 添加元素
	n := 1000

	rand.Seed(time.Now().UnixNano())
	for i := 0; i < n; i++ {
		bst.Add(rand.Intn(1000))
	}

	// 删除元素
	var nums []int
	for i := 0; i < n; i++ {
		nums = append(nums, i)
	}
	for i := range nums {
		// 打乱切片
		j := rand.Intn(i + 1)
		nums[i], nums[j] = nums[j], nums[i]
	}

	for i := 0; i < n; i++ {
		if bst.Contains(nums[i]) {
			bst.Remove(nums[i])
			fmt.Println("After remove", nums[i], ", size = ", bst.GetSize())
		}
	}
	// 最终二分搜索树为空
	fmt.Println(bst.GetSize())
}
