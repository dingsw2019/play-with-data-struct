package BST

import (
	"bytes"
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/utils"
)

type Node struct {
	e interface{}
	left *Node
	right *Node
}

func generateNode(e interface{}) *Node {
	return &Node{e:e}
}

type BST struct {
	root *Node
	size int
}

func New() *BST {
	return &BST{}
}

func (b *BST) GetSize() int {
	return b.size
}
func (b *BST) IsEmpty() bool{
	return b.size == 0
}

func (b *BST) Add(e interface{}){
	b.root = b.add(b.root, e)
}
func (b *BST) add(n *Node, e interface{}) *Node {
	if n == nil {
		b.size ++
		return generateNode(e)
	}
	if utils.Compare(n.e, e) < 0 {
		n.left = b.add(n.left, e)
	} else if utils.Compare(n.e, e) > 0 {
		n.right = b.add(n.right, e)
	}
	return n
}

func (b *BST) Contains(e interface{}) bool {
	return b.contains(b.root, e)
}
func (b *BST) contains(n *Node, e interface{}) bool {
	if n == nil {
		return false
	}
	if utils.Compare(n.e, e) == 0 {
		return true
	} else if utils.Compare(n.e, e) < 0 {
		return b.contains(n.left, e)
	} else {
		return b.contains(n.right, e)
	}
}

// 前序遍历 根-左-右
func (b *BST) PreOrder(){
	b.preOrder(b.root)
}
func (b *BST) preOrder(n *Node){
	if n == nil {
		return
	}
	fmt.Println(n.e)
	b.preOrder(n.left)
	b.preOrder(n.right)
}

// 中序遍历 左-根-右
func (b *BST) InOrder(){
	b.inOrder(b.root)
}
func (b *BST) inOrder(n *Node){
	if n == nil {
		return
	}
	b.inOrder(n.left)
	fmt.Println(n.e)
	b.inOrder(n.right)
}

// 后序遍历 左-右-根
func (b *BST) PostOrder(){
	b.postOrder(b.root)
}
func (b *BST) postOrder(n *Node){
	if n == nil {
		return
	}
	b.postOrder(n.left)
	b.postOrder(n.right)
	fmt.Println(n.e)
}

// 查找最小元素值
func (b *BST) Minimum() interface{} {
	if b.size == 0 {
		panic("BST is empty")
	}
	return b.minimum(b.root).e
}
// 查找最小元素
func (b *BST) minimum(n *Node) *Node {
	if n.left == nil {
		return n
	}
	return b.minimum(n.left)
}

// 查找最大元素值
func (b *BST) Maximum() interface{} {
	if b.size == 0 {
		panic("BST is empty")
	}
	return b.maximum(b.root).e
}
// 查找最大元素
func (b *BST) maximum(n *Node) *Node {
	if n.right == nil {
		return n
	}
	return b.maximum(n.right)
}

// 删除最小元素
func (b *BST) RemoveMin() interface{} {
	ret := b.Minimum()
	b.root = b.removeMin(b.root)
	return ret
}
func (b *BST) removeMin(n *Node) *Node {
	if n.left == nil {
		rightNode := n.right
		b.size --
		return rightNode
	}
	n.left = b.removeMin(n.left)
	return n
}
// 删除最大元素
func (b *BST) RemoveMax() interface{} {
	ret := b.Maximum()
	b.root = b.removeMax(b.root)
	return ret
}
func (b *BST) removeMax(n *Node) *Node {
	if n.right == nil {
		leftNode := n.left
		b.size --
		return leftNode
	}
	n.right = b.removeMax(n.right)
	return n
}

// 删除元素e
func (b *BST) Remove(e interface{}) {
	b.root = b.remove(b.root, e)
}
func (b *BST) remove(n *Node, e interface{}) *Node {
	if n == nil {
		return nil
	}

	if utils.Compare(n.e, e) < 0 {
		n.left = b.remove(n.left, e)
		return n
	} else if utils.Compare(n.e, e) > 0 {
		n.right = b.remove(n.right, e)
		return n
	} else {
		// 左子树为空
		if n.left == nil {
			rightNode := n.right
			n.right = nil
			b.size --
			return rightNode
		}
		// 右子树为空
		if n.right == nil {
			leftNode := n.left
			n.left = nil
			b.size --
			return leftNode
		}
		// 左右子树都存在
		successor := b.minimum(n.right)
		successor.right = b.removeMin(n.right)
		successor.left = n.left
		n.left = nil
		n.right = nil
		return successor
	}
}
func (b *BST) String() string {
	var buffer bytes.Buffer
	b.generateBSTString(b.root,0,&buffer)
	return buffer.String()
}
func (b *BST) generateBSTString(n *Node, depth int, buffer *bytes.Buffer) {
	if n == nil {
		buffer.WriteString(b.generateDepthString(depth) + "nil\n")
		return
	}
	buffer.WriteString(b.generateDepthString(depth) + fmt.Sprint(n.e) + "\n")
	b.generateBSTString(n.left, depth+1, buffer)
	b.generateBSTString(n.right, depth+1, buffer)
}

func (b *BST) generateDepthString(depth int) string {
	var buffer bytes.Buffer
	for i := 0; i < depth; i ++ {
		buffer.WriteString("--")
	}
	return buffer.String()
}