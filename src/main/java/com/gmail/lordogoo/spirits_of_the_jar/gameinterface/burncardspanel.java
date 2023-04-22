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

public class burncardspanel extends gamepanel
{

    
    JLabel text;

    DefaultListModel<burndisplay> burnmodel;
    backgroundlist burn;
    JScrollPane burnscroll;

    JButton burnbutton;
    JButton backbutton;    
    
    public burncardspanel(String name,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(name,c,i,s,bi);
        
        double size[][] = 
        {{TableLayout.FILL,20,.3,.3,20,TableLayout.FILL},
        {TableLayout.FILL,20,30,.7,30,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(4,7);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,3,6");
        
        
        text = new JLabel("Burn a card to improve the capabilities of your current body");
        this.add(text,"2,2,3,2");
        this.setComponentZOrder(text,0);
        componentlist.add(text);
        
        burnmodel = new DefaultListModel<burndisplay>();
        burn = new backgroundlist(burnmodel,i.gettexture(4).getimage());
        burn.setFixedCellWidth(64);
        burn.setFixedCellHeight(128);
        burn.setCellRenderer(new CustomCellRendererSelecteble());
        burn.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        burn.setVisibleRowCount(-1);
        burn.addListSelectionListener(new burnlistclick());
        burnscroll = new JScrollPane(burn);
        this.add(burnscroll,"2,3,3,3");
        this.setComponentZOrder(burnscroll,0);
        componentlist.add(burnscroll);
        
        
        burnbutton = new specialboarderbutton("burn card",i.gettexture(4).getimage(),this,5,null);
        burnbutton.addActionListener(new burnclick());
        this.add(burnbutton,"2,4");
        this.setComponentZOrder(burnbutton,0);
        componentlist.add(burnbutton);
        
        backbutton = new specialboarderbutton("back to profile",i.gettexture(4).getimage(),this,5,null);
        backbutton.addActionListener(new backclick());
        this.add(backbutton,"3,4");
        this.setComponentZOrder(backbutton,0);
        componentlist.add(backbutton);
    }

    
    public void switchto(menucontrol g){
        loadburnlist(g.getcurrentprofile().getgame());
        burnbutton.setEnabled(false);
        super.switchto(g);
    }
    
    public void loadburnlist(gamecontrol g){
        burnmodel.clear();
        for(int i = 0; i < g.getplayer().getcollection().getnum();i++){
            burndisplay temp = new burndisplay(g.getplayer().getcollection().getcollection(i));
            burnmodel.addElement(temp);
            int maxnum = 0;
            for(int m = 0; m < g.getplayer().numdeck();m++){
                for(int n = 0; n < g.getplayer().getdeck(m).getcollection().getnum(); n++){
                    int max = 0;
                    if(g.getplayer().getdeck(m).getcollection().getcollection(n).checkcard().getname().equals(g.getplayer().getcollection().getcollection(i).checkcard().getname())){
                        max = g.getplayer().getdeck(m).getcollection().getcollection(n).numcard();
                        if(maxnum < max){
                            maxnum = max;
                        }
                    }
                }
            }
            temp.setnum(maxnum);
        }
    }
    
    private class backclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("propanel");
        }    
    }
    
    private class burnclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            gamecontrol g = cont.getgamecontrol();
            gameinfocontrol ginfo = cont.getgameinfo();
            if(burn.getSelectedIndex()!=-1){
                //getcardtemp
                burndisplay temp = (burndisplay)burn.getSelectedValue();
                
                //add a copy of this card to the players currnet bodys restrictions
                cont.getcurrentprofile().getcurrentbody().addrestriction(temp.getcollection().checkcard(),1);
                
                //remove copies of this card from decks if its in said deck
                int maxnum = 0;
                for(int m = 0; m < g.getplayer().numdeck();m++){
                    for(int n = 0; n < g.getplayer().getdeck(m).getcollection().getnum(); n++){
                        int max = 0;
                        if(g.getplayer().getdeck(m).getcollection().getcollection(n).checkcard().getname().equals(temp.getcollection().checkcard().getname())){
                            max = g.getplayer().getdeck(m).getcollection().getcollection(n).numcard();
                            System.out.println("temp ammount: "+temp.getnum()+" "+max+" ");
                            if(temp.getnum() == max){ 
                                g.getplayer().getdeck(m).getcollection().removecard(n);
                            }
                        }
                    }
                }
                
                //remove copies of this card from player collection
                card tempcard = g.getplayer().getcollection().getcollection(burn.getSelectedIndex()).checkcard();
                g.getplayer().getcollection().getcollection(burn.getSelectedIndex()).removecard();
                if(g.getplayer().getcollection().getcollection(burn.getSelectedIndex()).numcard()==0){
                    g.getplayer().getcollection().removecollection(burn.getSelectedIndex());
                }
                
                //reload lists
                loadburnlist(cont.getgamecontrol());
                
                burnbutton.setEnabled(false);
            }
        }    
    }
    
    private class burnlistclick implements ListSelectionListener
    {    
        public void valueChanged(ListSelectionEvent e){
            System.out.println("burn list clicked");
            if(burn.getSelectedIndex() != -1){
                burnbutton.setEnabled(true);
            }
        }
    }
    
    

    
    
    
    
    
    /******************************************
    * burn display
    ******************************************/
    private class burndisplay extends JPanel{
        
        collectionelementmodel burn;
        int num;
        
        public burndisplay(collectionelementmodel p){
            burn = p;
            num = 0;
        }
        
        public void setnum(int t){
            num = t;
        }
        
        public int getnum(){
            return burn.numcard();
        }
        
        
        public collectionelementmodel getcollection(){
            return burn;
        }
        
        public void paintComponent(Graphics g) {
            g.drawImage(burn.checkcard().getsymbol().getimage(),0,20,64,64,null);
            g.drawString(burn.checkcard().getname()+"",5,10);
            
            if(burn.numcard() == num){
                
                g.setColor(Color.red);
            }
            
            g.drawString("num: "+burn.numcard()+"",5,this.getHeight()-35);
            g.drawString("in deck: "+num+"",5,this.getHeight()-20);
        } 
    } 
    
}
