package org.riko.ui;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.riko.ui.buttons.OptionsButton;
import org.riko.ui.frames.SelectFormatUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainUI {
    private static JFrame raam;

    public static void primaryGui() throws IOException {
        FlatMacDarkLaf.setup();

        URL tausttapilt = Thread.currentThread().getContextClassLoader().getResource("draggg.png");

        try {
            DropPanel.pilt = ImageIO.read(tausttapilt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        raam = new JFrame("Programm");
        Container c = raam.getContentPane();
        GroupLayout paigutus = new GroupLayout(c);
        c.setLayout(paigutus);
        raam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raam.setSize(600, 620);
        raam.setLayout(new BorderLayout());

        JPanel tooriistaPaneel = new JPanel();
        raam.add(tooriistaPaneel, BorderLayout.SOUTH);
        tooriistaPaneel.setLayout(new BoxLayout(tooriistaPaneel, BoxLayout.X_AXIS));
        tooriistaPaneel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton suvandiNupp = new OptionsButton("Suvandid");
        suvandiNupp.setHorizontalAlignment(SwingConstants.LEFT);

        suvandiNupp.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("optionsgear.png").getFile()));
        tooriistaPaneel.add(suvandiNupp);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(50, 0,0 ,0 ));
        raam.add(mainPanel, BorderLayout.CENTER);

        JPanel lohistusPaneel = new DropPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(pilt != null) {
                    int x = (this.getWidth() - pilt.getWidth()) / 2;
                    int y = (this.getHeight() - pilt.getHeight()) / 2;
                    g.drawImage(pilt, x, y, pilt.getWidth(), pilt.getHeight(), null);
                } else {
                    g.drawString("Pilti ei leitud", this.getWidth() / 2 - 50, this.getHeight() / 2);
                }
            }
        };

        lohistusPaneel.setPreferredSize(new Dimension(426, 426));
        mainPanel.add(lohistusPaneel);

        raam.getContentPane().add(mainPanel, BorderLayout.CENTER);
        raam.setVisible(true);
    }
}
