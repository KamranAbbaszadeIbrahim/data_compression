package com.example.datacompression.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Input {
    public static List<String> getPaths(String path){
        List<String> list = new ArrayList<>();
        File folder = new File(path);
        File[] files = folder.listFiles();

        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            if (files[i].isFile()) {
                list.add(files[i].getName());
            }
        }

        return list;
    }
}
