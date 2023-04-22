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
//TODO
//have some kind of indication what body the player is using
//either in this panel or in the deck select

public class deckmenupanel extends gamepanel
{
    menucontrol cont;
    gamecontrol gameref;
    
    JScrollPane collectionscroll;
    DefaultListModel collectionref;
    JList collection;
    JScrollPane deckscroll;
    DefaultListModel deckref;
    JList deck;
    
    JButton newdeck;
    JButton backtogamemenu;
    JComboBox decks;
    JLabel info;
    JLabel numcards;
    
    //data
    playermodel player;
    deckmodel currentdeck;
    int[] used;
    
    public deckmenupanel(String name,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(name,c,i,s,bi);
        cont = c;
        double size[][] = 
        {{TableLayout.FILL,20,.3,10,.3,.3,20,TableLayout.FILL},
        {TableLayout.FILL,20,.4,.4,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(6,5);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,6,5");
        
        backtogamemenu = new specialboarderbutton("back to deck manager",i.gettexture(4).getimage(),this,5,null);
        backtogamemenu.addActionListener(new gameclick());
        backtogamemenu.addMouseListener(new clickdilimeter());
        this.add(backtogamemenu,"2,4,5,4");
        this.setComponentZOrder(backtogamemenu,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)backtogamemenu);
        //componentlist.add(backtogamemenu);
        
        
        collectionref = new DefaultListModel();
        collection = new JList(collectionref);        
        collection.setCellRenderer(new CustomCellRenderer());
        collection.setFixedCellHeight(64);
        collection.setFixedCellWidth(64);
        collection.addListSelectionListener(new collectionclick());
        collection.addMouseListener(new clickdilimeter());
        collectionscroll = new specialscrollpane(collection);
        collectionscroll.setOpaque(false);
        this.add(collectionscroll,"2,2,2,3");
        this.setComponentZOrder(collectionscroll,0);
        new specialscrollpanecontainer(componentlist,containerlist,(specialscrollpane)collectionscroll);
        //componentlist.add(collectionscroll);
        
        deckref = new DefaultListModel();
        deck = new JList(deckref);
        deck.setCellRenderer(new CustomCellRenderer());
        deck.setFixedCellHeight(64);
        deck.setFixedCellWidth(64);
        deck.addMouseListener(new deckclick());
        deck.addMouseListener(new clickdilimeter());
        deckscroll = new specialscrollpane(deck);
        deckscroll.setOpaque(false);
        this.add(deckscroll,"4,2,5,3");
        this.setComponentZOrder(deckscroll,0);
        new specialscrollpanecontainer(componentlist,containerlist,(specialscrollpane)deckscroll);
        //componentlist.add(deckcroll);
    }
    
    public void loaddeck(){ 
        deckref.clear();
        for(int i = 0; i < currentdeck.getcollection().getnum();i++){
            collectionelementmodel temp = currentdeck.getcollection().getcollection(i);
            int numtemp = temp.numcard();
            int collectionref = player.getcollection().containscard(temp.checkcard());
            deckref.addElement(new collectiondeck(temp));
            used[collectionref] = numtemp;
        
        }
    }
    
    public void loadcollection(){
        collectionref.clear();
        collectionmodel col = player.getcollection();
        for(int i = 0; i < col.getnum();i++){
            collectionref.addElement(new collectionwrapper(col.getcollection(i),i));
        }
    }
    
    public void switchto(menucontrol game){
        gameref = game.getcurrentprofile().getgame();
        this.player = gameref.getplayer();
        
        //reset info
        collectionref.clear();
        currentdeck = gameref.getcurrentdeck();
        setCursor(Cursor.getDefaultCursor());
        used = new int[player.getcollection().getnum()];
        //get new info
        loadcollection();
        loaddeck();
        
        super.switchto(game);
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
    
    private class gameclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            setupswitchpanel("newdeckpanel");
        }    
    }
    

    private class deckclick implements MouseListener
    {
        public void mouseClicked(MouseEvent e){
            if(deck.getSelectedValue()!=null){
                //get the card clicked on
                int deckindex = deck.getSelectedIndex();
                card temp = currentdeck.removecard(deckindex);
                used[player.getcollection().containscard(temp)]--;
                loaddeck();
                loadcollection();
            }
        }
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
    }
    
    private class collectionclick implements ListSelectionListener
    {    
        public void valueChanged(ListSelectionEvent e){
            if(collection.getSelectedValue() != null){
                int itemindex = collection.getSelectedIndex();
                if(used[itemindex]<player.getcollection().getcollection(itemindex).numcard()){
                    card cardtemp = player.getcollection().checkcard(itemindex);
                    used[itemindex]++;
                    currentdeck.addcard(cardtemp);
                    loaddeck();
                    loadcollection();
                }
            }
        }
    }
    
    
    private class collectionwrapper extends JPanel{
        
        collectionelementmodel c;
        int index;
        
        collectionwrapper(collectionelementmodel c,int i){
            index = i;
            this.c = c;
        }
        
        public collectionelementmodel getcollection(){
            return c;
        }
        
        public void paintComponent(Graphics g) {
            if(c != null){
               int imagewidth = 64;
               int imageheight = 64;
               BufferedImage itemimage = c.checkcard().getsymbol().getimage();
               g.drawImage(itemimage,(this.getWidth()-imagewidth)/2,(this.getHeight()-imageheight)/2,64,64,null);
               g.drawString(c.checkcard().getname()+"",5,this.getHeight()-50);
               g.drawString("num "+c.numcard()+"",5,this.getHeight()-35);
               g.drawString("used "+used[index]+"",5,this.getHeight()-20);
               bodyinstanciated bodytemp = gameref.getplayer().getcurrentbody();
               if(bodytemp !=null){
                   
                   if(used[index] > bodytemp.isrestricted(c.checkcard())){
                       g.setColor(Color.red);
                   }
                   g.drawString("capacity "+used[index]+"/"+bodytemp.isrestricted(c.checkcard())+"",5,this.getHeight()-5);
                   
               }
            }
        }    
    }
    
    private class collectiondeck extends JPanel{
        
        collectionelementmodel c;
        
        collectiondeck(collectionelementmodel c){
            this.c = c;
        }
        
        public collectionelementmodel getcollection(){
            return c;
        }
        
        public void paintComponent(Graphics g) {
            if(c != null){
               int imagewidth = 64;
               int imageheight = 64;
               BufferedImage itemimage = c.checkcard().getsymbol().getimage();
               g.drawImage(itemimage,(this.getWidth()-imagewidth)/2,(this.getHeight()-imageheight)/2,64,64,null);
               g.drawString(c.checkcard().getname()+"",5,this.getHeight()-35);
               g.drawString("num "+c.numcard()+"",5,this.getHeight()-20);
               bodyinstanciated bodytemp = gameref.getplayer().getcurrentbody();
               if(bodytemp !=null){
                   if(c.numcard() > bodytemp.isrestricted(c.checkcard())){
                       g.setColor(Color.red);
                       g.drawString("body cannot support this many copys of this card",5,this.getHeight()-5);
               
                   }
               }
            
            }
        }    
    }
    
}
