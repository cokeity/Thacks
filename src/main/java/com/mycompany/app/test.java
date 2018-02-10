package com.mycompany.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class test {
    public static JPanel site_view;
    public static JPanel code_view;
    public static ArrayList<JLabel> html_labels = new ArrayList<JLabel>();
    public static ArrayList<JLabel> css_labels = new ArrayList<JLabel>();
    public static ArrayList<JLabel> blox = new ArrayList<JLabel>();
    public static JLabel wireframe;

    public static void main(String[] args){
        System.out.println("Hello World");

        //set colors
        Color pastelGreen = new Color(214, 229, 227);
        Color bg = new Color(65,71,112);
        Color true_blk = new Color(0 ,0 ,0);

        JFrame frame  = new JFrame();
        frame.setTitle("HELLO");
        //frame.setBackground(Color.black);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(1300,700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use JButtons, JPanels, JFrames, look upp addActionListeners for buttons
        //Look up observer pattern for connecting GUI to OO control code
        // Look into setting your frame and panel layouts using GridLayouts, etc.
        
        //codeview setup
        code_view = new JPanel(null);
        code_view.setPreferredSize(new Dimension(500,700));
        code_view.setBackground(bg);

        //siteview setup
        site_view = new JPanel(null);
        site_view.setPreferredSize(new Dimension(800,700));
        site_view.setBackground(pastelGreen);

        //add frames
        frame.add(code_view, BorderLayout.EAST);
        frame.add(site_view, BorderLayout.WEST);

        //set top bar
        JLabel top = new JLabel("");
        top.setBounds(0,0,500,40);
        top.setOpaque(true);
        top.setBackground(pastelGreen);
        //code_view.add(top);

        //tabHTML
        JButton html_tab = new JButton("HTML");
        html_tab.setBorder(null);
        html_tab.setBounds(10, 7, 100, 33);
        html_tab.setOpaque(true);
        html_tab.setBackground(bg);
        html_tab.setForeground(true_blk);


        //tabCSS
        JButton css_tab = new JButton("CSS");
        css_tab.setBorder(null);
        css_tab.setBounds(120, 7, 100, 33);
        css_tab.setOpaque(true);
        css_tab.setBackground(true_blk);
        css_tab.setForeground(bg);

        //create html arr
        String[] fruits = new String[] { " <html>", "\t\t<head>", "\t\t</head>", " </html>" };
        create_html_labels(fruits);
        //create css arr
        String[] fruit = new String[] { "<css>", "Apple", "Pear", "</css>" };
        create_css_labels(fruit);
        remove_css_labels(); //start off with only seeing html

        //html listener
        html_tab.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            html_tab.setBackground(bg);
            html_tab.setForeground(true_blk);
            css_tab.setBackground(true_blk);
            css_tab.setForeground(bg);
            display_html_labels();
            remove_css_labels();
          }
        });

        //css listener
        css_tab.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            css_tab.setBackground(bg);
            css_tab.setForeground(true_blk);
            html_tab.setBackground(true_blk);
            html_tab.setForeground(bg);
            remove_html_labels();
            display_css_labels();
          }
        });


        code_view.add(top);
        top.add(html_tab);
        top.add(css_tab);
        code_view.repaint();

        //wireframe
        wireframe = new JLabel("");
        wireframe.setBounds(40, 7, 720, 885);
        wireframe.setOpaque(true);
        wireframe.setBackground(Color.white);

        site_view.add(wireframe);
        wireframe.repaint();


        //add building blox
        ArrayList<int[]> test = new ArrayList<int[]>();
        int[] t1 = {0,0,100,100};
        test.add(t1);
        int[] t2 = {350,40,150,100};
        test.add(t2);
        create_blox(test);
        display_bloxz();


        frame.pack();
        frame.setVisible(true);

    }

    public static void create_html_labels(String[] in_str) {
        JLabel temp;
        int x = 10;
        int y = 50;
        int width = 480;
        int inc = 20; //for height
        for (String str: in_str) {
            temp = new JLabel(str);
            System.out.print(str);
            temp.setBounds(x, y, width, inc);
            y += inc;
            temp.setForeground(Color.white);
            html_labels.add(temp);
            code_view.add(temp);
            code_view.repaint();

        }

    }

    public static void remove_html_labels() {
        for (JLabel jl: html_labels){
            jl.setVisible(false);
        }
    }

    public static void display_html_labels() {
        for (JLabel jl: html_labels){
            jl.setVisible(true);
        }
    }

    public static void create_css_labels(String[] in_str) {
        JLabel temp;
        int x = 10;
        int y = 50;
        int width = 480;
        int inc = 20; //for height
        for (String str: in_str) {
            temp = new JLabel(str);
            System.out.print(str);
            temp.setBounds(x, y, width, inc);
            y += inc;
            temp.setForeground(Color.white);
            css_labels.add(temp);
            code_view.add(temp);
            code_view.repaint();

        }
        System.out.print("Sup");

    }

    public static void remove_css_labels() {
        for (JLabel jl: css_labels){
            jl.setVisible(false);
        }
    }

    public static void display_css_labels() {
        for (JLabel jl: css_labels){
            jl.setVisible(true);
        }
    }



    public static void create_blox(ArrayList<int[]> test) {
        JLabel temp;
        int x;
        int y;
        int width;
        int height;
        for (int[] r: test) {
            x = r[0];
            y = r[1];
            width = r[2];
            height = r[3]; //divide 2 for scaling
            String sample = "enter text here";
            temp = new JLabel(sample);
            temp.setOpaque(true);
            temp.setBounds(x, y, width, height);
            temp.setBackground(Color.gray);
            temp.setForeground(Color.white);
            blox.add(temp);
        }
    }

    public static void display_bloxz() {
        for (JLabel jl: blox) {
            System.out.print(jl);
            wireframe.add(jl);
        }
        site_view.repaint();
    }

   //public static void pixy_to_java_coord{}



}

