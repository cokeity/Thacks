package com.mycompany.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class GUI2 {
    public static JPanel site_view;
    public static JPanel code_view;
    public static ArrayList<JLabel> html_labels = new ArrayList<JLabel>();
    public static ArrayList<JLabel> css_labels = new ArrayList<JLabel>();
    public static ArrayList<JLabel> blox = new ArrayList<JLabel>();
    public static JLabel wireframe;
    public static String selected = "html";


    public static void setup(){
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

        /*
        //create html arr
        String[] fruits = new String[] { "<html> &lt;html&gt; <br/>&emsp;hello <br/> &emsp;&emsp;hihihih</html>", "<html>&lt;p&gt;<html>", "\t\t</head>", " </html>" };
        create_html_labels(fruits);
        //create css arr
        String[] fruit = new String[] { "<html>&lt;css&gt;<br/>boom</html>", "Apple", "Pear", "</css>" };
        create_css_labels(fruit);
        remove_css_labels(); //start off with only seeing html
        */

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
            selected = "html";
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
            selected = "css";
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
        /*
        ArrayList<int[]> test = new ArrayList<int[]>();
        int[] t1 = {0,0,100,100};
        test.add(t1);
        int[] t2 = {350,40,150,100};
        test.add(t2);
        create_blox(test);
        display_bloxz();
        */


        frame.pack();
        frame.setVisible(true);

    }

    public static void remove() {
        
        for (JLabel l : html_labels){
            code_view.remove(l);
            code_view.revalidate();
            code_view.repaint();
        
        }
        for (JLabel l : css_labels){
            code_view.remove(l);
            code_view.revalidate();
            code_view.repaint();
        }
    }

    public static void update(Page page) {
        remove();
        ArrayList<Container> all_containers =  page.get_containers();
        String[] html_type = make_types_html(all_containers);
        String[] css_type = make_types_css(all_containers);
        String[] html_labels_inp = transform_html(all_containers);
        create_html_labels(html_labels_inp, html_type);
        String[] css_labels_inp = transform_css(all_containers);
        create_css_labels(css_labels_inp, css_type);
        ArrayList<int[]> blox_inp = transform_blox(all_containers);
        create_blox(blox_inp, css_type);
        display_bloxz();

        if(selected == "html") {
            display_html_labels();
            remove_css_labels();
        } else {
            display_css_labels();
            remove_html_labels();
        }
    }
    /*
    public static ArrayList<JLabel> make_html_labels(ArrayList<Container> c) {
        int s = c.size();
        String tstr = "<html>&lt;!DOCTYPE html&gt; <br/> &lt;html&gt; <br/> &lt;&emsp;head&gt; <br/> &lt;link rel=&quotstylesheet&quot href=&quotstylesheets/main.css&quot&gt;<br/> &lt;&emsp;/head&gt; </html>";
        JLabel top = new JLabel(tstr);
    }
    */

    public static String[] make_types_html(ArrayList<Container> c) {
        int s = c.size();
        String[] ret = new String[s+2];
        ret[0] = "default";
        int i = 1;
        for(Container samp: c) {
            ret[i] = samp.get_element().get_type();
            i++;
        }
        ret[s+1] = "default";
        return ret;
    }

    public static String[] transform_html(ArrayList<Container> c) {
        int s = c.size();
        String[] ret = new String[s+2];
        ret[0] = "<html>&lt;!DOCTYPE html&gt; <br/> &lt;html&gt; <br/> &lt;&emsp;head&gt; <br/> &lt;link rel=&quotstylesheet&quot href=&quotstylesheets/main.css&quot&gt;<br/> &lt;&emsp;/head&gt; </html>";
        int i = 1;
        for(Container samp: c) {
            ret[i] = samp.to_html();
            i++;
        }
        ret[s+1] = "<html>&lt;/html&gt;</html>";
        return ret;
    }

    public static String[] transform_css(ArrayList<Container> c) {
        int s = c.size();
        String[] ret = new String[s];
        int i = 0;
        for(Container samp: c) {
            ret[i] = samp.to_html();
            i++;
        }
        return ret;

    }

    public static void create_html_labels(String[] in_str, String[] type_html) {
        JLabel temp;
        int x = 10;
        int y = 50;
        int width = 480;
        int inc = 20; //for height
        int l;
        Color color = Color.white;
        int i;
        for (i = 0; i < in_str.length; i++) {
            String str = in_str[i];
            l = countLines(str);
            temp = new JLabel(str);
            System.out.print(str + l);
            temp.setBounds(x, y, width, inc*l);
            y += (inc*l);
            switch (type_html[i]){
                case "default":
                    color = Color.white;
                    break;
                case "header":
                    color = Color.green;
                    break;
                case "paragraph":
                    color = Color.blue;
                    break;
                case "image":
                    color = Color.yellow;
                    break;
            }
            temp.setForeground(color);
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

    public static String[] make_types_css(ArrayList<Container> c) {
        int s = c.size();
        int i = 0;
        String[] ret = new String [s];
        for(Container samp: c) {
            ret[i] = samp.get_element().get_type();
            i++;
        }
        return ret;
    }

    public static void create_css_labels(String[] in_str, String[] css_type) {
        JLabel temp;
        int x = 10;
        int y = 50;
        int width = 480;
        int inc = 20; //for height
        int l;
        Color color = Color.white;
        int i;
        for (i = 0; i < in_str.length; i++)  {
            String str = in_str[i];
            l = countLines(str);
            temp = new JLabel(str);
            System.out.print(str);
            temp.setBounds(x, y, width, inc*l);
            y += (inc*l);
            switch (css_type[i]){
                case "header":
                    color = Color.green;
                    break;
                case "paragraph":
                    color = Color.blue;
                    break;
                case "image":
                    color = Color.yellow;
                    break;
            }
            temp.setForeground(color);
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



    public static void create_blox(ArrayList<int[]> test, String[] css_type) {
        JLabel temp;
        int x;
        int y;
        int width;
        int height;
        Color color = Color.white;
        int i;
        for (i = 0; i < test.size(); i++) {
            int[] r = test.get(i);
            x = r[0];
            y = r[1];
            width = r[2];
            height = r[3]; //divide 2 for scaling
            String sample = "enter text here";
            temp = new JLabel(sample);
            temp.setHorizontalAlignment(JLabel.CENTER);
            temp.setVerticalAlignment(JLabel.CENTER);
            temp.setOpaque(true);
            temp.setBounds(x, y, width, height);
            switch (css_type[i]){
                case "header":
                    color = Color.green;
                    break;
                case "paragraph":
                    color = Color.blue;
                    break;
                case "image":
                    color = Color.yellow;
                    break;
            }
            temp.setBackground(color);
            temp.setForeground(Color.white);
            blox.add(temp);
        }
    }

    public static ArrayList<int[]> transform_blox(ArrayList<Container> c) {
        ArrayList<int[]> ret = new ArrayList<int[]>();
        int[] temp;
        for(Container samp: c) {
            temp = container_to_pixy(samp);
            ret.add(pixy_to_java_coord(temp));
        }
        return ret;
    }

    public static void display_bloxz() {
        for (JLabel jl: blox) {
            System.out.print(jl);
            wireframe.add(jl);
        }
        site_view.repaint();
    }

   public static int[] pixy_to_java_coord(int[] pixcoord) {
        int[] ret = {0,0,0,0};
        if (pixcoord.length != 4) {
            return ret;
        } 
        ret[0] = pixcoord[0]-pixcoord[2]/2; //change x
        ret[1] = pixcoord[1]-pixcoord[3]/2; //change y
        //adjust to ui coord
        ret[0] /= 2;
        ret[1] /= 2;
        ret[2] /= 2;
        ret[3] /= 2;
        return ret; 
   }

   public static int[] container_to_pixy(Container c) {
      int[] ret = new int[4];
      ret[0] = c.get_x();
      ret[1] = c.get_y();
      ret[2] = c.get_width();
      ret[3] = c.get_height();
      return ret;
   }

    public static int countLines(String str) {
        if(str == null || str.isEmpty())
        {
            return 0;
        }
        int lines = 1;
        int pos = 0;
        while ((pos = str.indexOf("<br/>", pos) + 1) != 0) {
            lines++;
        }
        return lines;
    }





}

