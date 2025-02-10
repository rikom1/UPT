package org.riko.ui.buttons;

import li.flor.nativejfilechooser.NativeJFileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class OptionsButton extends JButton {

    public static JFrame optionsFrame = new JFrame("Sätted");
    public static JLabel saveLocationLabel = new JLabel("Save location: ");
    public static Image folderImage;
    public static File folder;

    public static void optionsFrame() {
        optionsFrame.setSize(400, 400);
        optionsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                optionsFrame.setVisible(false);
                optionsFrame.dispose();
            }
        });

        ImageIcon folderImg = new ImageIcon(OptionsButton.class.getResource("/folder.png"));
        JButton folderButton = new JButton();
        folderButton.setPreferredSize(new Dimension(125, 30));
        folderButton.setIcon(folderImg);
        folderButton.setText("Select folder");

        folderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("hi");
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fileChooser.showDialog(optionsFrame, "Save");
                    folder = fileChooser.getSelectedFile();
                    saveLocationLabel.setText(fileChooser.getSelectedFile().toString());
                    System.out.println(folder);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        saveLocationLabel.setBorder(BorderFactory.createEmptyBorder(60, 10, 10, 10));
        panel.add(saveLocationLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(folderButton);

        optionsFrame.add(panel, BorderLayout.CENTER);
        optionsFrame.add(buttonPanel, BorderLayout.SOUTH);

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