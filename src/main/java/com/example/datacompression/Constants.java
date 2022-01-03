package com.example.datacompression;

public abstract class Constants {
    public static String url1 = "images/apple/original";
    public static String url2 = "images/orange/original";
    public static String url3 = "images/strawberry/original";

    public static String[] url = {
            "images/apple/jpg_compressed",
            "images/orange/jpg_compressed",
            "images/strawberry/jpg_compressed"
    };

    public static String[] url0 = {
            "images/apple/original",
            "images/orange/original",
            "images/strawberry/original"
    };

    public static String[] out = {
            "images/apple",
            "images/orange",
            "images/strawberry"
    };

    public static String[] offset = {
            "-200",
            "-175",
            "-150",
            "-125",
            "-100",
            "-75",
            "-50",
            "-25",
            "25",
            "50",
            "75",
            "100",
            "125",
            "150",
            "175",
            "200"
    };

    public static String[] factor = {
            "0.0",
            "0.25",
            "0.5",
            "0.75",
            "1.25",
            "1.5",
            "1.75",
            "2",
            "2.25",
            "2.5",
            "2.75",
            "3",
            "3.25",
            "3.5",
            "3.75",
            "4",
    };
}
