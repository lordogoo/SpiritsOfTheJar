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
 * Write a description of class cardinfoanimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class cardinfoanimation extends animation
{
    int startx;
    int starty;
    int startwidth;
    int startheight;
    
    int endx;
    int endy;
    int endwidth;
    int endheight;
    
    BufferedImage background;
    instansiatedcard handcard;
    
    boolean interupt;
    
    public cardinfoanimation(gamepanel gp,triggeranimations t,int durration,Color b,
                             int sx,int sy,int sw,int sh,
                             int ex,int ey,int ew,int eh,
                             BufferedImage ba,instansiatedcard c)
    {
        super(gp,t,durration,b,0);
        
        startx = sx;
        starty = sy;
        startwidth = sw;
        startheight = sh;
        
        endx = ex;
        endy = ey;
        endwidth = ew;
        endheight = eh;
        
        background = ba;
        handcard = c;
        
        interupt = false;
    }
    
    public void update(){
        //System.out.println("anim update");
        if(interupt == false){
            if(currentframe != framesdurration){
                currentframe++;
            }
        }else{
            if(currentframe != 0){
                currentframe--;
            }else{
                this.end();
            }
        }
    }
    
    public void interupt(MouseEvent e){
        interupt = true;
    }
    
    public void draw(Graphics g,JComponent c){
        Graphics2D g2d = (Graphics2D) g;
        if(currentframe != framesdurration){
                 int tempx = ((startx+2) * framesdurration + (endx-(startx+2))*currentframe) / framesdurration;
                 int tempy = ((starty+2) * framesdurration + (endy-(starty+2))*currentframe) / framesdurration;
                 int tempwidth = ((startwidth-4) * framesdurration + (endwidth-(startwidth-4))*currentframe) / framesdurration;
                 int tempheight = ((startheight-4) * framesdurration + (endheight-(startheight-4))*currentframe) / framesdurration;;
                 Shape s = new RoundRectangle2D.Float(tempx,tempy,tempwidth,tempheight,10,10);
                 
                 Rectangle2D bound = new Rectangle2D.Double(0,0,tempwidth,tempheight);
                 g2d.setPaint(new  TexturePaint(background,bound));
                 g2d.fill(s);
                 
                 g2d.setColor(Color.BLACK);
                 g2d.setStroke(new BasicStroke(2));
                 g2d.draw(s);
                 
                 
                 BufferedImage itemimage = handcard.getcard().getsymbol().getimage();
                 g.drawString(handcard.getcard().getname()+"",5+tempx,15+tempy);
                 if(itemimage != null){
                     int imagewidth = itemimage.getWidth();
                     int imageheight = itemimage.getHeight();
                    
                     g.drawImage(itemimage,0+tempx,20+tempy,64,64,null);
                 }
                 
                 

        }else{
                 Shape s = new RoundRectangle2D.Float(endx,endy,endwidth,endheight,10,10);
                 
                 Rectangle2D bound = new Rectangle2D.Double(0,0,endwidth,endheight);
                 g2d.setPaint(new  TexturePaint(background,bound));
                 g2d.fill(s);
                 
                 g2d.setColor(Color.BLACK);
                 g2d.setStroke(new BasicStroke(2));
                 g2d.draw(s);
                
                 BufferedImage itemimage = handcard.getcard().getsymbol().getimage();
                 g.drawString(handcard.getcard().getname()+"",5+endx,15+endy);
                 if(itemimage != null){
                     int imagewidth = itemimage.getWidth();
                     int imageheight = itemimage.getHeight();
                    
                     g.drawImage(itemimage,0+endx,20+endy,64,64,null);
                 }
                 
                 g.drawString(handcard.getcard().getinheritencestring(),5+endx,94+endy);
                 
                 g.drawString(handcard.getcard().getplacementrulestring(),5+endx,134+endy);
                 
                 g.drawString(handcard.getcard().getonplayrulestring(),5+endx,174+endy);
                 
                 g.drawString(handcard.getcard().getonupdaterulestring(),5+endx,214+endy);
                 
                 g.drawString(handcard.getcard().getondestroyrulestring(),5+endx,254+endy);
                 
        }
    }
}
