package com.example.datacompression.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Objects {
    private String name;
    private String confidence;
    private List<Coordinates> coordinates;
}
