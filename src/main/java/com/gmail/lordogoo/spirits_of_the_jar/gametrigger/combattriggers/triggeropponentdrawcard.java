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
public class triggeropponentdrawcard extends trigger
{

    menucontrol cont;
    matchmodel match;
    
    JComponent opdeck;
    JComponent ophand;
    
    instansiatedcard opponentcards;
    handcarddisplay opponentdisplay;
    
    public triggeropponentdrawcard(menucontrol c,matchmodel m)
    {
        cont = c;
        match = m; 
    }
    
    public void trigger(gamepanel target){
        this.target = target;
        if(match.getopponentdeck().size()>0){
            if(match.getopponenthand().size() < 5){
                prewait();
                java.util.Timer animationthread = new java.util.Timer("animator");
                runanimation t = new runanimation(this);
                animationthread.schedule(t, 0, 40);
            }else{
                target.addtrigger(new triggeropponentupdatephase(cont,match));
                target.runtriggers(target);
            }
        }else{
            target.addtrigger(new triggerplayervictory(cont,match,match.getvictory().textplayerwins()));
            target.runtriggers(target);
        }
    }
    
    
    public void prewait(){
        opdeck = ((matchmenupanel)target).getopponentdeck();
        ophand = ((matchmenupanel)target).getopponenthand();
         
        opponentcards = match.drawcards(1,match.getopponentdeck()).elementAt(0);
        opponentdisplay =  new handcarddisplay(opponentcards,true,cont.getlib().gettexture(5).getimage());
        opponentdisplay .setSize(64,ophand.getHeight());
            
        //target.addanimation(new drawopponentcardanimation(target,this,20,new Color(0,0,0,150),match.getopponenthand().size(),opponentdisplay,
        //                   opdeck,ophand));
        target.addanimationdisplay();
    }
    
    public void postwait(){
        
        match.givehandcards(match.getopponenthand(),opponentcards);
        ((matchmenupanel)target).addopponenthand(opponentdisplay);
        
        target.removeanimationdisplay();
        target.addtrigger(new triggeropponentupdatephase(cont,match));
    }
    
}