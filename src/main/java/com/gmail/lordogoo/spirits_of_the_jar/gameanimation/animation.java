package com.gmail.lordogoo.spirits_of_the_jar.gameanimation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.pushingpixels.trident.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameinterface.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.*;
/**
 * Write a description of class animation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class animation
{
    gamepanel target;
    Color background;
    
    double updatepersentage;
    
    int framesdurration;
    int currentframe;

    int flashdurration;
    int flash;
    
    boolean active;
    
    triggeranimations trig;
    
    Timeline animation;
    
    
    public animation(gamepanel g,triggeranimations t,int durration,Color b,int fd)
    {
        trig = t;
        target = g;
        framesdurration = durration;
        currentframe = 0;
        flashdurration = fd;
        flash = 0;
        background = b;
        active = true;
        
    }
    
    public void startanimation(){
            animation = new Timeline(this);
            animation.addPropertyToInterpolate("currentframe",0,framesdurration);
            animation.setDuration(750);
            animation.play();    
    }
    
    public void setCurrentframe(int frame){
        currentframe = frame;
        this.update();
    }
    
    public int getCurrentframe(){
        return currentframe;
    }
    
    public void update(){
        if((currentframe == 0)&&(active)){
            System.out.println("terminate animation");
            System.out.println(trig);
            active = false;
            trig.endanimation(target);
        }
    } 
    
    public void interupt(MouseEvent e){
        cleanup();
    }
    
    public void end(){
        cleanup();
    }
    
    public void cleanup(){
        target.addanimation(null);
    }
    
    public void draw(Graphics g,JComponent c){
    }
    
    public int getcurrentframe(){
        return currentframe;
    }
    
    public int getdurration(){
        return framesdurration;
    }
}
