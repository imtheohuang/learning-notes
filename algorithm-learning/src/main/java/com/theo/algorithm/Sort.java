package com.theo.algorithm;

import java.util.Arrays;

/**
 * @author huangsuixin
 * @date 2019/10/06 15:53
 * @description 排序算法
 */
public class Sort {

    public static void main(String[] args) {
        int[] arr = {1, 5, 9, 10, 32, 7, 6, 4, 9, 2};

        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     *
     * @param arr arr
     */
    public static void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

}
