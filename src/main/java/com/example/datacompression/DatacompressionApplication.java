package com.example.datacompression;

import com.example.datacompression.service.ImageFilteringService;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.ImageObserver;
import java.awt.image.Kernel;

@SpringBootApplication
public class DatacompressionApplication {

    public static void main(String[] args) throws IOException{
        //SpringApplication.run(DatacompressionApplication.class, args);
        System.setProperty("java.awt.headless", "false");
        //ImageFilteringService.rename();
        //ImageFilteringService.changeToBlackAndWhite("png");
        //ImageFilteringService.changeContrast("png");
        //ImageFilteringService.changeBrightness("png");
        //ImageFilteringService.changeSharpening("jpg");
        //ImageFilteringService.changeBlurring("png");
        //ImageFilteringService.changeContrastAndBrightness("png");
        //ImageFilteringService.changeContrastAndSharpening("png");
        //ImageFilteringService.changeContrastAndBlurring("png");
        //ImageFilteringService.changeBrightnessAndSharpening("png");
        //ImageFilteringService.changeBrightnessAndBlurring("png");
        //ImageFilteringService.changeToJPG();
        //ImageFilteringService.changeToBlackAndWhite("jpg");
        //ImageFilteringService.changeContrast("jpg");
        //ImageFilteringService.changeBrightness("jpg");
        //ImageFilteringService.changeSharpening("jpg");
        //ImageFilteringService.changeBlurring("jpg");
        //ImageFilteringService.changeContrastAndBrightness("jpg");
        //ImageFilteringService.changeContrastAndSharpening("jpg");
        //ImageFilteringService.changeContrastAndBlurring("jpg");
        //ImageFilteringService.changeBrightnessAndSharpening("jpg");
        //ImageFilteringService.changeBrightnessAndBlurring("jpg");


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
        RescaleOp rescaleOp = new RescaleOp(1f, 0, null);
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
