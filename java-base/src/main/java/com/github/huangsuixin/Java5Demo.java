package com.github.huangsuixin;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author huangsuixin
 * @date 2019/08/15 10:37
 * @description //TODO
 */
public class Java5Demo {

    public static void main(String[] args) {

        String[] values = of("hello", "world");

        Integer[] data = of(1, 2, 3, 4);
        FileInputStream read = null;
        try {
            read = new FileInputStream("D://");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileInputStream read1 = new FileInputStream("D://")) {
            read1.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @SafeVarargs
    private static <T> T[] of(T... values) {
        return values;
    }
}
