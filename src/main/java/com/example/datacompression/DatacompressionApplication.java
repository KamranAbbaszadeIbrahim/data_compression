package com.example.datacompression;

import com.example.datacompression.model.Dir;
import com.example.datacompression.model.Objects;
import com.example.datacompression.model.Result;
import com.example.datacompression.util.Input;
import com.google.gson.Gson;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DatacompressionApplication {

    public static void main(String[] args) throws IOException{
        SpringApplication.run(DatacompressionApplication.class, args);
        System.setProperty("java.awt.headless", "false");
        //ImageFilteringService.rename();
        //ImageFilteringService.changeToBlackAndWhite("png");
        //ImageFilteringService.changeContrast("png"); +
        //ImageFilteringService.changeBrightness("png"); +
        //ImageFilteringService.changeSharpening("png"); +
        //ImageFilteringService.changeBlurring("png"); +
        //ImageFilteringService.changeContrastAndBrightness("png"); +
        //ImageFilteringService.changeContrastAndSharpening("png"); +
        //ImageFilteringService.changeContrastAndBlurring("png"); +
        //ImageFilteringService.changeBrightnessAndSharpening("png"); +
        //ImageFilteringService.changeBrightnessAndBlurring("png"); +

        //ImageFilteringService.changeToJPG();

        //ImageFilteringService.changeToBlackAndWhite("jpg");
        //ImageFilteringService.changeContrast("jpg"); +
        //ImageFilteringService.changeBrightness("jpg"); +
        //ImageFilteringService.changeSharpening("jpg"); +
        //ImageFilteringService.changeBlurring("jpg"); +
        //ImageFilteringService.changeContrastAndBrightness("jpg"); +
        //ImageFilteringService.changeContrastAndSharpening("jpg"); +
        //ImageFilteringService.changeContrastAndBlurring("jpg"); +
        //ImageFilteringService.changeBrightnessAndSharpening("jpg"); +
        //ImageFilteringService.changeBrightnessAndBlurring("jpg"); +

        //run8();
    }

    private static void run8() throws IOException{
        List<String> tmp = Input.getPathsAdvanced(Constants.out[0]).getPath();
        List<String> paths = new ArrayList<>();
        for(int i=250; i<tmp.size(); i++){
            paths.add(tmp.get(i));
        }
        System.out.println(paths.get(0));
    }

    private static void run7() throws IOException{
        System.out.println("run");
        Dir dir = Input.getPathsAdvanced(Constants.url[0]);
        for(int i=0; i< dir.getName().size(); i++){
            System.out.println(dir.getName().get(i) + " \t" + dir.getPath().get(i));
        }

        System.out.println(dir.getName().size());

    }

    private static void run6(){
        String string = "{\"status\":200,\"message\":\"Success\",\"data\":{\"error\":0,\"type\":\"objects\",\"objects\":[{\"name\":\"Plant\",\"confidence\":99.57505798339844,\"coordinates\":[]},{\"name\":\"Fruit\",\"confidence\":97.23832702636719,\"coordinates\":[]},{\"name\":\"Food\",\"confidence\":97.23832702636719,\"coordinates\":[]},{\"name\":\"Apple\",\"confidence\":91.79641723632812,\"coordinates\":[{\"x\":0.16583111882209778,\"y\":0.2945314347743988,\"width\":0.16521801054477692,\"height\":0.3112419843673706}]},{\"name\":\"Produce\",\"confidence\":91.1896743774414,\"coordinates\":[]},{\"name\":\"Persimmon\",\"confidence\":85.50629425048828,\"coordinates\":[]}],\"quota\":14992}}";
        Gson g = new Gson();
        Result r = g.fromJson(string, Result.class);
        if(r.getStatus().equals("200")){
            for(Objects objects: r.getData().getObjects()){
                if(objects.getName().equals("Apple") || objects.getName().equals("apple") || objects.getName().equals("APPLE")){
                    System.out.println(objects);
                }
            }
        }
        System.out.println(r.toString());
    }

    private static void test(){
        String[] s = {
                "0.1",
                "0.2",
                "0.3",
                "0.4",
                "0.5",
                "0.6",
                "0.7",
                "0.8",
                "0.9",
                "1",
                "1.1",
                "1.2",
                "1.3",
                "1.4",
                "1.5",
                "1.6",
                "1.7",
                "1.8",
                "1.9",
                "2",
                "2.1",
                "2.2",
                "2.3",
                "2.4",
                "2.5",
                "2.6",
                "2.7",
                "2.8",
                "2.9",
                "3"
        };

        for(String i: s){
            System.out.println(Float.parseFloat(i));
        }
    }

    private static void run() throws IOException {
        String imagePath = Constants.url1.concat("/1.png");
        BufferedImage myPicture = ImageIO.read(new File(imagePath));
        RescaleOp rescaleOp = new RescaleOp(1f, -75, null);
        rescaleOp.filter(myPicture, myPicture);  // Source and destination are the same.

        //ImageIO.write(myPicture, "png", new FileOutputStream(new File("images/test.png")));



        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);

        JFrame f = new JFrame();
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(new Dimension(myPicture.getWidth(), myPicture.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
    }

    private static void run2() throws IOException{
        ImagePlus imp = IJ.openImage(Constants.url1.concat("/47_2.png"));

        ImageProcessor ip = imp.getProcessor();
        ip.setColor(Color.BLUE);
        ip.setLineWidth(4);
        ip.drawRect(10, 10, imp.getWidth() - 20, imp.getHeight() - 20);

        imp.show();
    }

    private static void run3() throws IOException{
        String imagePath = Constants.url1.concat("/1.png");
        BufferedImage myPicture = ImageIO.read(new File(imagePath));

        // A 3x3 kernel that sharpens an image
        Kernel kernel = new Kernel(3, 3, new float[]{ -1, -1, -1, -1, 9, -1, -1, -1, -1});
        BufferedImageOp op = new ConvolveOp(kernel);

        myPicture = op.filter(myPicture, null);

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);

        JFrame f = new JFrame();
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(new Dimension(myPicture.getWidth(), myPicture.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
    }

    private static void run4() throws IOException{
        String imagePath = Constants.url1.concat("/1.png");
        BufferedImage myPicture = ImageIO.read(new File(imagePath));

        // A 3x3 kernel that sharpens an image
        Kernel kernel = new Kernel(3, 3, new float[]{ 1f / 9f, 1f / 9f, 1f / 9f,
                1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });

        BufferedImageOp op = new ConvolveOp(kernel);

        myPicture = op.filter(myPicture, null);

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);

        JFrame f = new JFrame();
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(new Dimension(myPicture.getWidth(), myPicture.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
    }

    private static void run5() throws IOException{
        String imagePath = Constants.url1.concat("/1.png");
        BufferedImage myPicture = ImageIO.read(new File(imagePath));



        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);

        JFrame f = new JFrame();
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(new Dimension(myPicture.getWidth(), myPicture.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
    }

}
