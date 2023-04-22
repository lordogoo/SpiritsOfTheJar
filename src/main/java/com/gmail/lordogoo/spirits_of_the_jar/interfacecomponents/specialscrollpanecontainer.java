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

public class specialscrollpanecontainer extends specialcomponentcontainer
{
    
    specialscrollpane scrollpane;
     
    public specialscrollpanecontainer()
    {
    }

    
    public specialscrollpanecontainer(Vector<JComponent> list,Vector<specialcomponentcontainer> containerlist,specialscrollpane l){
        scrollpane = l;
        list.add(scrollpane);
        containerlist.add(this);  
    }

    public void setopacity(float i){ 
        scrollpane.setopacity(i);
        scrollpane.repaint();
    }

    public void setEnabled(boolean b){
        scrollpane.setEnabled(b);
    }
    
    
}
