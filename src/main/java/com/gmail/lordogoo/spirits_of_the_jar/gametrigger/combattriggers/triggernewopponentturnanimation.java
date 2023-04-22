package com.gmail.lordogoo.spirits_of_the_jar.gametrigger.combattriggers;
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
public class triggernewopponentturnanimation extends triggeranimations
{
    
    textdisplay animationcomponent;

    menucontrol cont;
    matchmodel match;
    
    int durration = 40;
    
    public triggernewopponentturnanimation(menucontrol c,matchmodel m)
    {
        cont = c;
        match = m;
    }

    
    public void startanimation(gamepanel target){
        //target.addanimation(new opponentturnstartanimation(target,this,40,new Color(0,0,0,150),match.getopponentturnnum()));
        //target.addanimationdisplay();
        
        animationcomponent = new textdisplay(target,durration,match.getopponentturnnum());
        animationcomponent.setOpaque(false);
        animationcomponent.setLocation(0,0);
        animationcomponent.setSize(target.getcontrol().getxresolution(),target.getcontrol().getyresolution());
        target.addanimationcomponent(animationcomponent);
        target.addanimation(new  basicanimation(target,this,durration,null,animationcomponent));
    }
    
    public void endanimation(gamepanel target){
        target.removeanimationcomponent(animationcomponent);
        target.addtrigger(new triggeropponentdrawcard(cont,match));
        super.endanimation(target);
    }
    
    public class textdisplay extends animationscreenpanel
    {
        
       String texta;
       String textb;
       int textframe;
       int textframedurration;
        
       public textdisplay(gamepanel gp,int durration,int turnnum)
       {
          super(gp,durration);
          texta = "turn "+turnnum;
          textb = "It's the opponents turn";
          textframe = 0;
          textframedurration = (framesdurration*5)/10;
       }
       
       public void setcurrentframe(int i){
           currentframe = i;
           if(textframe < textframedurration){
            textframe++;
           }
       }
     
       public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        Color myred = new Color(255,150,0);
        Font f = new Font("Arial", Font.BOLD, 13);
        g2d.setFont(f);
        Color bb = new Color(0,0,0,100);
        g2d.setColor(bb);
        g2d.fillRect(0,0,target.getWidth(),target.getHeight());
        
        int textstartax = -g.getFontMetrics().stringWidth(texta);
        int textstartay = ((this.getHeight()/2) + 30);
        
        int textendax = (this.getWidth()/2) - (g.getFontMetrics().stringWidth(texta)/2);
        int textenday = ((this.getHeight()/2) + 30);
        
        int textadx = textstartax + (((textendax - textstartax)*textframe)/textframedurration);
        int textady = textstartay + (((textenday - textstartay)*textframe)/textframedurration);
        
        target.drawoutlinetext(g2d,"Arial",texta,textadx,textady,Color.black,myred);
        
        int textstartbx = -g.getFontMetrics().stringWidth(textb);
        int textstartby = ((this.getHeight()/2) - 30);
        
        int textendbx = (this.getWidth()/2) - (g.getFontMetrics().stringWidth(textb)/2);
        int textendby = ((this.getHeight()/2) - 30);
        
        int textbdx = textstartbx + (((textendbx - textstartbx)*textframe)/textframedurration);
        int textbdy = textstartby + (((textendby - textstartby)*textframe)/textframedurration);
       
        target.drawoutlinetext(g2d,"Arial",textb,textbdx,textbdy,Color.black,myred);
       }
       
    }
    
}