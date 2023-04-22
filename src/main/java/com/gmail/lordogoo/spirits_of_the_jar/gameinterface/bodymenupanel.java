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
 * Write a description of class bodymenupanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bodymenupanel extends gamepanel
{
    bodymenupanel thisref;
    gamecontrol game;
    
    JButton backtogamemenu,next,back,pick;
    JPanel display;
    
    Vector<bodydisplay> bodyref;
    int selectedvalue;
    
    worldinstanciated world;
    playermodel profile;
    
    public bodymenupanel(String name,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(name,c,i,s,bi);
        thisref = this;
        double size[][] = 
        {{TableLayout.FILL,20,50,.62,50,20,TableLayout.FILL},
        {TableLayout.FILL,20,30,.62,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(6,6);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,5,5");
        
        backtogamemenu = new specialboarderbutton("back to world selection",i.gettexture(4).getimage(),this,5,null);
        backtogamemenu.addActionListener(new gameclick());
        this.add(backtogamemenu,"2,2,4,2");
        this.setComponentZOrder(backtogamemenu,0);
        componentlist.add(backtogamemenu);
        
        JButton prev = new specialboarderbutton("<",i.gettexture(4).getimage(),this,5,null);
        prev.addActionListener(new prevclick());
        this.add(prev,"2,3");
        this.setComponentZOrder(prev,0);
        componentlist.add(prev);
        
        JButton next = new specialboarderbutton(">",i.gettexture(4).getimage(),this,5,null);
        next.addActionListener(new nextclick());
        this.add(next,"4,3");
        this.setComponentZOrder(next,0);
        componentlist.add(next);
        
        JButton pick = new specialboarderbutton("use this body",i.gettexture(4).getimage(),this,5,null);
        pick.addActionListener(new bodyclick());
        this.add(pick,"3,4");
        this.setComponentZOrder(pick,0);
        componentlist.add(pick);
        
        display = new JPanel();
        double displaysize[][] = {{TableLayout.FILL},{TableLayout.FILL}};
        display.setLayout(new TableLayout(displaysize));
        this.add(display,"3,3");
        this.setComponentZOrder(display,0);
        componentlist.add(display);
        
        
        bodyref = new Vector<bodydisplay>();
    }
    
    
    
    
    public void switchto(menucontrol g){
        cont = g;
        game = g.getcurrentprofile().getgame();
        world = game.getcurrentworld();
        profile = cont.getcurrentprofile();
        bodyref.clear();
        selectedvalue = 0;
        for(int i = 0; i < profile.numbody();i++){
            if(profile.getbody(i).getmodel().getoragin().getname().equals(world.getmodel().getname())){
                bodyref.addElement(new bodydisplay(profile.getbody(i)));
            }
        }
        
        display.add(bodyref.elementAt(selectedvalue),"0,0");
        super.switchto(g);
    }
    
    private class gameclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("worldpanel");
        }    
    }
    
    private class nextclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            display.removeAll();
            selectedvalue++;
            if(selectedvalue >= bodyref.size()){
                selectedvalue = 0;
            }
            display.add(bodyref.elementAt(selectedvalue),"0,0");
            display.revalidate();
            thisref.repaint();
        }    
    }
    
    private class prevclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            display.removeAll();
            selectedvalue--;
            if(selectedvalue < 0){
                selectedvalue = bodyref.size()-1;
            }
            display.add(bodyref.elementAt(selectedvalue),"0,0");
            display.revalidate();
            thisref.repaint();
        }    
    }
    
    private class bodyclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            bodydisplay temp = bodyref.elementAt(selectedvalue);
            cont.getcurrentprofile().setcurrentbody(temp.getbody());
            setupswitchpanel("playpanel");
        }    
    }
    
    private class bodydisplay extends JPanel{
    
        bodyinstanciated body;
        
        public bodydisplay(bodyinstanciated b){
            body = b;
        }
        
        public bodyinstanciated getbody(){
            return body;
        }
        
        public void paintComponent(Graphics g) {
            if(body != null){
               int imagewidth = 64;
               int imageheight = 64;
               //BufferedImage itemimage = world.getimage();
               g.setColor(Color.white);
               g.fillRect(0,0,this.getWidth(),this.getHeight());
               //g.drawImage(itemimage,(this.getWidth()-imagewidth)/2,(this.getHeight()-imageheight)/2,null);
               g.setColor(Color.black);
               g.drawString(body.getmodel().getname()+"",5,this.getHeight()-35);
            }
        }
    
    }

}
