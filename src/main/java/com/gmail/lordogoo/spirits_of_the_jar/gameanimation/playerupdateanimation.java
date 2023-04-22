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
import java.util.*;import com.gmail.lordogoo.spirits_of_the_jar.*;
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
import com.gmail.lordogoo.spirits_of_the_jar.gameinterface.*;import com.gmail.lordogoo.spirits_of_the_jar.*;
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
import com.gmail.lordogoo.spirits_of_the_jar.*;
/**
 * Write a description of class platerupdateanimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class playerupdateanimation extends animation
{
    int currenttile;
    int tiledepth;
    Vector<Vector<Vector<animation>>> animationlist;

    public playerupdateanimation(gamepanel g,triggeranimations t,int durration,Color b,Vector<Vector<Vector<animation>>> a)
    {
        super(g,t,durration,b,0);
        animationlist = a;
        tiledepth = -1;
        currenttile = 0;
        
        currentframe = framesdurration;
    }
    
    public void update(){   
        //if there are no animations to process end the algoritm
        if((animationlist.size()==0)||(currenttile == animationlist.size())){
            this.end();
            return;
        }
        
        if(currentframe == framesdurration){
            tiledepth++;
            while((isanimationprocessable()==false)){
                if(currenttile == animationlist.size()){
                    this.end();
                    return;
                }
                //if tile is empty or processed skip it
                if((animationlist.elementAt(currenttile)==null)||(tiledepth == animationlist.elementAt(currenttile).size())){
                    currenttile ++;
                    tiledepth = 0;
                //if the card is unprocessable skip it   
                }else if(isanimationempty(animationlist.elementAt(currenttile).elementAt(tiledepth))){
                    tiledepth++;
                }
            
            }
            currentframe = 0;
        }else{
            currentframe++;
        }

            
    }
    
    public boolean isanimationprocessable(){
        if(animationlist.size() == currenttile){
            return false;
        }
        if(animationlist.elementAt(currenttile).size() == 0){
            return false;
        }
        if(animationlist.elementAt(currenttile).size() == tiledepth){
            return false;
        }
        if(isanimationempty(animationlist.elementAt(currenttile).elementAt(tiledepth)) == true){
            return false;
        }
        return true;
    }
    
    public boolean isanimationempty(Vector<animation> cardanimtemp){
        if(cardanimtemp.size() == 0){
            return true;
        }else{
            int numnull = 0;
            for(int i = 0; i < cardanimtemp.size();i++){
                if(cardanimtemp.elementAt(i) == null){
                    numnull++;
                }
            }
            if(numnull != cardanimtemp.size()){
                return true;
            }
        }
        return false;
    }
    
    public void draw(Graphics g,JComponent c){
        if(isanimationprocessable()){
            Vector<animation> animtemp = animationlist.elementAt(currenttile).elementAt(animationlist.elementAt(currenttile).size());
            for(int i = 0; i < animtemp.size(); i++){
                if(animtemp.elementAt(i) != null){
                    animtemp.elementAt(i).draw(g,c);
                }
            }
        }
    }

}
