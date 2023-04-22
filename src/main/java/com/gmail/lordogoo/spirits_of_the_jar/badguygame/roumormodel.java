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

public class roumormodel
{
    
    int slotnum;
    boolean known;
    String text;    
    imagewithindex image;
    badguyinstansiated badguy;

    public roumormodel(int s ,String text,badguyinstansiated b)
    {
        badguy = b;
        slotnum = s;
        known = false;
        this.text = text;
    }

    public roumormodel(int s,imagewithindex i,badguyinstansiated b)
    {
        badguy = b;
        image = i;
        known = false;
    }
    
    public String gettext(){
        return text;
    }
    
    public int getslotnum(){
        return slotnum;
    }
    
    public imagewithindex getimage(){
        return image;
    }
    
    public boolean getknown(){
        return known;
    }
     
    public void setknown(boolean t){
        known = t;
    }
    
    public badguyinstansiated getbadguy(){
        return badguy;
    }
    
    //save load functions
    public roumormodel(badguyinstansiated b,BufferedReader in,texturelibrary lib,gameinfocontrol gcont) throws Exception{
        String line = in.readLine();
        String[] list = line.split("[|]");
        known = Boolean.parseBoolean(list[1]);
        text = list[2];
        if(list[3].equals("")){
            image = null;
        }else{
            image = lib.getnpcwithindex(Integer.parseInt(list[3]));
        }
        slotnum = Integer.parseInt(list[4]);
        
        badguy = b;
    }
    
    
    public void saveinformation(PrintWriter out){
        String imagestring = "";
        if(image != null){
            imagestring = image.getindex()+"";
        }
        out.println(badguy.getmodel().getname()+"|"+known+"|"+text+"|"+imagestring+"|"+slotnum);
    }
    
}
