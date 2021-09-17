package solution

import "github.com/dingsw2019/play-with-data-struct/go/03-Stacks-and-Queues/04-More-about-Leetcode/arraystack"

func IsValid(s string) bool {
	brackets := map[rune]rune{')':'(', ']':'[', '}':'{'}
	stack := arraystack.New(len(s))

	for _, char := range s {
		if char == '(' || char == '[' || char == '{' {
			stack.Push(char)
		} else if !stack.IsEmpty() && brackets[char] == stack.Peek() {
			stack.Pop()
		} else {
			return false
		}
	}
	return stack.IsEmpty()
}