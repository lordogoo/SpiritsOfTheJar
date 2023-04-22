package com.gmail.lordogoo.spirits_of_the_jar.gameanimation;
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
public class drawinitialcardanimation extends animation
{
    Vector<handcarddisplay> playercards;
    Vector<handcarddisplay> opponentcards;
    
    JComponent pdeck;
    JComponent phand;
    JComponent odeck;
    JComponent ohand;
    
    int pframewidth;
    int oframewidth;
    
    int[] playertimers;
    int[] opponenttimers;

    public drawinitialcardanimation(gamepanel g,triggeranimations t,int durration,Color b,
                                    Vector<handcarddisplay> pc,Vector<handcarddisplay> oc,
                                    JComponent pdeck,JComponent phand,
                                    JComponent odeck,JComponent ohand)
    {
        super(g,t,durration,b,0);
        
        this.pdeck = pdeck;
        this.phand = phand;
        this.odeck = odeck;
        this.ohand = ohand;
        
        playercards = pc;
        opponentcards = oc;
        
        pframewidth = framesdurration/playercards.size();
        oframewidth = framesdurration/opponentcards.size();
    
        playertimers = new int[playercards.size()];
        opponenttimers = new int[opponentcards.size()];
    }

    
    public void update(){
        if(currentframe < framesdurration){
           int pindex = currentframe/pframewidth;
           playertimers[pindex]++;
           int oindex = currentframe/oframewidth;
           opponenttimers[oindex]++;
           target.repaint();
           currentframe++;
        } else {
            System.out.println("trigger end");
            this.end();
        }    
    }
    
    public void draw(Graphics g,JComponent c){    
        for(int i = 0; i < playercards.size();i++){
            int xloc = pdeck.getX()+((((phand.getX()-pdeck.getX())+64*i)*playertimers[i])/pframewidth);
            int yloc = pdeck.getY();
            //g.drawRect(xloc,yloc,30,30);
            
            //playercards.elementAt(i).setLocation(xloc,yloc);
            playercards.elementAt(i).paintComponent(g,xloc,yloc);
            
        }
        for(int j = 0; j < opponentcards.size();j++){
            int xloc = odeck.getX()+(((ohand.getX()-odeck.getX())+64*j)*opponenttimers[j]/oframewidth);
            int yloc = odeck.getY();
            //g.drawRect(xloc,yloc,30,30);
            //opponentcards.elementAt(j).setLocation(xloc,yloc);
            opponentcards.elementAt(j).paintComponent(g,xloc,yloc);          
        }
    
    }
    
}
