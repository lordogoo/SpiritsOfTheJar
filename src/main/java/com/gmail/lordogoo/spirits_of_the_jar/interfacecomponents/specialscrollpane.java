package com.gmail.lordogoo.spirits_of_the_jar.interfacecomponents;
import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class specialboarderscrollpane here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class specialscrollpane extends JScrollPane
{


    float alpha;

    public specialscrollpane(JComponent c)
    {
        super(c);
        alpha = 1.f;
    }
    
    public void setopacity(float op){
        alpha = op;
    }
    
    public void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D)g;
            int type = AlphaComposite.SRC_OVER;
            g2d.setComposite(AlphaComposite.getInstance(type, alpha));
            super.paintComponent(g2d);
    }
    
    

 
}
