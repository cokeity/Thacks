package com.mycompany.app;

import java.util.ArrayList;

public class Container{

    private String classname;

    private int page;
    private int x;
    private int y;
    private int width;
    private int height;

    private PositionCSS style;
    private HTMLElement element;

    public Container(String classname, String position, String left
            , String top, HTMLElement element, int page, int x, int y,
            int width, int height){
        this.classname = classname;
        this.style = new PositionCSS(classname, position, left, top);
        this.element = element;
        this.page = page;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String get_classname(){
        return classname;
    }

    public PositionCSS get_css_style(){
        return style;
    }

    public HTMLElement get_element(){
        return element;
    }

    public String to_html(){
        return element.toString();
    }

    public int get_x(){
        return x;
    }

    public int get_y(){
        return y;
    }

    public void set_x(int x){
        this.x = x;
    }

    public void set_y(int y){
        this.y = y;
    }

    public int get_width(){
        return width;
    }

    public int get_height(){
        return height;
    }
}


