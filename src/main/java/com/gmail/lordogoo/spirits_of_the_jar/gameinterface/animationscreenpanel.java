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
import java.awt.font.*;
import java.applet.*;
import java.io.*;
import java.util.*; 
import java.text.*;
/**
 * Write a description of class animationscreenpanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class animationscreenpanel extends JPanel
{
    
    public int currentframe;
    public int framesdurration;
    public gamepanel namepanel;
    public int index;
    
    public animationscreenpanel(gamepanel gp,int durration)
    {
        currentframe = 0;
        framesdurration = durration;
        namepanel = gp;
    }

    public int getcurrentframe(){
        return currentframe;
    }
    
    public void setcurrentframe(int i){
        currentframe = i;
    }
    
    public boolean isfinished(){
        return (currentframe >= framesdurration);
    }
    
    public void setindex(int i){
        index = i;
    }
    
    public int getindex(){
        return index;
    }
    
    public void setlocation(){}
    
    public void drawtextmultyline(float x,float y,float length,String message,Graphics2D g2d){
        AttributedString messagestring = new AttributedString(message);
        AttributedCharacterIterator paragraph = messagestring.getIterator();
        int paragraphStart = paragraph.getBeginIndex();
        int paragraphEnd = paragraph.getEndIndex();
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph, frc);
        float breakWidth = length;
        float drawPosY = y;

        lineMeasurer.setPosition(paragraphStart);


        while (lineMeasurer.getPosition() < paragraphEnd) {

            TextLayout layout = lineMeasurer.nextLayout(breakWidth);
            float drawPosX = layout.isLeftToRight()
                ? x : breakWidth - layout.getAdvance();
            drawPosY += layout.getAscent();
            layout.draw(g2d, drawPosX, drawPosY);


            drawPosY += layout.getDescent() + layout.getLeading();
        }
    }
    
    
    
    
}
