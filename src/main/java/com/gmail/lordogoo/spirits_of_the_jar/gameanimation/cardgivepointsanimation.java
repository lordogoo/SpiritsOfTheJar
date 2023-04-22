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
 * Write a description of class cardgivepointsanimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class cardgivepointsanimation extends animation
{


    matchmenupanel panel;
    
    instansiatedcard card;
    gametilemodel tile;
    int points;

    public cardgivepointsanimation(gamepanel g,triggeranimations t,int durration,Color b,instansiatedcard c,gametilemodel ti,int p)
    {
        super(g,t,durration,b,0);
        panel = (matchmenupanel)g;
        card = c;
        tile = ti;
        points = p;
    }
    
    public void interupt(MouseEvent e){
    }
    
    public void draw(Graphics g,JComponent c){
        int cardx = panel.getgameboardpanel().getX();
        int cardy = panel.getgameboardpanel().getY();
        panel.getmatch().getgameboardx();
        panel.getmatch().getgameboardy();
        Graphics2D g2d = (Graphics2D)g;

        double p1x = panel.getgameboard().getX() + panel.getgametile(tile.getxlocation(),tile.getylocation()).getX();
        double p1y = panel.getgameboard().getY() + panel.getgametile(tile.getxlocation(),tile.getylocation()).getY();
        double p2x;
        double p2y;
        if(card.getowner() == true){
            p2x = panel.getplayerdeck().getX();
            p2y = panel.getplayerdeck().getY();
        }else{
            p2x = panel.getopponentdeck().getX();
            p2y = panel.getopponentdeck().getY();
        }
        double length = Math.sqrt((p2x - p1x)*(p2x-p1x)+(p2y-p1y)*(p2y-p1y));
        
        double p3x = (p1x+p2x)/2.0;
        double p3y = (p1y+p2y)/2.0;
               
        double slope = -(p2x-p1x)/(p2y-p1y);
        double cx = p3x + (length*1.0)/(2*Math.sqrt(slope*slope+1));
        double cy = p3y + (length*slope)/(2*Math.sqrt(slope*slope+1));
        
        if(points >= 0){
            g.setColor(Color.green);
        }else{
            g.setColor(Color.red);
        }
        
        QuadCurve2D q = new QuadCurve2D.Float();
        q.setCurve(p1x, p1y, cx, cy, p2x, p2y);
        g2d.draw(q);
        
        
    }
    

}
