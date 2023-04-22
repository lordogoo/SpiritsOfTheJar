package com.gmail.lordogoo.spirits_of_the_jar.playergame; 
import java.io.Serializable;
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
 * Write a description of class collection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class collectionmodel implements Serializable
{
    Vector<collectionelementmodel>  list;
    
    public collectionmodel()
    {
       list = new Vector<collectionelementmodel>();   
    }
    
    public void addcard(card c){
        int index = containscard(c);
        if(index != -1){
            list.elementAt(index).addcard(c);
        }else{
            collectionelementmodel capsel = new collectionelementmodel(c);
            capsel.addcard(c);
            for(int i = 0; i <= list.size(); i++){
                if(i != list.size() ){
                    if(list.elementAt(i).checkcard().getname().compareTo(c.getname())>0){
                        list.insertElementAt(capsel,i);
                        break;
                    }
                }else{
                    list.add(capsel);
                    break;
                }
            }
        }
        
    }
    
    public card removecard(int i){
        if(list.elementAt(i).numcard() == 1){
            card c = list.elementAt(i).checkcard(); 
            list.remove(i);
            return c;
        }else{
           card c = list.elementAt(i).checkcard();
           list.elementAt(i).removecard();
           return c;
        }
    }
    
    public void removecollection(int i){
        list.remove(i);
    }
    
    public card checkcard(int i){
        return list.elementAt(i).checkcard();
    }
    
    public int checknum(int i){
        return list.elementAt(i).numcard();
    }
    
    
    public int containscard(card c){
        /*System.out.println(c);
        if(c!= null){
            System.out.println(c.getname());
        }*/
        for(int i = 0; i < list.size();i++){
            if(c.getname().compareTo(list.elementAt(i).checkcard().getname())==0){
                return i;
            }
        }
        return -1;
    }
    
    public int getnum(){
        return list.size();
    }
    
    public collectionelementmodel getcollection(int i){
        return list.elementAt(i);
    }
}
