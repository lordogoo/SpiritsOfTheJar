package com.gmail.lordogoo.spirits_of_the_jar.interfacecomponents;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.applet.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
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

public class specialboarderbutton extends JButton
{

    BufferedImage background;
    int distance;
    float alpha;
    GradientPaint gpbackground;
    gamepanel panel;
    
    Clip onpresssoundeffect;
    
    specialimage normal;
    specialimage disabled;
    specialimage clicked;
    specialimage currentdisplay;
    
    
    
    public specialboarderbutton(String s,BufferedImage b,gamepanel p,int dist,GradientPaint gpb)
    {
        this.setText(s);
        distance = dist;
        panel = p;
        background = b;
        gpbackground = gpb;
        this.setBorder(null);
        alpha = 1.f;  
        this.setOpaque(false);
    }
    
    
    public void onresize(){
        currentdisplay.onresize(this.getWidth(),this.getHeight());
    }
        
    public void setopacity(float op){
        alpha = op;
    }
    
    public void generategrapgics(){
        int x = this.getX();
        int y = this.getY();
        int w = this.getWidth( );        
        int h = this.getHeight( );
        int distance = 5;
        
        normal = new specialimage(x,y,w,h,distance,specialimage.NORTH,new Color(0,0,0,0),background,true);
        clicked = new specialimage(x,y,w,h,distance,specialimage.SOUTH,new Color(0,0,0,100),background,true);
        disabled = new specialimage(x,y,w,h,distance,specialimage.SOUTH,new Color(0,0,0,0),background,false);
    }
    
    
    public void paintComponent(Graphics g) {
        
        int w = this.getWidth( );        
        int h = this.getHeight( );
        
        if((currentdisplay == null)||((currentdisplay.getwidth() != w)||(currentdisplay.getheight() != h))){   
             generategrapgics();
        } 
        
        if(this.isEnabled() == true){
            if(this.getModel().isArmed()==false){
                currentdisplay = normal;
            }else{
                currentdisplay = clicked;
            }
        }else{
            currentdisplay = disabled;
        }
        
        paintComponent(g,0,0);
    }
    
    public void paintComponent(Graphics g,int x,int y){
        int w = this.getWidth( );        
        int h = this.getHeight( );
        Graphics2D g2d = (Graphics2D)g;
        
        if(currentdisplay != null){
            
            int type = AlphaComposite.SRC_OVER;
            g2d.setComposite(AlphaComposite.getInstance(type, alpha));
            
            //Rectangle2D bound = new Rectangle2D.Double(x,y,w,h);
            //g2d.setPaint(new  TexturePaint(currentdisplay.getimage(),bound));

            //g2d.fillRect(x,y,w,h);
        
            g2d.drawImage(currentdisplay.getimage(),x,y,null);
        
            Font f = new Font("Arial", Font.BOLD, 13);
            g2d.setFont(f);
        
            int newx = (this.getWidth() - g2d.getFontMetrics().stringWidth(this.getText()))/2;
            int newy = (this.getHeight() + g2d.getFontMetrics().getHeight())/2;
            Color myblue = new Color(0,150,255);
            Color mygrey = new Color(150,150,150);
            if(this.isEnabled()){
                drawoutlinetext(g2d,"Arial",this.getText(),newx+x,newy-2+y,Color.black,myblue);
            }else{
                drawoutlinetext(g2d,"Arial",this.getText(),newx+x,newy-2+y,Color.black,mygrey);
            }
            
        }else{
            
        }
    }

     public void drawoutlinetext(Graphics2D g2d,String font,String s,int x,int y,Color back,Color front){   
            Font f = new Font(font, Font.BOLD, 13);
            g2d.setFont(f);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(back);
            
            FontRenderContext frc = g2d.getFontRenderContext();
            TextLayout tl = new TextLayout(s, f, frc);
            float sw = (float) tl.getBounds().getWidth();
            AffineTransform transform = new AffineTransform();
            transform.setToTranslation(x,y);
            Shape shape = tl.getOutline(transform);
            Rectangle r = shape.getBounds();
            g2d.setColor(back);
            g2d.setStroke(new BasicStroke(3));
            g2d.draw(shape);
            g2d.setColor(front);
            g2d.drawString(s,x,y);
     }
}
