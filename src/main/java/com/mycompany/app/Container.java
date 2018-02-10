package com.mycompany.app;

import java.util.ArrayList;

public class Container{

    private String classname;

    private PositionCSS style;
    private HTMLElement element;

    public Container(String classname, String position, String left
            , String top, HTMLElement element){
        this.classname = classname;
        this.style = new PositionCSS(classname, position, left, top);
        this.element = element;
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

}


