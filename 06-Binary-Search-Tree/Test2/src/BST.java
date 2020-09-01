import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node (E e){
            this.e = e;
            this.left = this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = new Node(null);
        size = 0;
    }

    public int size(){return size;}
    public boolean isEmpty(){return size==0;}

    public void add(E e){
        add(root,e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (node.e.compareTo(e) < 0)
            node.left = add(node.left,e);
        else if (node.e.compareTo(e) > 0)
            node.right = add(node.right,e);

        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node node,E e) {
        if (node == null)
            return false;

        if (node.e.equals(e))
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left,e);
        else
            return contains(node.right,e);
    }

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

    private void preOrderNR(){

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        if (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null)
                stack.push(cur.right);

            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    public void inOrder(){
        preOrder(root);
    }

    private void inOrder(Node node){

        if (node == null)
            return;

        preOrder(node.left);
        System.out.println(node.e);
        preOrder(node.right);
    }

    public void inOrderNR(){

        Stack<Node> stack = new Stack<>();
        Node p = root;
        while (!stack.isEmpty() || p != null) {

            if (p != null) {
                stack.push(p);
                p = p.left;

            } else {
                p = stack.pop();
                System.out.println(p.e);
                p = p.right;
            }
        }
    }

    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();

        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.remove();
            System.out.println(node.e);

            if (node.left != null)
                q.add(node.left);

            if (node.right != null)
                q.add(node.right);
        }
    }

    public E minimum(){
        Node node = minimum(root);
        return node == null ? null : node.e;
    }
    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    public E maximum(){
        Node node = maximum(root);
        return node == null ? null : node.e;
    }
    private Node maximum(Node node){
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

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

    private Node remove(Node node,E e) {

        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0) {

            node.left = remove(node.left,e);
            return node;
        }
        else if (e.compareTo(node.e) > 0) {

            node.right = remove(node.right,e);
            return node;
        }
        else {

            if (node.right == null) {

                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else {

                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = null;
                node.right = null;

                return successor;
            }
        }
    }
}
