package com.mycompany.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import com.fazecast.jSerialComm.*;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Comm {


    //private static ArrayList<Page> pages = new ArrayList<Page>();
    
    public static Page page = new Page(0, 0, 0, 0, 0);

    //page id to assign next
    private static int pid = 0;
    private static String classname = "dopey";
    private static int cname = 0;


   /* public static void filter_pages(HashSet<String> pos){
        int i;
        for (i = 0; i < pages.size() ; i++){
            Page p = pages.get(i);
            String coord = p.get_x()+":"+p.get_y();
            if (!pos.contains(coord))
            {
                pages.remove(i);
            }
        }
    }*/

    public static void filter_containers(HashSet<String> pos){
       /* int i;
        for (i = 0; i < pages.size(); i++){
            int j;
            Page p = pages.get(i);*/
        if (page != null){
            ArrayList<Container> cont = page.get_containers();
            int j;
            for (j = 0; j < cont.size(); j++){
                Container c = cont.get(j);
                String coord = c.get_x()+":"+c.get_y();
                if (!pos.contains(coord)){
                    page.remove_container(j);
                }
            }
        }
    }

    //returns page to put container in, or null if 
    //page doesn't exist there or container already exists
    /*public static Page get_page(int x, int y){
        for (Page p : pages){
            int left = p.get_x()+p.get_width()/2;
            int right = p.get_x()-p.get_width()/2;
            int top = p.get_y()-p.get_height()/2;
            int bottom = p.get_y()+p.get_height()/2;

            if (x >= left && x <= right && y <= bottom && y >= top){
                ArrayList<Container> cont = p.get_containers();
                for (Container c : cont){
                    if (c.get_x() == x && c.get_y() == y){
                        return null;
                    }
                }
                return p;
            }
        }
        return null;
    }*/

    /*public static boolean page_exists(int x, int y){
        for (Page p : pages){
            if (p.get_x() == x && p.get_y() == y)
                return true;
        }
        return false;
    }*/

    public static void process_request_line(String sig, int x, int y
            , int width, int height)
    {
        switch (sig) {
            case "0" :
             //   if (!page_exists(x, y)){
                if (page == null){
                    Page p = new Page(pid, x, y, width, height);
                } else {
                    page.set_x(x);
                    page.set_y(y);
                    page.set_width(width);
                    page.set_height(height);
                }
             //       pid++;
             //       pages.add(p);
              //  }
                break;
            case "1" :
                //Page p = get_page(x, y);
                if (page != null){
                    Header h = new Header("enter text here", "header");
                    Container n = new Container(classname+Integer.toString(cname), "absolute"
                            , Integer.toString(x), Integer.toString(y), h, page.get_id()
                            , x, y, width, height);
                    System.out.println(n.to_html());
                    cname++;
                    page.add_container(n);
                }
                break;
            case "2" :
                //p = get_page(x, y);
                if (page != null){
                    Paragraph par = new Paragraph("enter paragraph here", "paragraph");
                    Container n = new Container(classname+Integer.toString(cname), "absolute"
                            , Integer.toString(x), Integer.toString(y), par, page.get_id()
                            , x, y, width, height);
                    cname++;
                    page.add_container(n);
                }
                break;
            case "3" :
                //p = get_page(x, y);
                if (page != null){
                    Image im = new Image("enter image source here", "image");
                    Container n = new Container(classname+Integer.toString(cname), "absolute"
                            , Integer.toString(x), Integer.toString(y), im, page.get_id()
                            , x, y, width, height);
                    cname++;
                    page.add_container(n);
                }
                break;
            case "5" :

                break;
            case "6" :

                break;
            case "7" :

                break;
        }
    }

    public static void process_request_body(String update_request){
        
        String[] lines = update_request.split("\n");  
        int i;
        String sig = "", x = "", y = "", width = "", height = "";

        //HashSet<String> pages = new HashSet<String>();
        HashSet<String> containers = new HashSet<String>();

        for (i = 0; i < lines.length; i++){
            if (!lines[i].equals("")){
                System.out.println("parsing line "+lines[i]);
                String[] sig_split = lines[i].split("sig: ");
                String[] split_two = sig_split[1].split(" ", 2);
                sig = split_two[0];
                String[] x_split = split_two[1].split("x: ");
                String[] split_three = x_split[1].split(" ", 2);
                x = split_three[0];
                String[] y_split = split_three[1].split("y: ");
                String[] split_four = y_split[1].split(" ", 2);
                y = split_four[0];
                String[] width_split = split_four[1].split("width: ");
                String[] split_five = width_split[1].split(" ", 2);
                width = split_five[0];
                String[] height_split = split_five[1].split("height: ");
                String[] split_six = height_split[1].split(" ", 2);
                height = split_six[0];
                try {
                    
                    if (sig.equals("")){
                        //pages.add(x + ":" + y);
                    } else {
                        containers.add(x + ":" + y);
                    }

                    int ix = Integer.parseInt(x);
                    int iy = Integer.parseInt(x);
                    int iwidth = Integer.parseInt(x);
                    int iheight = Integer.parseInt(x);
                    process_request_line(sig, ix, iy, iwidth, iheight);
                } catch (Exception E){
                    System.out.println("bad request: " + update_request);
                }
                sig = "";
                x = "";
                y = "";
                width = "";
                height = "";
            }
        }
        //filter_pages(pages);
        //filter_containers(containers);
       
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        GUI2 g = new GUI2();

        g.setup();
        
        SerialPort sp = SerialPort.getCommPort("/dev/cu.usbmodem1411");

        sp.setComPortParameters(9600, 8, 1, 0);

        //sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (sp.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }


        InputStream s = sp.getInputStream();
           
        int b = 0;
        String resp = "", update_request = "";
        boolean form_request = false;

        System.out.println("Reading inputs");

        while ((b = s.read()) != -1){
            byte[] barray = new byte[1];
            barray[0] = (byte)b;
            String x = new String(barray, "UTF-8");
            if (x.equals("\n")){
                
                if (resp.contains("SP"))
                {
                    form_request = true;
                }
                else if (resp.contains("EP") && !update_request.equals(""))
                {
                    form_request = false;
                    
                    System.out.println("sending request\n\n"+update_request);
                    process_request_body(update_request);
                    g.update(page);

                    System.out.println("containerssssssssssss: "+page.get_containers().size());
                    page = new Page(0, 0, 0, 0, 0);
                    update_request = "";
                    Thread.sleep(5000);
                    //System.out.println(update_request);
                    
                
                }
                else if (form_request)
                {
                    if (resp.contains("sig: ") && resp.contains("x: ") && resp.contains("y: ") && resp.contains("width: ") && resp.contains("height: "))
                    update_request += resp;
                }
                 
                resp = "";
            }
            resp += x;
        }

        System.out.println("Done reading");

        Thread.sleep(1000);

        if (sp.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
            return;
        }
    }
}   
