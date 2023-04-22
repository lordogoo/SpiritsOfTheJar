package com.gmail.lordogoo.spirits_of_the_jar.bodygame;

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
 * Write a description of class bodymodel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bodymodel
{
    String name;

    
    factionmodel faction;
    worldmodel oragin;
    
    Vector<card> restrictions;
    Vector<Integer> restrictednum;
    boolean discovered;
    int speed;
    int bounty;
    int production;
    
    Vector<String> namelist;
    Vector<imagewithindex> images;
    
    public bodymodel(String n,int s,int b,int p,worldmodel o,Vector<imagewithindex> i)
    {
        name = n;
        speed = s;
        bounty = b;
        production = p;
        oragin = o;
        images = i;
        
        restrictions = new Vector<card>();
        restrictednum = new Vector<Integer>();
        namelist = new Vector<String>();
    }
    
    public bodymodel(String n,int s,worldmodel o)
    {
        name = n;
        speed = s;
        oragin = o;
        
        images = new Vector<imagewithindex>();
        restrictions = new Vector<card>();
        restrictednum = new Vector<Integer>();
        namelist = new Vector<String>();
    }
    
    public bodymodel(String n,int s,worldmodel o,Vector<card> c,Vector<Integer> in)
    {
        name = n;
        speed = s;
        oragin = o;
        
        restrictions = new Vector<card>();
        for(int i = 0; i < c.size();i++){
            restrictions.add(c.elementAt(i));
        }
        
        restrictednum = new Vector<Integer>();
        for(int i = 0; i < in.size();i++){
            restrictednum.add(in.elementAt(i));
        }
    }
    
    //restriction functions
    public void addrestriction(card c,int num){
        restrictions.add(c);
        restrictednum.add(Integer.valueOf(num));
    }
    
    public boolean isrestricted(){
        //todo
        return false;
    }
    
    public Vector<card> getrestrictedcards(){
        return restrictions;
    }
    
    public int contiansrestriction(card c){
        for(int i = 0; i < restrictions.size();i++){
            if(restrictions.elementAt(i).getname().equals(c.getname())){
                return i;
            }
        }
        return -1;
    }
    
    public Vector<Integer> getrestrictednum(){
        return restrictednum;
    }
    
    public int numrestricted(){
        return restrictions.size();
    }
    
    public card getrestriction(int i){
        return restrictions.elementAt(i);
    }
    
    public Integer getrestrictionnum(int i){
        return restrictednum.elementAt(i);
    }
    
    //name functions
    public void addrandomname(String s){
         namelist.add(s);
    }
    
    public String getrandomname(int i){
        return namelist.elementAt(i);
    }
    
    public int numrandomname(){
        return namelist.size();
    }
    
    //set/get functions
    public void setdiscovered(boolean b){
        discovered = b;
    }

    public boolean isdiscovered(){
        return discovered;
    } 
    
    public int getspeed(){
        return speed;
    }
    
    public int getbounty(){
        return bounty;
    }
    
    public int getproduction(){
        return production;
    }
    
    public String getname(){
        return name;
    }
    
    //image functions
    public int numimages(){
        return images.size();
    }
    
    public BufferedImage getimage(int i){
        return images.elementAt(i).getimage();
    }
    
    public void addimage(imagewithindex i){
        images.add(i);
    }
    
    public imagewithindex getrandomimage(){
        Random r = new Random();
        return images.elementAt(Math.abs(r.nextInt()%images.size()));
    }
    /*
    public bodymodel copy(){
        return new bodymodel(name,speed,oragin,restrictions,restrictednum );
    }
    */
   //faction set/get
    public void addfaction(factionmodel f){
        faction = f;
    }
    
    public factionmodel getfaction(){
        return faction;   
    }
    
    public worldmodel getoragin(){
        return oragin;
    }
}
