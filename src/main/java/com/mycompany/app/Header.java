package com.mycompany.app;

import java.util.ArrayList;

public class Header extends HTMLElement{

    private String text;

    public Header(String text, String type){
        super(type);
        this.text = text;
    }

    public String to_html(){
        return "<h>\n" +
               "\t" + text + "\n" +
               "</h>\n";
    }

}
