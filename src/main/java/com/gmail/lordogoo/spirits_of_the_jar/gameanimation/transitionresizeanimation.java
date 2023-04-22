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
public class transitionresizeanimation extends animation
{
    int startx;
    int starty;
    int startw;
    int starth;
    
    int endx;
    int endy;
    int endw;
    int endh;
    
    specialimage image;
    
    public transitionresizeanimation(gamepanel gp,triggeranimations t,int durration,Color b,
                                     int sx,int sy,int sw,int sh,
                                     int ex,int ey,int ew,int eh,
                                     BufferedImage texture)
    {
        super(gp,t,durration,b,0);
        
        startx = sx;
        starty = sy;
        startw = sw;
        starth = sh;
        endx = ex;
        endy = ey;
        endw = ew;
        endh = eh;
        
        image = new specialimage(startx,starty,startw,starth,10,specialimage.NORTH,null,texture,true);
    }
    
    public void draw(Graphics g,JComponent c){
        Graphics2D g2d = (Graphics2D)g;
        int dx = startx + (((endx - startx)*currentframe)/framesdurration);
        int dy = starty + (((endy - starty)*currentframe)/framesdurration);
        int dw = startw + (((endw - startw)*currentframe)/framesdurration);
        int dh = starth + (((endh - starth)*currentframe)/framesdurration);
        image.onresize(dw,dh);

        g2d.drawImage(image.getimage(),dx,dy,null);


    }
}
