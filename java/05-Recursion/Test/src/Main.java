public class Main {
    public static void main(String[] args) {
//        LinkedList<Integer> linkedlist = new LinkedList<>();
        DummyLinkedList<Integer> linkedlist = new DummyLinkedList<>();
        for (int i = 0; i < 5; i++){
            linkedlist.addLast(i);
        }
        System.out.println(linkedlist);

        for (int i = 5; i < 10; i++){
            linkedlist.addFirst(i);
        }

        System.out.println(linkedlist);

        int ele = linkedlist.getFirst();
        System.out.println("getFirst element: " + ele);

        ele = linkedlist.getLast();
        System.out.println("getLast element: " + ele);

        int remEle = linkedlist.removeFirst();
        System.out.println("remove first element: " + remEle);
        System.out.println(linkedlist);

        remEle = linkedlist.removeLast();
        System.out.println("remove last element: " + remEle);
        System.out.println(linkedlist);
    }
}
