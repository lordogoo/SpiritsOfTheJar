package com.gmail.lordogoo.spirits_of_the_jar.interfacecomponents;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.applet.*;
import java.io.*;
import java.util.*;
/**
 * Write a description of class speciallabelcontainer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class speciallabelcontainer extends specialcomponentcontainer
{

    speciallabel label;

    public speciallabelcontainer(Vector<JComponent> list,String text)
    {
        label = new speciallabel(text);
        list.add(label);
    }
    
    public speciallabelcontainer(Vector<JComponent> list,Vector<specialcomponentcontainer> containerlist,speciallabel l){
        label = l;
        list.add(label);
        containerlist.add(this);  
    }
    
    public speciallabel getlabel(){
        return label;
    }
    
    
    public void setopacity(float i){ 
        label.setopacity(i);
        label.repaint();
    }

    public void setEnabled(boolean b){
        label.setEnabled(b);
    }
    

}
