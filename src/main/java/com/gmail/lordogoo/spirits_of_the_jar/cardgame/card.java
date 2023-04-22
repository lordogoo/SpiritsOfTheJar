package com.gmail.lordogoo.spirits_of_the_jar.cardgame;
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

public class card extends model
{
    //static info
    String name;
    String text;
    int rarity;
    int cost;
    
    imagewithindex symbol; 
    
    Vector<card> parents;
    
    
    placementrulemodel placement;
    //TODO
    Vector<ruleactivationmodel> onplace;
    Vector<ruleactivationmodel> onupdate;
    Vector<ruleactivationmodel> ondestroy;
    

    
    public card(String n,int r,imagewithindex s)
    {
        name = n;
        symbol = s;
        rarity = r;
        parents = new Vector<card>();
        placement = new placementrulemodel();
        
        onplace = new Vector<ruleactivationmodel>();
        onupdate = new Vector<ruleactivationmodel>();
        ondestroy = new Vector<ruleactivationmodel>();
        
        cost = 20*rarity;
    }
    
    
    public int getcost(){
        return cost;
    }
    
    public int getrarity(){
        return rarity;
    }
    
    //placement contraint rule functions
    public void addconstraint(ruledestinationmodel dest){
        placement.addconstraint(dest);
    }
    
    public int numconstraint(){
        return placement.numconstraint();
    }
    
    public ruledestinationmodel getconstriant(int i){
        return placement.getconstraint(i);
    }
    
    //placement activation rule functions
    public void addonplacerule(ruleactivationmodel place){
        onplace.add(place);
    }
    
    public void addonupdaterule(ruleactivationmodel update){
        onupdate.add(update);
    }
    
    public void addondestroyrule(ruleactivationmodel destroy){
        ondestroy.add(destroy);
    }
    
    public int numonplacerule(){
        return onplace.size();
    }
    
    public ruleactivationmodel getonplacerule(int i){
        return onplace.elementAt(i);
    }
    
    public ruleactivationmodel getonupdaterule(int i){
        return onplace.elementAt(i);
    }
    
    
    //parents functions
    public void addparent(card c){
        parents.add(c);
    }
    
    public int numparent(){
        return parents.size();
    }
    
    public card getparent(int i){
        return parents.elementAt(i);
    }
    
    public String getname(){
        return name;
    }
    
    public imagewithindex getsymbol(){
        return symbol;
    }
    
    public boolean canplay(gametilemodel tile){
        for(int i = 0; i < placement.numconstraint();i++){
            if(placement.getconstraint(i).canplace(tile)){
                return true;
            }
        }
        return false;
    }
    
    public void onplace(matchmodel m,instansiatedcard c,gametilemodel tile){
        System.out.println("++++on place event "+onplace.size()+"++++");
        for(int i = 0; i < onplace.size();i++ ){
            onplace.elementAt(i).execute(m,c,tile);
        }
    }
    
    public void onupdate(matchmodel m,instansiatedcard c,gametilemodel tile,int index){
        System.out.println("++++on place event "+onupdate.size()+"++++");
        for(int i = 0; i < onupdate.size();i++ ){
            onupdate.elementAt(i).execute(m,c,tile);
        }
    }
    
    public void ondestroy(matchmodel m,gametilemodel tile,int index){
    }
    /*
    public card copy(){
        card temp = new card(name,rarity,symbol);
        for(int i = 0; i < parents.size();i++){
            temp.addparent(this.getparent(i));
        }
        for(int i = 0; i < placement.numconstraint();i++){
            temp.addconstraint(placement.getconstraint(i));
        }
        for(int i = 0; i < numonplacerule();i++){
            temp.addonplacerule(this.getonplacerule(i));
        }
        return temp;
    }
    */
   
   public Vector<animation> getonplaceanimation(gamepanel g,trigger t,int durration,Color b,instansiatedcard c,gametilemodel tile){
        Vector<animation> animationlist = new Vector<animation>();
        for(int i = 0; i < onplace.size();i++){
            animationlist.add(onplace.elementAt(i).createanimation(g,t,durration,b,c,tile));
        }
        return animationlist;
    }
    
   public Vector<animation> getupdateanimation(gamepanel g,trigger t,int durration,Color b,instansiatedcard c,gametilemodel tile){
        Vector<animation> animationlist = new Vector<animation>();
        for(int i = 0; i < onupdate.size();i++){
            animationlist.add(onupdate.elementAt(i).createanimation(g,t,durration,b,c,tile));
        }
        return animationlist;
   }
   
   public String getinheritencestring(){
        String ih = "this card inherits from: ";
        for(int i = 0;i < parents.size();i++){
            ih = ih+ parents.elementAt(i).getname();
            if( i != parents.size()-1){
                ih = ih + ", ";
            }
        }
        return ih;
   }
   
   public String getplacementrulestring(){
        String pr = "this card can be placed: ";
        pr += placement.getplacementstring();
        return pr;
   }
   
   public String getonplayrulestring(){
        String ih = "when played do: ";
        for(int i = 0;i < onplace.size();i++){
            ih = ih+ onplace.elementAt(i).gettext();
            if( i != onplace.size()-1){
                ih = ih + " then ";
            }
        }
        if(onplace.size() == 0){
            ih+=" do nothing";
        }
        return ih;
   }
   
   public String getonupdaterulestring(){
        String ih = "at the begining of the turn do: ";
        for(int i = 0;i < onupdate.size();i++){
            ih = ih+ onupdate.elementAt(i).gettext();
            if( i != onupdate.size()-1){
                ih = ih + " then ";
            }
        }
        if(onupdate.size() == 0){
            ih+=" do nothing";
        }
        return ih;
   }
   
   public String getondestroyrulestring(){
        String ih = "when destroyed do: ";
        for(int i = 0;i < ondestroy.size();i++){
            ih = ih+ ondestroy.elementAt(i).gettext();
            if( i != ondestroy.size()-1){
                ih = ih + " then ";
            }
        }
        if(ondestroy.size() == 0){
            ih+=" do nothing";
        }
        return ih;
   }
}
