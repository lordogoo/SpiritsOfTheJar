package com.gmail.lordogoo.spirits_of_the_jar.gameobject;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
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
 * Write a description of class npcmodel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class npcmodel extends gameobjectmodel
{

    String name;
    bodymodel body;
    npcai ai;
    
    int speed;
    int radius;

    Vector<deckmodel> decks;
    
    
    npctrigger onnpctouch;
    npctrigger onplayertouch;
    npctrigger onplayertoggle;
    npctrigger onidle;
    
    public npcmodel(String n,bodymodel b,int s,int r)
    {
        name = n;
        body = b;
        ai = null;
        speed = s;
        radius = r;
        decks = new Vector<deckmodel>();
    }
    
    //deck set/get
    public void adddeck(deckmodel d){
        decks.add(d);
    }
    
    public int numdeck(){
        return decks.size();
    }
    
    public deckmodel deckmodel(int i){
        return decks.elementAt(i);
    }
    
    //ai set/get
    public void addai(npcai a){
        ai = a;
    }
    
    public npcai getai(){
        return ai;
    }
    
    //data set/get
    public int getspeed(){
        return speed;
    }
    
    public int getradius(){
        return radius;
    }
    
    public String getname(){
        return name;
    }
    
    public bodymodel getbody(){
        return body;
    }
    
    //trigger set/get
    public void addonnpctouchtrigger(npctrigger t){
        onnpctouch = t;
    }
    
    public npctrigger getonnpctouchtrigger(){
        return onnpctouch;
    }
    
    public void addonplayertouchtrigger(npctrigger t){
        onplayertouch = t;
    }
    
    public npctrigger getonplayertouchtrigger(){
        return onplayertouch;
    }
    
    public void addonplayertoggletrigger(npctrigger t){
        onplayertoggle = t;
    }
    
    public npctrigger getonplayertoggletrigger(){
        return onplayertoggle;
    }
    
    public void addonidletrigger(npctrigger t){
        onidle = t;
    }
    
    public npctrigger getonidletrigger(){
        return onidle;
    }
}
