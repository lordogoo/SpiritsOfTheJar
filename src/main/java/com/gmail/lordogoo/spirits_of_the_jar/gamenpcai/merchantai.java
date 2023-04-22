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

public class merchantai extends npcai
{

    int radius;

    public merchantai(int r)
    {
        super("merchantai");
        radius = r;
    }
    
    
    public void update(gamecontrol g,npcaiinstanciated current){
        
        
        //System.out.println(current.getnpc().getx()+","+current.getnpc().gety()+" "+current.getgoalx()+","+current.getgoaly()+" "+current.getnpc().gethome().getx()+","+current.getnpc().gethome().gety());
        npcinstanciated closest = null;
        //go through all the npc's
        for(int i = 0; i < g.getcurrentworld().getterrain().numnpc();i++){
            //if is in radious 
            int d = distance(current.getnpc(),g.getcurrentworld().getterrain().getnpc(i));
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
        

        if(closest != null){
            int dxe = current.getnpc().getx() - closest.getx();
            int dye = current.getnpc().gety() - closest.gety();
            current.setgoal(current.getnpc().getx()+(dxe),current.getnpc().gety()+(dye));
            current.getnpc().sethome(null);
        }else{
            
            if(current.getnpc().gethome() == null){
                newhome(g,current);
            }else{
                //if no enemy near go to home
                int dx = current.getnpc().gethome().getx() - current.getnpc().getx();
                int dy = current.getnpc().gethome().gety() - current.getnpc().gety();
                //if near home change home locaton
                if(dx*dx+dy*dy < radius*radius){
                    newhome(g,current);
                }
            }
            //set goal to be home
            current.setgoal(current.getnpc().gethome().getx(),current.getnpc().gethome().gety());
        }
    }
    
    
    public void newhome(gamecontrol g,npcaiinstanciated current){
                Vector<structureinstansiated> structurelist = new Vector<structureinstansiated>();
                for(int i = 0; i < g.getcurrentworld().getterrain().getstructurenum();i++){
                    for(int j = 0; j < current.getnpc().getbodytype().getfaction().numstructure(); j++){
                        if(g.getcurrentworld().getterrain().getstructure(i).getmodel().getname().equals(current.getnpc().getbodytype().getfaction().getstructure(j).getname())){
                            structurelist.add(g.getcurrentworld().getterrain().getstructure(i));
                            break;
                        }
                    }
                }
                
                if(structurelist.size() != 0){
                    int struct = 0;
                    if(structurelist.size() > 1){
                        Random rand = new Random();
                        struct = Math.abs(rand.nextInt()%structurelist.size());
                    }
                    current.getnpc().sethome(structurelist.elementAt(struct));
                }
    }
    
    
}
