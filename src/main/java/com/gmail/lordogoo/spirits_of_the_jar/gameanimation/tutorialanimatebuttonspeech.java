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

public class tutorialanimatebuttonspeech extends animation
{
    JButton button;

    String speeker;
    String message;
    
    BufferedImage background;
    
    Vector<tutorialentity> animationlist;
    int importantentity;
    boolean interupt;
    
    Polygon arrow;
    
    public tutorialanimatebuttonspeech(gamepanel gp,triggeranimations t,int durration,Color b,
                             String sp,String mess,BufferedImage back,
                             Vector<tutorialentity> te,int ie,JButton bu)
    {
                super(gp,t,durration,b,0);
        //assign meta data
        interupt = false;
        
        speeker = sp;
        message = mess;

        background = back;
        
        animationlist = te;
        importantentity = ie;
        
        //assign animation data
        tutorialentity temp = animationlist.elementAt(importantentity);      
        
        button = bu;
        
        arrow = new Polygon();
        arrow.addPoint(10,0);
        arrow.addPoint(30,0);
        arrow.addPoint(30,20);
        arrow.addPoint(40,20);
        arrow.addPoint(20,40);
        arrow.addPoint(0,20);
        arrow.addPoint(10,20);
        
        arrow.translate(button.getX()+(button.getWidth()/2)-20,button.getY()-20);
    }

    public void interupt(MouseEvent e){
        if((e.getX()>button.getX())&&(e.getX()<button.getX()+button.getWidth())){
            if((e.getY()>button.getY())&&(e.getY()<button.getY()+button.getHeight())){
                button.doClick(0);
                interupt = true;
            }
        }
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
        for(int i = 0; i < animationlist.size(); i++){
            BufferedImage character = animationlist.elementAt(i).getpicture();
            int characterx = animationlist.elementAt(i).getendx();
            int charactery = animationlist.elementAt(i).getendy();
            g.drawImage(character,characterx,charactery,character.getWidth(),character.getHeight(),null);
        }
        
        tutorialentity temp = animationlist.elementAt(importantentity);
        if(currentframe != framesdurration){
                 int tempx = ((temp.getstartspeechx()+2) * framesdurration + (temp.getendspeechx()-(temp.getstartspeechx()+2))*currentframe) / framesdurration;
                 int tempy = ((temp.getstartspeechy()+2) * framesdurration + (temp.getendspeechy()-(temp.getstartspeechy()+2))*currentframe) / framesdurration;
                 int tempwidth = ((temp.getstartspeechwidth()-4) * framesdurration + (temp.getendspeechwidth()-(temp.getstartspeechwidth()-4))*currentframe) / framesdurration;
                 int tempheight = ((temp.getstartspeechheight()-4) * framesdurration + (temp.getendspeechheight()-(temp.getstartspeechheight()-4))*currentframe) / framesdurration;;
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
            
                 int[] xtemppoints = {temp.getendspeechx(),temp.getendspeechx()+(temp.getendspeechwidth()/2),temp.getpointofinterestx()};
                 int[] ytemppoints = {temp.getendspeechy()+(temp.getendspeechheight()/2),temp.getendspeechy(),temp.getpointofinteresty()};   
                 Shape rec = new RoundRectangle2D.Float(temp.getendspeechx(),temp.getendspeechy(),temp.getendspeechwidth(),temp.getendspeechheight(),10,10);
                 Shape tri = new Polygon(xtemppoints,ytemppoints,3);
                 Area s = new Area();
                 s.add(new Area(rec));
                 s.add(new Area(tri));
                 
                 Rectangle2D bound = new Rectangle2D.Double(0,0,temp.getendspeechwidth(),temp.getendspeechheight());
                 g2d.setPaint(new  TexturePaint(background,bound));
                 g2d.fill(s);
                 
                 g2d.setColor(Color.BLACK);
                 g2d.setStroke(new BasicStroke(2));
                 g2d.draw(s);
                 
                 g.drawString(speeker+":",temp.getendspeechx()+20,temp.getendspeechy()+20);
                 g.drawString(message,temp.getendspeechx()+20,temp.getendspeechy()+40);
                 
                 g2d.setPaint(new  TexturePaint(background,bound));
                 g2d.fill(arrow);
                 
                 g2d.setColor(Color.BLACK);
                 g2d.setStroke(new BasicStroke(2));
                 g2d.draw(arrow);
                 
        }
    }
}
