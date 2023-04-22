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
/**
 * Write a description of class doubletaperedstroke here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class doubletaperedstroke implements Stroke
{

    BasicStroke stroke;
    float midwith;

    public doubletaperedstroke(float startwidth, float midwidth)
    {
        this.stroke = new BasicStroke(startwidth);
        this.midwith = midwidth;
    }

    public Shape createStrokedShape(Shape shape) {
        GeneralPath newshape = new GeneralPath();
        int numelements = 0;
        int pathlength = 0;
        
        float[] coords = new float[6];
        for (PathIterator i = shape.getPathIterator(null); !i.isDone(); i.next()) {
            int type = i.currentSegment(coords);
            numelements ++;
            pathlength += 1;
            
        }
            
        
        for (PathIterator i = shape.getPathIterator(null); !i.isDone(); i.next()) {
            int type = i.currentSegment(coords);
            switch (type) {
                case PathIterator.SEG_MOVETO:
                    //2p
                    //newshape.moveTo();
                    break;
                case PathIterator.SEG_LINETO:
                    //2p
                    //newshape.lineTo();
                    break;
                case PathIterator.SEG_QUADTO:
                    //4p
                    //newshape.quadTo();
                    break;
                case PathIterator.SEG_CUBICTO:
                    //6p
                    //newshape.curveTo();
                    break;
                case PathIterator.SEG_CLOSE:
                    //newshape.closePath();
                    break;
            }  
        }
        return stroke.createStrokedShape(newshape);
    }
        
        
        
        
}
