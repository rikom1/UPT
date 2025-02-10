package org.riko.ui.frames;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.riko.ui.MainUI;
import org.riko.utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SelectFormatUI {

    public static String format;

    public static String returnFormat() {
        System.out.println("HELLO BITCH");

        FlatMacDarkLaf.setup();

        JDialog dialog = new JDialog((Frame) null, "Select format", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 200);
        WindowUtils.centerWindow(dialog);

        JLabel label = new JLabel("Format:");
        String[] formats = {"JPG", "JPEG", "PNG", "GIF"};
        JComboBox<String> formatList = new JComboBox<>(formats);
        JButton submitButton = new JButton("Submit");

        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(label)
                                .addComponent(formatList)
                                .addComponent(submitButton))
                        .addContainerGap(20, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(label)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formatList)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(submitButton)
                        .addContainerGap(20, Short.MAX_VALUE)
        );

        dialog.add(mainPanel);

        final String[] selectedFormat = new String[1];

        submitButton.addActionListener(e -> {
            selectedFormat[0] = (String) formatList.getSelectedItem();
            dialog.dispose();
        });

        dialog.setVisible(true);

        return selectedFormat[0];
    }

    public static void promptForFormat() {
        format = returnFormat();
        System.out.println("Selected format: " + format);
    }

    public static void main() throws IOException {
        MainUI.primaryGui();
    }
}
