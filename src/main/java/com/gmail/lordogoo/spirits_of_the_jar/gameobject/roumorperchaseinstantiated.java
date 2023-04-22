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
/**
 * Write a description of class roumorperchaseinstantiated here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class roumorperchaseinstantiated extends perchaseinstantiated
{

    roumormodel roumor;

    public roumorperchaseinstantiated(roumormodel r,perchacemodel p)
    {
        super(p);
        type = "roumor";
        roumor = r;
    }
    
    public roumormodel getroumor(){
        return roumor;
    }

    //save load information
    public roumorperchaseinstantiated(worldinstanciated w,BufferedReader in,texturelibrary lib,gameinfocontrol gcont) throws Exception{
        super(null);
        String line = in.readLine();
        String[] list = line.split("[|]");
        type = "roumor";
        roumor = w.getbadguy(list[0]).getroumor(Integer.parseInt(list[1]));
        perchase = new perchacemodel("roumor",0,Integer.parseInt(list[2]),Integer.parseInt(list[3]),null,new shoprumortrigger(gcont.getmenucontrol(),roumor));
    }
    
    public void saveinformation(PrintWriter out){
        //String t,int in,int c,int w,imagewithindex i,shoptrigger e
        //new perchacemodel("roumor",0,50,50,null,new shoprumortrigger(cont,temproumor.getroumor(j)))
        //getcost()
        out.println(type);
        out.println(roumor.getbadguy().getmodel().getname()+"|"+roumor.getslotnum()+"|"+perchase.getcost()+"|"+perchase.getweight());
        
        
    }
    
    
    
    
}
