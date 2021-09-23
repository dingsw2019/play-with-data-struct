package BSTSet

import "github.com/dingsw2019/play-with-data-struct/go/07-Set-and-Map/01-Set-Basics-and-BSTSet/BST"

type BSTSet struct {
	BST *BST.BST
}

func New() *BSTSet {
	return &BSTSet{
		BST: BST.New(),
	}
}

func (b *BSTSet) Add(e interface{}) {
	b.BST.Add(e)
}

func (b *BSTSet) Remove(e interface{}) {
	b.BST.Remove(e)
}

func (b *BSTSet) Contains(e interface{}) bool {
	return b.BST.Contains(e)
}

func (b *BSTSet) GetSize() int {
	return b.BST.GetSize()
}

func (b *BSTSet) IsEmpty() bool {
	return b.BST.IsEmpty()
}
