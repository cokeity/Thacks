package com.mycompany.app;

import java.util.ArrayList;

public class Image extends HTMLElement{

    private String src;

    public Image(String src, String type){
        super(type);
        this.src = src;
    }

    public String to_html(){
        return "&lt;img src = "+ src +"&gt;&emsp;<br/>";
    }

}
