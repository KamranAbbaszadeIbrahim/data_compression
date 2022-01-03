package com.example.datacompression.controller;

import com.example.datacompression.service.CompressionService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
public class CompressionController {
    private final CompressionService compressionService;

    public CompressionController(CompressionService compressionService) {
        this.compressionService = compressionService;
    }


    @GetMapping("/image")
    public @ResponseBody byte[] getImage() throws IOException {
        return compressionService.getImage();
    }


    @GetMapping("/data")
    public List<byte[]> getImages() throws IOException {
        return compressionService.getImages();
    }
}
