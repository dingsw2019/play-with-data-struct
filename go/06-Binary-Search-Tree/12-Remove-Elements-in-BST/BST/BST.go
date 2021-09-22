package BST

import (
	"bytes"
	"fmt"
	"github.com/dingsw2019/play-with-data-struct/go/utils"
)

type Node struct {
	e     interface{}
	left  *Node
	right *Node
}

func generateNode(e interface{}) *Node {
	return &Node{e: e}
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

func (b *BST) IsEmpty() bool {
	return b.size == 0
}

// 向二分搜索树中添加新的元素e
func (b *BST) Add(e interface{}) {
	b.root = b.add(b.root, e)
}

// 向以 Node 为根的二分搜索树中插入元素 e, 递归算法
// 返回插入新节点后二分搜索树的根
func (b *BST) add(n *Node, e interface{}) *Node {
	if n == nil {
		b.size++
		return generateNode(e)
	}
	c := utils.Compare(n.e, e)
	if c < 0 {
		n.left = b.add(n.left, e)
	} else if c > 0 {
		n.right = b.add(n.right, e)
	}
	return n
}

// 看二分搜索树中是否包含元素 e
func (b *BST) Contains(e interface{}) bool {
	return contains(b.root, e)
}

func contains(n *Node, e interface{}) bool {
	if n == nil {
		return false
	}

	c := utils.Compare(n.e, e)
	if c == 0 {
		return true
	} else if c < 0 {
		return contains(n.left, e)
	} else {
		return contains(n.right, e)
	}
}

// 前序遍历, 根 - 左 - 右
func (b *BST) PreOrder() {
	preOrder(b.root)
}

// 以 Node 为根的二分搜索树, 递归算法
func preOrder(n *Node) {
	if n == nil {
		return
	}

	fmt.Println(n.e)
	preOrder(n.left)
	preOrder(n.right)
}

// 中序遍历, 左 - 根 - 右
func (b *BST) InOrder() {
	inOrder(b.root)
}

func inOrder(n *Node) {
	if n == nil {
		return
	}

	inOrder(n.left)
	fmt.Println(n.e)
	inOrder(n.right)
}

// 后序遍历, 左 - 右 - 根
func (b *BST) PostOrder() {
	postOrder(b.root)
}

func postOrder(n *Node) {
	if n == nil {
		return
	}

	postOrder(n.left)
	postOrder(n.right)
	fmt.Println(n.e)
}

// 寻找二分搜索树的最小元素
func (b *BST) Minimum() interface{} {
	if b.size == 0 {
		panic("BST is empty")
	}
	return minimum(b.root).e
}

// 返回以 Node 为根的二分搜索树的最小值所在的节点
func minimum(n *Node) *Node {
	if n.left == nil {
		return n
	}
	return minimum(n.left)
}

// 寻找二分搜索树的最大元素
func (b *BST) Maximum() interface{} {
	if b.size == 0 {
		panic("BST is empty")
	}
	return maximum(b.root).e
}
func maximum(n *Node) *Node {
	if n.right == nil {
		return n
	}
	return maximum(n.right)
}

// 删除二分搜索树中最小的元素, 返回其值
func (b *BST) RemoveMin() interface{} {
	ret := b.Minimum()
	b.root = b.removeMin(b.root)
	return ret
}

// 删除以 Node 为根的二分搜索树的最小节点
// 返回删除节点后新的二分搜索树的根
func (b *BST) removeMin(n *Node) *Node {
	if n.left == nil {
		rightNode := n.right
		b.size--
		return rightNode
	}
	n.left = b.removeMin(n.left)
	return n

}

// 删除二分搜索树中最大的元素, 返回其值
func (b *BST) RemoveMax() interface{} {
	ret := b.Maximum()
	b.root = b.removeMax(b.root)
	return ret
}
func (b *BST) removeMax(n *Node) *Node {
	if n.right == nil {
		leftNode := n.left
		b.size--
		return leftNode
	}
	n.right = b.removeMax(n.right)
	return n
}

// 删除元素 e
func (b *BST) Remove(e interface{}) {
	b.root = b.remove(b.root, e)
}

// 删除以 Node 为根的元素e, 并返回根, 递归
func (b *BST) remove(n *Node, e interface{}) *Node {
	if n == nil {
		// 未找到目标元素 e
		return nil
	}
	// 查找元素 e
	if utils.Compare(n.e, e) < 0 {
		n.left = b.remove(n.left, e)
		return n
	} else if utils.Compare(n.e, e) > 0 {
		n.right = b.remove(n.right, e)
		return n
	} else {
		// 删除节点的左树为空
		if n.left == nil {
			rightNode := n.right
			n.right = nil
			b.size--
			return rightNode
		}
		// 删除节点的右子树为空
		if n.right == nil {
			leftNode := n.left
			n.left = nil
			b.size--
			return leftNode
		}
		// 删除节点的左右子树不为空
		// 找到大于删除节点值,且最接近的节点来代替删除节点
		successor := minimum(n.right)
		successor.right = b.removeMin(n.right)
		successor.left = n.left
		n.left = nil
		n.right = nil
		return successor
	}
}

func (b *BST) String() string {
	var buffer bytes.Buffer
	generateBSTString(b.root, 0, &buffer)
	return buffer.String()
}

func generateBSTString(n *Node, depth int, buffer *bytes.Buffer) {
	if n == nil {
		buffer.WriteString(generateDepthString(depth) + "nil\n")
		return
	}
	buffer.WriteString(generateDepthString(depth) + fmt.Sprint(n.e) + "\n")
	generateBSTString(n.left, depth+1, buffer)
	generateBSTString(n.right, depth+1, buffer)
}

func generateDepthString(depth int) string {
	var buf bytes.Buffer
	for i := 0; i < depth; i++ {
		buf.WriteString("--")
	}
	return buf.String()
}
