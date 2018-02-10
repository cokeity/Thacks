package com.mycompany.app;

import java.util.ArrayList;

public class Page{

    private int id;
    private int x;
    private int y;
    private int width;
    private int height;

    private ArrayList<Container> containers = new ArrayList<Container>();
    
    public Page(){

    public Page(int id, int x, int y, int width, int height){
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void add_container(Container c){
        containers.add(c);
    }

    public void remove_container (int i){
        containers.remove(i);
    }

    public int get_x(){
        return x;
    }

    public int get_y(){
        return y;
    }

    public int get_width(){
        return width;
    }

    public int get_height(){
        return height;
    }

    public int get_id(){
        return id;
    }

    public ArrayList<Container> get_containers(){
        return containers;
    }

    public void set_x(int nx){
        this.x = nx;
    }

    public void set_y(int ny){
        this.y = ny;
    }

    public String to_html(){
        String html = "<html>\n";
        for (Container c: containers){
            html+=c.to_html();
        }
        html += "</html>\n";
        return html;
    }
}
