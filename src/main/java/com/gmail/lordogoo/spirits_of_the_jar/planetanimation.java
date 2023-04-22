package com.gmail.lordogoo.spirits_of_the_jar;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
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

public class planetanimation extends JPanel
{
    
    int i;    
    BufferedImage planet;
    Animator animation;
    PropertySetter setter;
    JComponent container;

    public planetanimation(JComponent c,BufferedImage p)
    {
        planet = p;
        i = 0;
        container = c;
        setter = new PropertySetter(this, "i",0,360);
        
        //animation = new Animator(20000,Animator.INFINITE,Animator.RepeatBehavior.LOOP,setter);
        animation = new Animator(20000,Animator.INFINITE,Animator.RepeatBehavior.LOOP,setter);
        //animation.start();
    }
    
    public void stop(){
        animation.stop();
    }
    
    public void paintComponent(Graphics g) {
        if(planet != null){
           Graphics2D g2d = (Graphics2D)g;
           AffineTransform affineTransform = new AffineTransform(); 
           affineTransform.rotate(Math.toRadians(i),planet.getWidth()/2,planet.getHeight()/2); 
           g2d.drawImage(planet,affineTransform,this);
           container.repaint();
        }
    }
    
    public int getI(){
        return i;
    }
    
    public void setI(int i){
        this.i = i;
        this.repaint();
        container.repaint();
    }
    
    public BufferedImage getimage(){
        if(planet != null){
            Rectangle r = this.getBounds();
            BufferedImage i = new BufferedImage(this.getWidth(),this.getHeight(),
            BufferedImage.TYPE_INT_RGB);
            Graphics g = i.getGraphics();
            this.paint(g);
            return i;
        }
        return null;
    }
    
    
}
