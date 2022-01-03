package com.example.datacompression.service;

import com.example.datacompression.Constants;
import com.example.datacompression.util.Input;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ImageFilteringService {
    public static void rename () throws IOException {

        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(int j=0; j < paths.size(); j++){
                BufferedImage myPicture = ImageIO.read(new File(Constants.url[i].concat("/").concat(paths.get(j))));
                String outputName = Constants.out[i].concat("/").concat("original 2/").concat(String.valueOf(j+1).concat(".png"));
                FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("original 2/")));

                System.out.println(outputName);
                ImageIO.write(myPicture, "png", new FileOutputStream(outputName));
            }
        }
    }

    public static void changeToBlackAndWhite(String format) throws IOException{

        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(String path: paths){
                BufferedImage tmp = ImageIO.read(new File(Constants.url[i].concat("/").concat(path)));
                BufferedImage myPicture = new BufferedImage(tmp.getWidth(), tmp.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

                Graphics2D graphic = myPicture.createGraphics();
                graphic.drawImage(tmp, 0, 0, Color.WHITE, null);
                graphic.dispose();

                String outputName = Constants.out[i].concat("/black and white/").concat(path);
                FileUtils.forceMkdir(new File(Constants.out[i].concat("/black and white")));

                System.out.println(outputName);

                ImageIO.write(myPicture, format, new FileOutputStream(outputName));
            }
        }
        System.out.println("END");
    }

    public static void changeContrast(String format) throws IOException {

        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(String path: paths){
                for(int j=0; j<Constants.factor.length; j++){
                    BufferedImage myPicture = ImageIO.read(new File(Constants.url[i].concat("/").concat(path)));

                    System.out.println("scaleFactor: " + Constants.factor[j]);
                    System.out.println(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100))));


                    RescaleOp rescaleOp = new RescaleOp(Float.parseFloat(Constants.factor[j]), 0, null);
                    rescaleOp.filter(myPicture, myPicture);

                    String outputName;
                    if(Float.parseFloat(Constants.factor[j]) < 1) {
                        outputName = Constants.out[i].concat("/").concat("contrast -").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("%/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("contrast -").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("%")));
                    }
                    else if(Float.parseFloat(Constants.factor[j]) > 1){
                        outputName = Constants.out[i].concat("/").concat("contrast +").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("%/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("contrast +").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("%")));
                    }
                    else{
                        continue;
                    }
                    System.out.println(outputName);
                    ImageIO.write(myPicture, format, new FileOutputStream(outputName));
                }
            }
        }
        System.out.println("END");
    }

    public static void changeBrightness(String format) throws IOException{
        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(String path: paths){
                for(int j=0; j<Constants.offset.length; j++){
                    BufferedImage myPicture = ImageIO.read(new File(Constants.url[i].concat("/").concat(path)));

                    System.out.println("offset: " + Constants.offset[j]);

                    RescaleOp rescaleOp = new RescaleOp(1f, Integer.parseInt(Constants.offset[j]), null);
                    rescaleOp.filter(myPicture, myPicture);

                    String outputName;
                    if(Integer.parseInt(Constants.offset[j]) < 0) {
                        outputName = Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("%/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("%")));
                    }
                    else if(Integer.parseInt(Constants.offset[j]) > 0){
                        outputName = Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("%/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("%")));
                    }
                    else{
                        continue;
                    }
                    System.out.println(outputName);
                    ImageIO.write(myPicture, format, new FileOutputStream(outputName));
                }
            }
        }
        System.out.println("END");
    }

    public static void changeSharpening(String format) throws IOException{
        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(String path: paths){
                BufferedImage myPicture = ImageIO.read(new File(Constants.url[i].concat("/").concat(path)));

                Kernel kernel = new Kernel(3, 3, new float[]{ -1, -1, -1, -1, 9, -1, -1, -1, -1});
                BufferedImageOp op = new ConvolveOp(kernel);

                myPicture = op.filter(myPicture, null);

                String outputName = Constants.out[i].concat("/sharpened/").concat(path);
                FileUtils.forceMkdir(new File(Constants.out[i].concat("/sharpened")));

                System.out.println(outputName);

                ImageIO.write(myPicture, format, new FileOutputStream(outputName));
            }
        }
        System.out.println("END");
    }

    public static void changeBlurring(String format) throws IOException{
        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(String path: paths){
                BufferedImage myPicture = ImageIO.read(new File(Constants.url[i].concat("/").concat(path)));

                Kernel kernel = new Kernel(3, 3, new float[]{ 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });
                BufferedImageOp op = new ConvolveOp(kernel);

                myPicture = op.filter(myPicture, null);

                String outputName = Constants.out[i].concat("/blurred/").concat(path);
                FileUtils.forceMkdir(new File(Constants.out[i].concat("/blurred")));

                System.out.println(outputName);

                ImageIO.write(myPicture, format, new FileOutputStream(outputName));
            }
        }
        System.out.println("END");
    }

    public static void changeContrastAndBrightness(String format) throws IOException{
        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(String path: paths){
                for(int j=0; j<Constants.factor.length; j++){
                    BufferedImage myPicture = ImageIO.read(new File(Constants.url[i].concat("/").concat(path)));

                    System.out.println("scaleFactor: " + Constants.factor[j] + " ; offset: " + Constants.offset[j]);

                    RescaleOp rescaleOp = new RescaleOp(Float.parseFloat(Constants.factor[j]), Integer.parseInt(Constants.offset[j]), null);
                    rescaleOp.filter(myPicture, myPicture);

                    String outputName;
                    if(Float.parseFloat(Constants.factor[j]) < 1) {
                        outputName = Constants.out[i].concat("/").concat("contrast -").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and ").concat("brightness ").concat(Constants.offset[j]).concat("%/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("contrast -").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and ").concat("brightness ").concat(Constants.offset[j]).concat("%")));
                    }
                    else if(Float.parseFloat(Constants.factor[j]) > 1){
                        outputName = Constants.out[i].concat("/").concat("contrast +").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and ").concat("brightness ").concat(Constants.offset[j]).concat("%/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("contrast +").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and ").concat("brightness ").concat(Constants.offset[j]).concat("%")));
                    }
                    else{
                        continue;
                    }
                    System.out.println(outputName);
                    ImageIO.write(myPicture, format, new FileOutputStream(outputName));
                }
            }
        }
        System.out.println("END");
    }

    public static void changeContrastAndSharpening(String format) throws IOException{
        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(String path: paths){
                for(int j=0; j<Constants.factor.length; j++){
                    BufferedImage myPicture = ImageIO.read(new File(Constants.url[i].concat("/").concat(path)));
                    System.out.println("scaleFactor: " + Constants.factor[j]);

                    RescaleOp rescaleOp = new RescaleOp(Float.parseFloat(Constants.factor[j]), 0, null);
                    rescaleOp.filter(myPicture, myPicture);

                    Kernel kernel = new Kernel(3, 3, new float[]{ -1, -1, -1, -1, 9, -1, -1, -1, -1});
                    BufferedImageOp op = new ConvolveOp(kernel);

                    myPicture = op.filter(myPicture, null);

                    String outputName;
                    if(Float.parseFloat(Constants.factor[j]) < 1) {
                        outputName = Constants.out[i].concat("/").concat("contrast -").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and sharpened/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("contrast -").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and sharpened")));
                    }
                    else if(Float.parseFloat(Constants.factor[j]) > 1){
                        outputName = Constants.out[i].concat("/").concat("contrast +").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and sharpened/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("contrast +").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and sharpened")));
                    }
                    else{
                        continue;
                    }
                    System.out.println(outputName);
                    ImageIO.write(myPicture, format, new FileOutputStream(outputName));
                }
            }
        }
        System.out.println("END");
    }

    public static void changeContrastAndBlurring(String format) throws IOException{
        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(String path: paths){
                for(int j=0; j<Constants.factor.length; j++){
                    BufferedImage myPicture = ImageIO.read(new File(Constants.url[i].concat("/").concat(path)));
                    System.out.println("scaleFactor: " + Constants.factor[j]);

                    RescaleOp rescaleOp = new RescaleOp(Float.parseFloat(Constants.factor[j]), 0, null);
                    rescaleOp.filter(myPicture, myPicture);

                    Kernel kernel = new Kernel(3, 3, new float[]{ 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });
                    BufferedImageOp op = new ConvolveOp(kernel);

                    myPicture = op.filter(myPicture, null);

                    String outputName;
                    if(Float.parseFloat(Constants.factor[j]) < 1) {
                        outputName = Constants.out[i].concat("/").concat("contrast -").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and blurred/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("contrast -").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and blurred")));
                    }
                    else if(Float.parseFloat(Constants.factor[j]) > 1){
                        outputName = Constants.out[i].concat("/").concat("contrast +").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and blurred/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("contrast +").concat(String.valueOf(Math.abs((int) ((1-Double.parseDouble(Constants.factor[j])) * 100)))).concat("% and blurred")));
                    }
                    else{
                        continue;
                    }
                    System.out.println(outputName);
                    ImageIO.write(myPicture, format, new FileOutputStream(outputName));
                }
            }
        }
        System.out.println("END");
    }

    public static void changeBrightnessAndSharpening(String format) throws IOException{
        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(String path: paths){
                for(int j=0; j<Constants.offset.length; j++){
                    BufferedImage myPicture = ImageIO.read(new File(Constants.url[i].concat("/").concat(path)));

                    System.out.println("offset: " + Constants.offset[j]);

                    RescaleOp rescaleOp = new RescaleOp(1f, Integer.parseInt(Constants.offset[j]), null);
                    rescaleOp.filter(myPicture, myPicture);

                    Kernel kernel = new Kernel(3, 3, new float[]{ -1, -1, -1, -1, 9, -1, -1, -1, -1});
                    BufferedImageOp op = new ConvolveOp(kernel);

                    myPicture = op.filter(myPicture, null);

                    String outputName;
                    if(Integer.parseInt(Constants.offset[j]) < 0) {
                        outputName = Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("% and sharpened/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("% and sharpened")));
                    }
                    else if(Integer.parseInt(Constants.offset[j]) > 0){
                        outputName = Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("% and sharpened/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("% and sharpened")));
                    }
                    else{
                        continue;
                    }
                    System.out.println(outputName);
                    ImageIO.write(myPicture, format, new FileOutputStream(outputName));
                }
            }
        }
        System.out.println("END");
    }

    public static void changeBrightnessAndBlurring(String format) throws IOException{
        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(String path: paths){
                for(int j=0; j<Constants.offset.length; j++){
                    BufferedImage myPicture = ImageIO.read(new File(Constants.url[i].concat("/").concat(path)));

                    System.out.println("offset: " + Constants.offset[j]);

                    RescaleOp rescaleOp = new RescaleOp(1f, Integer.parseInt(Constants.offset[j]), null);
                    rescaleOp.filter(myPicture, myPicture);

                    Kernel kernel = new Kernel(3, 3, new float[]{ 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });
                    BufferedImageOp op = new ConvolveOp(kernel);

                    myPicture = op.filter(myPicture, null);

                    String outputName;
                    if(Integer.parseInt(Constants.offset[j]) < 0) {
                        outputName = Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("% and blurred/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("% and blurred")));
                    }
                    else if(Integer.parseInt(Constants.offset[j]) > 0){
                        outputName = Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("% and blurred/").concat(path);
                        FileUtils.forceMkdir(new File(Constants.out[i].concat("/").concat("brightness ").concat(Constants.offset[j]).concat("% and blurred")));
                    }
                    else{
                        continue;
                    }
                    System.out.println(outputName);
                    ImageIO.write(myPicture, format, new FileOutputStream(outputName));
                }
            }
        }
        System.out.println("END");
    }

    public static void changeToJPG() throws IOException{
        for(int i=0; i<Constants.url.length; i++){
            List<String> paths = Input.getPaths(Constants.url[i]);

            for(String path: paths){
                BufferedImage myPicture = ImageIO.read(new File(Constants.url[i].concat("/").concat(path)));

                String outputName = Constants.out[i].concat("/jpg_compressed/").concat(path);
                FileUtils.forceMkdir(new File(Constants.out[i].concat("/jpg_compressed")));

                System.out.println(outputName);

                ImageIO.write(myPicture, "jpg", new FileOutputStream(outputName));
            }
        }
        System.out.println("END");
    }
}
