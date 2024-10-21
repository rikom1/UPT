package org.riko.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DropPanel extends JPanel implements DropTargetListener {

    private BufferedImage image;
    private String text;

    public DropPanel() {
        this.setBorder(BorderFactory.createBevelBorder( BevelBorder.LOWERED, new Color(30,60,10, 0).brighter(), new Color(114, 21, 13, 0).darker() ) );
        DropTarget dt = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null);
        this.setDropTarget(dt);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        this.setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED, Color.RED.brighter(), Color.RED.darker()));
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        this.setBorder( BorderFactory.createBevelBorder( BevelBorder.LOWERED, UIManager.getColor( "MenuBar.highlight" ), UIManager.getColor( "MenuBar.shadow" ) ) );
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            text  = (String) dtde.getTransferable().getTransferData( DataFlavor.stringFlavor );
            image = (BufferedImage) dtde.getTransferable().getTransferData( DataFlavor.imageFlavor );
            dtde.dropComplete( true );
            repaint();
        } catch( UnsupportedFlavorException e ) {
            e.printStackTrace();
        } catch( IOException e ) {
            e.printStackTrace();
        }
        this.setDropTarget( null );
    }
}
