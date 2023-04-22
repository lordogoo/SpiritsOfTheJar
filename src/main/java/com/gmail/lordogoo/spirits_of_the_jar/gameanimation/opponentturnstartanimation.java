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
import com.gmail.lordogoo.spirits_of_the_jar.gameinterface.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.*;

public class opponentturnstartanimation extends animation
{

    gamepanel game;
    String texta;
    String textb;
    int textframe;
    int textframedurration;
    
    public opponentturnstartanimation(gamepanel g,triggeranimations t,int durration,Color b,int turnnum)
    {
        super(g,t,durration,b,0);
        game = g;
        texta = "turn "+turnnum;
        textb = "It's the opponents turn";
        textframe = 0;
        textframedurration = (framesdurration*5)/10;
    }

    
    public void update(){
        if(textframe < textframedurration){
            textframe++;
        }
        if(currentframe != framesdurration){
            currentframe++;
        }else{
            this.end();
        }
    }
    
    public void draw(Graphics g,JComponent c){
        Graphics2D g2d = (Graphics2D)g;
        Color myred = new Color(255,150,0);
        Font f = new Font("Arial", Font.BOLD, 13);
        g2d.setFont(f);
        Color bb = new Color(0,0,0,100);
        g2d.setColor(bb);
        g2d.fillRect(0,0,target.getWidth(),target.getHeight());
        
        int textstartax = -g.getFontMetrics().stringWidth(texta);
        int textstartay = ((c.getHeight()/2) + 30);
        
        int textendax = (c.getWidth()/2) - (g.getFontMetrics().stringWidth(texta)/2);
        int textenday = ((c.getHeight()/2) + 30);
        
        int textadx = textstartax + (((textendax - textstartax)*textframe)/textframedurration);
        int textady = textstartay + (((textenday - textstartay)*textframe)/textframedurration);
        
        game.drawoutlinetext(g2d,"Arial",texta,textadx,textady,Color.black,myred);
        
        int textstartbx = -g.getFontMetrics().stringWidth(textb);
        int textstartby = ((c.getHeight()/2) - 30);
        
        int textendbx = (c.getWidth()/2) - (g.getFontMetrics().stringWidth(textb)/2);
        int textendby = ((c.getHeight()/2) - 30);
        
        int textbdx = textstartbx + (((textendbx - textstartbx)*textframe)/textframedurration);
        int textbdy = textstartby + (((textendby - textstartby)*textframe)/textframedurration);
       
        game.drawoutlinetext(g2d,"Arial",textb,textbdx,textbdy,Color.black,myred);
        
    }
}
