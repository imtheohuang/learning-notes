package com.theo.algorithm;

/**
 * @author huangsuixin
 * @date 2019/10/06 15:59
 * @description 查找算法
 */
public class Search {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 10, 20, 30, 54, 60, 82};

        int value = binarySearch(arr, 3);
        System.out.println(value);
    }

    /**
     * 二分法查找
     *
     * @param arr   arr
     * @param value 值
     * @return 索引
     */
    public static int binarySearch(int[] arr, int value) {

        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {

            int mid = (low + high) / 2;
            if (value == arr[mid]) {
                return mid;
            }

            if (value < arr[mid]) {
                high = mid - 1;
            }

            if (value > arr[mid]) {
                low = mid + 1;
            }
        }

        return -1;
    }
}
