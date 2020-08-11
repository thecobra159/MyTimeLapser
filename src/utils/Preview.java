package utils;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Preview extends SwingWorker<String, String> {
    private final boolean runnable = true;
    private TGui frame = null;
    private JLabel preview = null;
    private Controller controller = null;
    private boolean AS;
    private AreaSelector area;
    private JFrame jframe;
    private boolean hideFrame = false;

    public Preview(TGui frame, Controller con) {
        this.jframe = frame;
        this.frame = frame;
        this.preview = frame.getPreview();
        this.controller = con;
    }

    public Preview(AreaSelector aSelector, Controller con) {
        this.preview = aSelector.getPreview();
        this.jframe = aSelector;
        this.controller = con;
        this.AS = true;
        System.out.println("yay" + this.AS);
    }


    private void updateFrame(BufferedImage img) {
        if (controller.customArea || this.AS) {
            img = img.getSubimage(controller.screenPosition.width, controller.screenPosition.height, controller.screenSize.width, controller.screenSize.height);
        }
        Image reimg = img.getScaledInstance(this.preview.getWidth(), this.preview.getHeight(), Image.SCALE_SMOOTH);

        this.preview.setIcon(new ImageIcon(reimg));
    }

    private void doPreviewShot() {
        try {
            Robot robot = new Robot();
            BufferedImage previewShot;
            if (!AS) {
                previewShot = robot.createScreenCapture(new Rectangle(new Dimension(this.frame.getRHeight(), this.frame.getRWidth())));
            } else {
                previewShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            }
            updateFrame(previewShot);
        } catch (AWTException ex) {
            Logger.getLogger(Recorder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected String doInBackground() throws Exception {
        while (runnable) {
            doPreviewShot();
            try {
                Thread.sleep(controller.getFrameRate());
            } catch (InterruptedException ex) {
                Logger.getLogger(Preview.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
