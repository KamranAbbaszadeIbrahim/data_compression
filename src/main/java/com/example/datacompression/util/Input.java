package com.example.datacompression.util;

import com.example.datacompression.Constants;
import com.example.datacompression.model.Dir;
import org.springframework.core.io.FileSystemResource;

import java.io.*;

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

    public static Dir getPathsAdvanced(String path){
        Dir dir = new Dir();
        List<String> name = new ArrayList<>();
        List<String> p = new ArrayList<>();
        File folder = new File(path);

        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(files[i].isDirectory()){
                if(files[i].getName().equals(Constants.exception)){
                    continue;
                }
                Dir tmp = getPathsAdvanced(files[i].getPath());
                name.addAll(tmp.getName());
                p.addAll(tmp.getPath());
            }
            if (files[i].isFile()) {
                name.add(files[i].getName());
                p.add(files[i].getPath());
            }
        }
        dir.setName(name);
        dir.setPath(p);
        return dir;
    }

    public static FileSystemResource getTestFile(String path) throws IOException {
        return new FileSystemResource(path);
    }
}
