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
public class showcardsmenupanel extends gamepanel
{
    menucontrol cont;
    gamecontrol game;
    showcardsmenupanel thisref;
    
    JButton done;
    
    JLabel message;
    JLabel cashl;
    JLabel cash;
    JLabel cardl;
    
    JScrollPane cardlistpane;
    DefaultListModel cardlistmodel;
    JList cardlist;
    
    trigger nexttrigger;
    
    public showcardsmenupanel(String n,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(n,c,i,s,bi);
        thisref = this;
        double size[][] = 
        {{TableLayout.FILL,20,.3,.2,.3,20,TableLayout.FILL},
        {TableLayout.FILL,20,20,20,20,20,.6,20,20,TableLayout.FILL,1}};
        
        this.setdimensions(7,10);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        specialboarderpanel panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,5,8");
        
        message = new JLabel("");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(message,"2,2,4,2");
        this.setComponentZOrder(message,0);
        
        cashl = new JLabel("cash received:");
        this.add(cashl,"2,3,4,3");
        this.setComponentZOrder(cashl,0);
        
        cash = new JLabel("");
        this.add(cash,"2,4,4,4");
        this.setComponentZOrder(cash,0);
        
        cardl = new JLabel("cards received");
        this.add(cardl,"2,5,4,5");
        this.setComponentZOrder(cardl,0);
        
        cardlistmodel = new DefaultListModel();
        cardlist = new JList(cardlistmodel);
        cardlist.setCellRenderer(new CustomCellRenderer());
        cardlist.setFixedCellHeight(64);
        cardlist.setFixedCellWidth(64);
        cardlistpane = new JScrollPane(cardlist); 
        this.add(cardlistpane,"2,6,4,6");
        this.setComponentZOrder(cardlistpane,0);
        
        done = new JButton("done");
        done.addActionListener(new doneclick());
        this.add(done,"2,7,4,7");
        this.setComponentZOrder(done,0);
    }
    
    public void switchto(menucontrol g){
        cardlistmodel.clear();
        cont = g;
        game = g.getgamecontrol();
        Vector<card> list = g.getcurrentprofile().getnewcards();
        for(int i = 0; i < list.size();i++){
            cardlistmodel.addElement(new cardwrapper(list.elementAt(i)));
        }    
    }
    
    public void setmessage(String m){
        message.setText(m);
    }
    
    public void setcash(int c){
        cash.setText(""+c);
    }
    
    public void setnexttrigger(trigger t){
        nexttrigger = t;
    }
    
    private class doneclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            cont.getcurrentprofile().clearnewcards();
            if(nexttrigger!=null){
                nexttrigger.trigger(thisref);
            }else{
                dialogmodel diag = cont.getgamecontrol().getcurrentdialog();
                cont.switchpanel(diag.getreturnpanel().getname());
            }
        }    
    }
    
    
    private class cardwrapper extends JPanel{
        
        card c;
        
        public cardwrapper(card c){
            this.c = c;
        }
        
        public card getcard(){
            return c;
        }
        
        public void paintComponent(Graphics g) {
            if(c != null){
               int imagewidth = 64;
               int imageheight = 64;
               BufferedImage itemimage = c.getsymbol().getimage();
               g.drawImage(itemimage,(this.getWidth()-imagewidth)/2,(this.getHeight()-imageheight)/2,64,64,null);
               g.drawString(c.getname()+"",5,this.getHeight()-35);
            }
        }    
    }
    
}
