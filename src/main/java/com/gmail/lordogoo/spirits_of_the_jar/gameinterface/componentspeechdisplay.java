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
 * Write a description of class componentspeechdisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class componentspeechdisplay extends speechdisplay
{
    
    JComponent component;
    
    RoundRectangle2D[] rec;
    int biggest;
    
    public componentspeechdisplay(gamepanel gp,int durration,
                             String sp,String mess,BufferedImage back,
                             Vector<tutorialentity> te,int ie,JComponent c)
    {
        super(gp,durration,sp,mess,back,te,ie);
        tutorialentity temp = animationlist.elementAt(importantentity);
        component = c;
        
        rec = new RoundRectangle2D[4];
        
        rec[0] = new RoundRectangle2D.Float(temp.getendspeechx(),
                                          temp.getendspeechy(),
                                          temp.getendspeechwidth(),
                                          component.getY() - 2*temp.getendspeechy(),
                                          10,10);
        rec[1] = new RoundRectangle2D.Float(temp.getendspeechx()+component.getX()+component.getWidth(),
                                          temp.getendspeechy(),
                                          temp.getendspeechwidth()-component.getX()-component.getWidth(),
                                          temp.getendspeechheight(),
                                          10,10);
        rec[2] = new RoundRectangle2D.Float(temp.getendspeechx(),
                                            temp.getendspeechy()+component.getY()+component.getHeight(),
                                            temp.getendspeechwidth(),
                                            temp.getendspeechheight()-component.getY()-component.getHeight(),
                                            10,10);
        rec[3] = new RoundRectangle2D.Float(temp.getendspeechx(),
                                            temp.getendspeechy(),
                                            component.getX()-2*temp.getendspeechx(),
                                            temp.getendspeechheight(),
                                            10,10);
        biggest = 0;
        for(int i = 1; i < 4; i++){
            if(area(rec[biggest]) < area(rec[i]) ){
                biggest = i;
            }
        }
                                            
    }

    private double area(RoundRectangle2D r) {
        return r.getWidth() * r.getHeight();
    }
    
    public void paintComponent(Graphics g)
    {
        System.out.println("component speech display "+biggest);
        Graphics2D g2d = (Graphics2D) g;
        
        tutorialentity temp = animationlist.elementAt(importantentity);
        if(currentframe != framesdurration){
                 int tempx = ((temp.getstartspeechx()+2) * framesdurration + ((int)rec[biggest].getX()-(temp.getstartspeechx()+2))*currentframe) / framesdurration;
                 int tempy = ((temp.getstartspeechy()+2) * framesdurration + ((int)rec[biggest].getY()-(temp.getstartspeechy()+2))*currentframe) / framesdurration;
                 int tempwidth = ((temp.getstartspeechwidth()-4) * framesdurration + ((int)rec[biggest].getWidth()-(temp.getstartspeechwidth()-4))*currentframe) / framesdurration;
                 int tempheight = ((temp.getstartspeechheight()-4) * framesdurration + ((int)rec[biggest].getHeight()-(temp.getstartspeechheight()-4))*currentframe) / framesdurration;;
                 int[] xtemppoints = {tempx,tempx+(tempwidth/2),temp.getpointofinterestx()};
                 int[] ytemppoints = {tempy+(tempheight/2),tempy,temp.getpointofinteresty()};
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
        }else{
                 int[] xtemppoints = {(int)rec[biggest].getX(),(int)rec[biggest].getX()+((int)rec[biggest].getWidth()/2),temp.getpointofinterestx()};
                 int[] ytemppoints = {(int)rec[biggest].getY()+((int)rec[biggest].getHeight()/2),(int)rec[biggest].getY(),temp.getpointofinteresty()};   
                 Shape rectangle = rec[biggest];
                 Shape tri = new Polygon(xtemppoints,ytemppoints,3);
                 Area s = new Area();
                 s.add(new Area(rectangle));
                 s.add(new Area(tri));
                 
                 Rectangle2D bound = new Rectangle2D.Double(0,0,temp.getendspeechwidth(),temp.getendspeechheight());
                 g2d.setPaint(new  TexturePaint(background,bound));
                 g2d.fill(s);
                 
                 g2d.setColor(Color.BLACK);
                 g2d.setStroke(new BasicStroke(2));
                 g2d.draw(s);
                 
                 g.drawString(speeker+":",(int)rec[biggest].getX()+20,(int)rec[biggest].getY()+20);
                 //g.drawString(message,(int)rec[biggest].getX()+20,(int)rec[biggest].getY()+40);
                 float length = (float)rec[biggest].getWidth();
                 drawtextmultyline((int)rec[biggest].getX()+20,(int)rec[biggest].getY()+20,length-20,message,g2d);
        }
    }
    
    
    public void setlocation(){
        this.setLocation(0,0);
    }

}
