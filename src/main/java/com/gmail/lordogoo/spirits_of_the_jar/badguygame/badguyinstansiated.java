package com.gmail.lordogoo.spirits_of_the_jar.badguygame;
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
public class badguyinstansiated extends modelobject
{

    boolean defeated;
    badguymodel badguy;
    roumorlistmodel roumorlist;
    
    public badguyinstansiated(int p,badguymodel b)
    {
        super(p);
        badguy = b;
        defeated = false;
        
        roumorlist = new roumorlistmodel();
    }
    
    public void setdefeated(boolean d){
        defeated = d;
    }
    
    public boolean getdefeadted(){
        return defeated;
    }
    
    public badguymodel getmodel(){
        return badguy;
    }
    
    public void addroumor(roumorlistmodel r){
        roumorlist = r;
    }
    
    public roumormodel getroumor(int i){
        return roumorlist.getroumor(i);
    }
    
    //save load information
    public badguyinstansiated(BufferedReader in,texturelibrary lib,gameinfocontrol gcont) throws Exception{
        super(0);
        System.out.println("read badguy");
        String line = in.readLine();
        System.out.println(line);
        String[] list = line.split("[|]");
        primarykey = Integer.parseInt(list[0]);
        badguy = gcont.getbadguy(list[1]);
        defeated = Boolean.parseBoolean(list[2]);
        int roumorlistsize = Integer.parseInt(list[3]);
        
        this.roumorlist = new roumorlistmodel();
        for(int i = 0; i < roumorlistsize;i++){
            this.roumorlist.addroumor(new roumormodel(this,in,lib,gcont),i);
        }
        
    }
        
    public void saveinformation(PrintWriter out){
        out.println(primarykey+"|"+badguy.getname()+"|"+defeated+"|"+roumorlist.size());
        
        for(int i = 0; i < roumorlist.size();i++){
            roumorlist.getroumor(i).saveinformation(out);
        }
    }
    
}
