package org.riko.ui;

import org.riko.ui.buttons.OptionsButton;
import org.riko.ui.frames.SelectFormatUI;
import org.riko.utils.ChangeFormat;

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

public class DropPanel extends JPanel implements DropTargetListener {

    public static BufferedImage pilt;
    public static File file;

    public DropPanel() {
        this.setBorder(BorderFactory.createBevelBorder( BevelBorder.LOWERED, new Color(30,60,10, 0).brighter(), new Color(114, 21, 13, 0).darker() ) );
        DropTarget dt = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null);
        this.setDropTarget(dt);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        this.setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED, Color.GRAY.brighter(), Color.GRAY.brighter()));
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        this.setBorder(BorderFactory.createBevelBorder( BevelBorder.LOWERED, new Color(30,60,10, 0).brighter(), new Color(114, 21, 13, 0).darker() ) );
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
            Transferable tr = dtde.getTransferable();

            if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                List<File> fileList = (List<File>) tr.getTransferData(DataFlavor.javaFileListFlavor);

                if (!fileList.isEmpty()) {
                    System.out.println("File dropped: " + fileList.get(0).getAbsolutePath());
                    file = fileList.get(0);

                    if (SelectFormatUI.format == null) {
                        SelectFormatUI.promptForFormat();
                    }

                    if (SelectFormatUI.format != null) {
                        if (SelectFormatUI.format.equals("JPG")) {
                            ChangeFormat.changeFormat(file, "jpg", OptionsButton.folder.getAbsolutePath());
                        } else if (SelectFormatUI.format.equals("JPEG")) {
                            ChangeFormat.changeFormat(file, "jpeg", OptionsButton.folder.getAbsolutePath());
                        } else if (SelectFormatUI.format.equals("PNG")) {
                            ChangeFormat.changeFormat(file, "png", OptionsButton.folder.getAbsolutePath());
                        } else if (SelectFormatUI.format.equals("GIF")) {
                            ChangeFormat.changeFormat(file, "gif", OptionsButton.folder.getAbsolutePath());
                        }
                    } else {
                        System.out.println("Format not selected or is null");
                    }

                    dtde.dropComplete(true);
                }
            } else {
                System.out.println("Unsupported data flavor");
            }

            dtde.dropComplete(true);



        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
            dtde.dropComplete(false);
        }

        this.setDropTarget(null);
    }
}
