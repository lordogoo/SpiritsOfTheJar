package com.gmail.lordogoo.spirits_of_the_jar.interfacecomponents;
import javax.swing.event.*;
import info.clearthought.layout.*;
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
/**
 * Write a description of class specialpanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class specialpanel extends JPanel
{
    
    public float alpha;
    
    public specialpanel()
    {
        super();
    }
    
    public void setopacity(float op){
        alpha = op;
    }
    
    public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            int type = AlphaComposite.SRC_OVER;
            g2d.setComposite(AlphaComposite.getInstance(type, alpha));
            super.paintComponent(g2d);
    }
    
}
