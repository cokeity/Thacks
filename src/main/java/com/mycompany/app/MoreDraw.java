package com.mycompany.app;

import javax.swing.*;
import java.awt.*;

public class MoreDraw extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        //g.drawRect(100,100,100,100);
        g.fillRect(100,100,100,100);

    }


}