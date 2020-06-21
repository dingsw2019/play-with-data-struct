import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    private class Node {
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

    public boolean isEmpty(){
        return size==0;
    }

    // 添加元素, 递归算法
    public void add(E e){
        root = add(root,e);
    }

    private Node add(Node node, E e){

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = add(node.left,e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right,e);

        return node;
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

    // 后序遍历, 递归算法
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

    // 前序遍历, 非递归算法
    public void preOrderNR(){

        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){

            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null)
                stack.push(cur.right);

            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    // 中序遍历, 非递归算法
    public void inOrderNR(){
        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();
        Node p = root;

        while (!stack.isEmpty() || p != null) {

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

    // 广度遍历
    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left != null)
                q.add(cur.left);

            if (cur.right != null)
                q.add(cur.right);
        }
    }

    public E minimumNR(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        Node p = root;
        while (p.left != null)
            p = p.left;

        return p.e;
    }

    // 查找二分搜索树的最小值
    public E minimum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        return minimum(root).e;
    }

    private Node minimum(Node node){

        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    // 查找二分搜索树的最大值
    public E maximum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        return maximum(root).e;
    }

    public Node maximum(Node node){
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    // 删除二分搜索树的最小值
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
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

    // 删除二分搜索树的最大值, 递归算法
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node){

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e){
        root = remove(root,e);
    }

    private Node remove(Node node, E e){

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left,e);
            return node;

        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right,e);
            return node;

        } else {

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

//    @Override
//    public String toString(){
//
//    }
}
