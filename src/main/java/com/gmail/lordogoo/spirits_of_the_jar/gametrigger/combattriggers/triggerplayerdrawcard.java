package com.gmail.lordogoo.spirits_of_the_jar.gametrigger.combattriggers;
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
 * Write a description of class triggerdrawanimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class triggerplayerdrawcard extends triggeranimations
{

    carddisplay display;

    menucontrol cont;
    matchmodel match;
    
    JComponent pdeck;
    JComponent phand;
    
    instansiatedcard playercards;
    handcarddisplay playerdisplay;

    
    
    public triggerplayerdrawcard(menucontrol c,matchmodel m)
    {
        cont = c;
        match = m;  
    }
    
    
    public void trigger(gamepanel target){
        this.target = target;
        if(match.getplayerdeck().size()>0){
            if(match.getplayerhand().size() < 5){
                prewait();
                java.util.Timer animationthread = new java.util.Timer("animator");
                runanimation t = new runanimation(this);
                animationthread.schedule(t, 0, 40);
            }else{
                target.addtrigger(new triggerplayerupdatephase(match));
                target.runtriggers(target);
            }
        }else{
            target.addtrigger(new triggeropponentvictory(cont,match,"you ran out of cards"));
            target.runtriggers(target);
        }
    }
    
    
    public void prewait(){
        pdeck = ((matchmenupanel)target).getplayerdeck();
        phand = ((matchmenupanel)target).getplayerhand();
        
        playercards = match.drawcards(1,match.getplayerdeck()).elementAt(0);
        playerdisplay =  new handcarddisplay(playercards,false,cont.getlib().gettexture(5).getimage());
        playerdisplay .setSize(64,phand.getHeight());
            
        //target.addanimation(new drawplayercardanimation(target,this,20,new Color(0,0,0,150),match.getplayerhand().size(),playerdisplay,
        //                   pdeck,phand));
        target.addanimationdisplay();
    }
    
    public void endanimation(gamepanel target){
        match.givehandcards(match.getplayerhand(),playercards);
        ((matchmenupanel)target).addplayerhand(playerdisplay);
        target.removeanimationdisplay();
        target.addtrigger(new triggerplayerupdatephase(match));
        super.endanimation(target);
    }
   
    public class carddisplay extends animationscreenpanel
    {
        
       int numcards;
       handcarddisplay playercards;
    
       JComponent pdeck;
       JComponent phand;
        
       public carddisplay(gamepanel gp,int durration,int nc,handcarddisplay pc,JComponent pdeck,JComponent phand)
       {
        super(gp,durration);
        numcards = nc;
        playercards = pc;
        
        this.pdeck = pdeck;
        this.phand = phand;   
       }
     
       public void paintComponent(Graphics g){
        int xloc = pdeck.getX()+((((phand.getX()-pdeck.getX())+64*numcards)*currentframe)/framesdurration);
        int yloc = pdeck.getY();
        playercards.paintComponent(g,xloc,yloc);
       }
       
    }
    
    
    
}
