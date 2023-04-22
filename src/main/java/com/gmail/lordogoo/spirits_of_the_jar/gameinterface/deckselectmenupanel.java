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

public class deckselectmenupanel extends gamepanel{

    deckselectmenupanel thisref;
    gamecontrol game;
    playermodel player;
    
    textpopup tpnew;
    textpopup tprename;
    
    JButton newdeck;
    JButton editdeck;
    JButton renamedeck;
    JButton deletedeck;
    JButton backtomenu;

    DefaultListModel decklistmodel;
    JList decklist;
    JScrollPane deckscroll;
    
    gamepanel returnpanel;
    
    public deckselectmenupanel(String n,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(n,c,i,s,bi);
        thisref = this;
        double size[][] = 
        {{TableLayout.FILL,20,.2,.6,20,TableLayout.FILL},
        {TableLayout.FILL,20,30,30,30,30,.8,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(5,9);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,4,8");
        
        newdeck = new specialboarderbutton("new deck",i.gettexture(4).getimage(),this,5,null);
        newdeck.addActionListener(new newclick());
        newdeck.addMouseListener(new clickdilimeter());
        this.add(newdeck,"2,2");
        this.setComponentZOrder(newdeck,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)newdeck);
        //componentlist.add(newdeck);
        
        editdeck = new specialboarderbutton("edit deck",i.gettexture(4).getimage(),this,5,null);
        editdeck.setEnabled(false);
        editdeck.addActionListener(new editclick());
        editdeck.addMouseListener(new clickdilimeter());
        this.add(editdeck,"2,3");
        this.setComponentZOrder(editdeck,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)editdeck);
        //componentlist.add(editdeck);
        
        renamedeck = new specialboarderbutton("rename deck",i.gettexture(4).getimage(),this,5,null);
        renamedeck.setEnabled(false);
        renamedeck.addActionListener(new renameclick());
        renamedeck.addMouseListener(new clickdilimeter());
        this.add(renamedeck,"2,4");
        this.setComponentZOrder(renamedeck,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)renamedeck);
        //componentlist.add(renamedeck);
        
        deletedeck = new specialboarderbutton("delete deck",i.gettexture(4).getimage(),this,5,null);
        deletedeck.setEnabled(false);
        deletedeck.addActionListener(new deleteclick());
        deletedeck.addMouseListener(new clickdilimeter());
        this.add(deletedeck,"2,5");
        this.setComponentZOrder(deletedeck,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)deletedeck);
        //componentlist.add(deletedeck);
              
        decklistmodel = new DefaultListModel();
        decklist = new JList(decklistmodel);
        decklist.addListSelectionListener(new decklistclick());
        decklist.addMouseListener(new clickdilimeter());
        deckscroll = new specialscrollpane(decklist);
        deckscroll.setOpaque(false);
        this.add(deckscroll,"3,2,3,6");
        this.setComponentZOrder(deckscroll,0);
        new specialscrollpanecontainer(componentlist,containerlist,(specialscrollpane)deckscroll);
        //componentlist.add(decklist);
        
        backtomenu = new specialboarderbutton("back to main menu",i.gettexture(4).getimage(),this,5,null);
        backtomenu.addActionListener(new menuclick());
        backtomenu.addMouseListener(new clickdilimeter());
        this.add(backtomenu,"2,7,3,7");
        this.setComponentZOrder(backtomenu,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)backtomenu);
        //componentlist.add(backtomenu);
        
        acceptclick ac1 = new acceptclick();
        cancelclick cc1 = new cancelclick();   
        tpnew = new textpopup(thisref,textureref,"Enter the name of the deck you wish to create.",ac1,cc1);
        ac1.addtextfield(tpnew.gettextfield());
        this.addpopup(tpnew);
        
        renameacceptclick ac2 = new renameacceptclick();
        cancelclick cc2 = new cancelclick();
        textpopup tprename = new textpopup(thisref,textureref,"Enter the new name of your deck.",ac2,cc2);
        ac2.addtextfield(tprename.gettextfield());
        this.addpopup(tprename);
        
    }
    
    public void setreturnpanel(gamepanel p){
        returnpanel = p;
    }
    
    public gamepanel getreturnpanel(){
        return returnpanel;
    }
    
    public void disableall(){
        newdeck.setEnabled(false);
        editdeck.setEnabled(false);
        renamedeck.setEnabled(false);
        deletedeck.setEnabled(false);
        decklist.setEnabled(false);
        backtomenu.setEnabled(false);
    }
    
    public void enableall(){
        newdeck.setEnabled(true);
        decklist.setEnabled(true);
        backtomenu.setEnabled(true);
        enableifselected();
    }
    
    public void enableifselected(){
        if(decklist.getSelectedIndex() != -1){
            editdeck.setEnabled(true);
            renamedeck.setEnabled(true);
            deletedeck.setEnabled(true);
        }
    }
    
    public void loaddecks(){
        decklistmodel.clear();
        for(int i = 0; i < player.numdeck();i++){
            decklistmodel.addElement(player.getdeck(i).getname());
        }
    }
    
    public void switchto(menucontrol g){
        game = g.getcurrentprofile().getgame();
        player = game.getplayer();
        loaddecks();
        editdeck.setEnabled(false);
        renamedeck.setEnabled(false);
        deletedeck.setEnabled(false);
        if(returnpanel != null){
            backtomenu.setText("back to the game");
        }else{
            backtomenu.setText("back to world");
        }
        
        super.switchto(g);
    }
    
    public void switchaway(){
        returnpanel = null;
    }
    
    private class clickdilimeter implements MouseListener{
    
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
            clickinteruptanimation(e);
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }
    
    }
    
    private class newclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
           soundref.playsoundeffect(0);
           disableall();
           addpopupdisplay(tpnew);
        }    
    }
    
    private class editclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            game.setcurrentdeck(player.getdeck(decklist.getSelectedIndex()));
            setupswitchpanel("deckpanel");
        }    
    }
    
    private class renameclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            disableall();
            addpopupdisplay(tprename);
        }    
    }
    
    private class deleteclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            player.removedeck(decklist.getSelectedIndex());
            loaddecks();
            editdeck.setEnabled(false);
            renamedeck.setEnabled(false);
            deletedeck.setEnabled(false);
        }    
    }
    
    private class menuclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            if(returnpanel != null){
                setupswitchpanel(returnpanel.getname());
            }else{
                cont.switchpanel("gamepanel");
            }
        }    
    }

    private class decklistclick implements ListSelectionListener
    {    
        public void valueChanged(ListSelectionEvent e){
            if(decklist.getSelectedValue() != null){
                enableifselected();
            }
        }
    }
    
    private class acceptclick implements ActionListener{
        
        JTextField name;
        
        public void addtextfield(JTextField n){
            name = n;
        }
        
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);

            thisref.removepopupdisplay();
            deckmodel newdeck = new deckmodel(name.getText());
            player.adddeck(newdeck);
            loaddecks();
            game.setcurrentdeck(newdeck);
            if(!tutorialrunning){
                enableall();
                setupswitchpanel("deckpanel");
            }
        }    
    }
    
    private class renameacceptclick implements ActionListener{
        
        JTextField name;
        
        public void addtextfield(JTextField n){
            name = n;
        }
        
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            thisref.removepopupdisplay();
            player.renamedeck(decklist.getSelectedIndex(),name.getText());
            loaddecks();
            enableall();
        }    
    }
    
    private class cancelclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            thisref.removepopupdisplay();
            enableall();
        }    
    }
    
   

}
