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
 * Write a description of class triggerkillbadguy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class triggerkillbadguy extends dialogtrigger
{

    public triggerkillbadguy(menucontrol m,dialogtrigger nextrigger)
    {
        super(m);
    }

    public void trigger(gamepanel target){
        
        ((npcinstanciated)((npceventmodel)cont.getevent()).getactivated()).getbadguy().setdefeated(true);
        badguyinstansiated badguytemp = ((npcinstanciated)((npceventmodel)cont.getevent()).getactivated()).getbadguy();
        
        //go through the shops and remove the roumors
        for(int i = 0; i < cont.getcurrentprofile().getgame().numworld(); i++){
            for(int j = 0; j < cont.getcurrentprofile().getgame().getworld(i).getterrain().getstructurenum();j++){
                for(int k = 0; k < cont.getcurrentprofile().getgame().getworld(i).getterrain().getstructure(j).getshop().numperchace();k++){
                    if(cont.getcurrentprofile().getgame().getworld(i).getterrain().getstructure(j).getshop().getperchace(k) instanceof roumorperchaseinstantiated){
                        if(((roumorperchaseinstantiated)cont.getcurrentprofile().getgame().getworld(i).getterrain().getstructure(j).getshop().getperchace(k)).getroumor().getbadguy().getmodel().getname().equals(badguytemp.getmodel().getname())){
                            cont.getcurrentprofile().getgame().getworld(i).getterrain().getstructure(j).getshop().removeperchace(k);
                            k--;
                        }
                    }
                }
            }
        }
        
        //find out how many bad guys exist
        int numbadguy = 0;
        for(int i = 0; i < cont.getcurrentprofile().getgame().numworld();i++){
            numbadguy += cont.getcurrentprofile().getgame().getworld(i).numbadguy();
        }
        
        //find out how many badguys are dead
        int numbadguycaptured = 0;
        for(int i = 0; i < cont.getcurrentprofile().getgame().numworld();i++){
            for(int j = 0; j < cont.getcurrentprofile().getgame().getworld(i).numbadguy(); j++){
                if(cont.getcurrentprofile().getgame().getworld(i).getbadguy(j).getdefeadted()){
                    numbadguy += 1;
                }
            }
        }
        
        //if all the bad guys are dead run the end of game trigger
        if(numbadguy == numbadguycaptured){
            nexttrigger.trigger(target);
        }
    }
}
