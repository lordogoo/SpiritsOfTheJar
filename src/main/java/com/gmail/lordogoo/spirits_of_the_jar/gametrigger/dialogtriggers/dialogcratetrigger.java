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
 * Write a description of class dialogcratetrigger here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class dialogcratetrigger extends dialogtrigger
{

    String text;

    public dialogcratetrigger(menucontrol m,String t)
    {
        super(m);
        text = t;
    }
    
    public dialogcratetrigger(menucontrol m,String t,trigger tr)
    {
        super(m);
        text = t;
        nexttrigger = tr;
    }
    
    public void trigger(gamepanel target){
        this.target = target;
        cleanup();
    }
        
    public void interupt(){
    }
    
    public void end(){
    }
    
    public void cleanup(){
        if(cont.getevent()!=null){
            cont.getevent().setmoveaway(true);
        }
        System.out.println("start DIALOG");
        dialogmodel diag = new dialogmodel(target);
        diag.settext(text);
        cont.getgamecontrol().setcurrentdialog(diag);
        gamepanel dialog = cont.getpanel("diagpanel");
        dialog.addtrigger(nexttrigger);
        ((dialogmenupanel)dialog).setnexttrigger(nexttrigger);
        cont.switchpanel("diagpanel");
    }
}
