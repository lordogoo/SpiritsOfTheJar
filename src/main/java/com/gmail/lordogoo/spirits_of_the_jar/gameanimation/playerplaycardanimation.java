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
/**
 * Write a description of class playerplaycardanimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class playerplaycardanimation extends animation
{

    Vector<animation> animationlist;

    public playerplaycardanimation(gamepanel g,triggeranimations t,int durration,Color b,Vector<animation> a)
    {
        super(g,t,durration,b,0);
        animationlist = a;
        int nullnumber = 0;
        for(int i = 0;i<animationlist.size();i++){
            if(animationlist.elementAt(i)==null){
                nullnumber++;
            }
        }
        if(nullnumber == animationlist.size()){
            framesdurration = 0;
        }
    }

    public void update(){
        System.out.println("anim update");
        if(currentframe == framesdurration){
            this.end();
        }else{
           for(int i = 0; i < animationlist.size();i++){
               if(animationlist.elementAt(i) != null){
                   animationlist.elementAt(i).update();
               }
           }
           currentframe++;
        }
    }
    
    public void draw(Graphics g,JComponent c){
        for(int i = 0; i < animationlist.size(); i++){
            //System.out.println("======="+animationlist.elementAt(i));
            if(animationlist.elementAt(i) != null){
                animationlist.elementAt(i).draw(g,c);
            }
        }
    }
    
}
