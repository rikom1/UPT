package org.riko.ui;

import org.riko.ui.buttons.SattedNupp;
import org.riko.ui.frames.FormaadiValimiseUI;
import org.riko.utils.VahetaFormaat;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LohistusPaneel extends JPanel implements DropTargetListener {

    public static BufferedImage pilt;
    public static File file;

    public LohistusPaneel() {
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(30, 60, 10, 0).brighter(), new Color(114, 21, 13, 0).darker()));
        DropTarget dt = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null);
        this.setDropTarget(dt);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY.brighter(), Color.GRAY.brighter()));
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(30, 60, 10, 0).brighter(), new Color(114, 21, 13, 0).darker()));
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE); // Aktsepteerib lohistamise toimingu
            Transferable tr = dtde.getTransferable(); // Hangib lohistatud andmed
            this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(30, 60, 10, 0).brighter(), new Color(114, 21, 13, 0).darker()));

            if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) { // kontrollib, kas lohistatud andmed on failid
                List<File> fileList = (List<File>) tr.getTransferData(DataFlavor.javaFileListFlavor); // saab lohistatud failid

                if (!fileList.isEmpty()) {
                    System.out.println("File dropped: " + fileList.get(0).getAbsolutePath());
                    file = fileList.get(0); // Hangib esimese lohistatud faili

                    FormaadiValimiseUI.format = null;
                    FormaadiValimiseUI.valitudFormaat(); // avab formaadi valimise akna

                    if (FormaadiValimiseUI.format != null) {
                        if (FormaadiValimiseUI.format.equals("JPG")) {
                            VahetaFormaat.muudaFormaat(file, "jpg", SattedNupp.kaust.getAbsolutePath());
                        } else if (FormaadiValimiseUI.format.equals("JPEG")) {
                            VahetaFormaat.muudaFormaat(file, "jpeg", SattedNupp.kaust.getAbsolutePath());
                        } else if (FormaadiValimiseUI.format.equals("PNG")) {
                            VahetaFormaat.muudaFormaat(file, "png", SattedNupp.kaust.getAbsolutePath());
                        } else if (FormaadiValimiseUI.format.equals("GIF")) {
                            VahetaFormaat.muudaFormaat(file, "gif", SattedNupp.kaust.getAbsolutePath());
                        }
                    } else {
                        System.out.println("Format not selected or is null");
                    }

                    dtde.dropComplete(true); // märgib, et lohistamine on lõpetatud
                }
            } else {
                System.out.println("Unsupported data flavor"); // kui lohistatud andmed ei ole failid
            }

            dtde.dropComplete(true); // märgib, et lohistamine on lõpetatud
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
            dtde.dropComplete(false); // märgib, et lohistamine ei õnnestunud
        }

        this.setDropTarget(new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null)); // aktiveerib uuesti lohistamise
    }
}
