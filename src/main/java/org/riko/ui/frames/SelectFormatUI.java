package org.riko.ui.frames;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.riko.ui.MainUI;
import org.riko.utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SelectFormatUI {

    public static String format;

    public static String formaat() {
        FlatMacDarkLaf.setup();

        JDialog dialog = new JDialog((Frame) null, "Vali formaat", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 200);
        WindowUtils.centerWindow(dialog);

        JLabel silt = new JLabel("Formaat:");
        String[] formaadid = {"JPG", "JPEG", "PNG", "GIF"};
        JComboBox<String> formaadiNimekiri = new JComboBox<>(formaadid);
        JButton sisestaNupp = new JButton("Submit");

        JPanel peaPaneel = new JPanel();
        GroupLayout paigutus = new GroupLayout(peaPaneel);
        peaPaneel.setLayout(paigutus);
        paigutus.setAutoCreateGaps(true);
        paigutus.setAutoCreateContainerGaps(true);

        paigutus.setHorizontalGroup(
                paigutus.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(paigutus.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(silt)
                                .addComponent(formaadiNimekiri)
                                .addComponent(sisestaNupp))
                        .addContainerGap(20, Short.MAX_VALUE)
        );

        paigutus.setVerticalGroup(
                paigutus.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(silt)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formaadiNimekiri)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sisestaNupp)
                        .addContainerGap(20, Short.MAX_VALUE)
        );

        dialog.add(peaPaneel);

        final String[] valitudFormaat = new String[1];

        sisestaNupp.addActionListener(e -> {
            valitudFormaat[0] = (String) formaadiNimekiri.getSelectedItem();
            dialog.dispose();
        });

        dialog.setVisible(true);

        return valitudFormaat[0];
    }

    public static void valitudFormaat() {
        format = formaat();
        System.out.println("Valitud formaat: " + format);
    }

    public static void main() throws IOException {
        MainUI.primaryGui();
    }
}
