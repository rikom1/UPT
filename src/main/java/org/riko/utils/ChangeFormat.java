package org.riko.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChangeFormat {

    public static void changeFormat(File file, String format, String saveLocation) {
        try {
            BufferedImage image = ImageIO.read(file);

            if(format.equals("jpg") || format.equals("gif")) {
                BufferedImage convertedImage = new BufferedImage(
                        image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
                convertedImage.getGraphics().drawImage(image, 0, 0, null);

                image = convertedImage;
            }

            System.out.println(saveLocation + "  hi :DDDDDDDDD");

            try{
                File newFile = new File(saveLocation, file.getName().replaceFirst("[.][^.]+$", "") + "." + format.toLowerCase());

                boolean success = ImageIO.write(image, format, newFile);
                System.out.println("Success: " + success);
            } catch (IOException e){
                System.out.println("Error: " + e);
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}