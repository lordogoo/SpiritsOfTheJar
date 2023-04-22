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
import java.awt.font.*;
import java.applet.*;
import java.io.*;
import java.util.*;
import org.jdesktop.jxlayer.*;
import org.jdesktop.jxlayer.plaf.*; 
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
 * Write a description of class containerplate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class containerplate extends JPanel
{
    menucontrol cont;
    JLayeredPane layeredPane;
    //JXLayer layer;
    
    animationdisplay animationsurface;
    popupbox popup;
    gamepanel gpanel;

    public containerplate(menucontrol c,gamepanel g)
    {
        gpanel = g;
        cont = c;
        double size[][] = 
        {{TableLayout.FILL},
        {TableLayout.FILL}};
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);

        layeredPane = new JLayeredPane(){
               @Override
               public boolean isOptimizedDrawingEnabled() {
                  return false;
               }
        };
               
        layeredPane.addMouseListener(new MouseListener(){
            public void mouseClicked (MouseEvent me) {
                gpanel.clickinteruptanimation(me);
            } 
            public void mouseEntered (MouseEvent me) {} 
            public void mousePressed (MouseEvent me) {} 
            public void mouseReleased (MouseEvent me) {}  
            public void mouseExited (MouseEvent me) {}  
        });       
               
        

        /*
        AbstractLayerUI layerUI = new AbstractLayerUI() {

            @Override  
            protected void paintLayer(Graphics2D g2, JXLayer l) {
                // this paints layer as is
                super.paintLayer(g2, l);
                // custom painting:
                // here we paint translucent foreground
                // over the whole layer
                g2.setColor(new Color(0, 128, 0, 128));
                g2.fillRect(0, 0, l.getWidth(), l.getHeight());
            }
            
            protected void processMouseEvent(MouseEvent e, JXLayer l){
            }
            
        };
        */
        //layer = new JXLayer(g);
        //layer.setUI(layerUI);
        this.add(layeredPane,"0,0");
        //this.add(layer,"0,0");
        //System.out.println(gpanel.getname()+" "+cont.getxresolution()+" "+cont.getyresolution());
        gpanel.setBounds(0,0,cont.getxresolution(),cont.getyresolution());
        gpanel.setLocation(0,0);
        //gpanel.setSize(layeredPane.getWidth(),layeredPane.getHeight());
        
        layeredPane.add(gpanel,Integer.valueOf(1),0);
        //this.add(gpanel,"0,0");
        layeredPane.setVisible(true);
        gpanel.setVisible(true);
    }
    
    public void resize(){
        /*
        if(animationsurface != null){
            animationsurface.setBounds(0,0,cont.getxresolution(),cont.getyresolution());
        }
        */
        if(popup != null){
            popup.setBounds(0,0,cont.getxresolution(),cont.getyresolution());
        }
        gpanel.setBounds(0,0,cont.getxresolution(),cont.getyresolution());
    }
    
    public gamepanel getgamepanel(){
        return gpanel;
    }
    
    //animation display
    public void addanimationdisplay(animationdisplay a){
        animationsurface = a;      
        layeredPane.add(animationsurface,Integer.valueOf(3),0);
    }
    
    public void addanimationcomponent(animationscreenpanel c){
        layeredPane.add(c,Integer.valueOf(3),0);
        c.setindex(layeredPane.getIndexOf(c));
    }
    
    public void removeanimationcomponent(animationscreenpanel c){
        int temp = layeredPane.getIndexOf(c);
        if(temp != -1){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    System.out.println("remove animation component");
                    layeredPane.remove(temp);
                }
            });
        }
    }
    
    public synchronized void removeanimationdisplay(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println("remove animation display");
                layeredPane.remove(layeredPane.getIndexOf(animationsurface));
                animationsurface = null;
            }
        });
    }
    
    //popup display
    public void addpopupdisplay(popupbox p){
        popup = p;
        layeredPane.add(popup,Integer.valueOf(2),0);
    }
    
    public void removepopupdisplay(){
        layeredPane.remove(layeredPane.getIndexOf(popup));
        this.popup = null;
    }
    
    public void repaintlayeredpane(){
        layeredPane.repaint();
    }
    
}
