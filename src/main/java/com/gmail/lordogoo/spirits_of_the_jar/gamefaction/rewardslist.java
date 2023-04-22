package com.gmail.lordogoo.spirits_of_the_jar.gamefaction;
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
 * Write a description of class rewardslist here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class rewardslist
{
    
    Vector<rewarditem> rewards;
    int totalweight;
    public rewardslist()
    {
        rewards = new Vector<rewarditem>();
        totalweight = 0;
    }

    public void addreward(card c,int w){
        rewards.add(new rewarditem(c,w));
        totalweight+=w;
    }
    
    public void addreward(rewarditem r){
        rewards.add(r);
        totalweight+= r.getweight();
    }
    
    public Vector<card> getcards(int num){
        Random r = new Random();
        Vector<card> list = new Vector<card>();
        for(int i = 0; i < num; i++){
            
            int weighttemp = Math.abs(r.nextInt()%totalweight);
            int j = 0;
            while(weighttemp > rewards.elementAt(j).getweight()){
                weighttemp -= rewards.elementAt(j).getweight();
                j++;
            }
            
            list.add(rewards.elementAt(j).getcard());
        }
        
        return list;
    }

}
