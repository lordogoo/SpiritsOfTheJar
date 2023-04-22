package com.gmail.lordogoo.spirits_of_the_jar.gameinterface;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.applet.*;
import java.io.*;
import java.util.*;

public class backgroundpanel extends JPanel{

    BufferedImage background;
    
    public backgroundpanel(BufferedImage b){
        background = b;
    }
        
    public void paintComponent(Graphics g) {
        int i = 0;
        int j = 0;
        while(j < this.getHeight()){
            i = 0;
            while(i < this.getWidth()){
                g.drawImage(background, i, j, null); 
                i+= background.getWidth();
            }
            j+= background.getHeight();
        }
    }
}
