package com.gmail.lordogoo.spirits_of_the_jar.gametrigger.dialogtriggers;
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
 * Write a description of class triggerwarptotown here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class triggerwarptotown extends dialogtrigger
{
    
    public triggerwarptotown(menucontrol m)
    {
        super(m);
    }

    public void trigger(gamepanel target){
        this.target = target;

        int x = ((npceventmodel)cont.getevent()).getactivated().getx();
        int y = ((npceventmodel)cont.getevent()).getactivated().gety();
        structureinstansiated nearest = null;
        for(int i = 0; i < cont.getgamecontrol().getcurrentworld().getterrain().getstructurenum();i++){
            if(cont.getgamecontrol().getcurrentworld().getterrain().getstructure(i).getmodel().getfaction() == 
               ((npcinstanciated)((npceventmodel)cont.getevent()).getactivated()).getbody().getmodel().getfaction()){
               int sx = cont.getgamecontrol().getcurrentworld().getterrain().getstructure(i).getx();
               int sy = cont.getgamecontrol().getcurrentworld().getterrain().getstructure(i).gety();
               if(nearest != null){
                   if( (sx-x)*(sx-x)+(sy-y)*(sy-y) < (nearest.getx()-x)*(nearest.getx()-x)+(nearest.gety()-y)*(nearest.gety()-y) ){
                       nearest = cont.getgamecontrol().getcurrentworld().getterrain().getstructure(0);
                   }
               }else{
                   nearest = cont.getgamecontrol().getcurrentworld().getterrain().getstructure(0);
               }
               
            }
        }

        if(nearest == null){
            return;
        }else{
            int xloc = nearest.getx();
            int yloc = nearest.gety();
            if(((npceventmodel)cont.getevent()).getactivated().isplayer()){
                cont.getgamecontrol().getcurrentworld().setoffset(xloc,yloc);
            }
            (((npceventmodel)cont.getevent()).getactivated()).setlocation(xloc,yloc);
        }
    
    }
    
    
}
