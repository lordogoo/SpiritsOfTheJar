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
public class coinanimation extends animation
{

    boolean choice;
    
    Vector<BufferedImage> frames;
    
    int coinframe;
    int coinframedurration;
    int coinframetrigger;
    
    int textframe;
    int textframedurration;
    int textframetrigger;
    
    int nexttextframe;
    int nexttextdurration;
    int nexttexttrigger;
    
    public coinanimation(gamepanel gp,triggeranimations t,int durration,Color b,boolean choice,Vector<BufferedImage> f)
    {
        super(gp,t,durration,b,0);
        frames = f;
        
        this.choice = choice;
        coinframe = 0;
        coinframedurration = 50;
        coinframetrigger = 0;
        
        textframe = 0;
        textframedurration = 20;
        textframetrigger = 30;
        
        nexttextframe = 0;
        nexttextdurration = 20;
    }
    
    public void update(){
        if(currentframe == framesdurration){
            target.repaint();
        }else{
           
           if((currentframe >= coinframetrigger)&&(currentframe < (coinframedurration+coinframetrigger))){
               coinframe++;
           }
           if((currentframe >= textframetrigger)&&(currentframe < (textframedurration+textframetrigger))){
               textframe++;
           }
           currentframe++;
           target.repaint();
        }
    }
    
    public void draw(Graphics g,JComponent c){
        //draw background
        g.setColor(background);
        g.fillRect(0,0,c.getWidth(),c.getHeight());
        
        BufferedImage imagetemp;
        if(choice == false){
            imagetemp = frames.elementAt(3);
        }else {
            imagetemp = frames.elementAt(6);
        }
        //draw coin
        int startx = (c.getWidth()/2) - (imagetemp.getWidth()/2);
        int starty = (- imagetemp.getHeight());
        int endx = (c.getWidth()/2) - (imagetemp.getWidth()/2);
        int endy = (c.getHeight()/2) - (imagetemp.getHeight()/2);
        int dx = startx + ((endx - startx)*coinframe)/coinframedurration;
        int dy = starty + ((endy - starty)*coinframe)/coinframedurration;
        int angle = 180+((810*coinframe)/coinframedurration);
        
        Graphics2D g2d = (Graphics2D)g;

        AffineTransform affineTransform = new AffineTransform(); 
        affineTransform.translate(dx,dy);
        affineTransform.rotate(Math.toRadians(angle),imagetemp.getWidth()/2,imagetemp.getHeight()/2);
        
        g2d.drawImage(imagetemp,affineTransform,c);
        
        //draw text
        Font f = new Font("Arial", Font.BOLD, 13);
        g2d.setFont(f);
        
        String text;
        Color playercolor;
        if(choice == false){
            text = "opponent won coin toss";
            playercolor = new Color(255,150,0);
        }else{
            text = "you won the coin toss";
            playercolor = new Color(0,150,255);
        }
        
        int textstartx = -g.getFontMetrics().stringWidth(text);
        int textstarty = ((c.getHeight()/2) - (imagetemp.getHeight()/2))-20;
        
        int textendx = (c.getWidth()/2) - (g.getFontMetrics().stringWidth(text)/2);
        int textendy = ((c.getHeight()/2) - (imagetemp.getHeight()/2))-20;
        
        int textdx = textstartx + (((textendx - textstartx)*textframe)/textframedurration);
        int textdy = textstarty + (((textendy - textstarty)*textframe)/textframedurration);
        
        g.setColor(Color.red);
        g.drawString(text,textdx,textdy);
        target.drawoutlinetext(g2d,"Arial",text,textdx,textdy,Color.black,playercolor);
        
        //draw next text
        
    }
}
