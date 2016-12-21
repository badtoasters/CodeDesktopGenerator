package curtis.toaster;

import curtis.toaster.Blocks.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import java.util.Random;

public class Main {
    public static final String PATH = "C:\\Users\\Public\\Pictures\\background.png";

    public static void main(String[] args) {
        NameGenerator.init();

        BlockMethod method = new BlockMethod(0);
        method.setMethodName(NameGenerator.getRandomMethodName());
        method.randomize();

        generate(method);

        System.out.println(method);

        BufferedImage background = createBackground( method );
        saveImage(background);

        WallpaperChanger.change(PATH);

        System.exit(0);
    }

    public static void saveImage( BufferedImage image ) {
        try {
            File outputfile = new File(PATH);
            ImageIO.write(image, "bmp", outputfile);
        } catch (IOException e) {

        }
    }

    public static BufferedImage createBackground(Block parent) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2d = image.createGraphics();
        image = setGradientMask(image , SwingConstants.VERTICAL);

        g2d.setColor(Color.WHITE);
        g2d.setFont( new Font("Verdana", Font.PLAIN ,20) );
        String method = parent.toString().replaceAll("\t","    ");
        String[] parts = method.split("\n");

        for ( int i = 0 ; i < parts.length ; i++ ) {
            g2d.drawString(parts[i], width/5 , 25*i + height/5);
        }

        return image;
    }

    public static void generate(Block parent) {
        Random gen = new Random();
        // function that controls the number of blocks at each layer
        int number = gen.nextInt(5/(parent.getLayer()+1) )+2;
        for ( int i = 0 ; i < number ; i++ ) {
            double d = gen.nextGaussian();

            if ( d < -.9 && parent.getLayer() < 3 ) {
                BlockForLoop newBlock = new BlockForLoop(0);
                parent.addChildren(newBlock);
                newBlock.randomize();
                generate(newBlock);
            } else if ( d < -.5 && parent.getLayer() < 3 ) {
                BlockIfStatement newBlock = new BlockIfStatement(0);
                parent.addChildren(newBlock);
                newBlock.randomize();
                generate(newBlock);
            }  else if ( d < 0) {
                BlockDeceleration newBlock = new BlockDeceleration(0);
                newBlock.randomize();
                parent.addChildren(newBlock);
            } else {
                BlockSetValue newBlock = new BlockSetValue(0);
                parent.addChildren(newBlock);
                newBlock.randomize();
            }
        }
    }

    public static BufferedImage setGradientMask(BufferedImage gradient, int orientation) {
        // algorithm derived from Romain Guy's blog

        Graphics2D g = gradient.createGraphics();
        GradientPaint paint = new GradientPaint(0.0f, 0.0f,
                new Color(000000),
                orientation == SwingConstants.HORIZONTAL? gradient.getWidth() : 0.0f,
                orientation == SwingConstants.VERTICAL? gradient.getHeight() : 0.0f,
                new Color(660000));
        g.setPaint(paint);
        g.fill(new Rectangle2D.Double(0, 0, gradient.getWidth(), gradient.getHeight()));

        g.dispose();
        gradient.flush();

        return gradient;
    }
}
