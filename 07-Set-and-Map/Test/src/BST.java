import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){return size == 0;}

    public void add(E e){
        root = add(root,e);
    }

    private Node add(Node node, E e){
        if (node == null){
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = add(node.left,e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right,e);

        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node node, E e){
        if (node == null)
            return false;
        if (e.equals(node.e))
            return true;
        else if(e.compareTo(node.e) < 0)
            return contains(node.left,e);
        else
            return contains(node.right,e);
    }

    // 前序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 广度遍历
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            System.out.println(cur.e);

            if (cur.left != null)
                queue.add(cur.left);

            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    // 前序遍历, 非递归
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {

            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null)
                stack.add(cur.right);

            if (cur.left != null)
                stack.add(cur.left);
        }
    }

    // 中序遍历, 非递归
    public void inOrderNR(){
        Stack<Node> stack = new Stack<>();
        Node p = root;

        while (!stack.isEmpty() || p != null){
            // 入栈
            if (p != null){
                stack.push(p);
                p = p.left;

            // 出栈
            } else {
                p = stack.pop();
                System.out.println(p.e);
                p = p.right;
            }
        }
    }

    // 最小值
    public E minimum(){
        if (isEmpty())
            throw new IllegalArgumentException("empty");
        return minimum(root).e;
    }

    private Node minimum(Node node){
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    // 删除最小值
    public E removeMin(){
        E ret = minimum();
        removeMin(root);
        return ret;
    }
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }


    // 最大值
    public E maximum(){
        if (isEmpty())
            throw new IllegalArgumentException("empty");
        return maximum(root).e;
    }

    private Node maximum(Node node){
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    // 删除最大值
    public E removeMax(){
        E ret = maximum();
        removeMax(root);
        return ret;
    }
    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 删除任意节点
    public void remove(E e){
        if (isEmpty())
            throw new IllegalArgumentException("empty");

        root = remove(root,e);
    }
    private Node remove(Node node, E e){

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left,e);
            return node;

        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right,e);
            return node;

        } else { //e.compareTo(node.e) == 0
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }

}
