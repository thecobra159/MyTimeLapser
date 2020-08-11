package utils;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recorder extends SwingWorker<String, String> {
    private JLabel screen;
    private int frameRate = 1; //One frame every X seconds
    private boolean runnning = true;
    private final Controller con;
    private Saver saver;

    public Recorder(Controller con) {
        this.con = con;
    }

    public void startCapture() {

        this.saver = new Saver(con);
        this.runnning = true;

        System.out.println("Starting with frame rate of " + frameRate + "(" + con.gui.getFrameRate() + "ms) Saving in: " + con.getSavePath());
    }

    public void stopCapture(){
        this.runnning = false;
    }

    private void doScreenShot() {
        try {
            Robot robot = new Robot();
            BufferedImage screenshot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            this.saver.saveShot(screenshot);

        } catch (AWTException ex) {
            Logger.getLogger(Recorder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected String doInBackground() throws Exception {
        startCapture();

        while (runnning) {
            doScreenShot();
            Thread.sleep(con.gui.getFrameRate());
        }
        return null;
    }
}
