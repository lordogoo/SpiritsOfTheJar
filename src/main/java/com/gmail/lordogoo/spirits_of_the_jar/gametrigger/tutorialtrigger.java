package com.gmail.lordogoo.spirits_of_the_jar.gametrigger;

import com.gmail.lordogoo.spirits_of_the_jar.*;
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;
import com.gmail.lordogoo.spirits_of_the_jar.cardgame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.*;
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

public class tutorialtrigger
{
    tutorialcontrol tcont;
    gamepanel namepanel;
    boolean active;
    int sequence;
    
    trigger trig;
    
    public tutorialtrigger(trigger t,tutorialcontrol tc,gamepanel np,int sq)
    {
        active = true;
        trig = t;
        tcont = tc;
        namepanel = np;
        sequence = sq;
        t.addtutorialtrigger(this);
    }

    public void loadnextanimation(){
        System.out.println("load next trigger");
        if(tcont != null){
            tutorialtrigger tutorialtemp = tcont.gettrigger(namepanel);
            
            if(tutorialtemp != null){
                trigger temp = tutorialtemp.gettrigger();
                namepanel.addtrigger(temp);
            }
        }
    }
    
    public void suspendnextanimation(){
        System.out.println("suspend next trigger");
        if(tcont != null){
            tutorialtrigger tutorialtemp = tcont.gettrigger(namepanel);
            
            if(tutorialtemp != null){
                trigger temp = tutorialtemp.gettrigger();
                namepanel.suspendtrigger(temp);
            }
        }
    }
    
    public trigger gettrigger(){
        return trig;
    }
       
    public boolean isactive(){
        return active;
    }
    
    public void setactive(boolean a){
        active = a;
    }
    
    public int getsequence(){
        return sequence;
    }
    
    public String getpanelname(){
        return namepanel.getname();
    }
    
    
    
    
}
