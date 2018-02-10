package com.mycompany.app;

import java.util.ArrayList;

public class Image extends HTMLElement{

    private String src = "dopey.gif";

    public Image(String text){
    }

    public String to_html(){
        return "<img src = "+ src +">\n";
    }

}
