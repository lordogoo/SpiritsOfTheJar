package com.gmail.lordogoo.spirits_of_the_jar.controlers;

import javax.swing.event.*;
import info.clearthought.layout.*;
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
 * Write a description of class game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class gamecontrol
{

    //TODO look if some perameters can be deleted

    gameinfocontrol gameinfo;
    tutorialcontrol tutorial;
    
    modellist worldindex;
    Vector<worldinstanciated> worldlist;
     
    //game information current
    worldinstanciated world;
    playermodel player;
    
    deckmodel deck;
    matchmodel match;
    dialogmodel dialog;
    structureinstansiated struct;
    
    public gamecontrol(tutorialcontrol tut,gameinfocontrol info,playermodel p)
    {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>new game<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        gameinfo = info;
        tutorial = tut;

        player = p;        
        worldindex = new modellist();
        worldlist = new Vector<worldinstanciated>();
        for(int i = 0; i < info.numworld();i++){
            worldlist.add(new worldinstanciated(worldindex.getnum(),info.getworld(i)));
        }
        
        worldlist.elementAt(0).setdiscovered(true);
        worldlist.elementAt(1).setdiscovered(true);
   
        if(player.getfirstplay()){
            Random r = new Random();
            boolean choice = r.nextBoolean();
            if(choice){
                System.out.println("!_!start body "+"merchant");
                player.addbody(new bodyinstanciated(info.getbody("merchant")));
            }else{
                System.out.println("!_!start body "+"outlaw");
                player.addbody(new bodyinstanciated(info.getbody("outlaw")));
            }
            System.out.println("!_!start body "+"manikin");
            player.addbody(new bodyinstanciated(info.getbody("manikin")));
        }
    }
    
    //player
    public playermodel getplayer(){
        return player;
    }
    
    //set current information for game
    public void setcurrentworld(worldinstanciated w){
        world = w;
    }    
    
    public void setcurrentdeck(deckmodel d){
        deck = d;
    }
    
    public void setcurrentmatch(matchmodel m){
        match = m;
    }
    
    public void setcurrentstruct(structureinstansiated s){
        struct = s;
    }
    
    public void setcurrentdialog(dialogmodel d){
        dialog = d;
    }
    
    //get current information
    public worldinstanciated getcurrentworld(){
        return world;
    }
    
    
    public deckmodel getcurrentdeck(){
        return deck;
    }
    
    public matchmodel getcurrentmatch(){
        return match;
    }
    
    public structureinstansiated getcurrentstruct(){
        return struct;
    }
    
    public dialogmodel getcurrentdialog(){
        return dialog;
    }
    
    public tutorialcontrol gettutorial(){
        return tutorial;
    }
    
    //world stuff
    public worldinstanciated getworld(int i){
        return worldlist.elementAt(i);
    }
    
    public int numworld(){
        return worldlist.size();
    }
    
    //save load functions
    public gamecontrol(BufferedReader in,menucontrol m,texturelibrary lib,gameinfocontrol gcont,playermodel p) throws Exception{
        gameinfo = gcont;
        
        player = p;
        worldlist = new Vector<worldinstanciated>();
        if(in.readLine().equals("*world")){
            int worldnum = Integer.parseInt(in.readLine());
            worldindex = new modellist(Integer.parseInt(in.readLine()));
            for(int i = 0; i < worldnum;i++){
                worldlist.add(new worldinstanciated(in,lib,gcont));
            }
        }
        
        tutorial = new tutorialcontrol(in,m,lib);
    }
    
    public void saveinformation(PrintWriter out){
        out.println("*world");
        out.println(worldlist.size());
        out.println(worldindex.checknum());
        for(int i = 0; i < worldlist.size(); i++){
               worldlist.elementAt(i).saveinformation(out);
        }
        
        
        tutorial.saveinformation(out);
    }
}
