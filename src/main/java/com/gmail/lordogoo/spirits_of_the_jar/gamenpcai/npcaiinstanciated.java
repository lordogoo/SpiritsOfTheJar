package com.gmail.lordogoo.spirits_of_the_jar.gamenpcai;
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
 * Write a description of class npcaiinstanciated here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class npcaiinstanciated
{
    npcai ai;
    
    npcinstanciated npc;
    structureinstansiated home;
    structureinstansiated previous;
    
    boolean scared;
    
    int xgoal;
    int ygoal;
    
    public npcaiinstanciated(npcinstanciated n,npcai a,structureinstansiated h)
    {
        npc = n;
        home = h;
        ai = a;
    }

    public void update(gamecontrol game){
        if(ai != null){
            ai.update(game,this);
        }
    }
    
    public int getxvilosity(){
        if(ai != null){
            return ai.getxvilosity(this);
        }
        return 0;
    }
    
    public int getyvilosity(){
        if(ai != null){
            return ai.getyvilosity(this);
        }
        return 0;
    }

    public void setgoal(int x,int y){
        xgoal = x;
        ygoal = y;
    }
    
    public int getgoalx(){
        return xgoal;
    }
    
    public int getgoaly(){
        return ygoal;
    }
    
    public npcinstanciated getnpc(){
        return npc;
    }
    
    public structureinstansiated getstructure(){
        return home;
    }
    
    public structureinstansiated getpreviousstructure(){
        return previous;
    }
    
    public npcai getmodel(){
        return ai;
    }
}
