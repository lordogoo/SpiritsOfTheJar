package com.gmail.lordogoo.spirits_of_the_jar.gameanimation;

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
 * Write a description of class particle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class particle
{

    int x;
    int y;
    
    
    int seed;
    Shape particle;
    Color color;
    float alpha;
    int durration;
    
    BufferedImage particleimage;
    
    Vector<particletransition> transitions;
    
    public particle(int x,int y,Color c,float a,int s,int d,Shape sp)
    {
        this.x = x;
        this.y = y;
        
        particle = sp;
        color = c;
        alpha = a;
        seed = s;
        durration = d;
        
        Rectangle2D rect = sp.getBounds2D();
       
        particleimage = new BufferedImage((int)Math.ceil(rect.getX()),(int)Math.ceil(rect.getY()),BufferedImage.TYPE_INT_ARGB_PRE);
    }

    public void update(){
    }
    
    public void draw(BufferedImage draw){
        Graphics g = draw.createGraphics();
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(color);
        int type = AlphaComposite.SRC_OVER;
        g2d.setComposite(AlphaComposite.getInstance(type, alpha));
        g2d.fill(particle);
    }
    
    public particle copy(int x,int y,int s){
        return new particle(x,y,color,alpha,s,durration,particle);
    }

}
