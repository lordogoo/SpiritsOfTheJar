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
 * Write a description of class profilemenupanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class profilemenupanel extends gamepanel
{

    menucontrol menuref;

    JLabel info;
    DefaultListModel playermodel; 
    JList playerlist;
    JScrollPane playerscroll;
    
    JButton newplayer;
    JButton deleteplayer;
    JButton selectplayer;

    
    playermodel currentprofile;
    
    public profilemenupanel(String name,menucontrol c,texturelibrary i,soundlibrary s,int bi){
        super(name,c,i,s,bi);
        thisref = this;
        menuref = c;
        
        double size[][] = 
        {{TableLayout.FILL,20,.2,.6,20,TableLayout.FILL},
        {TableLayout.FILL,20,30,30,.7,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(5,7);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,4,6");
        
        playermodel = new DefaultListModel();
        playerlist = new JList(playermodel);
        playerlist.addListSelectionListener(new profilelistclick());
        playerscroll = new specialscrollpane(playerlist);
        playerscroll.setOpaque(false);
        this.add(playerscroll,"3,2,3,4");
        this.setComponentZOrder(playerscroll,0);
        new specialscrollpanecontainer(componentlist,containerlist,(specialscrollpane)playerscroll);
        //componentlist.add(playerscroll);
        
        newplayer = new specialboarderbutton("new profile",i.gettexture(4).getimage(),this,5,null);
        newplayer.addActionListener(new newclick());
        this.add(newplayer,"2,2");
        this.setComponentZOrder(newplayer,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)newplayer);
        
        deleteplayer = new specialboarderbutton("delete profile",i.gettexture(4).getimage(),this,5,null);
        deleteplayer.addActionListener(new deleteclick());
        this.add(deleteplayer,"2,3");
        this.setComponentZOrder(deleteplayer,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)deleteplayer);
        
        
        selectplayer = new specialboarderbutton("select profile",i.gettexture(4).getimage(),this,5,null);
        selectplayer.addActionListener(new selectclick());
        this.add(selectplayer,"2,5,3,5");
        this.setComponentZOrder(selectplayer,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)selectplayer);
    }
    
    public void switchto(menucontrol g){
        //load profiles
        loadprofilenames();
        
        //if a profile is already loaded select it
        if(cont.getcurrentprofile() != null){
            for(int i = 0; i < playermodel.size();i++){
                if(playermodel.elementAt(i).toString().equals(cont.getcurrentprofile().getname())){
                    playerlist.setSelectedIndex(i);
                }
            }
        }
        
        enableall();
        super.switchto(g);
    }
        
    public void loadprofilenames(){
        playermodel.clear();
        File dir =  new File("profiles");
        if(!dir.exists()){
            dir.mkdir();
        }else{
            
            String[] list = dir.list();
            int numfiles = list.length;
            for(int i = 0; i < numfiles;i++){
                int index = list[i].lastIndexOf('.'); 
                String filename = list[i].substring(0,index);
                playermodel.addElement(new  profiledisplay(filename));
                 
            }
        }
    }
    
    public void specialconditionsonenable(){
        if(playerlist.getSelectedIndex()!=-1){
              deleteplayer.setEnabled(true);
              selectplayer.setEnabled(true);
        }else{
              deleteplayer.setEnabled(false);
              selectplayer.setEnabled(false);
        } 
    }
    
    private class newclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
           soundref.playsoundeffect(0);
           acceptclick ac = new acceptclick();
           cancelclick cc = new cancelclick();
           
           textpopup tp = new textpopup(thisref,textureref,"enter the name of the profile you wish to create",
                                        ac,cc);
           ac.addtextfield(tp.gettextfield());
           addpopupdisplay(tp);
        }    
    }
    
    private class deleteclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            profiledisplay temp = (profiledisplay)playerlist.getSelectedValue();
            System.out.println("delete "+temp.toString());
            File f = new File("profiles/"+temp.toString()+".prof");
            f.delete();
            loadprofilenames();
            deleteplayer.setEnabled(false);
            selectplayer.setEnabled(false);
        }    
    }
    
    private class selectclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            profiledisplay temp = (profiledisplay)playerlist.getSelectedValue();
            playermodel playertemp = menuref.loadprofile(temp.toString());
            menuref.setcurrentprofile(playertemp);
            menuref.getinitfile().saveinformation();
            setupswitchpanel("menupanel");
        }    
    }
    
    private class profilelistclick implements ListSelectionListener
    {    
        public void valueChanged(ListSelectionEvent e){
            
            if(playerlist.getSelectedIndex()!=-1){
                deleteplayer.setEnabled(true);
                selectplayer.setEnabled(true);
            }
        }
    }
    
    /*************************
     * popup action listeners
     */
    private class acceptclick implements ActionListener{
        
        JTextField name;
        
        public void addtextfield(JTextField n){
            name = n;
        }
        
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            thisref.removepopupdisplay();
            thisref.enableall();
            currentprofile = new playermodel(name.getText());
            menuref.saveprofile(currentprofile,name.getText());
            loadprofilenames();
                
            //TODO highlight added element
            for(int i = 0; i <  playermodel.size();i++){
                    
                if(((profiledisplay)playermodel.elementAt(i)).toString().equals(name.getText())){
                    playerlist.setSelectedIndex(i);
                }
                    
            }
        }    
    }
    
    private class cancelclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
                //popup.hide();
                soundref.playsoundeffect(0);
                thisref.removepopupdisplay();
                thisref.enableall();
        }    
    }
    
    
    
    /****************************
     * profile display
     */
    public class profiledisplay{
        
        String name;
        
        public profiledisplay(String n){
            name = n;
        }
        
        public String toString(){
            return name;
        }
    }    
    
}
