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

/******************************************
* hand card display
******************************************/
public class handcarddisplay extends JPanel{

     BufferedImage background;
     boolean ishidden;
     instansiatedcard handcard;
        
     public handcarddisplay(instansiatedcard c,boolean v,BufferedImage t){
        handcard = c;
        ishidden = v;
        background = t;
     }
        
     public instansiatedcard getcard(){
        return handcard;
     }
        
     public BufferedImage getimage(){
        Rectangle r = getBounds();
        BufferedImage i = new BufferedImage(r.width, r.height,
                    BufferedImage.TYPE_INT_RGB);
        Graphics g = i.getGraphics();
        paint(g);
        return i;
     }
     
     public BufferedImage gettexture(){
        return background;
     }

     
     
     
     
     public void paintComponent(Graphics g) {
         customdraw(g,0,0);
     }
     
     public void paintComponent(Graphics g,int x,int y) {
         customdraw(g,x,y);
     }
     
     public void customdraw(Graphics g,int x,int y){
         Graphics2D g2d = (Graphics2D) g;
         if(handcard != null){
             if(ishidden == false){
                 
                 Shape s = new RoundRectangle2D.Float(x+2,y+2,this.getWidth()-4,this.getHeight()-4,10,10);
                 
                 Rectangle2D bound = new Rectangle2D.Double(0,0,this.getWidth(),this.getHeight());
                 g2d.setPaint(new  TexturePaint(background,bound));
                 g2d.fill(s);
                 
                 
                 g2d.setColor(Color.BLACK);
                 g2d.setStroke(new BasicStroke(2));
                 g2d.draw(s);
                 
                 BufferedImage itemimage = handcard.getcard().getsymbol().getimage();
                 g.drawString(handcard.getcard().getname()+"",5+x,15+y);
                 if(itemimage != null){
                     int imagewidth = itemimage.getWidth();
                     int imageheight = itemimage.getHeight();
                    
                     g.drawImage(itemimage,0+x,20+y,64,64,null);
                 }
             }else{
             }
         }
        
     }
     
     
     
}