package com.gmail.lordogoo.spirits_of_the_jar.gametrigger;
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
/**
 * Write a description of class tutorialtriggerendgroup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tutorialtriggerendgroup extends triggeranimations
{

    gamepanel namepanel;
    Vector<tutorialentity> entitylist;
    Vector<animationscreenpanel> animationcomponent;

    
    int durration = 20;
    
    public tutorialtriggerendgroup(gamepanel gp)
    {
        super();
        namepanel = gp;
        entitylist = new Vector<tutorialentity>();
        animationcomponent = new Vector<animationscreenpanel>();
    }
    
    
    public void addentity(BufferedImage ch,boolean vh,boolean s,int loc){
        entitylist.add(new tutorialentity(namepanel,ch,vh,s,loc)); 
    }
    
    public void startanimation(gamepanel target){
        System.out.println("start tutorialtriggerendgroup");
        animationcomponent = namepanel.getanimationcomponentlist();
        target.addanimation(new  tutorialendgroup(target,this,durration,null,entitylist,animationcomponent));
    }
    
    public void endanimation(gamepanel target){
        
        
        for(int i = 0; i < animationcomponent.size();i++){
            //System.out.println("removing "+i+""+animationcomponent.elementAt(i).getindex());
            namepanel.removeanimationcomponent(animationcomponent.elementAt(i));
        }
        triggercontainer.loadnextanimation();
        super.endanimation(target);
        System.out.println("end tutorialtriggerendgroup "+animationcomponent.size());
    }
}
