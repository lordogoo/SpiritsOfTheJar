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
public class shoppanel extends gamepanel
{
    gamepanel thisref;
    
    DefaultListModel buylistmodel;
    JList buylist;
    JScrollPane buyscroll;
    JButton buy;
    
    JLabel cash;
    
    DefaultListModel selllistmodel;
    JList selllist;
    JScrollPane sellscroll;
    JButton sell;
    
    JButton back;

    public shoppanel(String n,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(n,c,i,s,bi);
        thisref = this;
        
        double size[][] = 
        {{TableLayout.FILL,20,.2,.2,.2,.2,20,TableLayout.FILL},
        {TableLayout.FILL,20,30,.4,30,.4,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(7,8);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,6,7");
        
        
        back = new specialboarderbutton("back to town",i.gettexture(4).getimage(),this,5,null);
        back.addActionListener(new backclick());
        this.add(back,"2,2,5,2");
        this.setComponentZOrder(back,0);
        componentlist.add(back);
        
        buylistmodel = new DefaultListModel();
        buylist = new JList(buylistmodel);
        buylist.setFixedCellWidth(64);
        buylist.addListSelectionListener(new buyclick());
        buylist.setCellRenderer(new CustomCellRendererSelecteble());
        buylist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        buylist.setVisibleRowCount(-1);
        buyscroll = new JScrollPane(buylist);
        this.add(buyscroll,"2,3,5,3");
        this.setComponentZOrder(buyscroll,0);
        componentlist.add(buyscroll);
        
        buy = new specialboarderbutton("buy",i.gettexture(4).getimage(),this,5,null);
        buy.addActionListener(new buybuttonclick());
        this.add(buy,"2,4");
        this.setComponentZOrder(buy,0);
        componentlist.add(buy);
        
        cash = new JLabel();
        this.add(cash,"3,4");
        this.setComponentZOrder(cash,0);
        componentlist.add(cash);
        
        selllistmodel = new DefaultListModel();
        selllist = new JList(selllistmodel);
        selllist.setFixedCellWidth(64);
        selllist.addListSelectionListener(new sellclick());
        selllist.setCellRenderer(new CustomCellRendererSelecteble());
        selllist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        selllist.setVisibleRowCount(-1);
        sellscroll = new JScrollPane(selllist);
        this.add(sellscroll,"2,5,5,5");
        this.setComponentZOrder(sellscroll,0);
        componentlist.add(sellscroll);
        
        sell = new specialboarderbutton("sell",i.gettexture(4).getimage(),this,5,null);
        sell.addActionListener(new sellbuttonclick());
        this.add(sell,"2,6");
        this.setComponentZOrder(sell,0);
        componentlist.add(sell);
    }
    
    public void switchto(menucontrol g){
        loadbuylist(g.getcurrentprofile().getgame());
        loadselllist(g.getcurrentprofile().getgame());
        
        
        buylist.setFixedCellHeight(buyscroll.getHeight());
        selllist.setFixedCellHeight(sellscroll.getHeight());
        
        buy.setEnabled(false);
        sell.setEnabled(false);
        
        cash.setText("cash: "+g.getcurrentprofile().getmoney());
        
        super.switchto(g);
    }

    public void loadbuylist(gamecontrol g){
        buylistmodel.clear();
        for(int i = 0; i < g.getcurrentstruct().getshop().numperchace();i++){
            buylistmodel.addElement(new buydisplay(g.getcurrentstruct().getshop().getperchace(i)));
        }
    }
    
    public void loadselllist(gamecontrol g){
        selllistmodel.clear();
        for(int i = 0; i < g.getplayer().getcollection().getnum();i++){
            selldisplay temp = new selldisplay(g.getplayer().getcollection().getcollection(i));
            selllistmodel.addElement(temp);
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
            setupswitchpanel("structpanel");
        }    
    }

    private class buybuttonclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            if(buylist.getSelectedIndex()!=-1){
                
                //give player card
                buydisplay buytemp = ((buydisplay)buylist.getSelectedValue());
                trigger temp = buytemp.gettrigger();
                temp.trigger(thisref);
                //remove buy option
                cont.getgamecontrol().getcurrentstruct().getshop().removeperchace(buylist.getSelectedIndex());
                //reload
                loadbuylist(cont.getgamecontrol());
                loadselllist(cont.getgamecontrol());
                
                //update wallet
                cont.getgamecontrol().getplayer().addmoney(-buytemp.getcost());
                cash.setText("cash: "+cont.getgamecontrol().getplayer().getmoney());
            
                buy.setEnabled(false);
                sell.setEnabled(false);
            }
        }    
    }
    
