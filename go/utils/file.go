package utils

import (
	"bufio"
	"io"
	"os"
	"strings"
)

func ReadFile(filename string) []string {
	var words []string
	// 打开文件
	file, err := os.Open(filename)
	if err != nil {
		panic(err)
	}
	defer file.Close()
	// 读取文件数据
	reader := bufio.NewReader(file)
	for {
		// 取一行
		line, err := reader.ReadString('\n')
		if err != nil || io.EOF == err {
			break
		}
		// 分词
		wordSlice := strings.Fields(line)
		for _, word := range wordSlice {
			if word = extractStr(strings.ToLower(word)); word != "" {
				words = append(words, word)
			}
		}
	}
	return words
}

func extractStr(str string) string {
	var res []rune
	for _, letter := range str {
		if letter >= 'a' && letter <= 'z' {
			res = append(res, letter)
		}
	}
	return string(res)
}
