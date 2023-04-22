package com.gmail.lordogoo.spirits_of_the_jar.gameobject;
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
public class structureinstansiated extends gameobjectinstanciated
{
    
    
    String name;
    
    npcmodel goal;
    structuremodel type;
    
    int wealth;
    
    shopmodel shop;

    public structureinstansiated(int p,structuremodel t,int x,int y)
    {
        super(p,x,y);
        type = t;
        shop = new shopmodel();
        wealth = 0;
        primarykey = p;
        goal = null;
    }

    public structuremodel getmodel(){
         return type;
    }
    
    public shopmodel getshop(){
        return shop;
    }
    
    public void increasewealth(int w){
        wealth += w;
    }
    
    public void decreasewealth(int w){
        wealth -= w;
    }
    
    public int getwealth(){
        return wealth;
    }
    
    public npcmodel getgoal(){
        return goal;
    }
    
    public void setgoal(npcmodel g){
        goal = g;
    }
    
    //save information
    public structureinstansiated(worldinstanciated w,BufferedReader in,texturelibrary lib,gameinfocontrol gcont) throws Exception{      
        super(0);
        String line = in.readLine();
        String[] list = line.split("[|]");
        primarykey = Integer.parseInt(list[0]);
        type = gcont.getstructure(list[1]);
        name = list[2];
        wealth = Integer.parseInt(list[3]);
        int shopnum =  Integer.parseInt(list[4]);
        locx = Integer.parseInt(list[5]);
        locy = Integer.parseInt(list[6]);
        alive = Boolean.parseBoolean(list[7]);
        killedbyplayer = Boolean.parseBoolean(list[8]);
        
        shop = new shopmodel();
        for(int i = 0; i < shopnum; i++){
            String type = in.readLine();
            if(type.equals("card")){
                shop.addperchace(new cardperchaseinstantiated(in,lib,gcont));
            }else if(type.equals("roumor")){
                shop.addperchace(new roumorperchaseinstantiated(w,in,lib,gcont));
            }
        }
        
        if(list[9].equals("null")){
            goal = null;
        }else{
            goal = gcont.getnpc(list[9]);
        }
    }
    
    public void saveinformation(PrintWriter out){
        String npcgoal;
        if(goal == null){
            npcgoal = "null";
        }else{
            npcgoal = goal.getname();
        }
         
        out.println(primarykey+"|"+type.getname()+"|"+name+"|"+wealth+"|"+shop.numperchace()+"|"+locx+"|"+locy+"|"+alive+"|"+killedbyplayer+"|"+npcgoal);
        for(int i = 0; i < shop.numperchace();i++){
            shop.getperchace(i).saveinformation(out);
        }
    }
    
}
