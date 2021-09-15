import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();

//        int[] nums = {5,3,6,8,4,2};
//        for (int num : nums)
//            bst.add(num);

//        bst.preOrder();
//        System.out.println();
//
//        bst.preOrderNR();
//        System.out.println();

//        bst.inOrder();
//        System.out.println();
//
//        bst.inOrderNR();
//        System.out.println();
//
//        bst.postOrder();
//        System.out.println();

//        bst.levelOrder();
//        System.out.println();

//        System.out.println("最小值: " + bst.minimum());
//
//        System.out.println("最大值: " + bst.maximum());
//
//        // 删除最小值
//        bst.removeMin();
//        System.out.println("最小值: " + bst.minimum());
//
//        // 删除最大值
//        bst.removeMax();
//        System.out.println("最大值: " + bst.maximum());

        Random random = new Random();
        int n = 1000;

        for (int i=0; i<n; i++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty())
            nums.add(bst.removeMin());

        System.out.println(nums);

        for (int i=1; i<nums.size(); i++)
            if (nums.get(i-1) > nums.get(i))
                throw new IllegalArgumentException("Error");

        System.out.println("removeMax test completed.");
    }
}
