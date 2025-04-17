package org.riko.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChangeFormat {

    public static void muudaFormaat(File fail, String formaat, String salvestusAsukoht) {
        try {
            BufferedImage pilt = ImageIO.read(fail);

            if(formaat.equals("jpg") || formaat.equals("gif")) {
                BufferedImage muundatudPilt = new BufferedImage(
                        pilt.getWidth(), pilt.getHeight(), BufferedImage.TYPE_INT_RGB);
                muundatudPilt.getGraphics().drawImage(pilt, 0, 0, null);

                pilt = muundatudPilt;
            }

            try{
                File uusFail = new File(salvestusAsukoht,
                        fail.getName().replaceFirst("[.][^.]+$", "") + "." + formaat.toLowerCase());

                boolean tulemus = ImageIO.write(pilt, formaat, uusFail);
                System.out.println("Töötas: " + tulemus);
            } catch (IOException e){
                System.out.println("Viga: " + e);
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}