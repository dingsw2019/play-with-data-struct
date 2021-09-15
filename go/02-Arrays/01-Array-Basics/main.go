package main

import "fmt"

/**
go 中数组的使用

数组最大的优点：快速查询. scores[2]
数组最好应用于"索引有语意"的情况
 */
func main() {

	// 初始化数据大小
	var arr [10]int
	for i := 0; i < len(arr); i ++ {
		arr[i] = i
	}

	// 初始化数组值
	scores := []int{100,99,98}
	for i := 0; i < len(scores); i ++ {
		fmt.Println(scores[i])
	}

	// foreach遍历数组
	for _,  score := range scores {
		fmt.Println(score)
	}

	// 修改数组值
	scores[0] = 98
	for i := 0; i < len(scores); i ++ {
		fmt.Println(scores[i])
	}
}
