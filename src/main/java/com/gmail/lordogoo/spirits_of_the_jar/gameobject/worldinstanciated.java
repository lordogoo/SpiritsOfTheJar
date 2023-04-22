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
public class worldinstanciated extends modelobject
{
    
    worldmodel world;
    boolean discovered;
    pcinstanciated player;
    
    
    int offsetx;
    int offsety;
    
    modellist badguylistindex;
    Vector<badguyinstansiated> badguy;
    terraininstanciated terrain;
    


    public worldinstanciated(int p,worldmodel w)
    {
        super(p);
        world = w;
        discovered = false;
        badguylistindex = new modellist();
        badguy = new Vector<badguyinstansiated>();
        for(int i = 0; i < world.numbadguy();i++){
            badguy.add(new badguyinstansiated(badguylistindex.getnum(),world.getbadguy(i)));
        }
    }
    
    public boolean isdiscovered(){
        return discovered;
    }
    
    public void setdiscovered(boolean d){
        discovered = d;
    }
    
    
    //player functions
    public void addplayer(pcinstanciated p){
        player = p;
        terrain.addnpc(p);
    }
    
    public boolean hasplayer(){
        for(int i = 0; i < terrain.numnpc();i++){
            if(terrain.getnpc(i).isplayer()){
                return true;
            }
        }
        return false;
    }
    
    public void removeplayer(){
        for(int i = 0; i < terrain.numnpc();i++){
            if(terrain.getnpc(i).isplayer()){
                terrain.removenpc(i);
            }
        }
        player = null;
    }
    
    //terrain
    public terraininstanciated getterrain(){
        return terrain;
    }
    
    public void instansiateterrain(gameinfocontrol g,menucontrol cont){
        offsetx = 0;
        offsety = 0;
        
        terrain = world.getterrain().instanciateterrain(this);

    }
    
    public worldmodel getmodel(){
        return world;
    }
    
    //offset set/get functions
    public int getoffsetx(){
        return offsetx;
    }
    
    public int getoffsety(){
        return offsety;
    }
    
    public void addoffset(int x,int y){
        offsetx+=x;
        offsety+=y;
    }
    
    public void setoffset(int x,int y){
        offsetx = x;
        offsety = y;
    }
    
    public badguyinstansiated getbadguy(int i){
        return badguy.elementAt(i);
    }
    
    public badguyinstansiated getbadguy(String b){
        for(int i = 0; i < badguy.size(); i++){
            if(badguy.elementAt(i).getmodel().getname().equals(b)){
                return badguy.elementAt(i);
            }
        }
        return null;
    }
    
    public int numbadguy(){
        return badguy.size();
    }
       
    //save load functions
    public worldinstanciated(BufferedReader in,texturelibrary lib,gameinfocontrol gcont) throws Exception{
        super(0);
        String inline = in.readLine();
        String[] inlist = inline.split("[|]");
        primarykey = Integer.parseInt(inlist[0]);
        world = gcont.getworld(inlist[1]);
        offsetx = Integer.parseInt(inlist[2]);
        offsety = Integer.parseInt(inlist[3]); 
        discovered = Boolean.parseBoolean(inlist[4]);
        
        badguy = new Vector<badguyinstansiated>();
        if(in.readLine().equals("*badguy")){
            int numbadguy = Integer.parseInt(in.readLine());
            for(int i = 0; i < numbadguy;i++){
                badguy.add(new badguyinstansiated(in,lib,gcont));
            }
        }
        
        
        String terrainstring = in.readLine();
        if(terrainstring.equals("*terrain")){
            terrain = new terraininstanciated(this,in,lib,gcont);
        }else{
            terrain = null;
        }
    }
    
    public void saveinformation(PrintWriter out){
        out.println(primarykey+"|"+world.getname()+"|"+offsetx+"|"+offsety+"|"+discovered);
        
        out.println("*badguy");
        out.println(badguy.size());
        for(int i = 0; i < badguy.size();i++){
            badguy.elementAt(i).saveinformation(out);
        }
        
        
        if(terrain != null){
            out.println("*terrain");
            terrain.saveinformation(out);
        }else{
            out.println("null");
        }
        
    }
    
    
}
