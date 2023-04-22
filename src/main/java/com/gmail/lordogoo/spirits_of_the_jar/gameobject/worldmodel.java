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

public class worldmodel
{

    menucontrol cont;
    //semi static
    String name;
    String discription;
    imagewithindex image;

    //info used in data generation
    Vector<doodadmodel> doodad;
    Vector<npcmodel> npc;
    Vector<bodymodel> bodys;
    Vector<structuremodel> struct;
    Vector<badguymodel> badguy;
    Vector<terrainpeicemodel> terrain;
    
    terrainmodel terrainscripts;
    
    //event programming
    trigger beginenterworld;
    
    //shop info
    //TODO
    int maxweightmulty;
    int maxweightsingle;
    Vector<perchacemodel> multypurchase;
    Vector<card> multycard;
    Vector<perchacemodel> singlepurchase;
    Vector<card> singlecard;
   
    public worldmodel(menucontrol m,String n,terrainmodel tm,imagewithindex s)
    {
        cont = m;
        name = n;
        image = s;
        terrainscripts = tm;
        tm.addworld(this);
        
        bodys = new Vector<bodymodel>();
        doodad = new Vector<doodadmodel>();
        npc = new Vector<npcmodel>();
        struct = new Vector<structuremodel>();
        badguy = new Vector<badguymodel>();
        terrain = new Vector<terrainpeicemodel>();
        
        maxweightmulty = 0;
        maxweightsingle  = 0;
        multypurchase = new Vector<perchacemodel>();
        multycard = new Vector<card>();
        singlepurchase = new Vector<perchacemodel>();
        singlecard = new Vector<card>();
        //perchaselist = new Vector<perchacemodel>();
      
    }
    
    private worldmodel(menucontrol m,String n,terrainmodel tm,imagewithindex s,trigger e)
    {
        cont = m;
        name = n;
        image = s;
        terrainscripts = tm;
        tm.addworld(this);
        
        bodys = new Vector<bodymodel>();
        npc = new Vector<npcmodel>();
        struct = new Vector<structuremodel>();
        badguy = new Vector<badguymodel>();
        terrain = new Vector<terrainpeicemodel>();
        
        beginenterworld = e;
        
        maxweightmulty = 0;
        maxweightsingle = 0;
        multypurchase = new Vector<perchacemodel>();
        multycard = new Vector<card>();
        singlepurchase = new Vector<perchacemodel>();
        singlecard = new Vector<card>();
        //perchaselist = new Vector<perchacemodel>();
    }
    
    //purchase item functions
    
    
    //terrain functions
    public terrainmodel getterrain(){
        return terrainscripts;
    }
    
    //player actions
    public void retreat(){
    }
    
    //npc functions
    public int numnpc(){
        return npc.size();
    }
    
    public npcmodel getnpc(int i){
        return npc.elementAt(i);
    }
    
    public void addnpc(npcmodel n){
        npc.add(n);
    }
    
    public void removenpc(int i){
        npc.remove(i);
    }
    

    
    //trigger set/get
    public void setenterworldtrigger(trigger e){
        beginenterworld = e;
    }
    
    public trigger getenterworldtrigger(){
        return beginenterworld;
    }
    
    //body set/get functions
    public void addbody(bodymodel b){
        bodys.add(b);
    }
    
    public int numbody(){
        return bodys.size();
    }
    
    public bodymodel getbody(int i){
        return bodys.elementAt(i);
    }
    
    //doodads set/get methods
    public void adddoodad(doodadmodel s){
        doodad.add(s);
    }
    
    public doodadmodel getdoodad(int i){
        return doodad.elementAt(i);
    }
    
    public int numdoodad(){
        return doodad.size();
    }
    
    
    
    //structure set/get methods
    public void addstructure(structuremodel s){
        struct.add(s);
    }
    
    public structuremodel getstructure(int i){
        return struct.elementAt(i);
    }
    
    public int numstructure(){
        return struct.size();
    }
    
    //terrain 
    public void addterrainpeice(terrainpeicemodel t){
        terrain.add(t);
    }
    
    public terrainpeicemodel getterrainpeice(int i){
        return terrain.elementAt(i);
    }
    
    public int numterrainpeice(){
        return terrain.size();
    }
    
    /*****
     * todo
     */
    //purchace functions
    public int getmaxweightmulty(){
        return maxweightmulty;
    }
    
    public int getmaxweightsingle(){
        return maxweightsingle;
    }
    
    public void addmultycardperchace(perchacemodel p,card c){
        maxweightmulty += p.getweight();
        multypurchase.add(p);
        multycard.add(c);
    }
    
    public void addsinglecardperchase(perchacemodel p,card c){
        maxweightsingle+=p.getweight();
        singlepurchase.add(p);
        singlecard.add(c);
    }
    
    public perchacemodel getmulty(int i){
        return multypurchase.elementAt(i);
    }
    
    public card getmultycard(int i){
        return multycard.elementAt(i);
    }
    
    public perchacemodel checksingle(int i){
        return singlepurchase.elementAt(i);
    }
    
    public perchacemodel getsingle(int i){
        maxweightsingle-= singlepurchase.elementAt(i).getweight();
        return singlepurchase.remove(i);
    }
    
    public card getsinglecard(int i){
        return singlecard.remove(i);
    }
    
    public void addmultyperchace(perchacemodel p){
        maxweightmulty+=p.getweight();
        multypurchase.add(p);
    }
    
    public void addsingleperchace(perchacemodel p){
        maxweightsingle += p.getweight();
        singlepurchase.add(p);
    }
   
    //badguy functions
    public void addbadguy(badguymodel b){
        badguy.add(b);
    }
    
    public int numbadguy(){
        return badguy.size();
    }
    
    public badguymodel getbadguy(int i){
        return badguy.elementAt(i);
    }
    
    //minsc set/get functions
    public String getname(){
        return name;
    }
    
    public BufferedImage getimage(){
        return image.getimage();
    }
    
 }
