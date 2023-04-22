package com.gmail.lordogoo.spirits_of_the_jar;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
import org.jdesktop.animation.timing.triggers.FocusTrigger;
import org.jdesktop.animation.timing.triggers.TimingTrigger;
import org.jdesktop.animation.timing.triggers.TimingTriggerEvent;
import org.jdesktop.animation.timing.triggers.FocusTriggerEvent; 

public class imageanimation extends JPanel
{
    
    int i;    
    Vector<BufferedImage> animationlist;
    Animator animation;
    PropertySetter setter;
    JComponent container;
    
    public imageanimation(Vector<BufferedImage> im,JComponent c)
    {
        i = 0;
        container = c;
        animationlist = im;
        setter = new PropertySetter(this, "i",0,animationlist.size()-1);
        animation = new Animator(1000,1,Animator.RepeatBehavior.LOOP, setter);
        animation.start();
    }
    
    public void addimagelist(Vector<BufferedImage> im){
        animationlist = im;
    }
    
    public int numframes(){
        return animationlist.size();
    }
    
    public void frame(int n){
        i = n;
    }
    
    public void paintComponent(Graphics g) {
        BufferedImage itemimage = animationlist.elementAt(i);
        g.drawImage(itemimage,(this.getWidth()-itemimage.getWidth())/2,(this.getHeight()-itemimage.getHeight())/2,null);
        container.repaint();
        
    }
    
    public int getI(){
        return i;
    }
    
    public void setI(int i){
        this.i = i;
        this.repaint();
        container.repaint();
        System.out.println("animframe "+i);
    }
    
    public BufferedImage getimage(){
        return animationlist.elementAt(i);
        
    }
    
    public boolean isdone(){
        if(i == animationlist.size()){
            return true;
        }
        return false;
    }
    
}