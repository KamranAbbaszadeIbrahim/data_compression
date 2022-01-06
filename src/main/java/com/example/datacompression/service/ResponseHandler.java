package com.example.datacompression.service;

import com.example.datacompression.model.Objects;
import com.example.datacompression.model.Result;
import com.example.datacompression.util.Input;
import com.example.datacompression.util.Output;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseHandler {
    private final CompressionService compressionService;

    public ResponseHandler(CompressionService compressionService) {
        this.compressionService = compressionService;
    }

    public void handle(String path) throws IOException {
        List<String> tmp = Input.getPathsAdvanced(path).getPath();
        List<String> paths = new ArrayList<>();
        for(int i=0; i<tmp.size(); i++){
            paths.add(tmp.get(i));
        }
        int count = paths.size();

        for(String string: paths){
            Result result = compressionService.recognize(string);
            Output.output(result.toString(), "logs.txt");
            if(result.getStatus().equals("200") || result.getStatus().contains("200")){
                StringBuilder sb = new StringBuilder();
                boolean bool = true;
                for(Objects objects: result.getData().getObjects()){
                    sb.append(objects.toString()).append(" ");

                    if(objects.getName().equals("Strawberry") || objects.getName().equals("strawberry") || objects.getName().equals("STRAWBERRY")){
                        Output.output(string.concat(" \t").concat(objects.toString()));
                        System.out.println(string.concat(" \t").concat(objects.toString()));
                        bool = false;
                    }
                }
                if(bool){
                    Output.output(string.concat(" \t").concat("Could not recognize: ").concat(sb.toString()));
                    System.out.println(string.concat(" \t").concat("Could not recognize: ").concat(sb.toString()));
                }

            }
            else{
                Output.output(string.concat(" \t").concat("Could not recognize: ").concat("HTTP status != 200"));
                System.out.println("Terminating app");
                break;
            }
            System.out.println(--count + " images left");
        }

    }
}
