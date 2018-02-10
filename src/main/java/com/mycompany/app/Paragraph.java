package com.mycompany.app;

import java.util.ArrayList;

public class Paragraph extends HTMLElement{

    private String text;

    public Paragraph(String text){
        this.text = text;
    }

    public String to_html(){
        return "<p>\n" +
               "\t" + text + "\n" +
               "</p>\n";
    }

}