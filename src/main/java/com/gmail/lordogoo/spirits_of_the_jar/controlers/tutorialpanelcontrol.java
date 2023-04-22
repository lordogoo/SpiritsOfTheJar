package com.gmail.lordogoo.spirits_of_the_jar.controlers;

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
 * Write a description of class tutorialpanelcontrol here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tutorialpanelcontrol
{

    String panelname;
    int currentanimation;
    Vector<tutorialtrigger> triggerlist;
    
    
    public tutorialpanelcontrol(String pname){
        panelname = pname;
        
        currentanimation = 0;
        triggerlist = new Vector<tutorialtrigger>();
    }
    
    public void addtrigger(tutorialtrigger t){
        triggerlist.add(t);
    }
    
    public tutorialtrigger gettrigger(){
        if(currentanimation < triggerlist.size()){
            currentanimation++;
            return triggerlist.elementAt(currentanimation - 1);
        }
        return null;
    }
    
    public boolean ispanel(String p){
        if(p.equals(panelname)){
            return true;
        }
        return false;
    }

    public int size(){
        return triggerlist.size();
    }
    
    
    public tutorialpanelcontrol(BufferedReader in) throws Exception{
        String line = in.readLine();
        String[] list = line.split("[|]");
        //System.out.println("read in tutorial information "+list[0]+" "+list[1]);
        panelname = list[0]  ;
        currentanimation = Integer.parseInt(list[1]);
        triggerlist = new Vector<tutorialtrigger>();
    }
    
    public void saveinformation(PrintWriter out){
        out.println(panelname+"|"+currentanimation);
    }
    

}
