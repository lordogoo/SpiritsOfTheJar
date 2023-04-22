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
public class tutorialanimateentity extends animation
{

    BufferedImage pick;
    
    boolean vh;
    boolean side;

    int location;
    
    int xstart;
    int ystart;
    int xend;
    int yend;
    
    
    
    public tutorialanimateentity(gamepanel gp,triggeranimations t,int durration,Color b,BufferedImage p,
                                 boolean v,boolean s,int l)
    {
        super(gp,t,durration,b,0);
        vh = v;
        side = s;
        location = l;
        pick = p;
        
        if(vh){
            if(side){
                //north
                xstart = location;
                ystart = -pick.getHeight();
                xend = location;
                yend = 0;
            }else{
                //south
                xstart = location;
                ystart = gp.getHeight();
                xend = location;
                yend = gp.getHeight()-pick.getHeight();
            }
        }else{
             if(side){
                //east
                xstart = gp.getWidth();
                ystart = location;
                xend = gp.getWidth()-pick.getWidth();
                yend = location;
            }else{
                //west
                xstart = -pick.getHeight();
                ystart = location;
                xend = 0;
                yend = location;
            }
        }
        
    }

    public void update(){
        if(currentframe != framesdurration){
            currentframe++;
        }else{
            this.end();
        } 
    }
    
    public void draw(Graphics g,JComponent c){
        Graphics2D g2d = (Graphics2D) g;
        int imagelocx = xstart + (xend-xstart)*currentframe/framesdurration;
        int imagelocy = ystart + (yend-ystart)*currentframe/framesdurration;
        g.drawImage(pick,imagelocx,imagelocy,pick.getWidth(),pick.getHeight(),null);
    }
    
}
