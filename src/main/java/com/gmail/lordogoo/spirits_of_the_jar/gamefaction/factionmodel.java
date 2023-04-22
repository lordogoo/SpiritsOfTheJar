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
public class factionmodel
{

    rewardslist rewards;

    Vector<Integer> priority;
    Vector<npcmodel> members;
    Vector<npcmodel> startmember;
    Vector<structuremodel> base;
    Vector<factionmodel> hate;
    
    public factionmodel()
    {
        rewards = new rewardslist();
        priority = new Vector<Integer>();
        members = new Vector<npcmodel>();
        startmember = new  Vector<npcmodel>();
        base = new Vector<structuremodel>();
        hate = new Vector<factionmodel>();
    }

    
    public rewardslist getrewardslist(){
        return rewards;
    }
    
    public void addnpc(npcmodel b,int p){
        priority.add(Integer.valueOf(p));
        members.add(b);
        b.getbody().addfaction(this);
    }
    
    public npcmodel getnpc(int i){
        return members.elementAt(i);
    }
    
    public int numnpc(){
        return members.size();
    }
    
    public int getpriority(int i){
        return priority.elementAt(i).intValue();
    }
    
    public void addstartnpc(npcmodel n){
        startmember.add(n);
    }
    
    public npcmodel getstartnpc(int i){
        return startmember.elementAt(i);
    }
    
    public int numstartnpc(){
        return startmember.size();
    }
    
    public void addstructure(structuremodel s){
        base.add(s);
        s.addfaction(this);
    }
    
    public structuremodel getstructure(int i){
        return base.elementAt(i);
    }
    
    public int numstructure(){
        return base.size();
    }
    
    public void addhated(factionmodel f){
        hate.add(f);
    }
    
    public factionmodel gethated(int i){
        return hate.elementAt(i);
    }
    
    public int numhated(){
        return hate.size();
    }
    
    public boolean ishatednpc(bodymodel b){
        for(int i = 0; i < hate.size();i++){
            for(int j = 0; j < hate.elementAt(i).numnpc();j++){
                if(hate.elementAt(i).getnpc(j).getbody().getname().equals(b.getname())){
                    return true;
                }
            }
        }
        return false;
    }
}
