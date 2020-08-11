package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AreaSelectorGraph extends SwingWorker<String, String> {
    private final AreaSelector areaSelector;
    private Point firstPoint = null;
    public Point mouseLocation;
    public boolean running = true;
    private final Graphics gra;

    public AreaSelectorGraph(AreaSelector areaSelector) {
        this.areaSelector = areaSelector;
        this.gra = areaSelector.getGraphics();
    }

    public void setMouseLocation(Point location) {
        mouseLocation = location;
    }

    public void clickDone(MouseEvent e) {
        if (this.firstPoint == null) {
            this.firstPoint(e.getLocationOnScreen());
        }
    }

    private void firstPoint(Point p) {
        this.firstPoint = p;
        this.running = true;
        System.out.println("Executed");
        execute();
    }

    @Override
    protected String doInBackground() throws Exception {
        while (this.running) {
            this.draw();
            System.out.println("Drwa");
        }
        return null;
    }

    private void draw() {
        System.out.println("Printed1");
        gra.dispose();

        System.out.println("Printed");

        areaSelector.update(gra);
        areaSelector.repaint();
    }
}
