package com.mycompany.app;

import java.util.HashMap;
import java.util.Map;

public class PositionCSS{

    private String name;
    private String position;
    private String left;
    private String top;
    private static final String WIDTH = "800";
    private HashMap<String, String> attributes = new HashMap<String, String>();


    public PositionCSS(String name, String position, String left, String top){
        this.name = name;
        this.position = position;
        this.left = left;
        this.top = top;
    }

    public String get_position(){
        return position;
    }

    public String get_left(){
        return left;
    }

    public String get_top(){
        return top;
    }

    public void update_y(String top){
        this.top = top;
    }

    public void update_x(String left){
        this.left = left;
    }

    public void add_attribute(String key, String value){
        attributes.put(key, value);
    }

    public String to_css(){
        String css =
            "<html>."+name+" {<br/>"+
            "&emsp;position: "+position+";<br/>"+
            "&emsp;left: "+left+";<br/>"+
            "&emsp;top: "+top+";<br/>"+
            "&emsp;width: "+WIDTH+";<br/>";
        for (Map.Entry<String, String> entry : attributes.entrySet()){
            css+="&emsp;"+entry.getKey()+": "+entry.getValue()+";<br/>";
        }

        css+="}</html>";

        return css;
    }

}
