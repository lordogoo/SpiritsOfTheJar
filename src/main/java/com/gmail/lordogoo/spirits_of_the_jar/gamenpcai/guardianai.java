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
public class guardianai extends npcai
{
   
    int radius;

    public guardianai(int r)
    {
        super("gardianai");
        radius = r;
    }
    
    public void update(gamecontrol g,npcaiinstanciated current){
        npcinstanciated closest = null;
        
        if(current.getnpc().gethome() != null){
            //go through all the npc's
            for(int i = 0; i < g.getcurrentworld().getterrain().numnpc();i++){
                //if is in radious of home
                int d = distance(current.getnpc().gethome(),g.getcurrentworld().getterrain().getnpc(i));
                if((d < this.radius*this.radius)&&(current.getnpc() != g.getcurrentworld().getterrain().getnpc(i))){
                    //if an npc is hated
                    if(current.getnpc().getbodytype().getfaction().ishatednpc(g.getcurrentworld().getterrain().getnpc(i).getbodytype())){
                        if(closest == null){
                            closest =  g.getcurrentworld().getterrain().getnpc(i);
                        }else if( distance(current.getnpc(),closest)> distance(current.getnpc(),g.getcurrentworld().getterrain().getnpc(i))){
                            closest =  g.getcurrentworld().getterrain().getnpc(i);
                        }
                    }
                }
            }

            //
            if(closest != null){
                current.setgoal(closest.getx(),closest.gety());
            }else{
                current.setgoal(current.getnpc().gethome().getx(),current.getnpc().gethome().gety());
            }
        }
        //todo what do you do if you have no home?
    }
    
}
