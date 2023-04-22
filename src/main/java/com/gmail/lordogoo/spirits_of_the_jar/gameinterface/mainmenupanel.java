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
public class mainmenupanel extends gamepanel
{


    JLabel licon,lprofile;

    JButton profile,backgame,newgame,tutorialgame,savegame,loadgame,quitgame;
    boolean begin;
    
    public mainmenupanel(String name,menucontrol c,texturelibrary i,soundlibrary s,int bi){
        super(name,c,i,s,bi);
        cont = c;
        begin = true;
        
        double size[][] = 
        {{TableLayout.FILL,20,.5,20,TableLayout.FILL},
        {TableLayout.FILL,20,227,30,30,30,30,30,30,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(4,11);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,3,10");

        licon = new speciallabel();
        licon.setIcon(new ImageIcon(i.gameicon));
        licon.setHorizontalAlignment(JLabel.CENTER);
        this.add(licon,"2,2");
        this.setComponentZOrder(licon,0);
        new speciallabelcontainer(componentlist,containerlist,(speciallabel)licon);
        
        profile = new specialboarderbutton("select profile",i.gettexture(4).getimage(),this,5,null);
        profile.addActionListener(new profileclick());
        this.add(profile,"2,3");
        this.setComponentZOrder(profile,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)profile);
        
        //JLabel that tells what profile is playing
        lprofile = new speciallabel();
        this.add(lprofile,"2,4");
        this.setComponentZOrder(lprofile,0);
        new speciallabelcontainer(componentlist,containerlist,(speciallabel)lprofile);
        //componentlist.add(lprofile);
        
        //current game
        backgame = new specialboarderbutton("Continue campain",i.gettexture(4).getimage(),this,5,null);
        backgame.addActionListener(new backgameclick());
        this.add(backgame,"2,5");
        this.setComponentZOrder(backgame,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)backgame);
        
        //can stay
        newgame = new specialboarderbutton("New Game",i.gettexture(4).getimage(),this,5,null);
        newgame.addActionListener(new newgameclick());
        this.add(newgame,"2,6");
        this.setComponentZOrder(newgame,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)newgame);
        
        /*
        //dont need
        tutorialgame = new specialboarderbutton("Tutorial Game",i.gettexture(4),this,5,null);
        this.add(tutorialgame,"2,7");
        this.setComponentZOrder(tutorialgame,0);
        componentlist.add(tutorialgame);
        
        //dont need
        savegame = new specialboarderbutton("Save Game",i.gettexture(4),this,5,null);
        savegame.addActionListener(new savegameclick());
        this.add(savegame,"2,4");
        this.setComponentZOrder(savegame,0);
        componentlist.add(savegame);
        
        //dont nee
        loadgame = new specialboarderbutton("Load Game",i.gettexture(4),this,5,null);
        loadgame.addActionListener(new loadgameclick());
        this.add(loadgame,"2,5");
        this.setComponentZOrder(loadgame,0);
        componentlist.add(loadgame);
        */
       
        quitgame = new specialboarderbutton("Quit Game",i.gettexture(4).getimage(),this,5,null);
        quitgame.addActionListener(new quitgameclick());
        this.add(quitgame,"2,9");
        this.setComponentZOrder(quitgame,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)quitgame);
        
        initalsetup();
        newgame.setEnabled(false);
    }
    
    public void switchto(menucontrol g){
        System.out.println();
        initdisplay();
        if(g.getcurrentprofile() != null){
            newgame.setEnabled(true);
            System.out.println("()()()()GAME LOADED "+g.getcurrentprofile().getgame());
            if(g.getcurrentprofile().getgame() != null){
                activategame();
            }else{
                initalsetup();
            }
        }else{
            backgame.setVisible(false);
            newgame.setVisible(false);
        }
        
        super.switchto(g);
    }
    
    public void switchaway(menucontrol g){
    }
    
    public void initdisplay(){
        if(cont.getcurrentprofile() != null){
            lprofile.setText("current profile: "+cont.getcurrentprofile().getname());
            newgame.setEnabled(true);
            if(cont.getcurrentprofile().getgame() != null){
                activategame();
            }else{
                initalsetup();
            }
        }else{
            lprofile.setText("");
            newgame.setEnabled(false);
            initalsetup();
        }
    }
    
    
    public void initalsetup(){
        backgame.setEnabled(false);
    }
    
    public void activategame(){
        backgame.setEnabled(true);        
    }
    
    
    private class newgameclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            if(cont.getcurrentprofile().getgame() == null){
                cont.newgame();
                setupswitchpanel("nexuspanel");
            }else{
                thisref.disableall();
                newgameconfirm ac = new newgameconfirm();
                cancelconfirm cc = new cancelconfirm();
           
                labelpopup tp = new labelpopup(textureref,"<html>Are you sure you wish to create a new game."+
                                               "<br>All your progress will be lost."+
                                               "<br>This action will not affect your collection.",
                                        ac,cc);
                addpopupdisplay(tp);
            }
        }    
    }
    
    private class newgameconfirm implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            cont.newgame();
            thisref.removepopupdisplay();
            thisref.enableall();
            setupswitchpanel("nexuspanel");

        }    
    }
    
    private class cancelconfirm implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            thisref.removepopupdisplay();
            thisref.enableall();
        }    
    }
    
    
    private class backgameclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            setupswitchpanel("nexuspanel");
        }    
    }
    
    private class profileclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            setupswitchpanel("profpanel");
        }    
    }
    
    private class savegameclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
        }    
    }
    
    private class loadgameclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
        }    
    }
    
    private class quitgameclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            cont.setrunning(false);
            System.exit(0);
        }    
    }

}
