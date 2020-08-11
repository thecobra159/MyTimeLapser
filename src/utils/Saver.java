package utils;

import controller.Controller;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Saver {
    private Controller con;
    private String format = "png";
    private int counter = 1;

    public Saver(Controller con) {
        this.con = con;
        verifyLocation();
        getFormat();
    }

    private void verifyLocation() {
        File file = new File(con.getSavePath());

        if (!file.exists()) {
            file.mkdir();
        }
    }

    private void getFormat() {
        this.format = con.gui.getFormat();
    }

    public void saveShot(BufferedImage img) {
        try {
            File outputfile = new File(con.getSavePath() + "\\" + this.counter + "." + format);
            ImageIO.write(img, format, outputfile);
        } catch (IOException e) {
            System.out.println("Could not save file");
        }
        counter++;
    }
}
