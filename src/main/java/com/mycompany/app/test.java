package com.mycompany.app;

import javax.swing.*;
import java.awt.*;


public class test {


    public static void main(String[] args){
        System.out.println("Hello World");

        //set colors
        Color pastelGreen = new Color(214, 229, 227);
        Color blk = new Color(0, 0, 0, 200);

        JFrame frame  = new JFrame();
        frame.setTitle("HELLO");
        frame.setBackground(Color.black);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(1300,700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use JButtons, JPanels, JFrames, look upp addActionListeners for buttons
        //Look up observer pattern for connecting GUI to OO control code
        // Look into setting your frame and panel layouts using GridLayouts, etc.
        
        //codeview setup
        JPanel code_view = new JPanel();
        code_view.setPreferredSize(new Dimension(500,700));
        code_view.setBackground(blk);

        //siteview setup
        JPanel site_view = new JPanel();
        site_view.setPreferredSize(new Dimension(800,700));
        site_view.setBackground(pastelGreen);

        MoreDraw md = new MoreDraw();
        //code_view.add(md);

        //add frames
        frame.add(code_view, BorderLayout.EAST);
        frame.add(site_view, BorderLayout.WEST);

        frame.pack();
        frame.setVisible(true);
        code_view.add(md);

    }

}