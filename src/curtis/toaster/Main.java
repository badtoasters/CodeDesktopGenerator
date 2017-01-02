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

        // initalizes the method
        BlockMethod method = new BlockMethod(0);
        method.setMethodName(NameGenerator.getRandomMethodName());
        method.randomize();

        // Populates block with children
        generate(method);
        method.calcReturnVar();

        // optional, can be removed
        System.out.println(method);

        // creates the background
        BufferedImage background = createBackground( method );

        // saves background to the PATH
        saveImage(background);

        // changes the desktop's background
        WallpaperChanger.change(PATH);

        // exits out of the code
        System.exit(0);
    }

    /**
     *
     * @param image the image to save at the PATH
     */
    public static void saveImage( BufferedImage image ) {
        try {
            File outputfile = new File(PATH);
            ImageIO.write(image, "bmp", outputfile);
        } catch (IOException e) {

        }
    }

    /**
     *
     * @param parent the Block that will be printed on the background
     * @return
     */
    public static BufferedImage createBackground(Block parent) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        // creates image and adds a gradient onto it
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2d = image.createGraphics();
        image = setGradientMask(image);

        // writes the text of the code onto the background
        g2d.setColor(Color.WHITE);
        g2d.setFont( new Font("Verdana", Font.PLAIN ,20) );
        String method = parent.toString().replaceAll("\t","    ");
        String[] parts = method.split("\n");

        for ( int i = 0 ; i < parts.length ; i++ ) {
            g2d.drawString(parts[i], width/5 , 25*i + height/8);
        }

        return image;
    }

    /**
     * uses recursion to generate code
     * @param parent the parent block to be generated on
     */
    public static void generate(Block parent) {
        Random gen = new Random();
        // function that controls the number of blocks at each layer
        int number = gen.nextInt(5/(parent.getLayer()+1) )+2;
        for ( int i = 0 ; i < number ; i++ ) {
            double d = gen.nextGaussian();

            // adds a for loop to the code
            if ( d < -1 && parent.getLayer() < 3 ) {
                BlockForLoop newBlock = new BlockForLoop(0);
                parent.addChildren(newBlock);
                newBlock.randomize();
                generate(newBlock);
            }
            // adds an if block to the code
            else if ( d < -.4 && parent.getLayer() < 3 ) {
                BlockIfStatement newBlock = new BlockIfStatement(0);
                parent.addChildren(newBlock);
                newBlock.randomize();
                generate(newBlock);
            }
            // adds a deceleration block to the code
            else if ( d < -.3) {
                BlockDeceleration newBlock = new BlockDeceleration(0);
                parent.addChildren(newBlock);
                newBlock.randomize();
            }
            // adds a function call block to the code
            else if ( d < 0 && parent.getVariables().size() > 0 ) {
                // this function must be added to the children and then randomized
                BlockFunctionCall newBlock = new BlockFunctionCall(0);
                parent.addChildren(newBlock);
                newBlock.randomize();
            }
            // adds a set value block to the code
            else {
                BlockSetValue newBlock = new BlockSetValue(0);
                parent.addChildren(newBlock);
                newBlock.randomize();
            }
        }
    }

    /**
     * Source: http://www.java2s.com/Tutorial/Java/0261__2D-Graphics/CreateGradientMask.htm
     * @param image the image to add the gradient to
     * @return the image to be gradiented
     */
    public static BufferedImage setGradientMask(BufferedImage image) {
        // algorithm derived from Romain Guy's blog
        Random gen = new Random();

        // randomly generates color
        Color col;
        switch (gen.nextInt(4)) {
            case 0:
                col = new Color(0x000077);
                break;
            case 1:
                col = new Color(0x007700);
                break;
            case 2:
                col = new Color(0x770000);
                break;
            default:
                col = new Color(0x999999);
                break;

        }

        // random gradient direction
        Graphics2D g = image.createGraphics();
        GradientPaint paint = new GradientPaint(0.0f, 0.0f,
                new Color(000000),
                image.getWidth()*2 * (.10f + 1.7f*gen.nextFloat()),
                image.getHeight()*6 * (.10f + 1.7f*gen.nextFloat()),
                col);
        g.setPaint(paint);
        g.fill(new Rectangle2D.Double(0, 0, image.getWidth(), image.getHeight()));

        g.dispose();
        image.flush();

        return image;
    }
}
