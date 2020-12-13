package com.study.lc.binarysearch;

/**
 * @author llc
 * @description
 * @date 2020/6/26 14:04
 */
public class BinarySearchDemo {
    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private int search(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                return search(nums, left, mid - 1, target);
            } else if (nums[mid] < target) {
                return search(nums, mid + 1, right, target);
            } else {
                return mid;
            }
        }

        return -1;
    }

    private BinarySearchDemo() {
    }

    private static BinarySearchDemo INSTANCE = new BinarySearchDemo();

    public static BinarySearchDemo getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        int[] testArr = new int[]{-1, 0, 3, 5, 9, 12};
        int target = 9;
        int search = getInstance().search(testArr, target);
    }
}
