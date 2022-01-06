package com.example.datacompression.controller;

import com.example.datacompression.Constants;
import com.example.datacompression.service.ResponseHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
public class CompressionController {
    private final ResponseHandler handler;

    public CompressionController(ResponseHandler handler) {
        this.handler = handler;
    }

    @GetMapping
    public String test() throws IOException{
        try{
           handler.handle(Constants.out[2]);
           return "OK";
        }catch (Exception e){
            return e.getMessage();
        }
    }


}
