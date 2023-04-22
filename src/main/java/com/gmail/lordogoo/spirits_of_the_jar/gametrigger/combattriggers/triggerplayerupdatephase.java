package com.gmail.lordogoo.spirits_of_the_jar.gametrigger.combattriggers;
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
 * Write a description of class triggerplayerupdatephase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class triggerplayerupdatephase extends triggeranimations
{

    matchmodel match;
    int pointsupdate;

    public triggerplayerupdatephase(matchmodel m)
    {
        match = m;
    }
    
    public void prewait(){
        //build animation list
        Vector<Vector<Vector<animation>>> animationlist = new Vector<Vector<Vector<animation>>>();
        for(int i = 0; i < match.getgameboardx();i++){
            for(int j = 0; j < match.getgameboardy();j++){
                System.out.println("player update phase tile: "+i+" "+j);
                //go from the top card on the board to the bottom
                gametilemodel tiletemp = match.getgameboard().gettile(i,j);
                
                Vector<Vector<animation>> tilelist = new Vector<Vector<animation>>();
                for(int k =  tiletemp.numcard()-1; k >= 0;k--){
                    
                    instansiatedcard cardtemp = tiletemp.getcard(k);
                    if(tiletemp.getcard(k).getowner() == true){ 
                        Vector<animation> cardanimation = cardtemp.getcard().getupdateanimation(target,this,50,new Color(0,0,0,150),cardtemp,tiletemp);
                        tilelist.add(cardanimation);
                    }
                }
                animationlist.add(tilelist);
            }
        }
        
        //add animation display and run animation
        //target.addanimation(new playerupdateanimation(target,this,30,new Color(0,0,0,150),animationlist));
        target.addanimationdisplay();   
    }
    
    public void postwait(){
        //remove animation display
        target.removeanimationdisplay();
        
        //run update scripts
        for(int i = 0; i < match.getgameboardx();i++){
            for(int j = 0; j < match.getgameboardy();j++){
                gametilemodel tiletemp = match.getgameboard().gettile(i,j);
                //go from the top card on the board to the bottom
                for(int k =  match.getgameboard().gettile(i,j).numcard()-1; k >= 0;k--){
                    instansiatedcard cardtemp = tiletemp.getcard(k);
                    if(match.getgameboard().gettile(i,j).getcard(k).getowner() == true){
                        cardtemp.getcard().onupdate(match,cardtemp,tiletemp,k);
                    }
                }
            }
        }
        
        
        target.enableall();
    }
    

}
