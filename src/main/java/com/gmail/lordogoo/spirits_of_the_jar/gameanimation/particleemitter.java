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

public class particleemitter
{
    int currenttime;
    int durration;
    int num;
    
    int x;
    int y;
   
    particle p;
    
    particleanimation anim;

    public particleemitter(int x, int y, particle p,int t)
    {
        currenttime = 0;
        this.p = p;
        durration = t;
    }
    
    public void addanimation(particleanimation a){
        anim = a;
    }
    
    public void update(particleanimation a){
        Random rand = new Random();
        if(currenttime%durration == 0){
            for(int i = 0; i < num;i++){
                a.addparticle(p.copy(x,y,rand.nextInt()));
            }
        }
        
        currenttime ++;
    }
    
    
}
