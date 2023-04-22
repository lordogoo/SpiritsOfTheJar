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
import org.pushingpixels.trident.*;
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
 * Write a description of class tutorialanimationspawngroup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tutorialanimategroup extends animation
{
    
    Vector<tutorialentity> animationlist;
    Vector<animationscreenpanel> animatecomponent;

    public tutorialanimategroup(gamepanel gp,triggeranimations t,int durration,Color b,Vector<tutorialentity> al,Vector<animationscreenpanel> ac)
    {
        super(gp,t,durration,b,0);
        animationlist = al;
        animatecomponent = ac;
        animation = new Timeline(this);
        animation.addPropertyToInterpolate("currentframe",0,durration);
        animation.setDuration(750);
        animation.play();
    }

    public void update(){
        for(int i = 0; i < animatecomponent.size();i++){
            /*
            tutorialentity entity = animationlist.elementAt(i);
            int imagelocx = entity.getstartx() + (entity.getendx()-entity.getstartx())*currentframe/framesdurration;
            int imagelocy = entity.getstarty() + (entity.getendy()-entity.getstarty())*currentframe/framesdurration;
            animatecomponent.elementAt(i).setLocation(imagelocx,imagelocy);
            */
            ((animationscreenpanel)animatecomponent.elementAt(i)).setcurrentframe(currentframe);
            ((animationscreenpanel)animatecomponent.elementAt(i)).setlocation();
            
        }
        
        if((currentframe == framesdurration)&&(active)){
            System.out.println("terminate animation");
            System.out.println(trig);
            active = false;
            trig.endanimation(target);
        }
    }
    
    /*
    public void draw(Graphics g,JComponent c){
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < animationlist.size();i++){
            tutorialentity entity = animationlist.elementAt(i);
            
            int imagelocx = entity.getstartx() + (entity.getendx()-entity.getstartx())*currentframe/framesdurration;
            int imagelocy = entity.getstarty() + (entity.getendy()-entity.getstarty())*currentframe/framesdurration;
            g.drawImage(entity.getpicture(),imagelocx,imagelocy,entity.getpicture().getWidth(),entity.getpicture().getHeight(),null);
        }
    }
    */
}
