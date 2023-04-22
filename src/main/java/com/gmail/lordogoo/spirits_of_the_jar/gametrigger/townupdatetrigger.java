package com.gmail.lordogoo.spirits_of_the_jar.gametrigger;
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
public class townupdatetrigger extends trigger
{

    menucontrol cont;
    
    structureinstansiated struct;
    
    public townupdatetrigger(menucontrol m)
    {
        cont = m;
    }

    public void trigger(gamepanel target){
        this.target = target;
    
         struct = ((structureupdateeventmodel)cont.getevent()).getactive();
         cleanup();
    }
    
    public void cleanup(){
        Random rand = new Random();
        
        //if no goal set goal
        if(struct.getgoal() == null){
            if(struct.getmodel().getfaction().numnpc() > 0){
                int totalweight = 0;
                for(int i = 0; i < struct.getmodel().getfaction().numnpc(); i++){
                    totalweight += struct.getmodel().getfaction().getpriority(i);
                }
                
                int randweight = Math.abs(rand.nextInt()%(totalweight));
                int i = 0;
                while(struct.getmodel().getfaction().getpriority(i) <= randweight){
                    i++;
                    randweight -= struct.getmodel().getfaction().getpriority(i);
                }
                
                struct.setgoal(struct.getmodel().getfaction().getnpc(i));
            }
        }
        
        //if goal is achived activate and reset
        if(struct.getgoal() != null){
            if(struct.getgoal().getbody().getproduction() <= struct.getwealth()){
                struct.decreasewealth(struct.getgoal().getbody().getproduction());
                //t.getnpcindex();
                npcinstanciated npc = new npcinstanciated(cont.getgamecontrol().getcurrentworld().getterrain().getnpcindex(),struct.getgoal(),struct,struct.getx(),struct.gety());
                cont.getgamecontrol().getcurrentworld().getterrain().addnpc(npc);
                struct.setgoal(null);
            }
        }
        
        //increase wealth
        struct.increasewealth(1);
    }
    

}
