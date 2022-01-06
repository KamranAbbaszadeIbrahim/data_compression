package com.example.datacompression.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private String error;
    private String type;
    private List<Objects> objects;
    private String quota;
}
