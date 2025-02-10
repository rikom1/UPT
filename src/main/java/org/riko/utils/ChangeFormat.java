package org.riko.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChangeFormat {

    public static void changeFormat(File file, String format, String saveLocation) {
        try {
            BufferedImage image = ImageIO.read(file);
            System.out.println(saveLocation + "  hi :DDDDDDDDD");

            try{
                File newFile = new File(saveLocation, file.getName().replaceFirst("[.][^.]+$", "") + "." + format.toLowerCase());
                ImageIO.write(image, format, newFile);
                if(newFile.createNewFile()){
                    System.out.println("File created: " + newFile.getName());
                    System.out.println("File saved to: " + newFile.getAbsolutePath());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e){
                System.out.println("Error: " + e);
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}