package com.mycompany.app;

import javax.swing.*;
import java.awt.*;

public class test {
    public int clairesInstanceVariable = -990000;


    public static void main(String[] args){
        System.out.println("Hello World");

        JFrame frame  = new JFrame();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1300,700));
        // Use JButtons, JPanels, JFrames, look upp addActionListeners for buttons
        //Look up observer pattern for connecting GUI to OO control code
        // Look into setting your frame and panel layouts using GridLayouts, etc.

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }
}
