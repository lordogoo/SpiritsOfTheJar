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
public class tutorialanimatelistspeech extends animation
{

    JScrollPane list;
    JList listref;
    int index;

    String speeker;
    String message;
    
    BufferedImage background;
    
    Vector<tutorialentity> animationlist;
    int importantentity;
    boolean interupt;
    
    Polygon arrow;
    
    public tutorialanimatelistspeech(gamepanel gp,triggeranimations t,int durration,Color b,
                             String sp,String mess,BufferedImage back,
                             Vector<tutorialentity> te,int ie,JScrollPane l,JList lr,int i)
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
        
        list = l;
        listref = lr;
        index = i;
        
        arrow = new Polygon();
        arrow.addPoint(10,0);
        arrow.addPoint(30,0);
        arrow.addPoint(30,20);
        arrow.addPoint(40,20);
        arrow.addPoint(20,40);
        arrow.addPoint(0,20);
        arrow.addPoint(10,20);
        
        arrow.translate(list.getX()+(list.getWidth()/2)-20,list.getY());
    }

    public void interupt(MouseEvent e){
        if((e.getX()>list.getX())&&(e.getX()<list.getX()+list.getWidth())){
            if((e.getY()>list.getY())&&(e.getY()<list.getY()+list.getHeight())){
                listref.setSelectedIndex(index);
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
