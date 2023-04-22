package com.gmail.lordogoo.spirits_of_the_jar.gametrigger;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
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

public class trigger
{
    
    volatile boolean active;
    public tutorialtrigger triggercontainer;
    public gamepanel target;
    boolean blocked;

    public trigger()
    {
        active = true;
        blocked = false;
    }
    
    public void trigger(gamepanel target){
        this.target = target;
        prewait();
        
        //Timer animationthread = new Timer("animator");
        //runanimation t = new runanimation(this);
        //animationthread.schedule(t, 0, 40);
    }
    
    public boolean isactive(){
        while(blocked){
        }
        return active; 
    }
    
    public void addtutorialtrigger(tutorialtrigger t){
        triggercontainer = t;
    }
    
    
    
    
    
    public void triggerend(){
    
    }
    
    public void prewait(){
    }
    
    public void postwait(){
    }
    
    public boolean suspentioncondition(MouseEvent e){
        return true;
    }
    
    
    public void runnexttrigger(){
        if(target.hasnexttrigger()){
            target.runtriggers(target);
        }else{
            target.enableall();
        }
    }
    
    public void setnexttrigger(npctrigger t){
    }
    
    public class runanimation extends TimerTask {
        
        trigger trig;
        
        public runanimation(trigger t){
            super();
            
            trig = t;
        }
        

        
        public void run() {
            try{
                if(!target.getmenu().isrunning()){
                    this.cancel();
                }
                if(target.getanimation() != null){
                    target.getanimation().update();  
                    target.repaint();
                }else{
                    trig.postwait();
                    target.repaint();
                    target.runtriggers(target);
                    this.cancel();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
