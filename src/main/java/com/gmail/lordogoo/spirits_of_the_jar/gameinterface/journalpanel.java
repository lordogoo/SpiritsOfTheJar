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
public class journalpanel extends gamepanel
{

    menucontrol cont;

    JButton profile;
    JButton conversations;
    JButton progress;
    JButton back;


    public journalpanel(String n,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(n,c,i,s,bi);
        cont = c;
        
        double size[][] = 
        {{TableLayout.FILL,.2,.2,.2,.2,TableLayout.FILL},
        {TableLayout.FILL,30,.8,TableLayout.FILL,1}};
        
        this.setdimensions(4,3);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"0,0,5,3");
        
        profile = new specialboarderbutton("profile",i.gettexture(4).getimage(),this,5,null);
        profile.addActionListener(new profclick());
        this.add(profile,"1,1");
        this.setComponentZOrder(profile,0);
        componentlist.add(profile);
        
        conversations = new specialboarderbutton("conversations",i.gettexture(4).getimage(),this,5,null);
        //conversations.addActionListener(new convclick());
        conversations.setEnabled(false);
        this.add(conversations,"2,1");
        this.setComponentZOrder(conversations,0);
        componentlist.add(conversations);
        
        progress = new specialboarderbutton("progress",i.gettexture(4).getimage(),this,5,null);
        progress.addActionListener(new progclick());
        this.add(progress,"3,1");
        this.setComponentZOrder(progress,0);
        componentlist.add(progress);
        
        back = progress = new specialboarderbutton("back",i.gettexture(4).getimage(),this,5,null);
        back.addActionListener(new backclick());
        this.add(back,"4,1");
        this.setComponentZOrder(back,0);
        componentlist.add(back);
        
    }

    
    public void switchto(menucontrol g){

        super.switchto(g);
    }
    
    private class profclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("propanel");
        }    
    }
    
    private class progclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("bountpanel");
        }    
    }
    
    private class backclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("playpanel");
        }    
    } 
}
