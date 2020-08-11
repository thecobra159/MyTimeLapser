package utils;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AreaSelector extends JFrame {
    private Controller controller;
    private final JLabel preview;
    boolean active = true;

    public AreaSelector(Controller con) {

        this.controller = con;

        this.setTitle("Please, hover the place that you want to recored with this windown.");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        //this.setAlwaysOnTop(true);
        this.requestFocus();
        this.setUndecorated(true);
        this.setOpacity(0.55f);

        //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        JLabel back = new JLabel("Background holder");
        back.setSize(this.getSize());

        this.preview = back;

        JLabel message = new JLabel("Please select the area that you want to record");


        this.add(back);
        this.add(message);


        JOptionPane alert = new JOptionPane();
        alert.showMessageDialog(this,
                "Please, hover the place that you want to recored with the next windown." + " Press ENTER when you are done");


        alert.setVisible(true);


        final AreaSelectorGraph asg = new AreaSelectorGraph(this);

        KeyListener kl = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //Not important
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Not important
            }
        };


        MouseListener ms = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                asg.clickDone(e);
                System.err.println("Click done");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        MouseMotionListener mm = new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                asg.setMouseLocation(e.getLocationOnScreen());
            }
        };

        this.addKeyListener(kl);
        this.addMouseListener(ms);

        this.setVisible(true);

    }

    JLabel getPreview() {
        return this.preview;
    }
}
