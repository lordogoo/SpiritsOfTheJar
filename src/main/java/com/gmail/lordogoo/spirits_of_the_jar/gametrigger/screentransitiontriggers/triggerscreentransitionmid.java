package com.gmail.lordogoo.spirits_of_the_jar.gametrigger.screentransitiontriggers;
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
public class triggerscreentransitionmid extends trigger
{
    JComponent start;
    int startx;
    int starty;
    int startw;
    int starth;
    
    JComponent end;
    int endx;
    int endy;
    int endw;
    int endh;
    BufferedImage texture;

    public triggerscreentransitionmid(JComponent s,JComponent e,BufferedImage texture)
    {
        System.out.println("trigger resize animation init");
        start = s;
        end = e;
        startx = s.getX();
        starty = s.getY();
        startw = s.getWidth();
        starth = s.getHeight();
        endx = e.getX();
        endy = e.getY();
        endw = e.getWidth();
        endh = e.getHeight();
        this.texture = texture;
        System.out.println("trigger resize animation init end");
    }

    
    public void prewait(){
        System.out.println("trigger resize animation trigger");
        target.invisibleall();
        target.panelinvisible();
        //target.addanimation(new transitionresizeanimation(target,this,20,new Color(0,0,0,150),
        //                    startx,starty,startw,starth,endx,endy,endw,endh,texture));
        target.addanimationdisplay();        
    }
    
    public void postwait(){    
        target.removeanimationdisplay();
        target.panelvisible();
        target.repaint();
        System.out.println("end "+start.getSize()+" "+end.getSize());
        System.out.println("trigger resize animation trigger end");
    }
    
}
