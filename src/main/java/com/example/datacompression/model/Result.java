package com.example.datacompression.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String status;
    private String message;
    private Data data;
}
