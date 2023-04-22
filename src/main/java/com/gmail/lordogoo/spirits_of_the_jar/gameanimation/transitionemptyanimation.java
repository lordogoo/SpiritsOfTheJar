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

public class transitionemptyanimation extends animation
{

    public transitionemptyanimation(gamepanel gp,triggeranimations t,int durration,Color b)
    {
        super(gp,t,durration,b,0);
    }
    
    
    public void draw(Graphics g,JComponent c){
    }
}
