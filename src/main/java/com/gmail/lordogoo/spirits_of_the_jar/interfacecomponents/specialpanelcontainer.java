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

public class specialpanelcontainer extends specialcomponentcontainer
{
    
    
    specialpanel panel;
    
    public specialpanelcontainer()
    {
    }

    public specialpanelcontainer(Vector<JComponent> list,Vector<specialcomponentcontainer> containerlist,specialpanel p){
        panel = p;
        list.add(panel);
        containerlist.add(this);  
    }
    
    public void setopacity(float i){ 
        panel.setopacity(i);
        panel.repaint();
    }

    public void setEnabled(boolean b){
        panel.setEnabled(b);
    }
    
    
}
