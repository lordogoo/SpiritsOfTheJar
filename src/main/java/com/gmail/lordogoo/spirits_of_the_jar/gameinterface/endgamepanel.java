package com.gmail.lordogoo.spirits_of_the_jar.gameinterface;
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
 * Write a description of class endgamepanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class  endgamepanel extends gamepanel
{

    gamepanel thisref;
    menucontrol menuref;

    public  endgamepanel(String name,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(name,c,i,s,bi);
        thisref = this;
        menuref = c;
        
        double size[][] = 
        {{TableLayout.FILL,20,.8,20,TableLayout.FILL},
        {TableLayout.FILL,20,.8,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(4,4);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,3,4");
        
        JLabel euli = new JLabel("");
        this.add(euli,"2,2,2,2");
        this.setComponentZOrder(euli,0);
        componentlist.add(euli);
        
        JButton next = new JButton("next");
        next.addActionListener(new selectclick());
        this.add(next,"2,3");
        this.setComponentZOrder(next,0);
        componentlist.add(next);
    }

    private class selectclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("menupanel");
        }    
    }
    
}
