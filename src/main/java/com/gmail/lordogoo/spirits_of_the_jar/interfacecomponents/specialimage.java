package com.gmail.lordogoo.spirits_of_the_jar.interfacecomponents;
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

public class specialimage
{
    
    BufferedImage drawplate;
    
    int xloc;
    int yloc;
    int width;
    int height;
    int distance;
    int direction;
    BufferedImage background;
    Color tint;
    boolean enabled;
    
    Polygon north;
    Polygon east;
    Polygon south;
    Polygon west;
    Polygon center;
    Polygon back;
    Rectangle2D rect;
    
    Color light;
    Color dark;
    Color clear;
    
    public static int NORTH = 0;
    public static int EAST = 1;
    public static int SOUTH = 2;
    public static int WEST = 3;
    
    public specialimage(int x,int y,int w,int h,int distance,int dir,Color t,BufferedImage b,boolean en)
    {
        width = w;
        height = h;
        xloc = x;
        yloc = y;
        direction = Math.abs(dir%4);
        this.distance = distance;
        background = b;
        tint = t;
        enabled = en;
        light = new Color(255,255,255,200);
        dark = new Color(0,0,0,200);
        clear = new Color(0,0,0,0);
        
        onresize(w,h);
        
    }
    
    public int getwidth(){
        return width;
    }
    
    public int getheight(){
        return height;
    }
    
    public void onresize(int w,int h){
        width = w;
        height = h;
        
        //remeasure info
        drawplate = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB_PRE);
        back = new Polygon();
        back.addPoint(0,0);
        back.addPoint(w,0);
        back.addPoint(w,h);
        back.addPoint(0,h);
        north = new Polygon();
        north.addPoint(0,0);
        north.addPoint(w,0);
        north.addPoint(w-distance,distance);
        north.addPoint(distance,distance);
        east = new Polygon();
        east.addPoint(w,0);
        east.addPoint(w,h);
        east.addPoint(w-distance,h-distance);
        east.addPoint(w-distance,distance);
        south = new Polygon();
        south.addPoint(w,h);
        south.addPoint(0,h);
        south.addPoint(distance,h-distance);
        south.addPoint(w-distance,h-distance);
        west = new Polygon();
        west.addPoint(0,h);
        west.addPoint(0,0);
        west.addPoint(distance,distance);
        west.addPoint(distance,h-distance);
        center = new Polygon();
        center.addPoint(distance,distance);
        center.addPoint(w-distance,distance);
        center.addPoint(w-distance,h-distance);
        center.addPoint(distance,h-distance);
        rect = new Rectangle2D.Double(xloc,yloc,background.getWidth()+xloc,background.getHeight()+yloc);
       
        //color directions given lightsource direction
        Color no = clear;
        Color ea = clear;
        Color so = clear;
        Color we = clear;
        if(enabled == true){
            if(direction == 0){//north
                no = light;
                ea = light;
                so = dark;
                we = dark;
            }else if(direction == 1){
                no = dark;
                ea = light;
                so = light;
                we = dark;
            }else if(direction == 2){
                no = dark;
                ea = dark;
                so = light;
                we = light;
            }else{
                no = light;
                ea = dark;
                so = dark;
                we = light;
            }
        }
        //paint info    
        //redraw backplate
        Graphics2D g2d = drawplate.createGraphics() ;
        
        g2d.setPaint(new  TexturePaint(background,rect));
        g2d.fillPolygon(back);
        
        g2d.setPaint(no);
        g2d.fill(north);
        
        g2d.setPaint(ea);
        g2d.fill(east);
        
        g2d.setPaint(so);
        g2d.fill(south);
        
        g2d.setPaint(we);
        g2d.fill(west);
        
        if(tint!=null){
            g2d.setPaint(tint);
            g2d.fill(center);
        }
        
    }
    
    public BufferedImage getimage()
    {
        return drawplate;
    }
    
}
