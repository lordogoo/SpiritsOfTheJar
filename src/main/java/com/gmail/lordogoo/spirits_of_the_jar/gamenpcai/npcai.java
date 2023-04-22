package com.gmail.lordogoo.spirits_of_the_jar.gamenpcai;
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
 * Write a description of class npcai here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class npcai
{
    
    String name;
    
    public npcai(String n)
    {
        name = n;
    }
    
    public void update(gamecontrol game,npcaiinstanciated current){
    }
    
    
    public int distance(gameobjectinstanciated cur,npcinstanciated npc){
        int x = cur.getx() - npc.getx();
        int y = cur.gety() - npc.gety();
        return x*x+y*y;
    }
    
    
    public int getxvilosity(npcaiinstanciated current){
            
            int playdx = current.getgoalx() - current.getnpc().getx();
            int playdy = current.getgoaly() - current.getnpc().gety() ;
            int speed = current.getnpc().getmodel().getspeed();
            int npcrad = current.getnpc().getmodel().getradius();
        
            int velocity = 0;
            
             if((playdx <= npcrad)&&(playdx >= -npcrad)){
                
                velocity =  0;
            }else if(playdx > npcrad){
                if((playdy < npcrad)&&(playdy > -npcrad)){
                    velocity =  speed;
                }else{
                    velocity = (int)(speed*Math.cos(Math.PI/4));
                }
            }else if(playdx < -npcrad){
                if((playdy < npcrad)&&(playdy > -npcrad)){
                    velocity =  -speed;
                }else{
                    velocity = (int)(-speed*Math.cos(Math.PI/4));
                }
            }
            return velocity;
    }
    
    public int getyvilosity(npcaiinstanciated current){
        
            int playdx = current.getgoalx() - current.getnpc().getx();
            int playdy = current.getgoaly() - current.getnpc().gety();
            int speed = current.getnpc().getmodel().getspeed();
            int npcrad = current.getnpc().getmodel().getradius();
            
            if((playdy < npcrad)&&(playdy > -npcrad)){
                return 0;
            }else if(playdy > npcrad){
                if((playdx < npcrad)&&(playdx > -npcrad)){
                    return speed;
                }else{
                    return (int)(speed*Math.cos(Math.PI/4));
                }
            }else if(playdy < -npcrad){
                if((playdx < npcrad)&&(playdx > -npcrad)){
                    return -speed;
                }else{
                    return (int)(-speed*Math.cos(Math.PI/4));
                }
            }
            return 0;
    }
    
}
