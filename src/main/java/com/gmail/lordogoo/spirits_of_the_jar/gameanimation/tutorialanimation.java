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
/**
 * Write a description of class tutorialanimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tutorialanimation extends animation
{

    String speeker;
    String message;

    BufferedImage character;
    BufferedImage background;
    
    int xstart;
    int ystart;
    int startwidth;
    int startheight;

    int xend;
    int yend;
    int endwidth;
    int endheight;
    
    int characterx;
    int charactery;
    int pointofinterestx;
    int pointofinteresty;
    boolean vh;
    boolean side;
    

    boolean interupt;

    public tutorialanimation(gamepanel gp,triggeranimations t,int durration,Color b,
                             String sp,String mess,
                             BufferedImage ch,BufferedImage back,
                             int sx,int sy,int sw,int sh,
                             int ex,int ey,int ew,int eh,
                             int imx,int imy,int poix,int poiy)
    {
        super(gp,t,durration,b,0);
        interupt = false;
        
        speeker = sp;
        message = mess;
        
        character = ch;
        background = back;
        
        xstart = sx;
        ystart = sy;
        startwidth = sw;
        startheight = sh;
        
        xend = ex;;
        yend = ey;
        endwidth = ew;
        endheight = eh;
        
        characterx = imx;
        charactery = imy; 
        
        pointofinterestx = poix;
        pointofinteresty = poiy;
    }
    
    public void interupt(MouseEvent e){
        interupt = true;
    }
    
    public void update(){
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

    public void draw(Graphics g,JComponent c){
        Graphics2D g2d = (Graphics2D) g;
        if(currentframe != framesdurration){
                 int tempx = ((xstart+2) * framesdurration + (xend-(xstart+2))*currentframe) / framesdurration;
                 int tempy = ((ystart+2) * framesdurration + (yend-(ystart+2))*currentframe) / framesdurration;
                 int tempwidth = ((startwidth-4) * framesdurration + (endwidth-(startwidth-4))*currentframe) / framesdurration;
                 int tempheight = ((startheight-4) * framesdurration + (endheight-(startheight-4))*currentframe) / framesdurration;;
                 int[] xtemppoints = {tempx,tempx+(tempwidth/2),characterx};
                 int[] ytemppoints = {tempy+(tempheight/2),tempy,charactery};
                 Shape rec = new RoundRectangle2D.Float(tempx,tempy,tempwidth,tempheight,10,10);
                 Shape tri = new Polygon(xtemppoints,ytemppoints,3);
                 Area s = new Area();
                 s.add(new Area(rec));
                 s.add(new Area(tri));
                 
                 Rectangle2D bound = new Rectangle2D.Double(0,0,tempwidth,tempheight);
                 g2d.setPaint(new  TexturePaint(background,bound));
                 g2d.fill(s);
                 
                 g2d.setColor(Color.BLACK);
                 g2d.setStroke(new BasicStroke(2));
                 g2d.draw(s);
                 
                 g.drawImage(character,characterx,charactery,character.getWidth(),character.getHeight(),null);
        }else{
            
                 int[] xtemppoints = {xend,xend+(endwidth/2),characterx};
                 int[] ytemppoints = {yend+(endheight/2),yend,charactery};   
                 Shape rec = new RoundRectangle2D.Float(xend,yend,endwidth,endheight,10,10);
                 Shape tri = new Polygon(xtemppoints,ytemppoints,3);
                 Area s = new Area();
                 s.add(new Area(rec));
                 s.add(new Area(tri));
                 
                 Rectangle2D bound = new Rectangle2D.Double(0,0,endwidth,endheight);
                 g2d.setPaint(new  TexturePaint(background,bound));
                 g2d.fill(s);
                 
                 g2d.setColor(Color.BLACK);
                 g2d.setStroke(new BasicStroke(2));
                 g2d.draw(s);
                 
                 g.drawImage(character,characterx,charactery,character.getWidth(),character.getHeight(),null);
        }
    }
}
