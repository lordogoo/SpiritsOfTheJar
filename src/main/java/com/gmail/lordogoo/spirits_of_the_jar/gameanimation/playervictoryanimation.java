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
/**
 * Write a description of class playervictoryanimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class playervictoryanimation extends animation
{

    gamepanel game;
    String texta;
    String textb;
    int textframe;
    int textframedurration;
    
    public playervictoryanimation(gamepanel g,triggeranimations t,int durration,Color b,String m)
    {
        super(g,t,durration,b,0);
        game = g;
        texta = "You are victorious";
        textb = m;
        textframe = 0;
        textframedurration = (framesdurration*5)/10;
    }
    
    public void update(){
        if(textframe < textframedurration){
            textframe++;
        }
        if(currentframe != framesdurration){
            currentframe++;
            target.repaint();
        }
    }

    public void draw(Graphics g,JComponent c){
        Color myblue = new Color(0,150,255);
        Graphics2D g2d = (Graphics2D)g;
        Font f = new Font("Arial", Font.BOLD, 13);
        g2d.setFont(f);
        
        int textstartax = -g.getFontMetrics().stringWidth(texta);
        int textstartay = ((c.getHeight()/2) + 30);
        
        int textendax = (c.getWidth()/2) - (g.getFontMetrics().stringWidth(texta)/2);
        int textenday = ((c.getHeight()/2) + 30);
        
        int textadx = textstartax + (((textendax - textstartax)*textframe)/textframedurration);
        int textady = textstartay + (((textenday - textstartay)*textframe)/textframedurration);
        
        game.drawoutlinetext(g2d,"Arial",texta,textadx,textady,Color.black,myblue);
        
        int textstartbx = -g.getFontMetrics().stringWidth(textb);
        int textstartby = ((c.getHeight()/2) - 30);
        
        int textendbx = (c.getWidth()/2) - (g.getFontMetrics().stringWidth(textb)/2);
        int textendby = ((c.getHeight()/2) - 30);
        
        int textbdx = textstartbx + (((textendbx - textstartbx)*textframe)/textframedurration);
        int textbdy = textstartby + (((textendby - textstartby)*textframe)/textframedurration);
       
        game.drawoutlinetext(g2d,"Arial",textb,textbdx,textbdy,Color.black,myblue);
        
    }
}