package org.riko.ui.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class OptionsButton extends JButton {

    public static JFrame optionsFrame = new JFrame("Sätted");
    public static JLabel salvestiAsukoht = new JLabel("Save location: ");
    public static Image folderImage;
    public static File kaust;

    public static void optionsFrame() {
        optionsFrame.setSize(400, 400);
        optionsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                optionsFrame.setVisible(false);
                optionsFrame.dispose();
            }
        });

        ImageIcon kaustaPilt = new ImageIcon(OptionsButton.class.getResource("/folder.png"));
        JButton kaustaNupp = new JButton();
        kaustaNupp.setPreferredSize(new Dimension(125, 30));
        kaustaNupp.setIcon(kaustaPilt);
        kaustaNupp.setText("Select folder");

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
        salvestiAsukoht.setBorder(BorderFactory.createEmptyBorder(60, 10, 10, 10));
        panel.add(salvestiAsukoht);

        JPanel nupuPaneel = new JPanel();
        nupuPaneel.setLayout(new FlowLayout(FlowLayout.LEFT));
        nupuPaneel.add(kaustaNupp);

        optionsFrame.add(panel, BorderLayout.CENTER);
        optionsFrame.add(nupuPaneel, BorderLayout.SOUTH);

        //TODO: Add save button to the panel, add option: "Delete old file after processing?"
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