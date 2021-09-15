public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    @Override
    public int getSize(){
        return list.getSize();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public void push(E e){
        list.addFirst(e);
    }

    @Override
    public E pop(){
        return list.removeFirst();
    }

    @Override
    public E peek(){
        return list.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args) {

        LinkedListStack<Integer> stack = new LinkedListStack<>();

        // 入栈
        stack.push(1);
        stack.push(10);
        System.out.println("入栈结果：" + stack);

        // 出栈
        stack.pop();
        System.out.println("出栈结果：" + stack);

        // 查看栈顶
        int n = stack.peek();
        System.out.println("栈顶元素值：" + n);
        System.out.println(stack);
    }
}
