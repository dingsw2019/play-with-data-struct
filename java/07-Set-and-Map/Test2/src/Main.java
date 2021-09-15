import java.util.ArrayList;

public class Main {

    private static double testSet(Set<String> set, String filename){

        double startTime = System.nanoTime();

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename,words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);

            System.out.println("Total Different Words: " + set.getSize());
        }

        double endTime = System.nanoTime();

        return (endTime - startTime) / 100000000.0;
    }

    public static void main(String[] args) {


        String dir = "Test2/";
        String filename = "pride-and-prejudice.txt";

        BSTSet<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet,dir+filename);
        System.out.println("bst time: " + time1);

        System.out.println();

        LinkedListSet<String> listSet = new LinkedListSet<>();
        double time2 = testSet(listSet,dir+filename);
        System.out.println("linkedlist time: " + time2);


//        BST<Integer> bst = new BST<>();
//        int[] nums = {5,3,6,8,4,2};
//        for (int num : nums)
//            bst.add(num);
//        bst.preOrder();
//        System.out.println();
//
//        bst.preOrderNR();
//        System.out.println();
//
//        bst.inOrder();
//        System.out.println();
//
//        bst.inOrderNR();
//        System.out.println();

//        bst.postOrder();
//        System.out.println();

//        bst.levelOrder();
//        System.out.println();

        // 最小值, 删除最小值
//        System.out.println("最小值：" + bst.minimum());
//        bst.removeMin();
//        bst.inOrderNR();
//        System.out.println();

        // 最大值, 删除最大值
//        System.out.println("最大值：" + bst.maximum());
//        bst.removeMax();
//        bst.inOrderNR();
//        System.out.println();
        
        //
//        bst.remove(8);
//        bst.inOrderNR();
//        System.out.println();

    }
}
