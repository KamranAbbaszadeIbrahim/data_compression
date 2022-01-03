package com.example.datacompression.service;

import com.example.datacompression.Constants;
import com.example.datacompression.util.Input;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompressionService {

    public byte[] getImage() throws IOException {
        File file = new File("images/apple.png");
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] arr = new byte[(int)file.length()];

        fileInputStream.read(arr);

        fileInputStream.close();

        return arr;
    }

    public List<byte[]> getImages() throws IOException{

        List<String> paths = Input.getPaths(Constants.url1);
        List<byte[]> result = new ArrayList<>();

        for(String path: paths){
            File file = new File(Constants.url1.concat("/").concat(path));
            System.out.println(Constants.url1.concat("/").concat(path));
            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] arr = new byte[(int)file.length()];

            fileInputStream.read(arr);

            fileInputStream.close();

            result.add(arr);
        }
        return result;
    }
}
