package com.gmail.lordogoo.spirits_of_the_jar.gameinterface;
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
public class worldmenupanel extends gamepanel
{
    worldmenupanel thisref;
    gamecontrol game;
    
    
    JButton backtogamemenu,next,back,pick;
    JButton veiwdecks;
    JPanel display;
    
    Vector<worlddisplay> worldref;
    int selectedvalue;
    
    public worldmenupanel(String name,menucontrol c,texturelibrary i,soundlibrary s,int bi)
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
        
        backtogamemenu = new specialboarderbutton("back to main menu",i.gettexture(4).getimage(),this,5,null);
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
        
        JButton pick = new specialboarderbutton("go to this world",i.gettexture(4).getimage(),this,5,null);
        pick.addActionListener(new worldclick());
        this.add(pick,"3,4");
        this.setComponentZOrder(pick,0);
        componentlist.add(pick);
        
        display = new JPanel();
        double displaysize[][] = {{TableLayout.FILL},{TableLayout.FILL}};
        display.setLayout(new TableLayout(displaysize));
        this.add(display,"3,3");
        this.setComponentZOrder(display,0);
        componentlist.add(display);
        
        worldref = new Vector<worlddisplay>();
        
        this.repaint();
    }
    
    
    public void switchto(menucontrol g){
        game = g.getcurrentprofile().getgame();
        worldref.clear();
        selectedvalue = 0;
        for(int i = 0; i < game.numworld();i++){ 
            worldref.addElement(new worlddisplay(game.getworld(i)));
        }
        display.add(worldref.elementAt(selectedvalue),"0,0");

        super.switchto(g);
    }
    
    public void switchaway(gamecontrol g){
    }
    
    private class gameclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("menupanel");
        }    
    }
    
    private class nextclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            display.removeAll();
            selectedvalue++;
            if(selectedvalue >= worldref.size()){
                selectedvalue = 0;
            }
            display.add(worldref.elementAt(selectedvalue),"0,0");
            display.revalidate();
            thisref.repaint();
        }    
    }
    
    private class prevclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            display.removeAll();
            selectedvalue--;
            if(selectedvalue < 0){
                selectedvalue = worldref.size()-1;
            }
            display.add(worldref.elementAt(selectedvalue),"0,0");
            display.revalidate();
            thisref.repaint();
        }    
    }
    
    private class worldclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
                worlddisplay temp = worldref.elementAt(selectedvalue);
                game.setcurrentworld(temp.getworld());
                setupswitchpanel("bodypanel");
        }    
    }
    
    private class worlddisplay extends JPanel{
    
        worldinstanciated worldref;
        //planetanimation pa;
        
        
        public worlddisplay(worldinstanciated w){
            worldref = w;
            this.setLayout(null);
            //pa = new planetanimation(display,worldref.getimage());
            //pa.setSize(1000,98);
            //this.add(pa);
        }
        
        public worldinstanciated getworld(){
            return worldref;
        }
        
        public void stopanimation(){
            //pa.stop();
        }
        
        public void paintComponent(Graphics g) {
            if(worldref != null){
               int imagewidth = 64;
               int imageheight = 64;
               //BufferedImage itemimage = pa.getimage();

               Color defaultcolor = g.getColor();
               
               g.setColor(Color.black);
               g.fillRect(0,0,this.getWidth(),this.getHeight());
               
               //g.drawImage(itemimage,(this.getWidth()-imagewidth)/2,(this.getHeight()-imageheight)/2,null);
                              
               g.setColor(Color.white);
               g.drawString(worldref.getmodel().getname()+"",5,this.getHeight()-35);
 
               g.setColor(defaultcolor);
            }
        }
    
    }
    
}
