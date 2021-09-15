/**
 * 什么是递归
 *      将原来的问题, 转化为更小的同一问题
 *
 * 举例：数组求和
 *      Sum(arr[0...n-1]) = arr[0] + Sum(arr[1...n-1])
 *          Sum(arr[1...n-1]) 相比 Sum(arr[0...n-1]) 就是更小的同一问题
 *
 *      Sum(arr[1...n-1]) = arr[1] + Sum(arr[2...n-1])
 *          ... ...
 *      Sum(arr[n-1...n-1]) = arr[n-1] + Sum([])
 *
 *
 * 递归的两个基本步骤
 *      1. 求解的最基本问题, 到无穷时, 解值是多少
 *      2. 把原问题转化成更小的问题
 */
public class Sum {

    // 外部调用
    public static int sum(int[] arr){
        return sum(arr,0);
    }

    // 计算arr[l...n)这个区间内所有数字的和
    private static int sum(int[] arr, int l){
        if (l == arr.length)
            return 0;

        return arr[l] + sum(arr,l+1);
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(sum(nums));
    }
}
