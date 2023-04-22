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
public class nexusmenupanel extends gamepanel
{

    DefaultListModel worldslistmodel;
    JList worldslist;
    JScrollPane worldsscroll;
    
    DefaultListModel bodylistmodel;
    JList bodylist;
    JScrollPane bodyscroll;
    
    JButton enterworld;
    JButton backtomenu;
    

    public nexusmenupanel(String name,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(name,c,i,s,bi);
        cont = c;
        
        double size[][] = 
        {{TableLayout.FILL,20,.2,.5,.2,20,TableLayout.FILL},
        {TableLayout.FILL,20,30,.7,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(6,6);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,5,5");
        
        worldslistmodel = new DefaultListModel();
        worldslist = new JList(worldslistmodel);
        worldslist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        worldslist.setOpaque(false);
        worldslist.addListSelectionListener(new worldclick());
        worldslist.addMouseListener(new clickdilimeter());
        worldslist.setCellRenderer(new CustomCellRendererSelecteble());
        worldsscroll = new specialscrollpane(worldslist);
        worldsscroll.setOpaque(false);
        this.add(worldsscroll,"2,3");
        this.setComponentZOrder(worldsscroll,0);
        new specialscrollpanecontainer(componentlist,containerlist,(specialscrollpane)worldsscroll);
        //componentlist.add(worldsscroll);
        
        bodylistmodel = new DefaultListModel();
        bodylist = new JList(bodylistmodel);
        bodylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bodylist.setOpaque(false);
        bodylist.addListSelectionListener(new bodyclick());
        bodylist.addMouseListener(new clickdilimeter());
        bodylist.setCellRenderer(new CustomCellRendererSelecteble());
        bodyscroll  = new specialscrollpane(bodylist);
        bodyscroll.setOpaque(false);
        this.add(bodyscroll,"4,3");
        this.setComponentZOrder(bodyscroll,0);
        new specialscrollpanecontainer(componentlist,containerlist,(specialscrollpane)bodyscroll);
        //componentlist.add(bodyscroll);
        
        enterworld = new specialboarderbutton("Enter World",i.gettexture(4).getimage(),this,5,null);
        enterworld.addActionListener(new enterclick());
        enterworld.addMouseListener(new clickdilimeter());
        this.add(enterworld,"2,4,4,4");
        this.setComponentZOrder(enterworld,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)enterworld);
        //componentlist.add(enterworld);
        
        backtomenu = new specialboarderbutton("main menu",i.gettexture(4).getimage(),this,5,null);;
        backtomenu.addActionListener(new backtomenuclick());
        backtomenu.addMouseListener(new clickdilimeter());
        this.add(backtomenu,"2,2,4,2");
        this.setComponentZOrder(backtomenu,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)backtomenu);
        //componentlist.add(backtomenu);
    }


    public void switchto(menucontrol g){
        worldslistmodel.clear();
        worldslist.setFixedCellHeight(worldslist.getWidth());
        bodylist.setFixedCellHeight(bodylist.getWidth());
        
        for(int i = 0; i < cont.getcurrentprofile().getgame().numworld();i++){
            if(cont.getcurrentprofile().getgame().getworld(i).isdiscovered()){
                worldslistmodel.addElement(new worlddisplay(cont.getcurrentprofile().getgame().getworld(i)));
            }
        }
        
        bodylistmodel.clear();
        
        enterworld.setEnabled(false);
        
        super.switchto(g);
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
    
    
    private class enterclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            cont.getcurrentprofile().getgame().setcurrentworld(((worlddisplay)worldslist.getSelectedValue()).getworld());
            cont.getcurrentprofile().setcurrentbody(((bodydisplay)bodylist.getSelectedValue()).getbody());
            if(!tutorialrunning){
                setupswitchpanel("playpanel");
            }
        }    
    }
    
    private class backtomenuclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            soundref.playsoundeffect(0);
            //clickinteruptanimation();
            setupswitchpanel("menupanel");
        }    
    }
    
    private class worldclick implements ListSelectionListener
    {    
        public void valueChanged(ListSelectionEvent e){
            enterworld.setEnabled(false);
            if(worldslist.getSelectedValue() != null){
                bodylistmodel.clear();
            
                worldinstanciated worldtemp = ((worlddisplay)worldslist.getSelectedValue()).getworld();
                for(int i = 0; i < cont.getcurrentprofile().numbody(); i++){
                    System.out.println(cont.getcurrentprofile().getbody(i).getmodel().getname());
                    if(cont.getcurrentprofile().getbody(i).getmodel().getoragin().getname().equals(worldtemp.getmodel().getname())){
                        bodylistmodel.addElement(new bodydisplay(cont.getcurrentprofile().getbody(i)));
                    }
                }
            }
            
            //clickinteruptanimation();
        }
    }
    
    private class bodyclick implements ListSelectionListener
    {    
        public void valueChanged(ListSelectionEvent e){
            if((bodylist.getSelectedValue() != null)&&(tutorialrunning != true)){
                enterworld.setEnabled(true);
            }
            //clickinteruptanimation();
        }
    }
    
    
    private class worlddisplay extends JPanel{
        
        worldinstanciated world;
        
        public worlddisplay(worldinstanciated w){
            world = w;
        }
        
        public worldinstanciated getworld(){
            return world;
        }
        
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.drawImage(world.getmodel().getimage(),0,0,this.getWidth(),this.getHeight(),null);
            g.drawString(world.getmodel().getname(),5,15);
        }
    }
    
    private class bodydisplay extends JPanel{
        bodyinstanciated body;
        
        public bodydisplay(bodyinstanciated b){
            body = b;
        }
        
        public bodyinstanciated getbody(){
            return body;
        }
        
        public void paintComponent(Graphics g) {
            g.drawImage(body.getimage().getimage(),0,0,this.getWidth(),this.getHeight(),null);
            g.drawString(body.getname(),5,15);
        }
    }
    
}
