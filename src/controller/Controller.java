package controller;

import utils.Preview;
import utils.TGui;
import utils.Recorder;

import java.awt.*;

public class Controller {
    public static boolean Running = false;

    public TGui gui;
    public Recorder recorder;
    public Dimension screenSize;
    public Dimension screenPosition;
    public String savePath = "";
    public String realSavePath = "";
    public String projectName = "Project Name";
    public boolean customArea = false;

    private Preview preview;

    public Controller() {
        init();
    }

    private void init() {
        this.gui = new TGui(this);
        this.gui.setTitle("TimeLapser");
        this.preview = new Preview(gui, this);
        this.gui.updateSaveLocationHint(this.getSavePath());
        this.gui.setVisible(true);
        this.recorder = new Recorder(this);


        this.preview.execute();//Starts preview

        this.savePath = getSavePath();
    }

    public void startRecording() {
        Running = true;
        gui.lockOptions();
    }

    public void setProjectName(String name) {
        this.projectName = name;
        gui.updateSaveLocationHint(this.realSavePath + "\\" + this.projectName);
    }

    void stopRecording() {
        gui.unlockOptions();

        recorder.stopCapture();

        Running = false;
    }

    public void savePath(String absolutePath) {
        this.savePath = absolutePath + "\\" + this.projectName;
        this.realSavePath = absolutePath;
        gui.updateSaveLocationHint(absolutePath + "\\" + this.projectName);
    }

    public String getSavePath() {
        if (savePath.equals("")) {
            this.realSavePath = System.getProperty("user.dir");
            return System.getProperty("user.dir") + "\\" + this.projectName;
        } else {
            return this.savePath;
        }

    }

    public int getFrameRate() {
        return this.gui.getFrameRate();
    }

}
