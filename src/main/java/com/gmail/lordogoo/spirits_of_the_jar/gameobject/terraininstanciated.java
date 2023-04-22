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
public class terraininstanciated
{

    int xloc;
    int yloc;
    int width;    
    int height;
    
    int startx;
    int starty;


    public terrainmodel terrainref;
    public worldinstanciated worldref;
    
    public Vector<terrainpeiceinstanciated> polygons;
    public modellist doodadlistindex;
    public Vector<doodadinstansiated> doodads;
    public modellist structlistindex;
    public Vector<structureinstansiated> struct;
    public modellist npclistindex;
    public Vector<npcinstanciated> npc;

    public terraininstanciated(terrainmodel tr,worldinstanciated wr)
    {
        terrainref = tr;
        worldref = wr;
         
        xloc = terrainref.getx();
        yloc = terrainref.gety();
        width = terrainref.getwidth();    
        height = terrainref.getheight();
    
        startx = terrainref.getstartx();
        starty = terrainref.getstarty();
        
        
        
        Random r = new Random();
        
        //terrain peices
        polygons = new  Vector<terrainpeiceinstanciated>(); 
        terrainref.createpolygons(r,this);
        
        //add doodads
        doodadlistindex = new modellist();
        doodads = new Vector<doodadinstansiated>();
        terrainref.createdoodads(r,this);

        
        //add structurs
        structlistindex = new modellist();
        struct = new Vector<structureinstansiated>();
        terrainref.createstructures(r,this);

        //add npc's
        npclistindex = new modellist();
        npc = new Vector<npcinstanciated>();
        terrainref.createnpc(r,this);
        //add badguys
    }

    public worldinstanciated getworld(){
        return worldref;
    }
    
    public terrainmodel getmodel(){
        return terrainref;
    }
    
    //doodads
    public int getdoodadnum(){
        return doodads.size();
    }
    
    public int getdoodadindex(){
        return doodadlistindex.getnum();
    }
    
    public doodadinstansiated getdoodad(int i){
        return doodads.elementAt(i);
    }
    
    public void adddoodad(doodadinstansiated d){
        doodads.add(d);
    }
    
    
    //structures
    public int getstructurenum(){
        return struct.size();    
    }
    
    public int getstructureindex(){
        return structlistindex.getnum();
    }
    
    public structureinstansiated getstructure(int i){
        return struct.elementAt(i);
    }    
    
    public void addstructure(structureinstansiated s){
        struct.add(s);
    }
    
    //npcs
    public int numnpc(){
        return npc.size();
    }
    
    public int getnpcindex(){
        return npclistindex.getnum();
    }
    
    public npcinstanciated getnpc(int i){
        return npc.elementAt(i);
    }
    
    public void removenpc(int i){
        npc.remove(i);
    }
    
    public void addnpc(npcinstanciated n){
        npc.add(n);
    }
    
    //terrain peices
    public int getterrainpeicenum(){
        return polygons.size();
    }
    
    public  terrainpeiceinstanciated getterrainpeice(int i){
        return polygons.elementAt(i);
    }
    
    public void addterrainpeice(terrainpeiceinstanciated t){
        polygons.add(t);
    }
    
    //set/get methods
    public int getx(){
        return xloc;
    }
    
    public int gety(){
        return yloc;
    }
    
    public void setx(int x){
        xloc = x;
    }
    
    public void sety(int y){
        yloc = y;
    }
    
    public int getwidth(){
        return width;
    }
    
    public int getheight(){
        return height;
    }
    
    public int getstartx(){
        return startx;
    }
    
    public int getstarty(){
        return starty;
    }
    
        //
    public terraininstanciated(worldinstanciated w,BufferedReader in,texturelibrary lib,gameinfocontrol gcont) throws Exception{
        String line = in.readLine();
        String[] list = line.split("[|]");
        xloc = Integer.parseInt(list[0]);
        yloc = Integer.parseInt(list[1]);
        width = Integer.parseInt(list[2]);
        height = Integer.parseInt(list[3]);
        startx = Integer.parseInt(list[4]);
        starty = Integer.parseInt(list[5]);
        
        worldref = w;
        
        polygons = new Vector<terrainpeiceinstanciated>();
        if(in.readLine().equals("*terrain")){
            int terrainnum = Integer.parseInt(in.readLine());
            for(int i = 0; i < terrainnum; i++){
                polygons.add(new terrainpeiceinstanciated(in,lib,gcont));
            }
        }
        
        doodads = new Vector<doodadinstansiated>();
        if(in.readLine().equals("*doodads")){
            int doodadnum = Integer.parseInt(in.readLine());
            doodadlistindex = new modellist(Integer.parseInt(in.readLine()));
            for(int i = 0; i < doodadnum;i++){
                doodads.add(new doodadinstansiated(in,lib,gcont));
            }
        }
        
        struct = new Vector<structureinstansiated>();
        if(in.readLine().equals("*structure")){
            int structnum = Integer.parseInt(in.readLine());
            structlistindex = new modellist(Integer.parseInt(in.readLine()));
            for(int i = 0; i < structnum;i++){
                struct.add(new structureinstansiated(w,in,lib,gcont));
            }
        }
        
        npc = new Vector<npcinstanciated>();
        if(in.readLine().equals("*npc")){
            int npcnum = Integer.parseInt(in.readLine());
            npclistindex = new modellist(Integer.parseInt(in.readLine()));
            for(int i = 0; i < npcnum;i++){
                npc.add(new npcinstanciated(in,lib,gcont,w,this));
            }
        }
    }
   
    public void saveinformation(PrintWriter out){
        out.println(xloc+"|"+yloc+"|"+width+"|"+height+"|"+startx+"|"+starty);
        out.println("*terrain");
        out.println(polygons.size());
        for(int i = 0; i < polygons.size(); i++){
            polygons.elementAt(i).saveinformation(out);
        }
        
        out.println("*doodads");
        out.println(doodads.size());
        out.println(doodadlistindex.checknum());
        for(int i = 0; i < doodads.size();i++){
            doodads.elementAt(i).saveinformation(out);
        }
        
        out.println("*structure");
        out.println(struct.size());
        out.println(structlistindex.checknum());
        for(int i = 0; i < struct.size();i++){
            struct.elementAt(i).saveinformation(out);
        }
        out.println("*npc");
        out.println(npc.size());
        out.println(npclistindex.checknum());
        for(int i = 0; i < npc.size(); i++){
            npc.elementAt(i).saveinformation(out);
        }
    
    }
    
}
