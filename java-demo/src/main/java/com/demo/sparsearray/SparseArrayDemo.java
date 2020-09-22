package com.demo.sparsearray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 稀疏数组
 */
public class SparseArrayDemo {

    /**
     * 打印二维数组
     *
     * @param arr
     */
    public static void printArray(int[][] arr) {
        for (int[] row : arr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    /**
     * int 数组转String数组
     * @param arr
     * @return
     */
    public static String[] toStringArray(int[] arr) {
        String[] strArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            strArr[i] = Integer.toString(arr[i]);
        }
        return strArr;
    }


    /**
     * 保存数组到磁盘
     * @param arr
     */
    public static void saveArray(int[][] arr) {
        try {

            PrintWriter writer = new PrintWriter("map.data");
            for (int[] row : arr) {
                String[] strArr = toStringArray(row);
                writer.println(String.join(",", strArr));
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件读取数组
     * @return
     */
    public static int[][] readArray() {
        Scanner scanner;
        List<String[]> list = new ArrayList<>();

        try {
            scanner = new Scanner(new FileReader("map.data"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line.split(","));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int[][] arr = new int[list.size()][list.get(0).length];

        for (int i = 0; i < list.size(); i++) {
            String[] row = list.get(i);
            for (int j = 0; j < row.length; j++) {
                arr[i][j] = Integer.parseInt(row[j]);
            }
        }
        return arr;
    }


    /**
     * 二维数组转稀疏数组
     *
     * @param arr
     * @return
     */
    public static int[][] toSparseArray(int[][] arr) {
        // 统计二维数组中非零元素个数
        int count = 0;
        for (int[] row : arr) {
            for (int data : row) {
                if (data != 0) {
                    count++;
                }
            }
        }

        // 创建稀疏数组，并保存元数据
        int[][] sparseArr = new int[count + 1][3];
        sparseArr[0][0] = arr.length;
        sparseArr[0][1] = arr[0].length;
        sparseArr[0][2] = count;

        // 保存非零数据
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0) {
                    index++;
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = arr[i][j];
                }
            }
        }
        return sparseArr;
    }

    /**
     * 稀疏数组转二维数组
     *
     * @param sparseArr
     * @return
     */
    public static int[][] fromSparseArray(int[][] sparseArr) {
        int[][] arr = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            int[] row = sparseArr[i];
            arr[row[0]][row[1]] = row[2];
        }

        return arr;
    }


    public static void main(String[] args) {
        // 创建11 * 11 的二维数组
        int[][] arr = new int[11][11];
        arr[1][2] = 1;
        arr[3][4] = 2;

        System.out.println("原始的二维数组");
        printArray(arr);

        int[][] sparseArr = toSparseArray(arr);

        System.out.println("稀疏数组");
        printArray(sparseArr);

        // 存盘，读取
        saveArray(sparseArr);
        int[][] readArr = readArray();
        System.out.println("读取的稀疏数组");
        printArray(readArr);

        System.out.println("恢复的二维数组");
        int[][] arr2 = fromSparseArray(readArr);
        printArray(arr2);

    }

}