    private class sellbuttonclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            gamecontrol g = cont.getgamecontrol();
            gameinfocontrol ginfo = cont.getgameinfo();
            if(selllist.getSelectedIndex()!=-1){
                //update wallet
                selldisplay temp = (selldisplay)selllist.getSelectedValue();
                cont.getgamecontrol().getplayer().addmoney(temp.getcost());
                cash.setText("cash: "+cont.getgamecontrol().getplayer().getmoney());
                
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
                card tempcard = g.getplayer().getcollection().getcollection(selllist.getSelectedIndex()).checkcard();
                g.getplayer().getcollection().getcollection(selllist.getSelectedIndex()).removecard();
                if(g.getplayer().getcollection().getcollection(selllist.getSelectedIndex()).numcard()==0){
                    g.getplayer().getcollection().removecollection(selllist.getSelectedIndex());
                }
                
                
                //add the card to the sell list
                perchacemodel p = ginfo.getperchase(tempcard.getname());
                g.getcurrentstruct().getshop().addperchace(new perchaseinstantiated(p));
                //buylistmodel.addElement(new buydisplay(.getperchace(i)));
                
                //reload lists
                loadbuylist(cont.getgamecontrol());
                loadselllist(cont.getgamecontrol());
                
                sell.setEnabled(false);
                buy.setEnabled(false);
            }
        }    
    }
    
    private class buyclick implements ListSelectionListener
    {    
        public void valueChanged(ListSelectionEvent e){
            if(buylist.getSelectedIndex() != -1){
                if(((buydisplay)buylist.getSelectedValue()).getcost()<=cont.getgamecontrol().getplayer().getmoney()){
                    buy.setEnabled(true);
                }else{
                    buy.setEnabled(false);
                }
            }
        }
    }
    
    private class sellclick implements ListSelectionListener
    {    
        public void valueChanged(ListSelectionEvent e){
            if(selllist.getSelectedIndex() != -1){
                sell.setEnabled(true);
            }
        }
    }
    
    
    /******************************************
     * buy display
     ******************************************/
    private class buydisplay extends JPanel{
        
        perchaseinstantiated perchace;
        
        public buydisplay(perchaseinstantiated p){
            perchace = p;
        }
        
        public trigger gettrigger(){
            return perchace.getmodel().gettrigger();
        }
        
        public int getcost(){
            return perchace.getmodel().getcost();
        }
        
        public void paintComponent(Graphics g) {
                int imagesizex = 32;
                int imagesizey = 32;
                g.drawImage(perchace.getmodel().getimage(),0,20,64,64,null);
                g.drawString(perchace.getmodel().gettext()+"",5,10);
                g.drawString("cost :"+perchace.getmodel().getcost()+"",5,this.getHeight()-5);
        } 
    }    
    
    /******************************************
     * sell display
     ******************************************/
    private class selldisplay extends JPanel{
        
        collectionelementmodel sell;
        int cost;
        int num;
        
        public selldisplay(collectionelementmodel p){
            sell = p;
            cost = sell.checkcard().getcost();
            num = 0;
        }
        
        public void setnum(int t){
            num = t;
        }
        
        public int getnum(){
            return sell.numcard();
        }
        
        public int getcost(){
            return cost;
        }
        
        public collectionelementmodel getcollection(){
            return sell;
        }
        
        public void paintComponent(Graphics g) {
            g.drawImage(sell.checkcard().getsymbol().getimage(),0,20,64,64,null);
            g.drawString(sell.checkcard().getname()+"",5,10);

            g.drawString("value: "+(sell.checkcard().getrarity()*20)+"",5,this.getHeight()-5);
            
            if(sell.numcard() == num){
                
                g.setColor(Color.red);
            }
            
            g.drawString("num: "+sell.numcard()+"",5,this.getHeight()-35);
            g.drawString("in deck: "+num+"",5,this.getHeight()-20);
        } 
    } 
}
