package org.riko.ui.buttons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

public class OptionsButton extends JButton {

    public static JFrame optionsFrame = new JFrame("Seaded");
    public static JLabel salvestiAsukoht = new JLabel("Salvestatud failid asuvad: ");
    public static File kaust;

    public static void optionsFrame() {
        optionsFrame.setSize(300, 200);
        optionsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                optionsFrame.setVisible(false);
                optionsFrame.dispose();
            }
        });
        ;
        URL url = Thread.currentThread().getContextClassLoader().getResource("folder.png");

        JButton kaustaNupp = new JButton();
        kaustaNupp.setPreferredSize(new Dimension(125, 30));

        try{
            kaustaNupp.setIcon(new ImageIcon(ImageIO.read(url)));
        } catch (Exception e){
            System.out.println("Error: " + e);
            e.printStackTrace();
        }

        kaustaNupp.setText("Vali kaust");

        kaustaNupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser failiValik = new JFileChooser();
                    failiValik.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    failiValik.showDialog(optionsFrame, "Salvesta");
                    kaust = failiValik.getSelectedFile();
                    salvestiAsukoht.setText(failiValik.getSelectedFile().toString());
                    System.out.println(kaust);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        salvestiAsukoht.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(salvestiAsukoht);

        JPanel nupuPaneel = new JPanel();
        nupuPaneel.setLayout(new FlowLayout(FlowLayout.LEFT));
        nupuPaneel.add(kaustaNupp);

        optionsFrame.add(panel, BorderLayout.CENTER);
        optionsFrame.add(nupuPaneel, BorderLayout.SOUTH);

    }

    public OptionsButton(String options) {
        this.setText(options);
        optionsFrame();
        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!optionsFrame.isVisible()){
                    optionsFrame.setVisible(true);
                }
            }
        });
        this.setHorizontalAlignment(SwingConstants.LEFT);
    }

}