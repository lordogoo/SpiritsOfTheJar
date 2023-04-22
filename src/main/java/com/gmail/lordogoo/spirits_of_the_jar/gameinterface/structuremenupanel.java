package com.gmail.lordogoo.spirits_of_the_jar.gameinterface;
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
 * Write a description of class structuremenupanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class structuremenupanel extends gamepanel
{
    
    DefaultListModel npclistmodel;
    JList npclist;
    JButton back;
    JButton shop;

    public structuremenupanel(String n,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(n,c,i,s,bi);
        
        double size[][] = 
        {{TableLayout.FILL,20,.9,20,TableLayout.FILL},
        {TableLayout.FILL,20,30,.9,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(4,6);
        
        this.setLayout(new TableLayout(size));
       
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,3,5");
        
        back = new specialboarderbutton("Leave town",i.gettexture(4).getimage(),this,5,null);
        back.addActionListener(new backclick());
        this.add(back,"2,2");
        this.setComponentZOrder(back,0);
        componentlist.add(back);
        
        npclistmodel = new DefaultListModel();
        npclist = new JList(npclistmodel);
        this.add(npclist,"2,3");
        this.setComponentZOrder(npclist,0);
        componentlist.add(npclist);
        
        shop = new specialboarderbutton("shop",i.gettexture(4).getimage(),this,5,null);
        shop.addActionListener(new shopclick());
        this.add(shop,"2,4");
        this.setComponentZOrder(shop,0);
        componentlist.add(shop);

    }
    
    public void switchto(menucontrol g){
        
         super.switchto(g);
    }
    
    public void switchaway(menucontrol g){
        autosavetrigger autosave = new autosavetrigger(cont);
        autosave.trigger(this); 
    }
    
    private class backclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("playpanel");
        }    
    }
    
    private class shopclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("shoppanel");
        }    
    }

}
