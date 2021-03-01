package com.leetcode.practice.num;

public class ThreeProduct_628 {
	public static int maximumProduct(int[] nums) {
		if (nums.length == 3) {
			return nums[0] * nums[1] * nums[2];
		}


		int lastSortedIndex = nums.length;
		int flag = 0;

		boolean sorted = false;
		while (!sorted) {
			for (int i = 1; i < lastSortedIndex; i++) {
				if (nums[i - 1] > nums[i]) {
					swap(nums, i - 1, i);
					flag = i;
				}
			}
			if (flag <= nums.length - 3) {
				break;
			} else {
				lastSortedIndex = flag;
			}
		}

		return nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
	}

	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public static void main(String[] args) {
		int[] arr = {4, 3, 2, 1};
		int i = maximumProduct(arr);
		System.out.println(i);

	}
}
