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
import com.gmail.lordogoo.spirits_of_the_jar.*;
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;
import com.gmail.lordogoo.spirits_of_the_jar.cardgame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.battletriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.combattriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.dialogtriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.generictriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.npctriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.screentransitiontriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.shoptriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.playergame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameanimation.*;
import com.gmail.lordogoo.spirits_of_the_jar.badguygame.*;
import com.gmail.lordogoo.spirits_of_the_jar.bodygame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamefaction.*;
import com.gmail.lordogoo.spirits_of_the_jar.events.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameobject.*;
import com.gmail.lordogoo.spirits_of_the_jar.victorygame.*;
import com.gmail.lordogoo.spirits_of_the_jar.destinationrulegame.*;
import com.gmail.lordogoo.spirits_of_the_jar.terraintrigger.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamecardai.*;
import com.gmail.lordogoo.spirits_of_the_jar.activationrulegame.*;
import com.gmail.lordogoo.spirits_of_the_jar.interfacecomponents.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamepopup.*;
import com.gmail.lordogoo.spirits_of_the_jar.ruleconditionalgame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamenpcai.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameinterface.*;
/**
 * Write a description of class fuguredisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class figuredisplay extends animationscreenpanel
{
    
    tutorialentity entity;

    public figuredisplay(gamepanel gp,int durration,tutorialentity e)
    {
        super(gp,durration);
        entity = e;
        this.setBackground(new Color(0, 139, 139));
        //this.setBorder(new EmptyBorder(5, 5, 5, 5));
    }

     public void paintComponent(Graphics g) {
         customdraw(g,0,0);
     }
     
     public void paintComponent(Graphics g,int x,int y) {
         customdraw(g,x,y);
     }
     
     public void customdraw(Graphics g,int x,int y){
         Graphics2D g2d = (Graphics2D) g;

         if(entity != null){
              g.drawImage(entity.getpicture(),0+x,0+y,64,64,null);
         }
        
     }
    
    public void setlocation(){
            int imagelocx = entity.getstartx() + (entity.getendx()-entity.getstartx())*currentframe/framesdurration;
            int imagelocy = entity.getstarty() + (entity.getendy()-entity.getstarty())*currentframe/framesdurration;
            this.setLocation(imagelocx,imagelocy);
    }
    
    
}
