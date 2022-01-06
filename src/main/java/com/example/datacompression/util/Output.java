package com.example.datacompression.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Output {
    public static void output(String string){
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("statistics.txt", true));
            stream.write(string.getBytes());
            stream.write(System.lineSeparator().getBytes());
            stream.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    public static void output(String string, String path){
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path, true));
            stream.write(string.getBytes());
            stream.write(System.lineSeparator().getBytes());
            stream.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
}
