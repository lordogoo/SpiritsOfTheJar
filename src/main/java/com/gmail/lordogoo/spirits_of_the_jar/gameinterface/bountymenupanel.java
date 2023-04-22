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
 * Write a description of class bountymenupanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bountymenupanel extends gamepanel
{

    menucontrol cont;

    JButton profile;
    JButton conversations;
    JButton progress;
    JButton back;
    
    JLabel bountyl;
    
    DefaultListModel bountylistmodel;
    JList bountylist;
    JScrollPane bountyscroll;
    
    public bountymenupanel(String n,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(n,c,i,s,bi);
        cont = c;
        
        double size[][] = 
        {{TableLayout.FILL,.2,.2,.2,.2,TableLayout.FILL},
        {TableLayout.FILL,30,20,.8,TableLayout.FILL,1}};
        
        this.setdimensions(4,3);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"0,0,5,4");
    
        profile = new specialboarderbutton("profile",i.gettexture(4).getimage(),this,5,null);
        profile.addActionListener(new progclick());
        this.add(profile,"1,1");
        this.setComponentZOrder(profile,0);
        componentlist.add(profile);
        
        conversations = new specialboarderbutton("conversations",i.gettexture(4).getimage(),this,5,null);
        conversations.addActionListener(new convclick());
        this.add(conversations,"2,1");
        this.setComponentZOrder(conversations,0);
        componentlist.add(conversations);
        
        progress = new specialboarderbutton("progress",i.gettexture(4).getimage(),this,5,null);
        progress.setEnabled(false);
        this.add(progress,"3,1");
        this.setComponentZOrder(progress,0);
        componentlist.add(progress);
        
        back = progress = new specialboarderbutton("back",i.gettexture(4).getimage(),this,5,null);
        back.addActionListener(new backclick());
        this.add(back,"4,1");
        this.setComponentZOrder(back,0);
        componentlist.add(back);
        
        bountyl = new JLabel("Info on the evil spirits:");
        this.add(bountyl,"1,2,4,2");
        this.setComponentZOrder(bountyl,0);
        componentlist.add(bountyl);
        
        bountylistmodel = new DefaultListModel();
        bountylist = new JList(bountylistmodel);
        bountylist.setFixedCellWidth(64);
        bountylist.setFixedCellHeight(64);
        
        bountylist.setCellRenderer(new CustomCellRendererSelecteble());
        //bountylist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        //bountylist.setVisibleRowCount(-1);
        bountyscroll = new JScrollPane(bountylist);
        this.add(bountyscroll,"1,3,4,3");
        this.setComponentZOrder(bountyscroll,0);
        componentlist.add(bountyscroll);
        
    }
    
    public void switchto(menucontrol g){
        bountylistmodel.clear();
        /*
        for(int i = 0; i < g.numworld();i++){
            for(int j = 0; j < g.getworld(i).numroumor();j++){
                bountylistmodel.addElement(new badguyroumors(cont.getgamecontrol().getroumor(i)));
            }
        }
        */
        super.switchto(g);
    }
    
    private class convclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("journpanel");
        }    
    }
    
    private class progclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("propanel");
        }    
    }
    
    private class backclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("playpanel");
        }    
    }

    private class badguyroumors extends JPanel{
        
        roumorlistmodel r;
        
        badguyroumors(roumorlistmodel r){
            this.r = r;
        }
        
        public roumorlistmodel getroumorlist(){
            return r;
        }
        
        public void paintComponent(Graphics g) {
            if(r != null){
                
               if(r.getroumor(0).getknown()){
                   g.drawString("Name: "+r.getroumor(0).gettext()+"",5,15);
               }else{
                   g.drawString("Name: ?",5,15);
               }
                
               if(r.getroumor(1).getknown()){
                   g.drawString("Discription: "+r.getroumor(1).gettext()+"",5,30);
               }else{
                   g.drawString("Discription: ?",5,30);
               }
            
            }
        }
        
    }
    
}
