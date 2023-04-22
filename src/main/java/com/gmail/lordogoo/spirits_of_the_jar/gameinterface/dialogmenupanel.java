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
public class dialogmenupanel extends gamepanel
{
    dialogmenupanel thisref;

    JTextField text;
    JButton next;
    gamecontrol game;
    worldinstanciated world;
    
    trigger nexttrigger;
    dialogmodel diag;

    public dialogmenupanel(String n,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(n,c,i,s,bi);
        double size[][] = 
        {{TableLayout.FILL,20,.62,20,TableLayout.FILL},
        {TableLayout.FILL,20,.62,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(5,6);
        
        this.setLayout(new TableLayout(size));
        
        specialboarderpanel panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,3,4");
        
        
        
        text = new JTextField("textbox");
        text.setEnabled(false);
        text.setSize(100,80);
        this.add(text,"2,2");
        this.setComponentZOrder(text,0);
        
        next = new specialboarderbutton("next",i.gettexture(4).getimage(),this,5,null);
        next.addActionListener(new nextclick());
        this.add(next,"2,3");
        this.setComponentZOrder(next,0);
    }
    
    
    
    public void switchto(gamecontrol g){
        game = g;
        world = g.getcurrentworld();
        diag = g.getcurrentdialog();
        text.setText(diag.gettext());
    }
    
    public void settext(String t){
        text.setText(t);
    }
    
    public void setnexttrigger(trigger nt){
        nexttrigger = nt;
    }
    
    private class nextclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            
            if(nexttrigger != null){
                nexttrigger.trigger(thisref);
            }else{
                //return to currentpanel
                cont.getgamecontrol().setcurrentdialog(null);
                cont.switchpanel(diag.getreturnpanel().getname());
            }
        }    
    }
    
}
