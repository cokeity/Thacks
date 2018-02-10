package com.mycompany.app;

import java.util.ArrayList;

public class page{

    int id;
    private ArrayList<Container> containers = new ArrayList<Container>();
    
    public page(){

    }

    public void add_container(Container c){
        containers.add(c);
    }

    public ArrayList<Container> get_containers(){
        return containers;
    }

}
