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
public class prepairmatchmenupanel extends gamepanel
{
    gamecontrol game;
    playermodel player;
    bodyinstanciated body;
    matchmodel match;
    prepairmatchmenupanel thisref;
    
    JLabel info;
    DefaultListModel deckmodel; 
    JList decklist;
    JScrollPane deckscroll;
    
    JLabel victorytext;
    
    JButton deckmanager;
    JButton retreat;
    JButton fight;
    
    Vector<deckmodel> deckmodellist;
    
    public prepairmatchmenupanel(String name,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(name,c,i,s,bi);
        thisref = this;
        
        double size[][] = 
        {{TableLayout.FILL,20,.8,20,TableLayout.FILL},
        {TableLayout.FILL,20,20,20,20,.4,30,30,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(4,10);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        deckmodellist = new Vector<deckmodel>();
        
        specialboarderpanel panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,3,9");
        //TODO
        //stuff we need
        //opponent discription
        //deckselection
        //the option to edit decks
        //if applicable option to retreat
        JLabel infol = new speciallabel("Please select a deck to combat your opponent");
        infol.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(infol,"2,2");
        this.setComponentZOrder(infol,0);
        new speciallabelcontainer(componentlist,containerlist,(speciallabel)infol);
        //componentlist.add(infol);
        
        JLabel victoryl = new speciallabel("***Victory conditions***");
        victoryl.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(victoryl,"2,3");
        this.setComponentZOrder(victoryl,0);
        new speciallabelcontainer(componentlist,containerlist,(speciallabel)victoryl);
        //componentlist.add(victoryl);
        
        victorytext = new speciallabel();
        victorytext.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(victorytext,"2,4");
        this.setComponentZOrder(victorytext,0);
        new speciallabelcontainer(componentlist,containerlist,(speciallabel)victorytext);
        //componentlist.add(victorytext);
        
        deckmodel = new DefaultListModel();
        decklist = new JList(deckmodel);
        decklist.setOpaque(false);
        decklist.addListSelectionListener(new decklistclick());
        deckscroll = new specialscrollpane(decklist);
        deckscroll.setOpaque(false);
        this.add(deckscroll,"2,5");
        this.setComponentZOrder(deckscroll,0);
        new specialscrollpanecontainer(componentlist,containerlist,(specialscrollpane)deckscroll);
        //componentlist.add(deckscroll);
        
        deckmanager = new specialboarderbutton("manage decks",i.gettexture(4).getimage(),this,5,null);
        deckmanager.addActionListener(new deckclick());
        this.add(deckmanager,"2,6");
        this.setComponentZOrder(deckmanager,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)deckmanager);
        //componentlist.add(deckmanager);
        
        fight = new specialboarderbutton("fight",i.gettexture(4).getimage(),this,5,null);
        fight.addActionListener(new fightclick());
        this.add(fight,"2,7");
        this.setComponentZOrder(fight,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)fight);
        //componentlist.add(fight);
        
        retreat = new specialboarderbutton("retreat",i.gettexture(4).getimage(),this,5,null);
        retreat.addActionListener(new retreatclick());
        this.add(retreat,"2,8");
        this.setComponentZOrder(retreat,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)retreat);
        //componentlist.add(retreat);
        
        this.repaint();
    }

    public void switchto(menucontrol g){
        game = g.getcurrentprofile().getgame();
        match = game.getcurrentmatch();
        player = game.getplayer();
        body = game.getplayer().getcurrentbody();
        fight.setEnabled(false);
        retreat.setEnabled(false);
        deckmodel.clear();
        deckmodellist.clear();
        for(int i = 0; i < player.numdeck();i++){
            boolean acceptable = true;
            deckmodel decktemp = player.getdeck(i);
            System.out.println("test deck "+decktemp.getname());
            for(int j = 0; j < decktemp.getcollection().getnum();j++){
                int index = body.contiansrestriction(decktemp.getcollection().getcollection(j).checkcard());
                if(index != -1){
                    int numrestriction = body.getrestrictednum().elementAt(index).intValue();
                    int numcards = decktemp.getcollection().getcollection(j).numcard();
                
                    if((numrestriction < numcards)){
                        acceptable = false;
                        return;
                    }
                }else{
                    acceptable = false;
                    return;
                }
            }
            if(acceptable == true){
                deckmodellist.add(decktemp);
                deckmodel.addElement(decktemp.getname());
            }
        }
        System.out.println("&&&&&&&&&&&&&&&&match "+match.getvictory());
        victorytext.setText(match.getvictory().text());
        
        super.switchto(g);
    }
    
    private class deckclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            cont.setreturnpanel(thisref);
            setupswitchpanel("newdeckpanel");
        }    
    }
    private class fightclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            game.getcurrentmatch().startmatch(deckmodellist.elementAt(decklist.getSelectedIndex()));
            setupswitchpanel("matchpanel");
        }    
    }
    
    private class retreatclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
        
        }    
    }
    
    private class decklistclick implements ListSelectionListener
    {    
        public void valueChanged(ListSelectionEvent e){
            if(decklist.getSelectedValue() != null){
                fight.setEnabled(true);
            }
        }
    }
}
