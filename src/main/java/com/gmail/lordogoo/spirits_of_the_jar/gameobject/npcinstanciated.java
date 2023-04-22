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
 * Write a description of class npcinstanciated here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class npcinstanciated extends gameobjectinstanciated
{
    
    npcmodel npctype;
    String name;
    
    
    npcaiinstanciated ai;
    bodyinstanciated body;
    
    structureinstansiated home;
    badguyinstansiated badguy;
    
    rpsitem rps;
    
    public npcinstanciated(int p,npcmodel n,structureinstansiated h,int x,int y)
    {
        super(p,x,y);
        npctype = n;
        home = h;
        body = new bodyinstanciated(npctype.getbody());
        ai = new npcaiinstanciated(this,npctype.getai(),null);
        
    }
    
    public npcinstanciated(int p,bodyinstanciated b,int x,int y){
        super(p,x,y);
        body = b;
    }
    
    public boolean isplayer(){
        return false;
    }
    
    //location set/get
    public void setlocation(int x,int y){
        locx = x;
        locy = y;
    }
    
    public void move(int x,int y){
        locx+=x;
        locy+=y;
    }
    
    public int getx(){
        return locx;
    }
    
    public int gety(){
        return locy;
    }
    
    
    //get functions
    public bodyinstanciated getbody(){
        return body;
    }
    
    public bodymodel getbodytype(){
        return body.getmodel();
    }
    
    public npcaiinstanciated getai(){
        return ai;
    }
    
    public npcai getaitype(){
        return ai.getmodel();
    }
    
    public structureinstansiated gethome(){
        return home;
    }
    
    public void sethome(structureinstansiated h){
        home = h;
    }
    
    public npcmodel getmodel(){
        return npctype;
    }
    
    //trigger set/get
    public npctrigger getonnpctouchtrigger(){
        return npctype.getonnpctouchtrigger();
    }
    
    public npctrigger getonplayertouchtrigger(){
        return npctype.getonplayertouchtrigger();
    }
    
    public npctrigger getonplayertoggletrigger(){
        return npctype.getonplayertoggletrigger();
    }
    
    public npctrigger getonidletrigger(){
        return npctype.getonidletrigger();
    }
    
    //badguy set/get
    public void addbadguy(badguyinstansiated b){
        badguy = b;
    }
    
    public badguyinstansiated getbadguy(){
        return badguy;
    }
    
    //rps item set/get
    public void setrpsitem(rpsitem r){
        rps = r;
    }
    
    public rpsitem getrpsitem(){
        return rps;
    }
    
    public String getname(){
        return name;
    }
    
    //save load information
    public npcinstanciated(BufferedReader in,texturelibrary lib,gameinfocontrol gcont,worldinstanciated w,terraininstanciated t) throws Exception{
        super(0);
        String line = in.readLine();
        System.out.println("^^^^^^^^read npc^^^^^^^^^^");
        System.out.println(line);
        String[] list = line.split("[|]");
        primarykey = Integer.parseInt(list[0]);
        npctype = gcont.getnpc(list[1]);
        System.out.println(npctype);
        body = new bodyinstanciated(npctype.getbody());
        ai = new npcaiinstanciated(this,npctype.getai(),null);
        name = list[2];
        if(!list[3].equals("")){
            System.out.println("::::::::::::::::home load");
            System.out.println(list[3]);
            System.out.println(t);
            System.out.println(t.getstructure(Integer.parseInt(list[3])));
            home = t.getstructure(Integer.parseInt(list[3]));
        }
        badguy = w.getbadguy(list[4]);
        locx = Integer.parseInt(list[5]);
        locy = Integer.parseInt(list[6]);
        alive = Boolean.parseBoolean(list[7]);
        killedbyplayer = Boolean.parseBoolean(list[8]);
    }
    
    public void saveinformation(PrintWriter out){
        String badguystring = "";
        if(badguy != null){
            badguystring = badguy.getmodel().getname();
        }
        String homestring = "";
        if(home != null){
            homestring = ""+home.getprimarykey();
        }
        out.println(primarykey+"|"+npctype.getname()+"|"+name+"|"+homestring+"|"+badguystring+"|"+locx+"|"+locy+"|"+alive+"|"+killedbyplayer);
    }
}
