package com.demo.fileutil;

import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {

    public static void main(String[] args) throws IOException {
        // 覆盖写入
        PrintWriter writer = new PrintWriter(new java.io.FileWriter("data.txt"));
        writer.println("hello");
        writer.println("world");
        writer.close();

        // 追加写入
        PrintWriter appendWriter = new PrintWriter(new FileWriter("data.txt", true));
        appendWriter.println("hello");
        appendWriter.println("world");
        appendWriter.close();

        // 按行读取
        List<String> list = new ArrayList<>();

        Scanner scanner = new Scanner(new FileReader("data.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            list.add(line);
        }
        scanner.close();

        System.out.println(list);
        // [hello, world, hello, world]

    }


}
