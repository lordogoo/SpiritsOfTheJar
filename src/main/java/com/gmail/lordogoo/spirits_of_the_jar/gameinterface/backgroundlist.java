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
/**
 * Write a description of class backgroundlist here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class backgroundlist extends JList
{
    BufferedImage background;

    public backgroundlist(DefaultListModel model,BufferedImage b)
    {
        super(model);
        background = b;
    }
    
    public void paintComponent(Graphics g) {    
        int w = this.getWidth( );        
        int h = this.getHeight( );
        
        Graphics2D g2d = (Graphics2D)g;
        Polygon back = new Polygon();
        back.addPoint(0,0);
        back.addPoint(w,0);
        back.addPoint(w,h);
        back.addPoint(0,h);
        if(background != null){
            Rectangle2D rect = new Rectangle2D.Double(this.getLocation().getX(),this.getLocation().getY(),
              background.getWidth()+this.getLocation().getX(),background.getHeight()+this.getLocation().getY());
              g2d.setPaint(new  TexturePaint(background,rect));
            g2d.fillPolygon(back);
        }
        GradientPaint gp = new GradientPaint(0, h,new Color(255,255,255,0),0, 0,new Color(255,255,255,200));
        g2d.setPaint( gp );
        g2d.fillRect( 0, 0, w, h );
        
        setOpaque(false);
        super.paintComponent(g);
        setOpaque(true);
    }
}
