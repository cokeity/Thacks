package com.mycompany.app;

import java.util.ArrayList;

public class Paragraph extends HTMLElement{

    private String text;

    public Paragraph(String text, String type){
        super(type);
        this.text = text;
    }

    public String to_html(){
        return "&lt;p&gt;<br/>" +
               "&emsp;" + text + "<br/>" +
               "&lt;/p&gt;<br/>";
    }

}
