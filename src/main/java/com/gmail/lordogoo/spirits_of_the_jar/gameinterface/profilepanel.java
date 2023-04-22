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
 * Write a description of class profilepanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class profilepanel extends gamepanel
{

    menucontrol cont;

    JButton profile;
    JButton conversations;
    JButton progress;
    JButton back;
    
    JLabel currentbodyl;
    JLabel currentbody;
    JLabel characternamel;
    JLabel charactername;
    JLabel charactertype;
    JLabel characterfactionname;
        
    DefaultListModel<restrictiondisplay> restrictedmodel;
    backgroundlist restricted;
    JScrollPane restrictedscroll;
    
    JButton burncards;
    
    public profilepanel(String n,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(n,c,i,s,bi);
        cont = c;
        
        double size[][] = 
        {{TableLayout.FILL,.2,.2,.2,.2,TableLayout.FILL},
        {TableLayout.FILL,30,20,20,.8,30,TableLayout.FILL,1}};
        
        this.setdimensions(5,6);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"0,0,5,6");
        
        profile = new specialboarderbutton("profile",i.gettexture(4).getimage(),this,5,null);
        profile.setEnabled(false);
        this.add(profile,"1,1");
        this.setComponentZOrder(profile,0);
        componentlist.add(profile);
        
        conversations = new specialboarderbutton("conversations",i.gettexture(4).getimage(),this,5,null);
        conversations.addActionListener(new convclick());
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
        
        currentbodyl = new JLabel("bodytype:");
        this.add(currentbodyl,"1,2");
        this.setComponentZOrder(currentbodyl,0);
        componentlist.add(currentbodyl);
        
        currentbody = new JLabel();
        this.add(currentbody,"2,2");
        this.setComponentZOrder(currentbody,0);
        componentlist.add(currentbody);
        
        characternamel = new JLabel("character name:");
        this.add(characternamel,"1,3");
        this.setComponentZOrder(characternamel,0);
        componentlist.add(characternamel);
        
        
        charactername = new JLabel();
        this.add(charactername,"2,3");
        this.setComponentZOrder(charactername,0);
        componentlist.add(charactername);
        
        //JLabel charactertype;
        //JLabel characterfactionname;

        restrictedmodel = new DefaultListModel<restrictiondisplay>();
        restricted = new backgroundlist(restrictedmodel,null);
        restricted.setFixedCellHeight(20);
        restricted.setCellRenderer(new CustomCellRenderer());
        restrictedscroll = new  JScrollPane(restricted);
        this.add(restrictedscroll,"1,4,4,4");
        this.setComponentZOrder(restrictedscroll,0);
        componentlist.add(restrictedscroll);
        
        burncards = new specialboarderbutton("burn cards",i.gettexture(4).getimage(),this,5,null);
        burncards.addActionListener(new burnclick());
        this.add(burncards,"1,5,4,5");
        this.setComponentZOrder(burncards,0);
        componentlist.add(burncards);
        
    }
    
    public void switchto(menucontrol g){
        currentbody.setText(cont.getcurrentprofile().getcurrentbody().getmodel().getname());
        charactername.setText(cont.getcurrentprofile().getcurrentbody().getname());
        
        restrictedmodel.clear();
        bodyinstanciated bodytemp = cont.getcurrentprofile().getcurrentbody();
        for(int i = 0; i < bodytemp.getrestrictednum().size();i++){
            restrictedmodel.addElement(new restrictiondisplay(bodytemp.getrestrictedcards().elementAt(i), 
                                bodytemp.getrestrictednum().elementAt(i).intValue()));
        }
        
        super.switchto(g);
    }
    
    private class convclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("journpanel");
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
    
    private class burnclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("burnpanel");
        }    
    } 
    
    
    private class restrictiondisplay extends JPanel{
        int num;
        card c;
        
        public restrictiondisplay(card c,int n){
            num = n;
            this.c = c;
        }
        
        public void paintComponent(Graphics g) {
            g.drawString(c.getname()+" "+num,20,15);
        }
    }

}
