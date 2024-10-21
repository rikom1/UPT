package org.riko.ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainUI {
    private static JFrame frame;
    public static BufferedImage image;

    public static void primaryGui() throws IOException {
        FlatMacDarkLaf.setup();

        image = ImageIO.read(new File("C:\\Users\\mikso\\IdeaProjects\\UPT\\src\\main\\resources\\draggg.png"));

        frame = new JFrame("Converter");
        Container c = frame.getContentPane();
        GroupLayout layout = new GroupLayout(c);
        c.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 620);
        frame.setLayout(new BorderLayout());

        JPanel toolbarPanel = new JPanel();
        frame.add(toolbarPanel, BorderLayout.SOUTH);
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.X_AXIS));
        toolbarPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //TODO: Add options
        JButton optionsButton = new JButton("Options");
        optionsButton.setHorizontalAlignment(SwingConstants.LEFT);
        toolbarPanel.add(optionsButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(50, 0,0 ,0 ));
        frame.add(mainPanel, BorderLayout.CENTER);

        // Displaying the image, aligning
        JPanel dropPanel = new DropPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = (this.getWidth() - image.getWidth()) / 2;
                int y = (this.getHeight() - image.getHeight()) / 2;

                g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
                System.out.println(image.getWidth() + " and " + image.getHeight());
                System.out.println(this.getWidth() + " and lol " + this.getHeight());
            }
        };

        dropPanel.setPreferredSize(new Dimension(426, 426));
        mainPanel.add(dropPanel);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

//TODO: Resize image to 500x500, change preferred size of dropPanel to 500x500